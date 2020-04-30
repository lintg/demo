package com.mydubbo.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coba.core.monitor.accesslog.domain.MeAccessLog;
import com.coba.core.monitor.accesslog.service.IAccessLogService;
import com.coba.core.utils.UUIDUtils;
import com.mydubbo.common.dao.MeAccessLogDao;

/**
 * 记录访问日志的服务实现类
 * @author 柳磊
 *
 */
@Component
public class AccessLogService implements IAccessLogService {

	@Autowired
	private MeAccessLogDao accessLogDao;

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void log(MeAccessLog log) {
		log.setId(UUIDUtils.getUniqueLong());
		accessLogDao.insert(log);
	}

}
