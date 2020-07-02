package com.xiang.service;

import com.xiang.bean.po.AdminUser;
import com.xiang.service.BaseService;

public interface AdminUserService extends BaseService<AdminUser>{
	public AdminUser get(Long id);
	public void save(AdminUser record);
	public void update(AdminUser record);
	public AdminUser getUser(String userName);
}
