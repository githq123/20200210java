package com.res.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "admin", catalog = "restrant")
public class Admin {
	// 基本属性
	private int id;
	private String loginName;
	private String loginPwd;

	// 关联属性
	private Set<Functions> fs = new HashSet<Functions>();

	//配置Admin到Functions的多对多关联
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "powers", joinColumns = { @JoinColumn(name = "aid") }, inverseJoinColumns = { @JoinColumn(name = "fid") })
	public Set<Functions> getFs() {
		return fs;
	}

	public void setFs(Set<Functions> fs) {
		this.fs = fs;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Admin(String loginName, String loginPwd) {
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}

	public Admin() {
	}

}
