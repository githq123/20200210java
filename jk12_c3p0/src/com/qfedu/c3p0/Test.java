package com.qfedu.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	public static void main(String[] args) {
		//java.sql.Connection;
		//com.mysql.jdbc.Connection
		
		// TODO Auto-generated method stub
		//使用C3P0连接池
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//从连接池中取出一个连接
		conn=C3P0Utils.getConnection();
		try {
			String sql="select * from user where id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, 1);
			rs=ps.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("username"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//放心关，c3p0也是装饰者设计模式，关闭=归还。
			C3P0Utils.closeAll(conn, ps, rs);
		}
		
	}	

}
