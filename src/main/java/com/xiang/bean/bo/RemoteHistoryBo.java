package com.xiang.bean.bo;
public class RemoteHistoryBo {

    /**
	* ID
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
    private Long tenantid;
    /**
	* 
	*/
    private String userip;
    
    private Long userId;
    private Boolean userDel;
    private Long remoteGroupId;
    private String remoteip;
    private String userName;
    private String userNick;
    private String userRoles;
    private Long userTenantId;
    private Long groupId;
    private Boolean remotedel;
    private String remoteaccount;
    private String remotelocalip;
    private String remotelocalport;
    private String remotepassword;
    private String remoteport;
    private String remoteremark;
    
    private Integer page;
    private Integer rows;
    private String order_by;
    
    
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getUserDel() {
		return userDel;
	}

	public void setUserDel(Boolean userDel) {
		this.userDel = userDel;
	}

	public Long getRemoteGroupId() {
		return remoteGroupId;
	}

	public void setRemoteGroupId(Long remoteGroupId) {
		this.remoteGroupId = remoteGroupId;
	}

	public String getRemoteip() {
		return remoteip;
	}

	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public Long getUserTenantId() {
		return userTenantId;
	}

	public void setUserTenantId(Long userTenantId) {
		this.userTenantId = userTenantId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Boolean getRemotedel() {
		return remotedel;
	}

	public void setRemotedel(Boolean remotedel) {
		this.remotedel = remotedel;
	}

	public String getRemoteaccount() {
		return remoteaccount;
	}

	public void setRemoteaccount(String remoteaccount) {
		this.remoteaccount = remoteaccount;
	}

	public String getRemotelocalip() {
		return remotelocalip;
	}

	public void setRemotelocalip(String remotelocalip) {
		this.remotelocalip = remotelocalip;
	}

	public String getRemotelocalport() {
		return remotelocalport;
	}

	public void setRemotelocalport(String remotelocalport) {
		this.remotelocalport = remotelocalport;
	}

	public String getRemotepassword() {
		return remotepassword;
	}

	public void setRemotepassword(String remotepassword) {
		this.remotepassword = remotepassword;
	}

	public String getRemoteport() {
		return remoteport;
	}

	public void setRemoteport(String remoteport) {
		this.remoteport = remoteport;
	}

	public String getRemoteremark() {
		return remoteremark;
	}

	public void setRemoteremark(String remoteremark) {
		this.remoteremark = remoteremark;
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

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public Long getTenantid() {
		return tenantid;
	}

	public void setTenantid(Long tenantid) {
		this.tenantid = tenantid;
	}
	
}
