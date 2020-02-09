package com.res.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.res.dao.*;
import com.res.entity.*;
import com.res.service.*;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDAO;
	@Autowired
	OrderdtsDAO orderdtsDAO;

	// 根据查询条件(封装在对象o中)、指定页码、每页记录数，获取当前页的订单列表
	@Override
	public List<Orders> getOrderByConditionForPager(Orders o, int pageIndex,
			int pageSize) {
		return orderDAO.getOrderByConditionForPager(o, pageIndex, pageSize);
	}

	// 根据订单主表编号获取订单对象
	@Override
	public Orders getOrdersByOid(int oid) {
		return orderDAO.getOrdersByOid(oid);
	}

	/*
	 * @Override public int getTotalPages(int pageSize, Orders o) { // TODO
	 * Auto-generated method stub return 0; }
	 */

	// 根据查询条件(封装在对象o中),获取订单总记录数
	@Override
	public int getTotalCount(Orders o) {
		return orderDAO.getTotalCount(o);
	}

	// 新增订单
	@Override
	public int addOrder(Orders o) {
		return orderDAO.addOrder(o);
	}

	// 删除订单
	@Override
	public void deleteOrder(Orders o) {
		orderDAO.deleteOrder(o);
	}

	// 根据订单号获取订单明细
	@Override
	public List<Orderdts> getOrderdtsByOid(int oid) {
		return orderdtsDAO.getOrderdtsByOid(oid);
	}

	// 删除订单明细
	@Override
	public int deleteOrderdts(Orderdts od) {
		return orderdtsDAO.deleteOrderdts(od);
	}

	// 修改订单
	@Override
	public int modifyOrder(Orders o) {
		return orderDAO.modifyOrder(o);
	}

	// 餐品销量统计
	@Override
	public List findSalerStandby() {
		return orderDAO.findSalerStandby();
	}
}
