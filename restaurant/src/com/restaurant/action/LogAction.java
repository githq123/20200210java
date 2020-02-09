package com.restaurant.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.restaurant.entity.Users;

public class LogAction extends ActionSupport {
	private Users user;	
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}	
	@Override
	public String execute() throws Exception {
		if ("admin".equals(user.getLoginName()) && "123".equals(user.getLoginPwd())) {
			Map<String, Object> session=null;
			ActionContext ac=ActionContext.getContext();
			session=ac.getSession();
			session.put("user", user);
			return SUCCESS;      //SUCCESS 代表的就是“success”字符串
		}else{			
			return "login";			
		}
	}
}
