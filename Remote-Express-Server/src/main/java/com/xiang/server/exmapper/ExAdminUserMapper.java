package com.xiang.server.exmapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.xiang.bean.po.AdminUser;

public interface ExAdminUserMapper {
	@SqlParser(filter = true)
	@Select({
        "select",
        "user_name",
        "from admin_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
	public String getUserName(@Param("id") Long userId);
	
	@SqlParser(filter = true)
	@Select({
        "select",
        "id, del, user_name, password, nick, roles, gmt_modified,gmt_create",
        "from admin_user",
        "where user_name = #{userName}"
    })
	public AdminUser getUserByUserName(@Param("userName") String userName);
	
}
