package com.res.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.res.entity.*;
import com.res.service.*;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	MealService mealService;

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping("/getAllOrder")
	public Map<String, Object> getAllOrder(Orders o, String search_oid,
			int page, int rows) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		if (search_oid != null && !"".equals(search_oid)) {
			o.setOid(Integer.parseInt(search_oid));
		}
		// 根据查询条件获取订单记录总数
		int totalCount = orderService.getTotalCount(o);
		// 根据当前页码、每页记录数、查询条件获取指定页显示的订单列表
		List<Orders> oList = orderService.getOrderByConditionForPager(o, page,
				rows);
		result.put("total", totalCount);
		result.put("rows", oList);
		return result;
	}

	/**
	 * 根据主订单号获取订单明细
	 * 
	 * @param oid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getOrderDts")
	public List<Orderdts> getOrderDts(String oid) {
		List<Orderdts> ods = orderService.getOrderdtsByOid(Integer
				.parseInt(oid));
		for (Orderdts od : ods) {
			od.setMealId(od.getM().getMealId());
			od.setMealPrice(od.getM().getMealPrice());
			od.setTotalprice(od.getM().getMealPrice() * od.getMealCount());
		}
		return ods;
	}

	// 提交订单
	@ResponseBody
	@RequestMapping(value = "/commitOrder")
	public String commitOrder(String inserted, String order)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			// 创建ObjectMapper对象，实现JavaBean和JSON的转换
			ObjectMapper mapper = new ObjectMapper();
			// 设置输入时忽略JSON字符串中存在但JavaBean对象实际没有属性
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// 将json字符串order转换为Orders对象o(订单主表)
			Orders o = mapper.readValue(order, Orders[].class)[0];
			// 给对象o设置关联的ui属性值
			o.setU(userService.getUserById(o.getUserId()));
			// 将json字符串inserted转换为List<Orderdts>(订单子表或明细)
			List<Orderdts> odList = mapper.readValue(inserted,
					new TypeReference<ArrayList<Orderdts>>() {
					});
			Meal meal = null;
			double orderprice = 0;
			// 给订单子表对象的其他属性赋值
			for (Orderdts od : odList) {
				meal = mealService.getMealByMealId(od.getMealId());
				orderprice += meal.getMealPrice() * od.getMealCount();
				// 设置订单明细对象关联属性
				od.setO(o);
				od.setM(meal);
				// 设置订单主表与订单明细的关联(将订单子表对象添加到订单主表对象中)
				o.getOds().add(od);
			}

			// 将计算出的订单价格设置到订单主表对象o中
			o.setOrderPrice(orderprice);
			// 保存订单主表，级联保存订单子表记录
			if (orderService.addOrder(o) > 0) {
				return "success";
			} else {
				return "failure";
			}
		} catch (Exception e) {
			return "failure";
		}
	}

	/**
	 * 根据订单id获取订单
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("/getOrder")
	public ModelAndView getOrder(String oid) {
		String viewName = "modifyorder";
		ModelAndView mv = new ModelAndView(viewName);
		Orders o = orderService.getOrdersByOid(Integer.parseInt(oid));
		// o对象是存放request范围，在modifyorder.jsp页面中可通过requestScope来获得对象o
		mv.addObject("o", o); 
		return mv;
	}

	/**
	 * 删除订单
	 * 
	 * @param oids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteOrder", produces = "text/html;charset=UTF-8")
	public String deleteOrder(String oids) {
		String str = "";
		try {
			oids = oids.substring(0, oids.length() - 1);
			String[] ids = oids.split(",");
			for (String id : ids) {
				// 循环删除订单记录
				Orders o = orderService.getOrdersByOid(Integer.parseInt(id));
				orderService.deleteOrder(o);
			}
			str = "{\"success\":\"true\",\"message\":\"删除成功！\"}";
		} catch (Exception e) {
			str = "{\"success\":\"false\",\"message\":\"删除失败！\"}";
		}
		return str;
	}

	/**
	 * 修改订单
	 * 
	 * @param inserted
	 * @param updated
	 * @param deleted
	 * @param order
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/commitModifyOrder")
	public String commitModifyOrder(String inserted, String updated,
			String deleted, String order) throws JsonParseException,
			JsonMappingException, IOException {
		try {
			// 定义要插入的、要更新的、要删除的订单明细集合
			List<Orderdts> insertedOdList = null;
			List<Orderdts> updatedOdList = null;
			List<Orderdts> deletedOdList = null;
			// 创建ObjectMapper对象，实现JavaBean和JSON的转换
			ObjectMapper mapper = new ObjectMapper();
			// 设置输入时忽略子JSON字符串中存在但JavaBean对象实际没有属性
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			// 将json字符串order转换为JavaBean对象(订单主表)
			Orders tempoi = mapper.readValue(order, Orders[].class)[0];
			// 订单主表对象(原始的、修改前)
			Orders o = orderService.getOrdersByOid(tempoi.getOid());
			o.setU(userService.getUserById(tempoi.getUserId()));
			o.setOrderStatus(tempoi.getOrderStatus());
			o.setOrderTime(tempoi.getOrderTime());
			o.setOrderPrice(tempoi.getOrderPrice());
			// 此时的o即为更新后的订单主表对象
			// 下面处理订单明细部分
			// 处理删除的订单明细
			if (deleted != null) {
				deletedOdList = mapper.readValue(deleted,
						new TypeReference<ArrayList<Orderdts>>() {
						});
				for (Orderdts dod : deletedOdList) { // dod就是要删除的订单明细对象
					Set<Orderdts> odset = o.getOds();
					Iterator<Orderdts> itor = odset.iterator();
					// 定义delods,用于临时保存要从订单对象oi中移除的关联的订单明细
					List<Orderdts> delods = new ArrayList<Orderdts>();
					while (itor.hasNext()) {
						Orderdts odd = itor.next();
						if (dod.getOdid() == odd.getOdid()) {
							orderService.deleteOrderdts(odd);
							delods.add(odd);
						}
					}
					for (Orderdts delod : delods) {
						o.getOds().remove(delod);
					}
				}
			}
			// 处理要修改的订单明细
			if (updated != null) {
				updatedOdList = mapper.readValue(updated,
						new TypeReference<ArrayList<Orderdts>>() {
						});
				for (Orderdts uod : updatedOdList) {
					Set<Orderdts> odset = o.getOds();
					Iterator<Orderdts> itor = odset.iterator();
					// 定义removeods,用于临时保存要从订单对象o中移除的关联的订单明细
					List<Orderdts> removeods = new ArrayList<Orderdts>();
					// 定义addods,用于临时保存要添加到订单对象o中关联的订单明细
					List<Orderdts> addods = new ArrayList<Orderdts>();
					while (itor.hasNext()) {
						Orderdts odd = itor.next();
						if (uod.getOdid() == odd.getOdid()) {
							// 将要移除的修改前的订单明细对象添加到removeods
							removeods.add(odd);
							uod.setM(mealService.getMealByMealId(uod
									.getMealId()));
							// 将修改后的订单明细对象添加到addods中
							addods.add(uod);
						}
					}
					// 从订单对象o关联的订单明细集合中移除removeods中的对象
					for (Orderdts removeod : removeods) {
						o.getOds().remove(removeod);
					}
					// 向订单对象o关联额订单明细集合中添加addods中保存的对象
					for (Orderdts addod : addods) {
						o.getOds().add(addod);
					}
				}
			}
			// 处理新增的订单明细
			if (inserted != null) {
				insertedOdList = mapper.readValue(inserted,
						new TypeReference<ArrayList<Orderdts>>() {
						});
				Meal m = null;
				double orderPrice = 0;
				for (Orderdts iod : insertedOdList) {
					m = mealService.getMealByMealId(iod.getMealId());
					iod.setM(m);
					orderPrice += m.getMealPrice() * iod.getMealCount();
					iod.setO(o);
					// 向订单对象oi关联的订单明细集合中添加新增的订单明细对象
					o.getOds().add(iod);
				}
			}
			// 最后判断订单对象o关联的订单明细集合中是否还有记录
			// 修改后，没有订单明细数据，此时需要将订单主表一起删除
			if (o.getOds().size() == 0) { 
				orderService.deleteOrder(o);
			} else {
				orderService.modifyOrder(o);
			}
		} catch (Exception e) {
			return "failure";
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value="/salerStatistics", produces = "application/json;charset=UTF-8")
	public List<List> salerStatistics() {		
		List list = orderService.findSalerStandby();
		List<List> result=new ArrayList<List>();		
		Iterator itor = list.iterator();		
		List<String> list1 =new ArrayList<String>();
		List<Double> list2 =new ArrayList<Double>();
		while (itor.hasNext()) {
			Object[] obj = (Object[]) itor.next();
			list1.add(obj[0].toString());
			list2.add(Double.parseDouble(obj[1].toString()) );			
		}
		result.add(list1);
		result.add(list2);
		return result; 
	}
}
