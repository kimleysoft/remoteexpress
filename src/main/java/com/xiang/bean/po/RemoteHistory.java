package com.xiang.bean.po;

import java.util.Date;

public class RemoteHistory {
    private Long id;

    private Boolean del;

    private Long userid;

    private Long remoteid;

    private String userName;

    private String account;

    private String remark;

    private String userip;

    private Date gmtModified;

    private Date gmtCreate;

    private Boolean connectBreakFlag;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Boolean getConnectBreakFlag() {
        return connectBreakFlag;
    }

    public void setConnectBreakFlag(Boolean connectBreakFlag) {
        this.connectBreakFlag = connectBreakFlag;
    }
}