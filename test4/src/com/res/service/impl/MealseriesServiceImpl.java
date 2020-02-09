package com.res.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.res.dao.MealSeriesDAO;
import com.res.service.MealseriesService;

//在Spring容器中注册名为mealseriesService的MealseriesServiceImpl实例
@Service("mealseriesService")
// 使用@Transactional注解实现事务管理
@Transactional
public class MealseriesServiceImpl implements MealseriesService {

	// 使用@Autowired注解注入mealSeriesDAO实例
	@Autowired
	MealSeriesDAO mealSeriesDAO;

	// 获取菜系列表
	@Override
	public List getMealSeries() {
		return mealSeriesDAO.getMealSeries();
	}

}
