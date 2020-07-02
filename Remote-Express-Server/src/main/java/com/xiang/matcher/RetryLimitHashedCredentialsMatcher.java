package com.xiang.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {


	@Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
    	char[] userpass=(char[])authcToken.getCredentials();
    	String userpassstring=new String(userpass);
    	String passinfo = info.getCredentials().toString();
    	if(userpassstring.equals(passinfo)) {
    		return true;
    	}else {
			return false;
		}
    }
	
}
