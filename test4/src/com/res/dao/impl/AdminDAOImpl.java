package com.res.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.res.dao.AdminDAO;
import com.res.entity.Admin;

// 在Spring容器中注册实例名为adminDAO的AdminDAOImpl实例
@Repository("adminDAO")
public class AdminDAOImpl extends BaseDaoImpl<Admin> implements AdminDAO {

	// 管理员登录验证
	@Override
	public List<Admin> adminLogin(Admin admin) {
		String hql = "from Admin ad where ad.loginName = ? and ad.loginPwd = ?";
		Object[] param = new Object[] { admin.getLoginName(),
				admin.getLoginPwd() };
		return super.find(hql, param);
	}

	// 根据id获取管理员对象及功能权限
	@Override
	public Admin getAdminFunctions(int id) {
		return super.get(Admin.class, id);
	}

	// 获取所有管理员
	@Override
	public List<Admin> getAllAdmin() {
		String hql = "from Admin";
		return super.find(hql);
	}

	// 新增管理员
	@Override
	public void addAdmin(Admin admin) {
		super.save(admin);
	}
}
