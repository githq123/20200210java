package com.res.dao;

import java.io.Serializable;
import java.util.List;

/** 
 *  
 *  
 */

public interface BaseDao<T> {

	/**
	 * 
	 * 
	 * @param o
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 
	 * 
	 * @param o
	 */
	public void delete(T o);

	/**
	 * 
	 * 
	 * @param o
	 */
	public void update(T o);

	/**
	 * 
	 * 
	 * @param o
	 */
	public void saveOrUpdate(T o);

	/**
	 *
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql);

	/**
	 *
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	public List<T> find(String hql, Object[] param);

	/**
	 *
	 * 
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * 
	 * @param c
	 * @param id
	 * @return Object
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 
	 * @param hql
	 * @return
	 */
	public Object findUnique(String hql);

	/**
	 * 
	 * @param hql
	 */
	public Integer executeHql(String hql);

	// 保存或修改sql
	void saveOrUpdate(String sql);

	// 执行原生SQL
	public Integer executeSql(String sql, Object[] param);

	// 本地sql查询
	List queryBySql(String sql);

}