package com.xiang.service;

import com.xiang.bean.po.LoginHistory;

public interface LoginHistoryService extends BaseService<LoginHistory>{
	public LoginHistory get(Long id);
	public void save(LoginHistory record);
	public void update(LoginHistory record);
}
