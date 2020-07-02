package com.xiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.UserRemote;
import com.xiang.bean.po.UserRemoteExample;
import com.xiang.bean.po.UserRemoteExample.Criteria;
import com.xiang.restserver.Page;
import com.xiang.server.mapper.UserRemoteMapper;
import com.xiang.service.UserRemoteService;

@Service("userRemoteService")
public class UserRemoteServiceImpl extends BaseServiceImpl<UserRemote> implements UserRemoteService {
	@Autowired
	private UserRemoteMapper userRemoteMapper;
	@Override
	public UserRemote get(Long id) {
		return userRemoteMapper.selectByPrimaryKey(id);
	}
	@Override
	public void save(UserRemote record) {
		userRemoteMapper.save(record);
	}
	@Override
	public void batchSave(List<UserRemote> records) {
		userRemoteMapper.batchSave(records);
	}

	@Override
	public void update(UserRemote record) {
		userRemoteMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<UserRemote> getList(Map<String, Object> querys) {
		Page page = this.getPage(querys);
		try {
			UserRemoteExample example = getExample(querys);
			setPage(page);
			return userRemoteMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public UserRemote getSingle(Map<String, Object> querys) {
		if(querys==null) {
			querys=new HashMap<>();
		}
		querys.put(Page.PAGE, Page.FIRST_PAGE);
		querys.put(Page.LIMIT,1);
		List<UserRemote> list=getList(querys);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	private UserRemoteExample getExample(Map<String, Object> querys){
		if (!ObjectUtils.isEmpty(querys)) {
			UserRemoteExample example = new UserRemoteExample();
			Criteria criteria = example.createCriteria();
			setCriteria(criteria,querys);
			return example;
		}
		return null;
	}
	@Override
	public Long getCount(Map<String, Object> querys) {
		UserRemoteExample example = getExample(querys);
		return userRemoteMapper.countByExample(example);
	}
	@Override
	public void updateByExampleSelective(UserRemote record, Map<String, Object> querys) {
		userRemoteMapper.updateByExampleSelective(record, getExample(querys));
	}
}
