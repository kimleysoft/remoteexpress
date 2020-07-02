package com.xiang.server.mapper;

import com.xiang.bean.po.User;
import com.xiang.bean.po.UserExample;
import com.xiang.server.savemapper.SaveUserMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends SaveUserMapper {
    long countByExample(UserExample example);

    List<User> selectByExample(UserExample example);

    @Select({
        "select",
        "id, del, user_name, password, nick, gmt_modified, ",
        "gmt_create, group_id, last_login_ip, last_login_date, login_out_flag",
        "from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.xiang.server.mapper.UserMapper.BaseResultMap")
    User selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set del = #{del,jdbcType=BIT},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "nick = #{nick,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
          "group_id = #{groupId,jdbcType=BIGINT},",
          "last_login_ip = #{lastLoginIp,jdbcType=VARCHAR},",
          "last_login_date = #{lastLoginDate,jdbcType=TIMESTAMP},",
          "login_out_flag = #{loginOutFlag,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);
}