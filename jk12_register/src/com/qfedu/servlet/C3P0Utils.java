package com.qfedu.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	//ComboPooledDataSource就是出c3p0连接池的核心类
	//就是连接池本身，连接池在这里又称数据源
	
	public void C3P0Utils() throws PropertyVetoException, SQLException {
	ComboPooledDataSource dataSource=new ComboPooledDataSource();
    dataSource.setUser("root");// 用户姓名
    dataSource.setPassword("1234");// 用户密码
    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jiangxi1?useUnicode=true&characterEncoding=utf-8");// MySQL数据库连接url
    dataSource.setDriverClass("com.mysql.jdbc.Driver");

    // pool可以调用set方法进行Connection池的配置

    // 连接关闭之后，内存会被释放，下次取时会重新开(内存地址不共用)
    for (int i = 0; i < 20; i++) {
        Connection con = dataSource.getConnection();
        System.out.println(i + ":" + con);
        if (i % 2 == 0) {
            con.close();
        }
    }
}
	  public void C3p0PropertyDemo() throws SQLException {
	        ComboPooledDataSource dataSource = new ComboPooledDataSource();//空参，自动到classpath目录下面加载“c3p0-config.xml”配置文件---配置文件的存储位置和名称必须是这样，且使用“默认配置”
	        //ComboPooledDataSource pool = new ComboPooledDataSource("demo");//加载“c3p0-config.xml”文件中定义的“demo”这个配置元素
	         for(int i=0;i<25;i++){
	               Connection con = dataSource.getConnection();
	               System.out.println(i+":"+ con.hashCode());
	           }
	    }
	 
	}
	

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
