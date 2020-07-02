package com.xiang.bean.vo;

import java.util.Date;

public class UserRemoteRelationVo {

	private Long userid;
	private Long remoteid;
	private Long groupid;
	private String userName;
	private String remark;
	private String account;
	private String nick;
	private String ip;
	private Integer port;
	private Date lastlogindate;
	private String lastloginip;
	private String localip;
	private Integer localport;
	private Integer clientType;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getRemoteid() {
		return remoteid;
	}
	public void setRemoteid(Long remoteid) {
		this.remoteid = remoteid;
	}
	public Long getGroupid() {
		return groupid;
	}
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Date getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public String getLocalip() {
		return localip;
	}
	public void setLocalip(String localip) {
		this.localip = localip;
	}
	public Integer getLocalport() {
		return localport;
	}
	public void setLocalport(Integer localport) {
		this.localport = localport;
	}
	
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public static UserRemoteRelationVo convert(UserVo uv, RemoteVo rv) {
		UserRemoteRelationVo vo = new UserRemoteRelationVo();
		vo.setAccount(rv.getAccount());
		vo.setGroupid(rv.getGroupid());
		vo.setIp(rv.getIp());
		vo.setLastlogindate(uv.getLastLoginDate());
		vo.setLastloginip(uv.getLastLoginIp());
		vo.setLocalip(rv.getLocalip());
		vo.setLocalport(rv.getLocalport());
		vo.setNick(uv.getNick());
		vo.setPort(rv.getPort());
		vo.setRemark(rv.getRemark());
		vo.setRemoteid(rv.getId());
		vo.setUserid(uv.getId());
		vo.setUserName(uv.getUserName());
		vo.setClientType(rv.getClientType());
		return vo;
	}
}
