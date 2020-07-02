package com.xiang.server.mapper;

import com.xiang.bean.po.UserRemote;
import com.xiang.bean.po.UserRemoteExample;
import com.xiang.server.savemapper.SaveUserRemoteMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserRemoteMapper extends SaveUserRemoteMapper {
    long countByExample(UserRemoteExample example);

    List<UserRemote> selectByExample(UserRemoteExample example);

    @Select({
        "select",
        "id, del, userid, remoteid, user_name, account, password, remark, userip, ",
        "gmt_modified, gmt_create",
        "from user_remote",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.xiang.server.mapper.UserRemoteMapper.BaseResultMap")
    UserRemote selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRemote record, @Param("example") UserRemoteExample example);

    int updateByExample(@Param("record") UserRemote record, @Param("example") UserRemoteExample example);

    int updateByPrimaryKeySelective(UserRemote record);

    @Update({
        "update user_remote",
        "set del = #{del,jdbcType=BIT},",
          "userid = #{userid,jdbcType=BIGINT},",
          "remoteid = #{remoteid,jdbcType=BIGINT},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "account = #{account,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "userip = #{userip,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserRemote record);
}