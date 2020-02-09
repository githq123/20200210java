package com.qfedu.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//连接池实现
public class MyConnectionPool {
	//连接池对象用list实现，使用Collections.synchronizedList处理集合list类的线程安全问题
	private static List<Connection> pool=Collections.synchronizedList(new LinkedList<>());
	//初始化连接池
	static {
		//初始化10个连接
		try {
			for (int i = 0; i < 10; i++) {
				Connection conn=DBConnectionUtil.getConnection();
				MyConnection myConn=new MyConnection(conn, pool);
				pool.add(pool.size(),myConn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new ExceptionInInitializerError("数据库连接池初始化异常");
		}
	}
	//作为连接池
	//应该有两个方法
	//一个是取连接
	//一个是还连接
	public Connection getConnection() {
		Connection conn=null;
		//先判断连接池是否有连接
		if(pool.size()>0) {
			
			//当有连接的时候，取出第0个连接
			conn=pool.get(0);
			//把第0个连接从池中移除
			pool.remove(0);
			//把移除的这个返回
			return conn;
		}else {
			throw new RuntimeException("服务器繁忙，请稍后再试");
		}
	}
	
	//归还连接
	public void release(Connection conn) {
		pool.add(pool.size(),conn);
		System.out.println("归还了一个连接");
	}
	
	

}
