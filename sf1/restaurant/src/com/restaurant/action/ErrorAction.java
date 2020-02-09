package com.restaurant.action;

import com.opensymphony.xwork2.ActionSupport;

public class ErrorAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		this.addActionError("ActionError错误信息1");
		this.addActionError("ActionError错误信息2");
		this.addActionMessage("ActionMessage普通信息1");
		this.addActionMessage("ActionMessage普通信息2");
		this.addFieldError("fielderror1", "字段错误信息1");
		this.addFieldError("fielderror2", "字段错误信息2");
		return "success";
	}
	
	
}
