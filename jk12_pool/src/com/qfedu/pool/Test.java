package com.qfedu.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 11; i++) {
			new Thread(new MyUser()).start();
		}
		//假如5秒之后，是否它们就把连接归还了呢？
		try {
			Thread.sleep(5000);
			System.out.println("5秒之后了。");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//此时 若是再有用户去连接中请求连接，就会成功的。
		new Thread(new MyUser()).start();
		
	}

	//java多线程方式
	static class MyUser implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			MyConnectionPool mcp=new MyConnectionPool();
			Connection conn=null;
			PreparedStatement ps=null;
			//去池中取出一个连接
			conn=mcp.getConnection();
			try {
				if(conn==null) {
					System.out.println("获取的连接是null");
					return ;
				}
				//获取了连接了
				//模拟用户的行为，假设占用了一段时间
				Thread.sleep(new Random().nextInt(3000));
				String sql="select * from user where id=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, 1);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
					System.out.println(rs.getString("username"));
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				//归还连接
				//mcp.release(conn);
				try {
					if(conn!=null) {
						//本质上就是归还
					conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//别这么写，如果关闭连接，我们连接池就白忙活了。
				//DBConnectionUtil.closeAll(conn, ps, rs);
			}
		}
		
	}
}
