package com.res.entity;

public class Powers {
	private Admin admin;
	private Functions f;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Functions getF() {
		return f;
	}

	public void setF(Functions f) {
		this.f = f;
	}

	@Override
	public String toString() {
		return "Powers [admin=" + admin + ", f=" + f + "]";
	}

}
