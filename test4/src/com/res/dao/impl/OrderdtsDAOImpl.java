package com.res.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.res.dao.OrderdtsDAO;
import com.res.entity.Orderdts;

//在Spring容器中注册实例名为orderDAO的OrdersDAOImpl实例
@Repository("orderdtsDAO")
public class OrderdtsDAOImpl extends BaseDaoImpl<Orderdts> implements OrderdtsDAO {

	// 根据订单主表编号，获取订单明细列表
	@Override
	public List<Orderdts> getOrderdtsByOid(int oid) {
		String hql = "from Orderdts od where od.o.oid=" + oid;
		return super.find(hql);
	}

	// 删除订单明细
	@Override
	public int deleteOrderdts(Orderdts od) {		
		try {
			super.delete(od);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	

}
