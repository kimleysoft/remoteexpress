package com.xiang.service;

import java.util.List;
import java.util.Map;

import com.xiang.restserver.Page;

public interface BaseService<T> {
	public Page getPage(Map<String, Object> querys);
	public Long getCount(Map<String, Object> querys);
	public List<T> getList(Map<String, Object> querys);
	public void setDelById(String table,Long[] ids,Boolean del);
	public void setoffLineUser(String table,Long[] ids,Boolean outFlag);
	public void setDelById(String table, List<Long> ids, Boolean del);
	public void setFlag(String table,String field,Long[] ids,Object flag);
	public void setCriteria(Object criteria,Map<String, Object> querys);
	public void setPage(Page page);
	public Long getMax(String table,String field);
	public T getSingle(Map<String, Object> querys);
	
}
