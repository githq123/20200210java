package com.res.dao;

import java.util.List;

import com.res.entity.Orderdts;

public interface OrderdtsDAO {
	// 根据订单主表编号，获取订单明细列表
	public List<Orderdts> getOrderdtsByOid(int oid);

	// 删除订单明细
	public int deleteOrderdts(Orderdts od);
}
