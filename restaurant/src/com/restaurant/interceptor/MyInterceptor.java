package com.restaurant.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("自定义拦截器开始运行！");
		long startTime=System.currentTimeMillis();
		System.out.println("开始时间为："+startTime);
		String result=invocation.invoke();
		System.out.println("自定义拦截器已经结束！");
		long endTime=System.currentTimeMillis();
		System.out.println("结束时间为："+endTime);
		System.out.println("程序执行花费了："+(endTime-startTime));
		return result;
	}

}
