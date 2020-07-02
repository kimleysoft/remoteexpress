package com.xiang.server.exmapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiang.bean.po.UserRemote;
import com.xiang.bean.bo.UserRemoteBo;
import com.xiang.bean.vo.UserRemoteVo;

public interface ExUserRemoteMapper {
	
	@Select("SELECT DISTINCT r.`port` FROM remote r LEFT JOIN user_remote ur ON ur.remoteid = r.id WHERE ur.userid = #{userid,jdbcType=BIGINT} AND r.del = 0 AND ur.del =0;")
	List<Integer> selectPortsByUserid(@Param("userid")Long userid);
	List<UserRemoteVo> selectByConditionDO(UserRemoteBo bo);
	public int batchSave(@Param("records")List<UserRemote> records);
}
