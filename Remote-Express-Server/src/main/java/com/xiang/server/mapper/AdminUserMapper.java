package com.xiang.server.mapper;

import com.xiang.bean.po.AdminUser;
import com.xiang.bean.po.AdminUserExample;
import com.xiang.server.savemapper.SaveAdminUserMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminUserMapper extends SaveAdminUserMapper {
    long countByExample(AdminUserExample example);

    List<AdminUser> selectByExample(AdminUserExample example);

    @Select({
        "select",
        "id, del, user_name, password, nick, roles, gmt_modified, gmt_create ",
        "from admin_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.xiang.server.mapper.AdminUserMapper.BaseResultMap")
    AdminUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByExample(@Param("record") AdminUser record, @Param("example") AdminUserExample example);

    int updateByPrimaryKeySelective(AdminUser record);

    @Update({
        "update admin_user",
        "set del = #{del,jdbcType=BIT},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "nick = #{nick,jdbcType=VARCHAR},",
          "roles = #{roles,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(AdminUser record);
}