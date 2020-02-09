package com.restaurant.action;

import com.opensymphony.xwork2.ActionSupport;

public class MyAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		System.out.println("程序正在执行Action中的execute()方法！");
		return super.execute();	//返回success字符串
	}
	
	
}
