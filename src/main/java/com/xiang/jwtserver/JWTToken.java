package com.xiang.jwtserver;

import java.util.Map;

import org.apache.shiro.authc.HostAuthenticationToken;

import com.auth0.jwt.impl.PublicClaims;

/**
 * @author xiang
 * @createDate 2018年12月20日 下午2:41:25
 */
public class JWTToken implements HostAuthenticationToken {
	private static final long serialVersionUID = 1L;
	private String token;
    private String host;
    private String nick;
    private String userName;
    private Long userId;
    private String role;
    private String exp;
    Map<String, String> map;
	public JWTToken(String token) {
		this.token=token;
		    map = JWTAuth.verifyToken(token);
			 userName=map.get(JWTAuth.USERNAME);
			 userId=Long.parseLong(map.get(JWTAuth.USERID));
			 if(map.containsKey(JWTAuth.ROLE)) {
				 role=map.get(JWTAuth.ROLE);
			 }
			 nick=map.get(JWTAuth.NICK);
			 exp=map.get(PublicClaims.EXPIRES_AT);
	}
	
	public Map<String, String> getMap() {
		return map;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getToken(){
        return this.token;
    }
	/* @return username
	 */
	@Override
	public Object getPrincipal() {
		return this;
	}

	/* @return password
	 */
	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public String getHost() {
		return host;
	}
}
