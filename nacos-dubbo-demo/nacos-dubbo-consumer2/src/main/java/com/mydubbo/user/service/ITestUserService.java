package com.mydubbo.user.service;

import java.util.Map;

import com.coba.core.exception.BusinessException;
import com.coba.core.paginator.vo.Page;
import com.mydubbo.user.entity.User;

/** * 用户维护服务 * *@author lintg * */
public interface ITestUserService {
	/** * 增加 * * @param User * @return * @throws BusinessException */
	public int addInfo(User user) throws BusinessException;

	/** * 更新 * * @param User * @return * @throws BusinessException */
	public int updateInfo(User user) throws BusinessException;

	/** * 删除 * * @param User * @return * @throws BusinessException */
	public int deleteInfo(String recNo) throws BusinessException;

	/** * 获取信息 * @param id * @return */
	public User selectByPrimaryKey(String id);

	/**
	 * * 获取列表 * * @param pageNo * @param pageSize * @return * @throws
	 * BusinessException
	 */
	public Page<User> getList(Map<String, Object> params, int pageNo,
			int pageSize) throws BusinessException;
}