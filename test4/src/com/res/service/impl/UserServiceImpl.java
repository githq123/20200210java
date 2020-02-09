package com.res.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.res.dao.UserDAO;
import com.res.entity.Users;
import com.res.service.UserService;

//在Spring容器中注册名为userService的UserServiceImpl实例
@Service("userService")
// 使用@Transactional注解实现事务管理
@Transactional
public class UserServiceImpl implements UserService {

	// 使用@Autowired注解注入MealDAOImpl实例
	@Autowired
	UserDAO userDAO;

	@Override
	public List<Users> getValidUser() {
		return userDAO.getValidUser();
	}

	@Override
	public Users getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public List<Users> getUsersByConditionForPager(Users u, int pageIndex,
			int pageSize) {
		return userDAO.getUsersByConditionForPager(u, pageIndex, pageSize);
	}

	@Override
	public int getTotalCount(Users u) {
		return userDAO.getTotalCount(u);
	}

	@Override
	public void updateUserStatus(String uids, String flag) {
		userDAO.updateUserStatus(uids, flag);
	}

}
