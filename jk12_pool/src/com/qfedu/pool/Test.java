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
		//����5��֮���Ƿ����ǾͰ����ӹ黹���أ�
		try {
			Thread.sleep(5000);
			System.out.println("5��֮���ˡ�");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ʱ ���������û�ȥ�������������ӣ��ͻ�ɹ��ġ�
		new Thread(new MyUser()).start();
		
	}

	//java���̷߳�ʽ
	static class MyUser implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			MyConnectionPool mcp=new MyConnectionPool();
			Connection conn=null;
			PreparedStatement ps=null;
			//ȥ����ȡ��һ������
			conn=mcp.getConnection();
			try {
				if(conn==null) {
					System.out.println("��ȡ��������null");
					return ;
				}
				//��ȡ��������
				//ģ���û�����Ϊ������ռ����һ��ʱ��
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
				//�黹����
				//mcp.release(conn);
				try {
					if(conn!=null) {
						//�����Ͼ��ǹ黹
					conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//����ôд������ر����ӣ��������ӳؾͰ�æ���ˡ�
				//DBConnectionUtil.closeAll(conn, ps, rs);
			}
		}
		
	}
}
