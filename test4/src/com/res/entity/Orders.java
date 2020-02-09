package com.res.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders", catalog = "restrant")
public class Orders {
	// 订单基本属性
	private int oid;
	private int userId;
	private String orderTime;
	private String orderStatus;
	private double orderPrice;

	// 附加属性,用于订单查询
	private String orderTimeFrom;
	private String orderTimeTo;

	// 关联属性
	private Users u;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserId")
	public Users getU() {
		return u;
	}

	public void setU(Users u) {
		this.u = u;
	}

	// 关联属性
	@JsonIgnoreProperties(value = { "o", "m" })
	private Set<Orderdts> ods = new HashSet<Orderdts>();

	@OneToMany(mappedBy = "o", fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	public Set<Orderdts> getOds() {
		return ods;
	}

	public void setOds(Set<Orderdts> ods) {
		this.ods = ods;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OID", unique = true, nullable = false)
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	@Transient
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Transient
	public String getOrderTimeFrom() {
		return orderTimeFrom;
	}

	public void setOrderTimeFrom(String orderTimeFrom) {
		this.orderTimeFrom = orderTimeFrom;
	}

	@Transient
	public String getOrderTimeTo() {
		return orderTimeTo;
	}

	public void setOrderTimeTo(String orderTimeTo) {
		this.orderTimeTo = orderTimeTo;
	}

}
