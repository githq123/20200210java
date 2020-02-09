package com.res.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.res.dao.OrderDAO;
import com.res.entity.Orders;

//在Spring容器中注册实例名为orderDAO的OrdersDAOImpl实例
@Repository("orderDAO")
public class OrderDAOImpl extends BaseDaoImpl<Orders> implements OrderDAO {

	// 根据查询条件(封装在对象o中)、指定页码、每页记录数，获取当前页的订单列表
	@Override
	public List<Orders> getOrderByConditionForPager(Orders o, int pageIndex,
			int pageSize) {
		String hql = "from Orders o where 1=1";
		Object[] param = null;
		if (o != null) {
			if (o.getOid() > 0) {
				hql += " and o.oid=" + o.getOid();
			} else {
				List list = new ArrayList();
				if (o.getOrderStatus() != null
						&& !"请选择".equals(o.getOrderStatus())) {
					hql += " and o.orderStatus = ?";
					list.add(o.getOrderStatus());
				}
				if (o.getOrderTimeFrom() != null
						&& !"".equals(o.getOrderTimeFrom())) {
					hql += " and o.orderTime >= ?";
					list.add(o.getOrderTimeFrom());
				}
				if (o.getOrderTimeTo() != null
						&& !"".equals(o.getOrderTimeTo())) {
					hql += " and o.orderTime <? ";
					list.add(o.getOrderTimeTo());
				}
				if (o.getUserId() > 0) {
					hql += " and o.u.id= ? ";
					list.add(o.getUserId());
				}
				if (list.size() > 0)
					param = list.toArray();
			}
		}
		return super.find(hql, param, pageIndex - 1, pageSize);
	}

	// 根据查询条件(封装在对象o中),获取订单总记录数
	@Override
	public int getTotalCount(Orders o) {
		Integer count = null;
		try {
			String hql = "select count(o) from Orders o where 1=1";
			if (o != null) {
				if (o.getOid() > 0) {
					hql += " and o.oid=" + o.getOid();
				} else {
					if (o.getOrderStatus() != null
							&& !"请选择".equals(o.getOrderStatus())) {
						hql += " and o.orderStatus = '" + o.getOrderStatus()
								+ "'";
					}
					if (o.getOrderTimeFrom() != null
							&& !"".equals(o.getOrderTimeFrom())) {
						hql += " and o.orderTime >= '" + o.getOrderTimeFrom()
								+ "'";
					}
					if (o.getOrderTimeTo() != null
							&& !"".equals(o.getOrderTimeTo())) {
						hql += " and o.orderTime < '" + o.getOrderTimeTo()
								+ "'";
					}
					if (o.getUserId() > 0) {
						hql += " and o.u.id= " + o.getUserId();
					}
				}
			}
			count = Integer.parseInt(super.findUnique(hql).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 根据订单主表编号获取订单对象
	@Override
	public Orders getOrdersByOid(int oid) {
		return super.get(Orders.class, oid);
	}

	// 新增订单
	@Override
	public int addOrder(Orders o) {
		return (Integer) super.save(o);
	}

	// 删除订单
	@Override
	public void deleteOrder(Orders o) {
		super.delete(o);
	}

	// 修改订单
	@Override
	public int modifyOrder(Orders o) {
		try {
			super.saveOrUpdate(o);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	// 餐品销量统计
	@Override
	public List findSalerStandby() {
		String sql = "SELECT DISTINCT MealName, SUM(MealCount * m.MealPrice) AS mc FROM orderdts od, meal m WHERE od.MealId=m.MealId GROUP BY od.MealId";
		return super.queryBySql(sql);
	}
}
