package com.xiang.bean.vo;

import java.util.Date;

public class XUserPermissionToken {
	
	private String id;
	private String secretkey;
	private String company;
	private Date registerTime;
	private Date expireTime;
	private Integer userMaxNum;
	private String publishno;
	/**
	 * 0代表体验版，1代表正式版
	 */
	private Integer activeType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Integer getUserMaxNum() {
		return userMaxNum;
	}
	public void setUserMaxNum(Integer userMaxNum) {
		this.userMaxNum = userMaxNum;
	}
	public String getPublishno() {
		return publishno;
	}
	public void setPublishno(String publishno) {
		this.publishno = publishno;
	}
	public Integer getActiveType() {
		return activeType;
	}
	public void setActiveType(Integer activeType) {
		this.activeType = activeType;
	}
}