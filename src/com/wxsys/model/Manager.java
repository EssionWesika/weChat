package com.wxsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Manager", catalog = "wechat")
public class Manager extends BaseEntity{

	private static final long serialVersionUID = -3873392762861641950L;

	private String account;//本平台操作用户账号
	private String password;//本平台操作用户密码
	private Long loginTime;//最后登录时间
	private Integer authority;//0,1,2 权限字段 0为最高权限，全场唯一，1为一级权限，2为二级权限
	private String hasAppID;//该用户所持有并管理的微信AppId
	private String hasModelList;//该用户被赋予操作模块的权限
	private String language;//设置语言偏好
	
	
	@Column(name = "language")
	public String getLanguage() {
		return language;
	}
	@Column(name = "hasModelList")
	public String getHasModelList() {
		return hasModelList;
	}
	@Column(name = "account")
	public String getAccount() {
		return account;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	@Column(name = "login_time")
	public Long getLoginTime() {
		return loginTime;
	}
	@Column(name = "authority")
	public Integer getAuthority() {
		return authority;
	}
	@Column(name = "has_appid")
	public String getHasAppID() {
		return hasAppID;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setHasModelList(String hasModelList) {
		this.hasModelList = hasModelList;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	public void setHasAppID(String hasAppID) {
		this.hasAppID = hasAppID;
	}
	public Manager() {
		super();
	}
	public Manager(String account, String password, Long loginTime,
			Integer authority, String hasAppID, String hasModelList) {
		super();
		this.account = account;
		this.password = password;
		this.loginTime = loginTime;
		this.authority = authority;
		this.hasAppID = hasAppID;
		this.hasModelList = hasModelList;
	}
	@Override
	public String toString() {
		return "Manager [account=" + account + ", password=" + password
				+ ", loginTime=" + loginTime + ", authority=" + authority
				+ ", hasAppID=" + hasAppID + ", hasModelList=" + hasModelList
				+ "]";
	}
}
