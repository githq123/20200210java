package com.qfedu.test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//���������
		//����һ������������������ֵ��Ӧ����ȷ���ġ�
		
		//ʹ�÷��Ϳ���ʹ��
		//����һ������������������ֵ���ɵ����߾�����
		
		/*String str=new DBUtils<String>().get(String.class);
		System.out.println(str);*/
		/*
		 * User user=new DBUtils<User>().get(User.class); System.out.println(user);
		 */
		/*for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		for (int i = 0; i < 10; i++) {
			MyUser myUser=new MyUser();
			//start�ǿ���һ���̵߳���˼��
			new Thread(myUser).start();
		}
		
		System.out.println("main����");
		
	}
	//ʲô���߳�?
	//Javaʵ�ֶ��߳���Ҫ��������
	//Thread Runnable

	//run�������Ƕ��̣߳����߳̿�����ʱ�����еķ���
	static class MyUser implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("�߳̿�ʼ��");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("�߳���������");
		}
		
	} 
	
	
}
