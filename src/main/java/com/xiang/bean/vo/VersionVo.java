package com.xiang.bean.vo;

public class VersionVo {

	private String version;
	private String cversion;
	private Boolean updateflag;
	
	public String getVersion() {
		return version;
	}
	public String getCversion() {
		return cversion;
	}
	public void setCversion(String cversion) {
		this.cversion = cversion;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Boolean getUpdateflag() {
		return updateflag;
	}
	public void setUpdateflag(Boolean updateflag) {
		this.updateflag = updateflag;
	}
	
}
