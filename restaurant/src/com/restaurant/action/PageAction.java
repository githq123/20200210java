package com.restaurant.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport {
	private String pageName;

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	@Override
	public String execute() throws Exception {
		ActionContext.getContext().put("info", "您已经成功转向到"+pageName+".jsp页面！");
		return super.execute();
	}
	
	
}
