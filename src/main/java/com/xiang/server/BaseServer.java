package com.xiang.server;

public interface BaseServer {
	public void setDelById(String table,Long[] ids, Boolean del);
	public void setoffLineUser(String table,Long[] ids, Boolean outFlag);
	public void setFlag(String table,String field,Long[] ids, Object flag);
	public Long getMax(String table,String field);
	public <T> T copyModel(Object po, Class<T> cls, String... ignoreProperties);
	

}
