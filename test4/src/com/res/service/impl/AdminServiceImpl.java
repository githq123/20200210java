package com.res.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.res.dao.AdminDAO;
import com.res.entity.Admin;
import com.res.service.AdminService;

// 在Spring容器中注册名为adminService的AdminServiceImpl实例
@Service("adminService")
// 使用@Transactional注解实现事务管理
@Transactional
public class AdminServiceImpl implements AdminService {

	// 使用@Autowired注解注入UserInfoDAOImpl实例
	@Autowired
	AdminDAO adminDAO;

	// 管理员登录验证
	@Override
	public List<Admin> adminLogin(Admin admin) {
		return adminDAO.adminLogin(admin);
	}

	// 根据id获取管理员对象及功能权限
	@Override
	public Admin getAdminFunctions(int id) {
		return adminDAO.getAdminFunctions(id);
	}

	// 获取所有管理员
	@Override
	public List<Admin> getAllAdmin() {
		return adminDAO.getAllAdmin();
	}

	// 新增管理员
	@Override
	public void addAdmin(Admin admin) {
		adminDAO.addAdmin(admin);
	}

}
