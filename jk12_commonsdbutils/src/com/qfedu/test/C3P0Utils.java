package com.qfedu.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	//ComboPooledDataSource就是出c3p0连接池的核心类
	//就是连接池本身，连接池在这里又称数据源
	private static DataSource dataSoruce=new ComboPooledDataSource();
	public static DataSource getDataSource() {
		return dataSoruce;
	}
	
	public static Connection getConnection() {
		try {
			return dataSoruce.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException("服务器忙。。。");
		}
		
	}
	
	public static void closeAll(Connection conn,Statement s,ResultSet rs) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(s!=null) {
			try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
