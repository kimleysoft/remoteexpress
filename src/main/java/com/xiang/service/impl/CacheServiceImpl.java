package com.xiang.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.xiang.service.CacheService;
@Service("cacheService")
public class CacheServiceImpl implements CacheService{

	
	
	@Override
	@CacheEvict(value = {"userCache" }, key = "T(com.xiang.constants.CachePrefixConstants).USER_INFO + #id", beforeInvocation = true)
	public void clearUserInfoCache(Long id) {}

	@Override
	@CacheEvict(value = {"userCache" }, key = "T(com.xiang.constants.CachePrefixConstants).USER_INFO + #userName", beforeInvocation = true)
	public void clearUserInfoCache(String userName) {}
	
	@Override
	@CacheEvict(value = {"userCache" }, key = "T(com.xiang.constants.CachePrefixConstants).ADMIN_USER_INFO + #id", beforeInvocation = true)
	public void clearAdminUserInfoCache(Long id) {}
	
	@Override
	@CacheEvict(value = {"userCache" }, key = "T(com.xiang.constants.CachePrefixConstants).ADMIN_ENTITY_BY_ID + #id", beforeInvocation = true)
	public void clearAdminUserCache(Long id) {}

	@Override
	@CacheEvict(value = {"userCache" }, key = "T(com.xiang.constants.CachePrefixConstants).ADMIN_ENTITY_BY_USER_NAME + #userName", beforeInvocation = true)
	public void clearAdminUserCache(String userName) {}
}
