package com.xiang.service;

public interface CacheService {
	public void clearUserInfoCache(Long id);
	public void clearUserInfoCache(String userName);
	public void clearAdminUserInfoCache(Long id);
	public void clearAdminUserCache(Long id);
	public void clearAdminUserCache(String userName);
}
