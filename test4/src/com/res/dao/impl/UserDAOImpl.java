package com.res.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.res.dao.UserDAO;
import com.res.entity.Orders;
import com.res.entity.Users;

//在Spring容器中注册实例名为userDAO的UserDAOImpl实例
@Repository("userDAO")
public class UserDAOImpl extends BaseDaoImpl<Users> implements UserDAO {

	@Override
	public List<Users> getValidUser() {
		String hql = "from Users u where u.status=1";
		return super.find(hql);
	}

	@Override
	public Users getUserById(int id) {
		return super.get(Users.class, id);
	}

	@Override
	public List<Users> getUsersByConditionForPager(Users u, int pageIndex,
			int pageSize) {
		String hql = "from Users u where 1=1";
		Object[] param = null;
		if (u != null) {
			List list = new ArrayList();
			if (u.getLoginName() != null && !"".equals(u.getLoginName())) {
				hql += " and u.loginName like ?";
				list.add("%" + u.getLoginName() + "%");
			}
			if (list.size() > 0)
				param = list.toArray();
		}
		return super.find(hql, param, pageIndex - 1, pageSize);
	}

	@Override
	public int getTotalCount(Users u) {
		Integer count = null;
		try {
			String hql = "select count(u) from Users u where 1=1";
			if (u != null) {
				if (u.getLoginName() != null && !"".equals(u.getLoginName())) {
					hql += " and u.loginName like '%" + u.getLoginName() + "%'";
				}
			}
			count = Integer.parseInt(super.findUnique(hql).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void updateUserStatus(String uids, String flag) {
		String sql = "update users u set u.status=" + Integer.parseInt(flag);
		sql += " where u.id in " + uids;
		super.saveOrUpdate(sql);
	}
}
