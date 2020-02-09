package com.res.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.res.dao.FunctionsDAO;
import com.res.entity.Functions;

//在Spring容器中注册实例名为functionsDAO的FunctionsDAOImpl实例
@Repository("functionsDAO")
public class FunctionsDAOImpl extends BaseDaoImpl<Functions> implements
		FunctionsDAO {

	// 获取所有功能对象
	@Override
	public List<Functions> getAllFunctions() {
		String hql = "from Functions";
		return super.find(hql);
	}

}
