package com.xiang.bean.vo;

import java.util.Date;

/**
* @author xiang
* @createDate 2018年12月20日 下午2:12:19
*/
public class UserVo extends BaseVo{
	private Long id;
	private String userName;
	private String nick;
	private Long groupId;
	private String groupname;
	private String lastLoginIp;
	private Boolean loginOutFlag;

    private Date lastLoginDate;
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
