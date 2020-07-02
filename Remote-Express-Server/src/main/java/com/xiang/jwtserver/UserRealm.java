package com.xiang.jwtserver;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.User;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.service.UserService;
import com.xiang.shiro.EasyTypeToken;

/**
 * @author xiang
 * @createDate 2018年12月20日 上午11:56:45
 */
public class UserRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken principals) {
		UsernamePasswordToken token = (UsernamePasswordToken) principals;
		String userName = token.getUsername();
		User user = userService.getUser(userName);
		if (ObjectUtils.isEmpty(user)) {
			throw new APIException(ErrorCodes.AUTH);
		}
		return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), "userRealm");
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof EasyTypeToken;
	}

	/*
	 * 权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			simpleAuthorizationInfo.setRoles(JwtRealm.SHIRO_USER_ROLE);
		return simpleAuthorizationInfo;
	}
}