package com.restaurant.action;

import com.opensymphony.xwork2.ActionSupport;
import com.restaurant.dao.UserDAO;
import com.restaurant.dao.impl.UserDAOImpl;
import com.restaurant.entity.Users;

public class RegisterAction extends ActionSupport {
	
	private Users user;
	private String repassword;	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	@Override
	public String execute() throws Exception {
		UserDAO userDAO=new UserDAOImpl();
		int result=0;
		if (user.getLoginName()!=null && user.getLoginPwd()!=null && user.getLoginPwd().equals(repassword)) {
			result=userDAO.addUsers(user);			
		}
		String back;
		if (result!=0) {
			back="success";
		}else{
			back="input";
		}
		return back;		
	}
	//服务器端的验证
	@Override
	public void validate() {
		if (user.getLoginName()==null || "".equals(user.getLoginName().trim())) {
			this.addFieldError("loginName", "用户名不能为空！");
		}
		if (user.getLoginPwd()==null || "".equals(user.getLoginPwd().trim())) {
			this.addFieldError("loginPwd", "密码不能为空！");
		}
		if (!user.getLoginPwd().equals(repassword)) {
			this.addFieldError("repassword", "密码和确认密码不一致!");
		}
		//省略其他字段的校验
		
		//super.validate();
	}
	
	
	
}
