package com.res.dao.impl;

import org.springframework.stereotype.Repository;
import com.res.dao.PowersDAO;
import com.res.entity.Powers;

@Repository("powersDAO")
public class PowersDAOImpl extends BaseDaoImpl<Powers> implements PowersDAO {

	// 删除指定管理员的权限
	@Override
	public void delPowersByAdminid(int adminid) {
		String sql = "delete from powers where aid=" + adminid;
		super.executeSql(sql, null);
	}

	// 添加权限
	@Override
	public void addPowers(int aid, int fid) {
		String sql = " insert into powers(aid,fid) values(?,?)";
		Object[] params = new Object[] { aid, fid };
		super.executeSql(sql, params);
	}

}
