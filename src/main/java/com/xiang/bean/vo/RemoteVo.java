package com.xiang.bean.vo;
import java.util.Date;
public class RemoteVo {

    /**
	* 桌面ID
	*/
    private Long id;
    /**
	* 已删除
	*/
    private Boolean del;
    /**
	* 
	*/
    private String ip;
    /**
	* 
	*/
    private Integer port;
    
    private Integer clientType;
    /**
	* 備註
	*/
    private String remark;
    /**
	* 
	*/
    private String account;
    private String password;
    /**
	* 
	*/
    private Long groupid;

	private String groupname;
    /**
	* 
	*/
    private String localip;
    /**
	* 
	*/
    private Integer localport;
    /**
	* 更新时间
	*/
    private Date gmtModified;
    /**
	* 添加时间
	*/
    private Date gmtCreate;
    private Boolean conneBreakFlag;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
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
    public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
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
    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }
    public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
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
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    
    

	public Boolean getConneBreakFlag() {
		return conneBreakFlag;
	}

	public void setConneBreakFlag(Boolean conneBreakFlag) {
		this.conneBreakFlag = conneBreakFlag;
	}

	/**
     * 页面组件使用key
     * @return
     */
    public String getVid() {
    	if(this.getId()!=null) {
    		return this.getId().toString();
    	}
    	return null;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
