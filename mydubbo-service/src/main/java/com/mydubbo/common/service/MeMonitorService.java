/**
 * 软件著作权：XXX开发中心
 * 
 * 系统名称：寄往未来项目
 * 
 */
package com.mydubbo.common.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.monitor.MonitorService;
import com.alibaba.fastjson.JSON;
import com.coba.core.monitor.statistic.domain.MonitorRecord;
import com.mydubbo.common.dao.MinuteRecordDao;

/**
 * 服务监控统计实现
 * 
 * @author 周一鸣
 * @version
 */

//先不用这个服务 
//@Component
public class MeMonitorService implements MonitorService {

	@Autowired
	MinuteRecordDao mDao;

	private static final Logger logger = LoggerFactory.getLogger(MeMonitorService.class);

	private volatile boolean running = true;

	private static MeMonitorService INSTANCE = null;

	private final BlockingQueue<URL> queue;

	private final Thread writeThread;

	private static final String POISON_PROTOCOL = "poison";

	public static String PAN_PROVIDER = "PV";
	public static String PAN_CONSUMER = "CS";

	private static final int METHODINF_VARCHAR_LENGTH = 63;

	private static final int INTERFACE_NAME_VARCHAR_LENGTH = 128;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	public MeMonitorService() {
		queue = new LinkedBlockingQueue<URL>(Integer.parseInt(ConfigUtils.getProperty("dubbo.monitor.queue", "100000")));
		writeThread = new Thread(new Runnable() {
			public void run() {
				while (running) {
					try {
						write(); // 输出到数据库
					} catch (Throwable t) { // 防御性容错
						logger.error("Unexpected error occur at write stat log, cause: " + t.getMessage(), t);
						try {
							Thread.sleep(5000); // 失败延迟
						} catch (Throwable t2) {
						}
					}
				}
			}
		});
		writeThread.setDaemon(true);
		writeThread.setName("MecloudMonitorAsyncWriteLogThread");
		writeThread.start();
		INSTANCE = this;
	}

	public static MeMonitorService getInstance() {
		return INSTANCE;
	}

	@Override
	public void collect(URL statistics) {
		queue.offer(statistics);
		if (logger.isInfoEnabled()) {
			logger.info("collect statistics: " + statistics);
		}
	}

	@Override
	public List<URL> lookup(URL query) {
		logger.debug("lookup:" + query.toFullString());
		return null;
	}

	private void write() throws Exception {
		URL countUrl = queue.take();
		// 验证是否存在 Constants.SENT_KEY 
		if (!countUrl.hasParameter(Constants.SENT_KEY)) {
			return;
		}
		List<String> fullUrls = JSON.parseArray(countUrl.getParameter(Constants.SENT_KEY), String.class);
		if (fullUrls == null || fullUrls.isEmpty()) {
			return;
		}
		List<MonitorRecord> records = new LinkedList<MonitorRecord>();
		for (int i = 0; i < fullUrls.size(); i++) {
			URL statistics = URL.valueOf(fullUrls.get(i));
			// 不记录空数据
			if (statistics.getParameter(MonitorService.SUCCESS, 0) == 0 && statistics.getParameter(MonitorService.FAILURE, 0) == 0) {
				continue;
			}
			logger.debug("write:" + statistics.toFullString());
			if (POISON_PROTOCOL.equals(statistics.getProtocol())) {
				return;
			}
			String timestamp = statistics.getParameter(Constants.TIMESTAMP_KEY);
			Date now = null;
			if (timestamp == null || timestamp.length() == 0) {
				now = new Date();
			} else if (timestamp.length() == "yyyyMMddHHmmss".length()) {
				now = simpleDateFormat.parse(timestamp);
			} else {
				now = new Date(Long.parseLong(timestamp));
			}
			String type = null;
			String provider = null;
			String consumer = null;
			// url 中存在 PROVIDER key 表示是 consumer端发送来的数据
			if (statistics.hasParameter(PROVIDER)) {
				type = PAN_CONSUMER;
				consumer = statistics.getHost();
				provider = statistics.getParameter(PROVIDER);
				int j = provider.indexOf(':');
				if (j > 0) {
					provider = provider.substring(0, j);
				}
			// else 是 provider端发送的数据
			} else {
				type = PAN_PROVIDER;
				consumer = statistics.getParameter(CONSUMER);
				int j = consumer.indexOf(':');
				if (j > 0) {
					consumer = consumer.substring(0, j);
				}
				provider = statistics.getHost();
			}
			String fullString = statistics.toFullString();
			String method = fullString.substring((fullString.indexOf(statistics.getServiceInterface()) + statistics.getServiceInterface().length() + 1), fullString.indexOf("?"));
			MonitorRecord recordDomain = new MonitorRecord();
			recordDomain.setTime(now);
			recordDomain.setConsumerIP(consumer);
			recordDomain.setProviderIP(provider);
			recordDomain.setSide(type);
			recordDomain.setMethodName(subString(method, METHODINF_VARCHAR_LENGTH));
			recordDomain.setTimestamp(timestamp);
			recordDomain.setInterfaceName(subString(statistics.getServiceInterface(), INTERFACE_NAME_VARCHAR_LENGTH));
			recordDomain.setApplicationName(statistics.getParameter("application"));
			recordDomain.setSuccess(statistics.getParameter(MonitorService.SUCCESS, 1));
			recordDomain.setFailure(statistics.getParameter(MonitorService.FAILURE, 1));
			recordDomain.setElapsed(statistics.getParameter(MonitorService.ELAPSED, 1));
			recordDomain.setConcurrent(statistics.getParameter(MonitorService.CONCURRENT, 1));
			recordDomain.setMaxElapsed(statistics.getParameter(MonitorService.MAX_ELAPSED, 1));
			recordDomain.setMaxConcurrent(statistics.getParameter(MonitorService.MAX_CONCURRENT, 1));
			records.add(recordDomain);
		}
		insertRecord(records);
		records.clear();
	}

	public void close() {
		try {
			running = false;
			queue.offer(new URL(POISON_PROTOCOL, NetUtils.LOCALHOST, 0));
		} catch (Throwable t) {
			logger.warn(t.getMessage(), t);
		}
	}

	/**
	 * 插入记录
	 * 
	 * @param data
	 */
	public void insertRecord(MonitorRecord record) {
		mDao.insertRecord(record);
	}

	/**
	 * 插入记录
	 * 
	 * @param data
	 */
	public void insertRecord(List<MonitorRecord> records) {
		mDao.insertRecords(records);
	}

	private String subString(String src, int limit) {
		if (src != null) {
			byte[] strByte = src.getBytes();
			// 当 src byte length 超过 limit 后需要截取
			if (strByte.length > limit) {
				byte[] desc = new byte[limit];
				System.arraycopy(strByte, 0, desc, 0, desc.length);
				return new String(desc);
			}
		}
		return src;
	}
}
