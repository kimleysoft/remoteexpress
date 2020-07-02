package com.xiang.bean.vo;

import java.util.Date;

public class WrapperBaseVo {
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 已删除
	 */
	private Boolean del;
	/**
	 * 添加时间
	 */
	private Date gmtCreate;
	/**
	 * 最后修改时间
	 */
	private Date gmtModified;
	/**
	 * 状态
	 */
	private Integer apply;

	private Integer refType;

	private Long refId;

	public Integer getApply() {
		return apply;
	}

	public void setApply(Integer apply) {
		this.apply = apply;
	}

	public Integer getRefType() {
		return refType;
	}

	public void setRefType(Integer refType) {
		this.refType = refType;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

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

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
