package com.restaurant.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.restaurant.entity.Users;

public class AuthorityInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session=invocation.getInvocationContext().getSession();
		if (session==null) {
			return "login";
		}
		else{
			Users user=(Users)session.get("user");
			if(user==null){
				return "login";
			}else{
				return invocation.invoke();
			}
		}
	}

}
