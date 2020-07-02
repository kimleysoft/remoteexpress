package com.xiang.bean.bo;

public class RemoteBo {

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
    /**
	* 
	*/
    private String password;
    /**
	* 
	*/
    private Long groupid;
    /**
	* 
	*/
    private String localip;
    /**
	* 
	*/
    private Integer localport;
    private Boolean conneBreakFlag;
    private String order_by;
	private Integer page;
	private Integer rows;
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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

	public Boolean getConneBreakFlag() {
		return conneBreakFlag;
	}

	public void setConneBreakFlag(Boolean conneBreakFlag) {
		this.conneBreakFlag = conneBreakFlag;
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
