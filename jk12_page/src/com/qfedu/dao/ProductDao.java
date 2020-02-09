package com.qfedu.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qfedu.bean.Product;
import com.qfedu.utils.C3P0Utils;

public class ProductDao {
	//增删查改
	public void insertToProduct(String name,String price,int num,String category) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		//除了select用query方法，其余insert、delete、update都用update
		String sql="insert into product(name,price,num,category) values(?,?,?,?)";
		try {
			qr.update(sql,name,price,num,category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteProduct(int id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete * from product where id=?";
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Product selectProduct(int id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select id,name,price,num,category from product where id=?";
		try {
			return qr.query(sql, new BeanHandler<Product>(Product.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	public void updateProduct(int id,String name,String price,int num,String category) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update product set name=?,price=?,num=?,category=? where id=?";
		try {
			qr.update(sql,name,price,num,category,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//查一页数据的Product的方法
	//查询需要两个方法支持
	public long selectProductCount() {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select count(*) from product";
		try {
			return (long) qr.query(sql, new ScalarHandler());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//查这一页数据的每一个product
	public List<Product> selectPage(int pageSize,int pageNo){
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		//select * from tablename
		//where boolean1
		//group by columnname
		//having boolean1
		//order by columnname
		//limit ?,?
		//为啥要分页？
		//表的数据可能数十万条
		//一次性全查出来性能有问题。
		//所以我们想每一次只查询这个表数据的一部分。
		String sql="select id,name,price,num,category from product limit ?,?";
		try {
			return qr.query(sql, new BeanListHandler<Product>(Product.class),(pageNo-1)*pageSize,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

	
	
}
