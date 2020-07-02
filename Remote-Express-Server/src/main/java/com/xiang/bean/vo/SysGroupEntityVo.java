package com.xiang.bean.vo;
import java.util.Date;
import java.util.Date;
public class SysGroupEntityVo {

    /**
	* 组ID
	*/
    private Long id;
    /**
	* 已删除
	*/
    private Boolean del;
    /**
	* 
	*/
    private String alias;
    /**
	* 組類型，1用戶類，2電腦類
	*/
    private Integer typeflag;
    /**
	* 
	*/
    private String remark;
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
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public Integer getTypeflag() {
        return typeflag;
    }

    public void setTypeflag(Integer typeflag) {
        this.typeflag = typeflag;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
