package com.mydubbo.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coba.core.paginator.vo.Page;
import com.mydubbo.user.entity.User;
import com.mydubbo.user.service.IUserService;

/** * 用户维护控制器 
 * 使用dubbo服务

访问  http://127.0.0.1:8086/user/list
 * @author lintg * */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
//    @Reference(version = "1.0.0")
    @Reference
    private IUserService userService;

	/** * 跳转到jsp资源列表页面 * * @return */
//	@RequestMapping("/page")
//	public String listPage(Model model) {
//		return "/user/user";
//	}

	/** * 分页获取资源数据 * * @return */
	@RequestMapping("/list")
	@ResponseBody
//	public Map<String, Object> listData(Model model,
//			@RequestParam Map<String, Object> params) {
	public Map<String, Object> listData() {
		System.out.println("-----------dubbo服务");
		int pageNo = 1;
		int pageSize = 10;
//		if (params.get("page") != null) {
//			pageNo = Integer.parseInt((String) params.get("page"));
//		}
//		if (params.get("rows") != null) {
//			pageSize = Integer.parseInt((String) params.get("rows"));
//		}
		Map<String, Object> params = new HashMap<String, Object>();
		Page<User> userList = userService.getList(params, pageNo, pageSize);
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("total", userList.getTotalCount());
		maps.put("rows", userList.getResult());
		
		return maps;
	}

}