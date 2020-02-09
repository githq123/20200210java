package com.res.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.res.dao.MealSeriesDAO;
import com.res.entity.Mealseries;

//在Spring容器中注册实例名为adminDAO的AdminDAOImpl实例
@Repository("mealSeriesDAO")
public class MealSeriesDAOImpl extends BaseDaoImpl<Mealseries> implements MealSeriesDAO {

    // 获取菜系列表
	@Override
	public List getMealSeries() {		
		String hql = "from Mealseries";
		return super.find(hql);
	}

}
