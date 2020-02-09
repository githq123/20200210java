package com.restaurant.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.restaurant.entity.Users;

public class MessageAction extends ActionSupport implements ServletRequestAware {

	private Users user;	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	@Override
	public String execute() throws Exception {
		ActionContext ac=ActionContext.getContext();
		Map session=ac.getSession();
		if ("admin".equals(user.getLoginName()) && "123".equals(user.getLoginPwd())) {
			session.put("LOGIN_USER", user);
			ac.put("success", "登录成功，通过ActionContext类访问Servlet API！");
			request.setAttribute("messageAware","您好，通过xxxAware接口访问Servlet API");
			return "success";
		}else{			
			ac.put("error", "用户名和密码错误，登录失败！");
			ServletActionContext.getRequest().setAttribute("messageSAC", "您好，通过ServletActionContext类直接访问Servlet API");
			return "error";
		}
	}
	
	

}
