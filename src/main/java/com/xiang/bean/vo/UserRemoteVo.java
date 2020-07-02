package com.xiang.bean.vo;
import java.util.Date;
public class UserRemoteVo {

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
    private Long userid;
    /**
	* 
	*/
    private Long remoteid;
    private String userip;
    /**
	* 更新时间
	*/
    private Date gmtModified;
    /**
	* 添加时间
	*/
    private Date gmtCreate;
    
    private RemoteVo remote;
    /**
	* 
	*/
    private String ip;
    /**
	* 
	*/
    private Integer port;
    /**
	* 備註
	*/
    private String remark;
    /**
	* 
	*/
    private String account;

    /**
	* 
	*/
    private Long groupid;
    /**
	* 
	*/
    private String localip;
    private Date lastlogindate;
    private String lastloginip;
    private String nick;
    private String userName;
    /**
	* 
	*/
    private Integer localport;
    /**
	* 更新时间
	*/
    private Date remotegmtModified;
    /**
	* 添加时间
	*/
    private Date remotegmtCreate;
    
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
    public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
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
    

	public RemoteVo getRemote() {
		return remote;
	}

	public void setRemote(RemoteVo remote) {
		this.remote = remote;
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

	public Date getRemotegmtModified() {
		return remotegmtModified;
	}

	public void setRemotegmtModified(Date remotegmtModified) {
		this.remotegmtModified = remotegmtModified;
	}

	public Date getRemotegmtCreate() {
		return remotegmtCreate;
	}

	public void setRemotegmtCreate(Date remotegmtCreate) {
		this.remotegmtCreate = remotegmtCreate;
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
}
