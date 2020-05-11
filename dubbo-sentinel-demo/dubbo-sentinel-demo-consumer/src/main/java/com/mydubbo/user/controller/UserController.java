package com.mydubbo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mydubbo.result.Result;
import com.mydubbo.user.client.UserClient;

/** * 用户维护控制器 
 * 使用dubbo服务

http://127.0.0.1:8086/user/getInfo?name=xiaozhang
 * * */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
//    @Reference(version = "1.0.0")
//    @Reference
//    private IUserService userService;

    @Autowired
    private UserClient userClient;

	/** * 分页获取资源数据 * * @return */
	@RequestMapping("/getInfo")
	@ResponseBody
	public Result<String> getInfo(String name) {
		System.out.println("-----------UserController");
		int pageNo = 1;
		int pageSize = 10; 
//		if (params.get("page") != null) {
//			pageNo = Integer.parseInt((String) params.get("page"));
//		}
//		if (params.get("rows") != null) {
//			pageSize = Integer.parseInt((String) params.get("rows"));
//		}
		Result<String> result = userClient.getInfo(name);
		
		return result;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public Result<String> get(String name) {
		System.out.println("-----------get");

//		Result<String> result = userClient.getInfo(name);
		
		return new Result<>("测试！");
	}
}