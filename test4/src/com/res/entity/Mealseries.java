package com.res.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mealseries", catalog = "restrant")
public class Mealseries implements java.io.Serializable {

	private Integer seriesId;
	private String seriesName;
	//private Set<Meal> meals = new HashSet<Meal>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SeriesId", unique = true, nullable = false)
	public Integer getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public String getSeriesName() {
		return this.seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	
	/*@JsonIgnoreProperties(value={"mealseries"})
	// 使用@OneToMany注解实现Mealseries到Meal的一对多关联
	@OneToMany(mappedBy = "mealseries", cascade = { CascadeType.REMOVE },fetch=FetchType.EAGER)
	public Set<Meal> getMeals() {
		return meals;
	}

	public void setMeals(Set<Meal> meals) {
		this.meals = meals;
	}*/

	public Mealseries() {
	}

}