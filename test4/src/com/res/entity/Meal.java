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

@Entity
@Table(name = "meal", catalog = "restrant")
public class Meal implements java.io.Serializable {

	private Integer mealId;
	private Mealseries mealseries;
	private String mealName;
	private String mealSummarize;
	private String mealDescription;
	private Double mealPrice;
	private String mealImage;
	private Integer mealStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MealId", unique = true, nullable = false)
	public Integer getMealId() {
		return this.mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	// 使用@ManyToOne和@JoinColumn注解实现Meal到Mealseries的多对一关联
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "MealSeriesId")
	public Mealseries getMealseries() {
		return this.mealseries;
	}

	public void setMealseries(Mealseries mealseries) {
		this.mealseries = mealseries;
	}

	public String getMealName() {
		return this.mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getMealSummarize() {
		return this.mealSummarize;
	}

	public void setMealSummarize(String mealSummarize) {
		this.mealSummarize = mealSummarize;
	}

	public String getMealDescription() {
		return this.mealDescription;
	}

	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}

	public Double getMealPrice() {
		return this.mealPrice;
	}

	public void setMealPrice(Double mealPrice) {
		this.mealPrice = mealPrice;
	}

	public String getMealImage() {
		return this.mealImage;
	}

	public void setMealImage(String mealImage) {
		this.mealImage = mealImage;
	}

	public Integer getMealStatus() {
		return mealStatus;
	}

	public void setMealStatus(Integer mealStatus) {
		this.mealStatus = mealStatus;
	}

	public Meal() {
	}

}