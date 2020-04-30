package com.mydubbo.user.dao;

import java.util.List;
import java.util.Map;

import com.coba.core.daoframework.support.MeDaoRepository;
import com.mydubbo.user.entity.User;

/** * 用户维护DAO * @author lintg */
@MeDaoRepository
public interface TestUserDao {
	/** * 删除 * @param id * @return */
	int deleteByPrimaryKey(String id);

	/** * 增加 * @param record * @return */
	int insert(User record);

	/** * 获取信息 * @param id * @return */
	User selectByPrimaryKey(String id);

	/** * 获取列表(分页) * @param id * @return */
	List<User> selectList(Map<String, Object> params);

	/** * 更新信息 * @param id * @return */
	int updateByPrimaryKey(User record);
}