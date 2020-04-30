package com.mydubbo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coba.core.monitor.sqllog.appender.SqlMonitorDBAppender;
import com.coba.core.monitor.sqllog.appender.ThreadPoolAsyncAppender;
import com.coba.core.monitor.sqllog.service.ISqlExecuteLogService;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.ext.spring.ApplicationContextHolder;
/**
 * 
 * @description：SQL监控 配置
 */
@Configuration
@ConditionalOnProperty(value = "sql-monitor-param.enable", havingValue = "true")
@ConfigurationProperties(prefix = "sql-monitor-param")
public class SqlMonitorConfig {
	private static final  Logger log = LoggerFactory.getLogger(SqlMonitorConfig.class);
	@Value("${sql-monitor-param.queueSize}")
	private int queueSize;
	@Value("${sql-monitor-param.threadSize}")
	private int threadSize;
	@Value("${sql-monitor-param.discardingThreshold}")
	private int discardingThreshold;
	
	@Autowired
	private ISqlExecuteLogService logService;
	
	/**
	 *  logback 与 spring整合holder类,主要用于让 Appender 处于Spring容器内 
	 * @return
	 */
	@Bean
	public ApplicationContextHolder applicationContextHolder() {
		return new ApplicationContextHolder();
	}

	/**
	 * SQL监控的 DB Appender
	 * @return
	 */
	@Bean(initMethod = "start",destroyMethod = "stop")
    public SqlMonitorDBAppender sqlMonitorDB(){
		SqlMonitorDBAppender sqlMonitorDBAppender =  new SqlMonitorDBAppender();
		sqlMonitorDBAppender.setContext((Context)LoggerFactory.getILoggerFactory());
		sqlMonitorDBAppender.setLogService(logService);
		return sqlMonitorDBAppender;
    }
	
	/**
	 * SQL监控的 Async Appender,本身不处理记录功能,将请求分发给 sqlMoniotorDB 
	 * @param sqlMonitorDB
	 * @return
	 */
	@Bean(initMethod = "start",destroyMethod = "stop")
	public ThreadPoolAsyncAppender sqlMonitor(SqlMonitorDBAppender sqlMonitorDB){
		ThreadPoolAsyncAppender threadPoolAsyncAppender =  new ThreadPoolAsyncAppender();
		threadPoolAsyncAppender.setQueueSize(queueSize);
		threadPoolAsyncAppender.setThreadSize(threadSize);
		threadPoolAsyncAppender.setDiscardingThreshold(discardingThreshold);
		//设置具体处理请求的 appender- ref 
		threadPoolAsyncAppender.setAppenderRef((Appender)sqlMonitorDB);
		return threadPoolAsyncAppender;
	}
	
}
