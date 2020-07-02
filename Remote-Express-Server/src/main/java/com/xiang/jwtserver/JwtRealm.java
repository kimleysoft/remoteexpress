package com.xiang.jwtserver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.xiang.constants.RolesConstants;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;

/**
 * @author xiang
 * @createDate 2018年12月20日 上午11:56:45
 */
public class JwtRealm extends AuthorizingRealm {
	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken principals) {
		JWTToken jwtToken = (JWTToken) principals;
		String token = jwtToken.getToken();
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(jwtToken, token, "jwtRealm");
		return authenticationInfo;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}
	public final static Set<String> SHIRO_USER_ROLE=new HashSet<String>(Arrays.asList("user"));
	public final static Set<String> SHIRO_ADMIN_ROLE=new HashSet<String>(Arrays.asList("user","admin"));
	/*
	 * 权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object principal = super.getAvailablePrincipal(principals);
		JWTToken token = null;
		if (principal instanceof JWTToken) {
			token = (JWTToken) principal;
		} else {
			throw new APIException(ErrorCodes.LOGIN);
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(token.getRole().equals(RolesConstants.ADMIN_ROLE) ? SHIRO_ADMIN_ROLE : SHIRO_USER_ROLE);
		return simpleAuthorizationInfo;
	}
}