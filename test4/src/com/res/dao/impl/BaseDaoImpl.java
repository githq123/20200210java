package com.res.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Query;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.res.dao.BaseDao;

// 在Spring容器中注册实例名为baseDao的BaseDaoImpl实例
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
	// 通过@Autowired注解注入Spring容器中的SessionFactory实例
	@Autowired
	SessionFactory sessionFactory;

	public Session getCurrentSession() {

		return this.sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return this.getCurrentSession().save(o);
	}

	public void delete(T o) {
		this.getCurrentSession().delete(o);
	}

	public void update(T o) {
		this.getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object[] param) {
		Query<T> q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 0) {
			page = 0;
		}
		if (rows == null || rows < 1) {
			rows = 9;
		}
		Query<T> q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult(page * rows).setMaxResults(rows)
				.getResultList();
	}

	
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	public Object findUnique(String hql) {
		return this.getCurrentSession().createQuery(hql).getSingleResult();
	}

	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void saveOrUpdate(String sql) {
		Query<?> q = this.getCurrentSession().createNativeQuery(sql);
		q.executeUpdate();
	}

	@Override
	public Integer executeSql(String sql, Object[] param) {
		Query<?> q = this.getCurrentSession().createNativeQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i + 1, param[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public List queryBySql(String sql) {
		Query q = this.getCurrentSession().createNativeQuery(sql);
        return q.list();  
	}

}