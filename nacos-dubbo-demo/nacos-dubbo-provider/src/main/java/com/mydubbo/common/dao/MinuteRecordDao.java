/**
 * 软件著作权：XXX开发中心
 * 
 * 系统名称：寄往未来项目
 * 项目名称：服务监控治理平台
 */
package com.mydubbo.common.dao;

import java.util.List;

import com.coba.core.daoframework.support.MeDaoRepository;
import com.coba.core.monitor.statistic.domain.MonitorRecord;

/**
 * 单条数据访问借口
 * 
 * @author 周一鸣
 * @version
 */
@MeDaoRepository
public interface MinuteRecordDao {

	/**
	 * 插入记录
	 * 
	 * @param record
	 */
	void insertRecord(MonitorRecord record);
	
	/**
	 * 批量插入
	 */
	void insertRecords(List<MonitorRecord> records);
}
