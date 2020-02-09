package com.res.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.res.entity.Mealseries;
import com.res.service.MealseriesService;

@Controller
@RequestMapping("/mealSeries")
public class MealSeriesController {
	// 使用@Autowired注解注入MealseriesServiceImpl实例
	@Autowired
	MealseriesService mealseriesService;
	
	@ResponseBody
	@RequestMapping("/list")
	public List<Mealseries> list() {
		List<Mealseries> mealseries = mealseriesService.getMealSeries();
		return mealseries;
	}

}
