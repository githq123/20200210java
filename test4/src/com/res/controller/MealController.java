package com.res.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.res.entity.*;
import com.res.service.MealService;

@Controller
@RequestMapping("/meal")
public class MealController {
	// 使用@Autowired注解注入MealServiceImpl实例
	@Autowired
	MealService mealService;

	/**
	 * 显示餐品列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(Meal meal, int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		// 根据查询条件获取餐品记录总数
		int totalCount = mealService.getTotalCount(meal);
		// 根据当前页码、每页记录数、查询条件获取当前页的餐品列表
		List<Meal> mealList = mealService.getMealByConditionForPager(meal,
				page, rows);
		result.put("total", totalCount);
		result.put("rows", mealList);
		return result;
	}

	/**
	 * 添加餐品
	 */
	@RequestMapping(value = "/addMeal", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addMeal(Meal meal,
			@RequestParam(value = "pic", required = false) MultipartFile pic,
			HttpServletRequest request) {
		// 服务器端upload文件夹物理路径
		String path = request.getSession().getServletContext()
				.getRealPath("mealimages");
		// 获取文件名
		String fileName = pic.getOriginalFilename();
		// 实例化一个File对象，表示目标文件（含物理路径）
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			// 将上传文件写到服务器上指定的文件
			pic.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		meal.setMealImage(fileName);
		int result = mealService.addMeal(meal);
		String str = "";
		if (result > 0) {
			str = "{\"success\":\"true\",\"message\":\"添加成功!\"}";
		} else {
			str = "{\"success\":\"false\",\"message\":\"添加失败!\"}";
		}
		return str;
	}

	/**
	 * 商品下架(更新商品状态)
	 */
	@RequestMapping(value = "/modifyMealStatus", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String modifyMealStatus(String id) {
		String ids = "(" + id.substring(0, id.length() - 1) + ")"; // 形如(1,3,6)
		int result = mealService.updateMealStatus(ids);
		String str = "";
		if (result > 0) {
			str = "{\"success\":\"true\",\"message\":\"下架成功!\"}";
		} else {
			str = "{\"success\":\"false\",\"message\":\"下架失败!\"}";
		}
		return str;
	}

	/**
	 * 修改餐品
	 */
	@RequestMapping(value = "/updateMeal", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateMeal(Meal meal, int mealId,
			@RequestParam(value = "pic", required = false) MultipartFile pic,
			HttpServletRequest request) {
		// 获取修改前的餐品对象
		Meal editMeal = mealService.getMealByMealId(mealId);
		// 修改餐品对象信息
		editMeal.setMealStatus(meal.getMealStatus());
		editMeal.setMealseries(meal.getMealseries());
		editMeal.setMealName(meal.getMealName());
		editMeal.setMealSummarize(meal.getMealSummarize());
		editMeal.setMealDescription(meal.getMealDescription());
		editMeal.setMealPrice(meal.getMealPrice());
		String str = "";
		String fileName = "";
		if (pic.getSize() > 0) { // 重新上传餐品图片
			// 服务器端upload文件夹物理路径
			String path = request.getSession().getServletContext()
					.getRealPath("mealimages");
			// 获取文件名
			fileName = pic.getOriginalFilename();
			// 实例化一个File对象，表示目标文件（含物理路径）
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			try {
				// 将上传文件写到服务器上指定的文件
				pic.transferTo(targetFile);
				editMeal.setMealImage(fileName);
			} catch (Exception e) {
				e.printStackTrace();
				return "{\"success\":\"false\",\"message\":\"图片上传失败!\"}";
			}
		}
		try {
			mealService.updateMeal(editMeal);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"success\":\"true\",\"message\":\"修改失败!\"}";
		}
		return "{\"success\":\"true\",\"message\":\"修改成功!\"}";
	}

	/**
	 * 获取在售餐品列表
	 */
	@ResponseBody
	@RequestMapping("/getOnSaleMeal")
	public List<Meal> getOnSaleProduct() {
		List<Meal> onSaleMealList = mealService.getOnSaleMeal();
		return onSaleMealList;
	}

	/**
	 * 获取餐品单价
	 * 
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMealPriceByMealId")
	public String getMealPriceByMealId(String mealId) {
		if (mealId != null && !"".equals(mealId)) {
			Meal m = mealService.getMealByMealId(Integer.parseInt(mealId));
			return m.getMealPrice() + "";
		} else {
			return "";
		}
	}
	
}
