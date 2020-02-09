package com.res.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.res.entity.Users;
import com.res.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	// 使用@Autowired注解注入UserServiceImpl实例
	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping("/getValidUser")
	public List<Users> getValidUser() {
		Users u = new Users();
		u.setId(0);
		u.setLoginName("--请选择--");
		List<Users> uList = userService.getValidUser();
		uList.add(0, u);
		return uList;
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list(int page, int rows, Users u) {
		// 创建Map类型对象result,用于向前端页面发送数据
		Map<String, Object> result = new HashMap<String, Object>(2);
		// 根据登录名参数模糊查询所有客户记录数
		int totalCount = userService.getTotalCount(u);
		// 查询指定页显示的用户列表
		List<Users> uList = userService.getUsersByConditionForPager(u, page,
				rows);
		// 向Map类型的对象result中放入键值对，键为“total”,值为totalCount
		result.put("total", totalCount);
		// 向对象result中放入键值对，键为“rows”,值为uList
		result.put("rows", uList);
		// 通过@ResponseBody,发送到前端页面的result自动转成JSON格式
		return result;
	}

	// 启用或禁用用户
	@RequestMapping(value = "/setIsEnableUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String setIsEnableUser(@RequestParam(value = "uids") String uids,
			@RequestParam(value = "flag") String flag) {
		uids = "(" + uids.substring(0, uids.length() - 1) + ")";
		String str = "";
		try {
			userService.updateUserStatus(uids, flag);
			str = "{\"success\":\"true\",\"message\":\"设置成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"设置失败！\"}";
		}
		return str;
	}

}
