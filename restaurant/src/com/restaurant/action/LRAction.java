package com.restaurant.action;

import com.opensymphony.xwork2.ActionSupport;
import com.restaurant.entity.Users;

public class LRAction extends ActionSupport {
	private Users user;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public String login(){
		if ("admin".equals(user.getLoginName()) && "123".equals(user.getLoginPwd())) {
			return SUCCESS;      //SUCCESS 代表的就是“success”字符串
		}else{			
			return "error";			
		}
	}
	public String register(){
		return "register";
	}
}
