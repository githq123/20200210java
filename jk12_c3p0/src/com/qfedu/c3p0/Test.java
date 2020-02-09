package com.qfedu.c3p0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {

	public static void main(String[] args) {
		//java.sql.Connection;
		//com.mysql.jdbc.Connection
		
		// TODO Auto-generated method stub
		//ʹ��C3P0���ӳ�
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//�����ӳ���ȡ��һ������
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
			//���Ĺأ�c3p0Ҳ��װ�������ģʽ���ر�=�黹��
			C3P0Utils.closeAll(conn, ps, rs);
		}
		
	}	

}
