package com.res.dao;

import java.util.List;
import java.util.Map;

import com.res.entity.Orders;
import com.res.entity.Users;

public interface UserDAO {
	public List<Users> getValidUser();

	public Users getUserById(int id);

	public List<Users> getUsersByConditionForPager(Users u, int pageIndex,
			int pageSize);

	public int getTotalCount(Users u);

	public void updateUserStatus(String uids, String flag);
}
