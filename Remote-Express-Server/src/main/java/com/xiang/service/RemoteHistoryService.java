package com.xiang.service;

import com.xiang.bean.po.RemoteHistory;

public interface RemoteHistoryService extends BaseService<RemoteHistory>{
	public RemoteHistory get(Long id);
	public void save(RemoteHistory record);
	public void update(RemoteHistory record);
}
