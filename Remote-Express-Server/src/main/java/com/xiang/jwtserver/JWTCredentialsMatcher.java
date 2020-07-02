package com.xiang.jwtserver;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.google.common.base.Objects;
import com.xiang.bean.vo.AdminUserVo;
import com.xiang.constants.RolesConstants;
import com.xiang.server.AdminUserServer;
import com.xiang.server.UserServer;

public class JWTCredentialsMatcher implements CredentialsMatcher{
	@Resource
	private UserServer userServer;

	@Resource
	private AdminUserServer adminUserServer;


	@Override
	public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
 		JWTToken jwtToken = null;
		if (authenticationToken instanceof JWTToken) {
			jwtToken = (JWTToken) authenticationToken;
		} else if (authenticationToken.getPrincipal() instanceof JWTToken) {
			jwtToken = (JWTToken) authenticationToken.getPrincipal();
		} else if (authenticationToken.getPrincipal() instanceof String) {
			String token = (String) authenticationToken.getPrincipal();
			jwtToken = new JWTToken(token);
		} else {
			return false;
		}
		boolean result = false;
		if (jwtToken.getUserName() != null && jwtToken.getUserId() != null) {
			if(jwtToken.getRole().equals(RolesConstants.ADMIN_ROLE)) {
				AdminUserVo user = adminUserServer.getUser(jwtToken.getUserName());
				result = Objects.equal(user.getUserName(), jwtToken.getUserName());
			} else {
				String userName = userServer.getUserName(jwtToken.getUserId());
				result = Objects.equal(userName, jwtToken.getUserName());
			}
		}
		return result;
	}

}
