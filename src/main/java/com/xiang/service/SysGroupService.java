package com.xiang.service;

import java.util.List;

import com.xiang.bean.po.SysGroup;

public interface SysGroupService extends BaseService<SysGroup>{
	public SysGroup get(Long id);
	public void save(SysGroup record);
	public void update(SysGroup record);
	public SysGroup exitOrNo(String alias);
	public void batchSave(List<SysGroup> records);
}
