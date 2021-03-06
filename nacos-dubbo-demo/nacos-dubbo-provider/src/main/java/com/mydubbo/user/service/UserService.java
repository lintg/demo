package com.mydubbo.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.coba.core.cacheframework.cache.ICacheClient;
import com.coba.core.exception.BusinessException;
import com.coba.core.paginator.vo.Page;
import com.mydubbo.user.dao.UserDao;
import com.mydubbo.user.entity.User;

/** * 用户维护服务 * * @author lintg * */
//@Transactional
//@Component
@Service
public class UserService implements IUserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private ICacheClient cacheClient;
//	@Autowired
//	private IErrorLogsService errLogService;

	/** * 增加 */
	@Override
	public int addInfo(User user) throws BusinessException {
		try {
//			user.setId(UUIDUtils.generate());
			user.setId(RandomUtils.nextInt(0, 100000000));
			System.out.println("测试添加用户。。。。。。。。。。。。。。。。。。。。。。。。。");
			return userDao.insert(user);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			List<Object> list = new ArrayList<Object>();
			list.add(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}

	/** * 更新 */
	@Override
	public int updateInfo(User user) throws BusinessException {
		try {
			return userDao.updateByPrimaryKey(user);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			List<Object> list = new ArrayList<Object>();
			list.add(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}

	/** * 删除 */
	@Override
	public int deleteInfo(String recNo) throws BusinessException {
		try {
			return userDao.deleteByPrimaryKey(recNo);
		} catch (Exception e) {
			List<Object> list = new ArrayList<Object>();
			list.add(e.getMessage());
			throw new BusinessException(e.getMessage());
		}
	}

	/** * 获取记录 */
	@Override
	public User selectByPrimaryKey(String id) {
		return userDao.selectByPrimaryKey(id);
	}

	/** * 获取列表 */
	@Override
//	@SentinelResource(value = "getList")
	public Page<User> getList(Map<String, Object> params, int pageNo,
			int pageSize) throws BusinessException {
		System.out.println("------------------调用dubbo服务");
		cacheClient.put("group", "test", "缓存测试");
		Page<User> page = new Page<User>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		params.put("pageObject", page);
		page.setResult(userDao.selectList(params));
		return page;
	}
}