package com.res.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.res.dao.FunctionsDAO;
import com.res.entity.Functions;
import com.res.service.FunctionsService;

@Service("functionsService")
@Transactional
public class FunctionsServiceImpl implements FunctionsService {

	@Autowired
	private FunctionsDAO functionsDAO;

	// 获取所有功能对象
	@Override
	public List<Functions> getAllFunctions() {
		return functionsDAO.getAllFunctions();
	}

}
