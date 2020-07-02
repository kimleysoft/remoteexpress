package com.xiang.server.mapper;

import com.xiang.bean.po.RemoteHistory;
import com.xiang.bean.po.RemoteHistoryExample;
import com.xiang.server.savemapper.SaveRemoteHistoryMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RemoteHistoryMapper extends SaveRemoteHistoryMapper {
    long countByExample(RemoteHistoryExample example);

    List<RemoteHistory> selectByExample(RemoteHistoryExample example);

    @Select({
        "select",
        "id, del, userid, remoteid, user_name, account, remark, userip, gmt_modified, ",
        "gmt_create, connect_break_flag",
        "from remote_history",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.xiang.server.mapper.RemoteHistoryMapper.BaseResultMap")
    RemoteHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RemoteHistory record, @Param("example") RemoteHistoryExample example);

    int updateByExample(@Param("record") RemoteHistory record, @Param("example") RemoteHistoryExample example);

    int updateByPrimaryKeySelective(RemoteHistory record);

    @Update({
        "update remote_history",
        "set del = #{del,jdbcType=BIT},",
          "userid = #{userid,jdbcType=BIGINT},",
          "remoteid = #{remoteid,jdbcType=BIGINT},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "account = #{account,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "userip = #{userip,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "connect_break_flag = #{connectBreakFlag,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(RemoteHistory record);
}