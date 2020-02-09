package com.restaurant.action;

import javax.websocket.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.restaurant.entity.Users;

public class LoginAction extends ActionSupport {
	//第6章
//	private String loginName;
//	private String loginPwd;
//	public String getLoginName() {
//		return loginName;
//	}
//	public void setLoginName(String loginName) {
//		this.loginName = loginName;
//	}
//	public String getLoginPwd() {
//		return loginPwd;
//	}
//	public void setLoginPwd(String loginPwd) {
//		this.loginPwd = loginPwd;
//	}
//	@Override
//	public String execute() throws Exception {
//		if ("admin".equals(loginName) && "123".equals(loginPwd)) {
//			return SUCCESS;      //SUCCESS 代表的就是“success”字符串
//		}else{			
//			return "input";			
//		}
//	}	
	
	//7.2.4小节修改
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
			return SUCCESS;      //SUCCESS 代表的就是“success”字符串
		}else{			
			return "input";			
		}
	}	

	
}
