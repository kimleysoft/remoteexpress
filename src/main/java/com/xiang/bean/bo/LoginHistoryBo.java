package com.xiang.bean.bo;
public class LoginHistoryBo {

    /**
	* ID
	*/
    private Long id;
    /**
	* 已删除
	*/
    private Boolean del;
    /**
	* 
	*/
    private Long userid;
    /**
	* 
	*/
    private String loginip;
    /**
     * 登陸賬號
     */
    private String userName;
    /**
     * 備註/暱稱/姓名
     */
    private String nick;
    
    
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
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
    
}
