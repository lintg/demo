package com.mydubbo.myuser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydubbo.myuser.entity.MpUser;
import com.mydubbo.myuser.entity.UserDto;
import com.mydubbo.myuser.mapper.MpUserMapper;
import com.mydubbo.myuser.service.MpUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuan
 * @since 2019-05-11
 */
@Service
public class MpUserServiceImpl extends ServiceImpl<MpUserMapper, MpUser> implements MpUserService {
	
	 @Autowired
	 private MpUserMapper userMapper;
	 
	public List<MpUser> queryUserForPage(Integer current, Integer size){
        IPage<MpUser> userPage = new Page<>(current, size);//参数一是当前页，参数二是每页个数
        QueryWrapper<MpUser> wrapper = new QueryWrapper<MpUser>();
        //gt >  , lt <
//        wrapper.like("address", "州").gt("id", 8);
        wrapper.gt("id", 8);
        
        
        userPage = userMapper.selectPage(userPage, wrapper);
        return userPage.getRecords();
    }
	
	public List<MpUser> selectByMyPage(Integer current, Integer size){
        IPage<MpUser> userPage = new Page<>(current, size);//参数一是当前页，参数二是每页个数
        QueryWrapper<MpUser> wrapper = new QueryWrapper<MpUser>();
        wrapper.gt("id", 8);
        
        userPage = userMapper.selectMyPage(userPage, wrapper);
        
        System.out.println("总页数"+userPage.getPages());
        System.out.println("总记录数"+userPage.getTotal());

        return userPage.getRecords();
    }
	
	public List<MpUser> selectSqlPage(Integer current, Integer size){
		IPage<MpUser> userPage = new Page<>(current, size);//参数一是当前页，参数二是每页个数
		Map<String,Object> params = new HashMap<String,Object>();
//		UserDto userDto = new UserDto();
		//userDto.setId(8l);
//		userDto.setUsername("深圳");
		params.put("address", "深圳");
		params.put("id",5);
		
		userPage = userMapper.selectSqlPage(userPage, params);
		
		System.out.println("总页数"+userPage.getPages());
		System.out.println("总记录数"+userPage.getTotal());
		
		return userPage.getRecords();
	}
}
