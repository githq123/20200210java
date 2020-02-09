package com.res.dao;

public interface PowersDAO {
	// 删除指定管理员的权限
	public void delPowersByAdminid(int adminid);

	// 添加权限
	public void addPowers(int aid, int fid);
}
