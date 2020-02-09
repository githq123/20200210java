package com.qfedu.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//���ӳ�ʵ��
public class MyConnectionPool {
	//���ӳض�����listʵ�֣�ʹ��Collections.synchronizedList������list����̰߳�ȫ����
	private static List<Connection> pool=Collections.synchronizedList(new LinkedList<>());
	//��ʼ�����ӳ�
	static {
		//��ʼ��10������
		try {
			for (int i = 0; i < 10; i++) {
				Connection conn=DBConnectionUtil.getConnection();
				MyConnection myConn=new MyConnection(conn, pool);
				pool.add(pool.size(),myConn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new ExceptionInInitializerError("���ݿ����ӳس�ʼ���쳣");
		}
	}
	//��Ϊ���ӳ�
	//Ӧ������������
	//һ����ȡ����
	//һ���ǻ�����
	public Connection getConnection() {
		Connection conn=null;
		//���ж����ӳ��Ƿ�������
		if(pool.size()>0) {
			
			//�������ӵ�ʱ��ȡ����0������
			conn=pool.get(0);
			//�ѵ�0�����Ӵӳ����Ƴ�
			pool.remove(0);
			//���Ƴ����������
			return conn;
		}else {
			throw new RuntimeException("��������æ�����Ժ�����");
		}
	}
	
	//�黹����
	public void release(Connection conn) {
		pool.add(pool.size(),conn);
		System.out.println("�黹��һ������");
	}
	
	

}
