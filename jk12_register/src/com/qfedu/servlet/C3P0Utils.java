package com.qfedu.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	//ComboPooledDataSource���ǳ�c3p0���ӳصĺ�����
	//�������ӳر������ӳ��������ֳ�����Դ
	
	public void C3P0Utils() throws PropertyVetoException, SQLException {
	ComboPooledDataSource dataSource=new ComboPooledDataSource();
    dataSource.setUser("root");// �û�����
    dataSource.setPassword("1234");// �û�����
    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/jiangxi1?useUnicode=true&characterEncoding=utf-8");// MySQL���ݿ�����url
    dataSource.setDriverClass("com.mysql.jdbc.Driver");

    // pool���Ե���set��������Connection�ص�����

    // ���ӹر�֮���ڴ�ᱻ�ͷţ��´�ȡʱ�����¿�(�ڴ��ַ������)
    for (int i = 0; i < 20; i++) {
        Connection con = dataSource.getConnection();
        System.out.println(i + ":" + con);
        if (i % 2 == 0) {
            con.close();
        }
    }
}
	  public void C3p0PropertyDemo() throws SQLException {
	        ComboPooledDataSource dataSource = new ComboPooledDataSource();//�ղΣ��Զ���classpathĿ¼������ء�c3p0-config.xml�������ļ�---�����ļ��Ĵ洢λ�ú����Ʊ�������������ʹ�á�Ĭ�����á�
	        //ComboPooledDataSource pool = new ComboPooledDataSource("demo");//���ء�c3p0-config.xml���ļ��ж���ġ�demo���������Ԫ��
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
			throw new RuntimeException("������æ������");
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
