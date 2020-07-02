package com.xiang.jwtserver;

import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.subject.PrincipalCollection;

public class TenantRealmAuthorizer extends ModularRealmAuthorizer{
	
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		return super.isPermitted(principals, permission);
	}

}
