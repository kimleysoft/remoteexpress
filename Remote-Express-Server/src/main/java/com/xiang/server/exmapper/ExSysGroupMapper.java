package com.xiang.server.exmapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.xiang.bean.po.SysGroup;

public interface ExSysGroupMapper {
	public int batchSave(@Param("records")List<SysGroup> records);
	 @Select({
	    	"select",
	    	"id, del, alias, typeflag, remark, gmt_modified, gmt_create",
	    	"from sys_group",
	    	"where alias = #{alias,jdbcType=VARCHAR}"
	    })
	    @ResultMap("com.xiang.server.mapper.SysGroupMapper.BaseResultMap")
	    SysGroup selectByAlias(String alias);
}
