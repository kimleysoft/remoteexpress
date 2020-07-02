package com.xiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.SysGroup;
import com.xiang.bean.po.SysGroupExample;
import com.xiang.bean.po.SysGroupExample.Criteria;
import com.xiang.restserver.Page;
import com.xiang.server.mapper.SysGroupMapper;
import com.xiang.service.SysGroupService;

@Service("sysGroupService")
public class SysGroupServiceImpl extends BaseServiceImpl<SysGroup> implements SysGroupService {
	@Autowired
	private SysGroupMapper sysGroupMapper;
	@Override
	public SysGroup get(Long id) {
		return sysGroupMapper.selectByPrimaryKey(id);
	}
	@Override
	public void save(SysGroup record) {
		sysGroupMapper.save(record);
	}

	@Override
	public void update(SysGroup record) {
		sysGroupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SysGroup> getList(Map<String, Object> querys) {
		Page page = this.getPage(querys);
		try {
			SysGroupExample example = getExample(querys);
			setPage(page);
			return sysGroupMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public SysGroup getSingle(Map<String, Object> querys) {
		if(querys==null) {
			querys=new HashMap<>();
		}
		querys.put(Page.PAGE, Page.FIRST_PAGE);
		querys.put(Page.LIMIT,1);
		List<SysGroup> list=getList(querys);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	private SysGroupExample getExample(Map<String, Object> querys){
		if (!ObjectUtils.isEmpty(querys)) {
			SysGroupExample example = new SysGroupExample();
			Criteria criteria = example.createCriteria();
			setCriteria(criteria,querys);
			return example;
		}
		return null;
	}
	@Override
	public Long getCount(Map<String, Object> querys) {
		SysGroupExample example = getExample(querys);
		return sysGroupMapper.countByExample(example);
	}
	@Override
	public SysGroup exitOrNo(String alias) {
		// TODO Auto-generated method stub
		SysGroup group = sysGroupMapper.selectByAlias(alias);
		if(group != null) {
			return group;
		}else {
			return null;
		}
	}
	@Override
	public void batchSave(List<SysGroup> records) {
		sysGroupMapper.batchSave(records);
	}
}
