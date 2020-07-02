package com.xiang.bean.bo;
import java.util.Date;
import java.util.Date;
import java.util.Date;
public class AdminUserBo {

    /**
	* 用户ID
	*/
    private Long id;
    /**
	* 已删除
	*/
    private Boolean del;
    /**
	* 添加时间
	*/
    private Date addTime;
    /**
	* 登录账号
	*/
    private String userName;
    /**
	* 登录密码
	*/
    private String password;
    /**
	* 昵称
	*/
    private String nick;
    /**
	* 角色，支持多个(admin,user)
	*/
    private String roles;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
