package com.res.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.res.dao.*;
import com.res.service.*;

@Service("powersService")
@Transactional
public class PowersServiceImpl implements PowersService {

	@Autowired
	private PowersDAO powersDAO;
	@Autowired
	private AdminDAO adminDAO;

	// 删除指定管理员的权限
	@Override
	public void delPowersByAdminid(int adminid) {
		powersDAO.delPowersByAdminid(adminid);
	}

	// 添加权限
	@Override
	public void addPowers(int adminId, String[] fids) {
		for (String fid : fids) {
			powersDAO.addPowers(adminId, Integer.parseInt(fid));
		}
	}
}
