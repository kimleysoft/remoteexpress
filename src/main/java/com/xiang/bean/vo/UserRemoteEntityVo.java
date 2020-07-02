package com.xiang.bean.vo;
import java.util.Date;
import java.util.Date;
public class UserRemoteEntityVo {

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
    /**
	* 更新时间
	*/
    private Date gmtModified;
    /**
	* 添加时间
	*/
    private Date gmtCreate;
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
}
