package com.qfedu.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qfedu.bean.User;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		test4();
	}

	public static void test1() throws SQLException {
		// QueryRunner是dbutils中的核心对象，
		// 实例这个对象需要一个连接池（数据源）
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		User user = qr.query("select id,username,password,sex,age from user where id=?", new BeanHandler<User>(User.class), 1);
		System.out.println(user);
	}
	
	public static void test2() throws SQLException {
		// QueryRunner是dbutils中的核心对象，
		// 实例这个对象需要一个连接池（数据源）
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		List<User> users = qr.query("select id,username,password,sex,age from user", new BeanListHandler<User>(User.class));
		System.out.println(users);
	}
	
	public static void test3() throws SQLException {
		// QueryRunner是dbutils中的核心对象，
		// 实例这个对象需要一个连接池（数据源）
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		List<Map<String,Object>> users = qr.query("select id,username,password,sex,age from user", new MapListHandler());
		System.out.println(users);
	}
	
	public static void test4() throws SQLException {
		// QueryRunner是dbutils中的核心对象，
		// 实例这个对象需要一个连接池（数据源）
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		Object object = qr.query("select count(*) from user", new ScalarHandler());
		System.out.println(object);
	}

}
