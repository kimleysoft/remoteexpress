package com.xiang.server.mapper;

import com.xiang.bean.po.Remote;
import com.xiang.bean.po.RemoteExample;
import com.xiang.server.savemapper.SaveRemoteMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RemoteMapper extends SaveRemoteMapper {
    long countByExample(RemoteExample example);

    List<Remote> selectByExample(RemoteExample example);

    @Select({
        "select",
        "id, del, ip, port, client_type, remark, account, password, groupid, localip, ",
        "localport, gmt_modified, gmt_create, conne_break_flag",
        "from remote",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.xiang.server.mapper.RemoteMapper.BaseResultMap")
    Remote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Remote record, @Param("example") RemoteExample example);

    int updateByExample(@Param("record") Remote record, @Param("example") RemoteExample example);

    int updateByPrimaryKeySelective(Remote record);

    @Update({
        "update remote",
        "set del = #{del,jdbcType=BIT},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "port = #{port,jdbcType=INTEGER},",
          "client_type = #{clientType,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "account = #{account,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "groupid = #{groupid,jdbcType=BIGINT},",
          "localip = #{localip,jdbcType=VARCHAR},",
          "localport = #{localport,jdbcType=INTEGER},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "conne_break_flag = #{conneBreakFlag,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Remote record);
}