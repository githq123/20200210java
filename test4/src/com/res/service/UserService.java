package com.res.service;

import java.util.List;

import com.res.entity.Users;

public interface UserService {
	public List<Users> getValidUser();

	public Users getUserById(int id);

	public List<Users> getUsersByConditionForPager(Users u, int pageIndex,
			int pageSize);

	public int getTotalCount(Users u);

	public void updateUserStatus(String uids, String flag);
}
