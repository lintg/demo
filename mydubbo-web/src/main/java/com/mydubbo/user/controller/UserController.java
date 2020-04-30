package com.mydubbo.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coba.core.domain.JsonResponse;
import com.coba.core.exception.BusinessException;
import com.coba.core.paginator.vo.Page;
import com.mydubbo.user.entity.User;
import com.mydubbo.user.service.IUserService;

/** * 用户维护控制器 

访问http://127.0.0.1:8085/mydubbo-web/qx/user/page
 * @author lintg * */
@Controller
@RequestMapping(value = "/qx/user")
public class UserController {
	@Autowired
	private IUserService userService;

	/** * 跳转到jsp资源列表页面 * * @return */
	@RequestMapping("/page")
	public String listPage(Model model) {
		return "/user/user";
	}

	/** * 分页获取资源数据 * * @return */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> listData(Model model,
			@RequestParam Map<String, Object> params) {
		int pageNo = 1;
		int pageSize = 10;
		if (params.get("page") != null) {
			pageNo = Integer.parseInt((String) params.get("page"));
		}
		if (params.get("rows") != null) {
			pageSize = Integer.parseInt((String) params.get("rows"));
		}
		Page<User> userList = userService.getList(params, pageNo, pageSize);
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("total", userList.getTotalCount());
		maps.put("rows", userList.getResult());
		
		return maps;
	}

	/** * 新增 * * @return */
	@RequestMapping("/add")
	@ResponseBody
	public JsonResponse addInfo(User user) {
		JsonResponse res = new JsonResponse();
		res.setStatus("SUCCESS");
		try {
			//user.setId(100001);
			userService.addInfo(user);
		} catch (BusinessException ex) {
			res.setStatus("FAIL");
			res.setResult(ex.getMessage());
		}
		return res;
	}

	/** * 更新 * * @return */
	@RequestMapping("/update")
	@ResponseBody
	public JsonResponse updateInfo(User user) {
		JsonResponse res = new JsonResponse();
		res.setStatus("SUCCESS");
		try {
			userService.updateInfo(user);
		} catch (BusinessException ex) {
			res.setStatus("FAIL");
			res.setResult(ex.getMessage());
		}
		return res;
	}

	/** * 删除信息 * @param resource * @return */
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResponse deleteInfo(String id) {
		JsonResponse response = new JsonResponse();
		response.setStatus("SUCCESS");
		try {
			userService.deleteInfo(id);
		} catch (BusinessException ex) {
			response.setStatus("FAIL");
			if (ex.getMessage() != null) {
				response.setResult(ex.getMessage());
			} else {
				throw ex;
			}
		}
		return response;
	}
}