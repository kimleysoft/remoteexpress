package com.xiang.server.mapper;

import com.xiang.bean.po.LoginHistory;
import com.xiang.bean.po.LoginHistoryExample;
import com.xiang.server.savemapper.SaveLoginHistoryMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LoginHistoryMapper extends SaveLoginHistoryMapper {
    long countByExample(LoginHistoryExample example);

    List<LoginHistory> selectByExample(LoginHistoryExample example);

    @Select({
        "select",
        "id, del, userid, nick, user_name, loginip, gmt_modified, gmt_create, ",
        "operating_system, ref_type",
        "from login_history",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.xiang.server.mapper.LoginHistoryMapper.BaseResultMap")
    LoginHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LoginHistory record, @Param("example") LoginHistoryExample example);

    int updateByExample(@Param("record") LoginHistory record, @Param("example") LoginHistoryExample example);

    int updateByPrimaryKeySelective(LoginHistory record);

    @Update({
        "update login_history",
        "set del = #{del,jdbcType=BIT},",
          "userid = #{userid,jdbcType=BIGINT},",
          "nick = #{nick,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "loginip = #{loginip,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "operating_system = #{operatingSystem,jdbcType=VARCHAR},",
          "ref_type = #{refType,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(LoginHistory record);
}