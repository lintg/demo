package com.mydubbo.myuser.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mydubbo.myuser.entity.MpUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuan
 * @since 2019-05-11 
 */
public interface MpUserService extends IService<MpUser> {
	
	public List<MpUser> queryUserForPage(Integer current, Integer size);
	
	public List<MpUser> selectByMyPage(Integer current, Integer size);
	
	public List<MpUser> selectSqlPage(Integer current, Integer size);
	
}
