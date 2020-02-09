package com.res.dao;

import java.util.List;

import com.res.entity.Admin;

public interface AdminDAO {
	/**
	 * 管理员登录验证
	 * 
	 * @param admin
	 * @return
	 */
	public List<Admin> adminLogin(Admin admin);

	/**
	 * 根据id获取管理员对象及功能权限
	 * 
	 * @param id
	 * @return
	 */
	public Admin getAdminFunctions(int id);
	
	
	// 获取所有管理员
	public List<Admin> getAllAdmin();
	
	// 新增管理员
	public void addAdmin(Admin admin);

}
