package com.qfedu.test;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//正常情况下
		//调用一个方法，参数、返回值都应该是确定的。
		
		//使用泛型可以使得
		//调用一个方法，参数、返回值都由调用者决定。
		
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
			//start是开启一个线程的意思。
			new Thread(myUser).start();
		}
		
		System.out.println("main方法");
		
	}
	//什么是线程?
	//Java实现多线程需要两个核心
	//Thread Runnable

	//run方法就是多线程，当线程开启的时候运行的方法
	static class MyUser implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("线程开始了");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("线程运行完了");
		}
		
	} 
	
	
}
