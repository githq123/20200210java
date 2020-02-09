package com.res.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.res.dao.MealDAO;
import com.res.entity.Meal;

//在Spring容器中注册实例名为mealDAO的MealDAOImpl实例
@Repository("mealDAO")
public class MealDAOImpl extends BaseDaoImpl<Meal> implements MealDAO {

	/**
	 * 根据查询条件和每页记录数，获取指定页显示的餐品列表
	 */
	@Override
	public List<Meal> getMealByConditionForPager(Meal meal, int pageIndex,
			int pageSize) {
		String hql = "from Meal m where 1=1";
		Object[] param = null;
		if (meal != null) {
			List list = new ArrayList();
			if (meal.getMealName() != null && !meal.getMealName().equals("")) {
				hql += " and m.mealName like ?";
				list.add("%" + meal.getMealName() + "%");
			}
			if ((meal.getMealseries() != null)
					&& (meal.getMealseries().getSeriesId() != null)) {
				hql += " and m.mealseries.seriesId = ?";
				list.add(meal.getMealseries().getSeriesId());
			}
			if (list.size() > 0)
				param = list.toArray();
		}
		return super.find(hql, param, pageIndex - 1, pageSize);
	}

	/**
	 * 根据查询条件和每页记录数,计算总页数
	 */
	@Override
	public int getTotalPages(int pageSize, Meal meal) {
		int count = 0;
		int totalPages = 0;
		count = getTotalCount(meal);
		totalPages = (count % pageSize == 0) ? (count / pageSize) : (count
				/ pageSize + 1);
		return totalPages;
	}

	/**
	 * 获取指定条件的餐品总数
	 */
	@Override
	public int getTotalCount(Meal meal) {
		Integer count = null;
		try {
			String hql = "select count(m) from Meal m where 1=1";
			if (meal != null) {
				if (meal.getMealName() != null
						&& !meal.getMealName().equals("")) {
					hql += " and m.mealName like '%" + meal.getMealName()
							+ "%'";
				}
				if ((meal.getMealseries() != null)
						&& (meal.getMealseries().getSeriesId() != null)) {
					hql += " and m.mealseries.seriesId = "
							+ meal.getMealseries().getSeriesId();
				}
			}
			count = Integer.parseInt(super.findUnique(hql).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 根据id号获取餐品
	 */
	@Override
	public Meal getMealByMealId(int mealId) {
		return (Meal) super.get(Meal.class, mealId);
	}

	/**
	 * 添加餐品
	 */
	@Override
	public int addMeal(Meal meal) {
		return (Integer) super.save(meal);
	}

	/**
	 * 修改餐品
	 */
	@Override
	public void updateMeal(Meal meal) {
		super.update(meal);
	}

	/**
	 * 修改餐品状态(下架)
	 */
	@Override
	public int updateMealStatus(String ids) {
		String hql = "update Meal m set m.mealStatus=0 where m.mealId in "
				+ ids;
		return super.executeHql(hql);
	}

	/**
	 * 获取在售餐品列表
	 */
	@Override
	public List<Meal> getOnSaleMeal() {
		String hql = "from Meal m where m.mealStatus=1";
		return super.find(hql);
	}

}
