package com.mydubbo.common.dao;

import java.util.List;

import com.coba.core.daoframework.support.MeDaoRepository;
import com.coba.core.monitor.sqllog.domain.SqlMonitorLog;

@MeDaoRepository
public abstract interface SqlMonitorLogDao
{
  public abstract void insertRecord(SqlMonitorLog paramSqlMonitorLog);

  public abstract void insertRecords(List<SqlMonitorLog> paramList);
}