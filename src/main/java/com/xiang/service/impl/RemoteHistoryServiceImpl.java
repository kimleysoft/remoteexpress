package com.xiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.RemoteHistory;
import com.xiang.bean.po.RemoteHistoryExample;
import com.xiang.bean.po.RemoteHistoryExample.Criteria;
import com.xiang.restserver.Page;
import com.xiang.server.mapper.RemoteHistoryMapper;
import com.xiang.service.RemoteHistoryService;

@Service("remoteHistoryService")
public class RemoteHistoryServiceImpl extends BaseServiceImpl<RemoteHistory> implements RemoteHistoryService {
	@Autowired
	private RemoteHistoryMapper remoteHistoryMapper;
	@Override
	public RemoteHistory get(Long id) {
		return remoteHistoryMapper.selectByPrimaryKey(id);
	}
	@Override
	public void save(RemoteHistory record) {
		remoteHistoryMapper.save(record);
	}

	@Override
	public void update(RemoteHistory record) {
		remoteHistoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<RemoteHistory> getList(Map<String, Object> querys) {
		Page page = this.getPage(querys);
		try {
			RemoteHistoryExample example = getExample(querys);
			setPage(page);
			return remoteHistoryMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public RemoteHistory getSingle(Map<String, Object> querys) {
		if(querys==null) {
			querys=new HashMap<>();
		}
		querys.put(Page.PAGE, Page.FIRST_PAGE);
		querys.put(Page.LIMIT,1);
		List<RemoteHistory> list=getList(querys);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	private RemoteHistoryExample getExample(Map<String, Object> querys){
		if (!ObjectUtils.isEmpty(querys)) {
			RemoteHistoryExample example = new RemoteHistoryExample();
			Criteria criteria = example.createCriteria();
			setCriteria(criteria,querys);
			return example;
		}
		return null;
	}
	@Override
	public Long getCount(Map<String, Object> querys) {
		RemoteHistoryExample example = getExample(querys);
		return remoteHistoryMapper.countByExample(example);
	}
}
