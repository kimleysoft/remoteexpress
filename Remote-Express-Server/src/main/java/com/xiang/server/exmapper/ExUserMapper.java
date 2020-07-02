package com.xiang.server.exmapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.xiang.bean.bo.UserBo;
import com.xiang.bean.po.User;
import com.xiang.bean.po.UserExample;
import com.xiang.restserver.Page;

public interface ExUserMapper {
	@SqlParser(filter = true)
	@Select({
        "select",
        "id, del, add_time, user_name, password, nick, roles, gmt_modified, ",
        "gmt_create",
        "from user",
        "where user_name = #{userName} AND del = 0;"
    })
	public User getUserByUserName(@Param("userName") String userName);
	public List<User> getList(@Param("example") UserExample example,@Param("page")Page page);
	@SqlParser(filter = true)
	@Select({
        "select",
        "user_name",
        "from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
	public String getUserName(@Param("id") Long userId);
	
	int statisticsUserOnLine(UserBo bo);
	public List<String> isExistsUsers(@Param("unames")List<String> usernames);
	public void batchSave(@Param("users")List<User> users);
	
	@Select({
		"select count(*) from user u",
		"LEFT JOIN user_remote ur ON u.id = ur.userid",
		"WHERE u.last_login_ip = #{ip,jdbcType=VARCHAR} ",
		"AND login_out_flag = 0 AND ur.remoteid = #{remoteid,jdbcType=BIGINT}"
	})
	public int CountDuplicateRelate(@Param("ip") String ip, @Param("remoteid") Long remoteid);
}
