package com.xiang.bean.bo;

import java.util.Date;
import java.util.List;

public class UserUpdateBo{
	private String nick;
	private List<Long> roleIds;
	private Long id;
	private String userName;
	private String password;
	private String lastLoginIp;

	private Date lastLoginDate;
	private Long groupId;
	private Boolean loginOutFlag;
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public List<Long> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	public Boolean getLoginOutFlag() {
		return loginOutFlag;
	}
	public void setLoginOutFlag(Boolean loginOutFlag) {
		this.loginOutFlag = loginOutFlag;
	}
	
}
