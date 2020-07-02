package com.xiang.server.exmapper;

import org.apache.ibatis.annotations.Param;

public interface ExBaseMapper {
	public void setDel(@Param("table") String table,@Param("ids") Long[] ids,@Param("del")Boolean del);
	public void offLineUser(@Param("table") String table,@Param("ids") Long[] ids,@Param("outFlag")Boolean outFlag);
	public void setFlag(@Param("table") String table,@Param("field") String field,@Param("ids") Long[] ids,@Param("flag")Object flag);
	public Long getMax(@Param("table") String table,@Param("field") String field);
}
