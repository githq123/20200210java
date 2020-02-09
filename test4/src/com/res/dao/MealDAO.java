package com.res.dao;

import java.util.List;

import com.res.entity.Meal;

public interface MealDAO {	
	/**
	 * 根据查询条件和每页记录数，获取指定页显示的餐品列表
	 * @param meal
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Meal> getMealByConditionForPager(Meal meal, int pageIndex,
			int pageSize);

	
	/**
	 * 根据查询条件和每页记录数,计算总页数
	 * @param pageSize
	 * @param meal
	 * @return
	 */
	public int getTotalPages(int pageSize,Meal meal);
	
	
	/**
	 * 获取指定条件的餐品总数 
	 * @param meal
	 * @return
	 */
	public int getTotalCount(Meal meal);
	

	/**
	 * 根据id号获取餐品
	 * 
	 * @param mealId
	 * @return
	 */
	public Meal getMealByMealId(int mealId);

	/**
	 * 添加餐品
	 * 
	 * @param meal
	 */
	public int addMeal(Meal meal);

	/**
	 * 修改餐品对象
	 * 
	 * @param meal
	 */
	public void updateMeal(Meal meal);
	
	
	/**
	 * 修改餐品状态(下架)
	 * @param ids
	 * @return
	 */
	public int updateMealStatus(String ids);


	/**
	 * 获取在售餐品列表
	 * @return
	 */
	public List<Meal> getOnSaleMeal();

}
