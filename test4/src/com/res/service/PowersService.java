package com.res.service;


public interface PowersService {
	// 删除指定管理员的权限
	public void delPowersByAdminid(int adminid);

	// 添加权限
	public void addPowers(int adminId, String[] fids);
}
