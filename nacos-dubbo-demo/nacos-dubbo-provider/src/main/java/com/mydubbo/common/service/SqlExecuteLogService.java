/*
 * XXX有限公司 版权所有
 * Copyright 2015 licensed to MeGoInfo Co, Ltd;
 * You may not use this file except in compliance with the 'License' from MeGoInfo company.
 */
package com.mydubbo.common.service;

import java.util.List;
import java.util.Map;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.coba.core.monitor.sqllog.domain.SqlMonitorLog;
import com.coba.core.monitor.sqllog.service.ISqlExecuteLogService;
import com.mydubbo.common.dao.SqlMonitorLogDao;


@Service(interfaceClass = ISqlExecuteLogService.class)
public class SqlExecuteLogService implements ISqlExecuteLogService {
	@Autowired
	SqlMonitorLogDao logDao;

	public void insertRecord(SqlMonitorLog record) {
		this.logDao.insertRecord(record);
	}

	public void insertRecords(List<SqlMonitorLog> records) {
		this.logDao.insertRecords(records);
	}

	@Override
	public void deleteLog(Map param) {
		// TODO Auto-generated method stub
		
	}

}
