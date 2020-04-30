package com.mydubbo.common.dao;

import com.coba.core.daoframework.support.MeDaoRepository;
import com.coba.core.monitor.accesslog.domain.MeAccessLog;

/**
 * 服务访问日志 DAO
 * 
 * @author 柳磊
 * 
 */
@MeDaoRepository
public interface MeAccessLogDao {
	void insert(MeAccessLog log);
}
