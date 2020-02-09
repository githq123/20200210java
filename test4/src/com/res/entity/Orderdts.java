package com.res.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderdts", catalog = "restrant")
public class Orderdts {
	// 基本属性
	private int odid;
	private int mealCount;
	private double mealPrice;
	private double totalprice;
	// 关联属性
	private Orders o;
	private Meal m;
	private int mealId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ODID", unique = true, nullable = false)
	public int getOdid() {
		return odid;
	}

	public void setOdid(int odid) {
		this.odid = odid;
	}

	@Transient
	public int getMealId() {
		return mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	public int getMealCount() {
		return mealCount;
	}

	public void setMealCount(int mealCount) {
		this.mealCount = mealCount;
	}

	public double getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(double mealPrice) {
		this.mealPrice = mealPrice;
	}

	@Transient
	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	@JsonIgnoreProperties(value={"u","ods"})
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OID")
	public Orders getO() {
		return o;
	}

	public void setO(Orders o) {
		this.o = o;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MealId", unique = true)
	public Meal getM() {
		return m;
	}

	public void setM(Meal m) {
		this.m = m;
	}
}
