package com.xiang.service;

import java.util.List;
import java.util.Map;

import com.xiang.bean.po.UserRemote;

public interface UserRemoteService extends BaseService<UserRemote>{
	public UserRemote get(Long id);
	public void save(UserRemote record);
	public void update(UserRemote record);
	public void batchSave(List<UserRemote> records);
	public void updateByExampleSelective(UserRemote record, Map<String, Object> querys);
}
