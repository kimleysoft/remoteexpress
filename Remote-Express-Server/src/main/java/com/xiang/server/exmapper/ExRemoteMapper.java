package com.xiang.server.exmapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiang.bean.bo.RemoteBo;
import com.xiang.bean.po.Remote;
import com.xiang.bean.vo.RemotePortGroupVo;

public interface ExRemoteMapper {
	
	int statisticsRemoteOnLine(RemoteBo bo);
	
	List<RemotePortGroupVo> groupbyPortCount(@Param("ids") Long[] ids);
	
	List<Remote> unRelatedRemote(@Param("remark")String remark, @Param("userid")Long userid, @Param("groupid")Long groupid);
	
	@Select("SELECT MAX(`port`)+1 FROM remote;")
	Integer maxPort();
	List<Integer> existPort(@Param("ports")List<Integer> ports);
	void batchAdd(@Param("records")List<Remote> data);
}
