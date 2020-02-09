package com.qfedu.dbutils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.qfedu.bean.User;

public class DBUtils<T> {
	private Class<T> cls;
	public DBUtils(Class<T> cls) {
		this.cls=cls;
	}
	
	
	//ComboPooledDataSource���ǳ�c3p0���ӳصĺ�����
		//�������ӳر������ӳ��������ֳ�����Դ
		private static DataSource dataSoruce=new ComboPooledDataSource();
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
		
		public User getUser(String sql,Object...objs) {
			Connection conn=getConnection();
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				for (int i = 0; i < objs.length; i++) {
					ps.setObject(i+1, objs[i]);
				}
				
				ResultSet rs=ps.executeQuery();
				//��ѯ�Ľ����rs�����صĽ����ҪUser
				//���������Ҫ��rsת��Ϊuser
				if(rs.next()) {
					User user=new User();
					Class<User> cls=User.class;
					//��ȡ���е�User�ĳ�Ա
					Field [] fields = cls.getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						fields[i].setAccessible(true);
						String columnName=fields[i].getName();
						fields[i].set(user, rs.getObject(columnName));
					}
					return user;
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return null;
		}
		
		public T getBean(String sql,Object...objs) {
			Connection conn=getConnection();
			try {
				PreparedStatement ps=conn.prepareStatement(sql);
				for (int i = 0; i < objs.length; i++) {
					ps.setObject(i+1, objs[i]);
				}
				
				ResultSet rs=ps.executeQuery();
				//��ѯ�Ľ����rs�����صĽ����ҪUser
				//���������Ҫ��rsת��Ϊuser
				if(rs.next()) {
					//����clsָ�����Ŀղι��췽��
					T object=cls.newInstance();
					//��ȡ���е�User�ĳ�Ա
					Field [] fields = cls.getDeclaredFields();
					for (int i = 0; i < fields.length; i++) {
						fields[i].setAccessible(true);
						String columnName=fields[i].getName();
						fields[i].set(object, rs.getObject(columnName));
					}
					return object;
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return null;
		}
		
		
}
