package com.res.dao;

import java.util.List;

import com.res.entity.*;

public interface OrderDAO {
	// 根据查询条件(封装在对象o中)、指定页码、每页记录数，获取当前页的订单列表
	public List<Orders> getOrderByConditionForPager(Orders o, int pageIndex,
			int pageSize);

	// 根据查询条件(封装在对象o中),获取订单总记录数
	public int getTotalCount(Orders o);

	// 根据订单主表编号获取订单对象
	public Orders getOrdersByOid(int oid);

	// 新增订单
	public int addOrder(Orders o);

	// 删除订单
	public void deleteOrder(Orders o);	

	// 修改订单
	public int modifyOrder(Orders o);
	
	// 餐品销量统计
	public List findSalerStandby();

}
