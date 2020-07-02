package com.xiang.bean.bo;

import java.util.Date;

import com.xiang.bean.vo.UserVo;

public class UserBo extends UserVo{
	private String password;
	
	private String lastLoginIp;
	private String operatingSystem;
	private Boolean loginOutFlag;
	private Date lastLoginDate;
	private String order_by;
	private Integer page;
	private Integer rows;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public Boolean getLoginOutFlag() {
		return loginOutFlag;
	}

	public void setLoginOutFlag(Boolean loginOutFlag) {
		this.loginOutFlag = loginOutFlag;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
