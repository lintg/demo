package com.mydubbo.myuser.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mydubbo.myuser.entity.MpUser;
import com.mydubbo.myuser.service.MpUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuan
 * @since 2019-05-11
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/mp-user")
public class MpUserController {

    @Autowired
    private MpUserService mpUserService;

    /**
     * 添加一个新用户
     *
     * @author David Hong
     *
     * @return java.lang.Object
     */
    @ApiOperation("添加用户的接口")
    @GetMapping("/add")
    public Object post() {
        MpUser mpUser = new MpUser();
        mpUser.setUsername("yuan");
        mpUser.setAddress("广东深圳");
        mpUser.setOpenid("openid");
        mpUserService.save(mpUser);
        return "add";
    }

    /**
     * 通过id获取用户
     *
     * @author David Hong
     *
     * @param id
     * @return java.lang.Object
     */
    @ApiOperation("根据id查询用户的接口")
    @GetMapping("/{id}")
    public Object get(@PathVariable Long id) {
        return mpUserService.getById(id);
    }
    
    @ApiOperation("根据id更新用户的接口")
    @GetMapping("/update/{id}")
    public Object update(@PathVariable Long id) {
    	MpUser mpUser = new MpUser();
    	mpUser.setId(id);
        mpUser.setUsername("update");
        mpUser.setAddress("福建");
        mpUser.setOpenid("openid");
    	return mpUserService.updateById(mpUser);
    }

    /**
     * 通过id删除用户
     *
     * @author David Hong
     *
     * @param id
     * @return java.lang.Object
     */
    @ApiOperation("根据id删除用户的接口")
    @GetMapping("/del/{id}")
    public Object del(@PathVariable Long id) {
        mpUserService.removeById(id);
        return "del";
    }
    
    //http://127.0.0.1:8087/demo-druid/mp-user/queryUser?current=3&size=5 
    @ApiOperation("分页显示用户的接口")
    @GetMapping("queryUser")
    public Object queryList(Integer current, Integer size) {
        return mpUserService.queryUserForPage(current, size);
    }
    
    @ApiOperation("分页显示用户的接口2")
    @GetMapping("selectByMyPage")
    public Object selectByMyPage(Integer current, Integer size) {
    	return mpUserService.selectByMyPage(current, size);
    }
    
    @ApiOperation("分页显示用户的接口3")
    @GetMapping("selectSqlPage")
    public Object selectSqlPage(Integer current, Integer size) {
    	return mpUserService.selectSqlPage(current, size);
    }

}
