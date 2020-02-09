package com.res.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.res.dao.MealDAO;
import com.res.entity.Meal;
import com.res.service.MealService;

// 在Spring容器中注册名为mealService的MealServiceImpl实例
@Service("mealService")
// 使用@Transactional注解实现事务管理
@Transactional
public class MealServiceImpl implements MealService {

	// 使用@Autowired注解注入MealDAOImpl实例
	@Autowired
	MealDAO mealDAO;

	// 根据查询条件和每页记录数，获取指定页显示的餐品列表
	@Override
	public List<Meal> getMealByConditionForPager(Meal meal, int pageIndex,
			int pageSize) {
		return mealDAO.getMealByConditionForPager(meal, pageIndex, pageSize);
	}

	// 根据查询条件和每页记录数,计算总页数
	@Override
	public int getTotalPages(int pageSize, Meal meal) {
		return mealDAO.getTotalPages(pageSize, meal);
	}

	// 获取指定条件的餐品总数
	@Override
	public int getTotalCount(Meal meal) {
		return mealDAO.getTotalCount(meal);
	}

	// 添加餐品
	@Override
	public int addMeal(Meal meal) {
		return mealDAO.addMeal(meal);
	}

	// 修改餐品状态
	@Override
	public int updateMealStatus(String ids) {
		return mealDAO.updateMealStatus(ids);
	}

	// 根据id号获取餐品
	@Override
	public Meal getMealByMealId(int mealId) {
		return mealDAO.getMealByMealId(mealId);
	}

	// 修改餐品对象
	@Override
	public void updateMeal(Meal meal) {
		mealDAO.updateMeal(meal);
	}

	// 获取在售餐品列表
	@Override
	public List<Meal> getOnSaleMeal() {
		return mealDAO.getOnSaleMeal();
	}

}
