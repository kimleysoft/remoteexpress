package com.xiang.bean.bo;

public class UserRemoteBo {

    private Long id;
    private Boolean del;
    private Long userid;
    private Long remoteid;
    private Long[] remoteids;
    
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
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

	public Long[] getRemoteids() {
		return remoteids;
	}

	public void setRemoteids(Long[] remoteids) {
		this.remoteids = remoteids;
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

	public Long getRemoteid() {
		return remoteid;
	}

	public void setRemoteid(Long remoteid) {
		this.remoteid = remoteid;
	}
    
}
