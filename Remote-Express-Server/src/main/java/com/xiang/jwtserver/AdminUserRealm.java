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

import com.xiang.bean.po.AdminUser;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.service.AdminUserService;

/**
 * @author xiang
 * @createDate 2018年12月20日 上午11:56:45
 */
public class AdminUserRealm extends AuthorizingRealm {
	@Resource
	private AdminUserService adminUserService;

	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken principals) {
		UsernamePasswordToken token = (UsernamePasswordToken) principals;
		String userName = token.getUsername();
		AdminUser user = adminUserService.getUser(userName);
		if (ObjectUtils.isEmpty(user)) {
			throw new APIException(ErrorCodes.AUTH);
		}
		return new SimpleAuthenticationInfo(userName, user.getPassword(), "adminUserRealm");
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	/*
	 * 权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			simpleAuthorizationInfo.setRoles(JwtRealm.SHIRO_ADMIN_ROLE);
		return simpleAuthorizationInfo;
	}
}