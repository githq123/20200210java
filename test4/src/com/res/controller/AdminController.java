package com.res.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.res.util.JsonFactory;
import com.res.entity.*;
import com.res.service.AdminService;

@SessionAttributes(value = { "admininfo" })
@Controller
@RequestMapping("/admin")
public class AdminController {
	// 使用@Autowired注解注入AdminServiceImpl实例
	@Autowired
	AdminService adminService;

	// 后台登录验证
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(Admin admin, ModelMap model) {
		List<Admin> adminList = adminService.adminLogin(admin);
		if (adminList.size() > 0) {
			// 验证通过后，再判断是否已为该管理员分配功能权限			
			if (adminList.get(0).getFs().size() > 0) {
				// 验证通过且已分配功能权限，则将admininfo对象存入model中
				model.put("admininfo", adminList.get(0));
				// 以JSON格式向页面发送成功信息
				return "{\"success\":\"true\",\"message\":\"登录成功\"}";
			} else {
				return "{\"success\":\"false\",\"message\":\"您没有权限，请联系超级管理员设置权限！\"}";
			}
		} else {
			// 登录失败，重定向到login.jsp
			return "{\"success\":\"false\",\"message\":\"登录失败\"}";
		}
	}

	/**
	 * 登录后获取功能菜单列表
	 * 
	 * @param adminid
	 * @return
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public List<TreeNode> getTree(
			@RequestParam(value = "adminid") String adminid) {
		// 根据管理员id号获取Admin对象及关联的Functions对象集合
		Admin admin = adminService.getAdminFunctions(Integer.parseInt(adminid));
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<Functions> functionsList = new ArrayList<Functions>();
		// 获取Admin对象关联的Functions对象集合
		Set<Functions> functionsSet = admin.getFs();
		// 将Functions对象集合的类型从Set<Functions>转换为List<Functions>类型
		for (Functions functions : functionsSet) {
			functionsList.add(functions);
		}
		// 对List<Functions>类型的Functions对象集合排序
		Collections.sort(functionsList);
		// 将排序后的Functions对象集合转换到List<TreeNode>类型的列表nodes中
		for (Functions f : functionsList) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(f.getId());
			treeNode.setFid(f.getParentid());
			treeNode.setText(f.getName());
			nodes.add(treeNode);
		}
		// 调用自定义工具类JsonFactory的buildtree方法，为nodes列表中个TreeNode元素中的children赋值(即该节点包含的子节点)
		List<TreeNode> treeNodes = JsonFactory.buildtree(nodes, 0);
		// 以JSON格式向页面返回绑定tree所需的数据
		return treeNodes;
	}

	// 显示管理员列表
	@RequestMapping("/list")
	@ResponseBody
	public List<Admin> list() {
		List<Admin> aList = adminService.getAllAdmin();
		return aList;
	}

	/**
	 * 注销
	 * 
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	@ResponseBody
	public String loginout(SessionStatus status) {
		// @SessionAttributes清除
		status.setComplete();
		return "{\"success\":\"true\",\"message\":\"注销成功\"}";
	}

	// 新增管理员
	@RequestMapping(value = "/addAdmin", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addAdmin(Admin ai) {
		try {
			adminService.addAdmin(ai);
			return "{\"success\":\"true\",\"message\":\"新增成功\"}";
		} catch (Exception e) {
			return "{\"success\":\"false\",\"message\":\"新增失败\"}";
		}
	}

	// 获取指定id号的管理员已分配的功能权限
	@RequestMapping("/getFidByAdminId")
	@ResponseBody
	public String getFidByAdminId(
			@RequestParam(value = "adminid") String adminid) {
		// 根据管理员id号获取管理员对象及关联的Functinos对象集合
		Admin admin = adminService.getAdminFunctions(Integer.parseInt(adminid));
		// 定义变量sb,用于保存该管理员已分配的功能且属于叶子节点的功能id号
		StringBuffer sb = new StringBuffer();
		// 获取关联的功能集合（即已分配的功能权限）
		Set<Functions> fsSet = admin.getFs();
		// 遍历该功能集合，将属于叶子节点的功能id号添加到变量sb中，并以“,”号分隔
		for (Functions f : fsSet) {
			if (f.isIsleaf())
				sb.append(f.getId()).append(",");
		}
		if (sb.length() > 0)
			return sb.substring(0, sb.length() - 1).toString();
		else
			return "";
	}

}
