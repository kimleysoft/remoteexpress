package com.xiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.LoginHistory;
import com.xiang.bean.po.LoginHistoryExample;
import com.xiang.bean.po.LoginHistoryExample.Criteria;
import com.xiang.restserver.Page;
import com.xiang.server.mapper.LoginHistoryMapper;
import com.xiang.service.LoginHistoryService;

@Service("loginHistoryService")
public class LoginHistoryServiceImpl extends BaseServiceImpl<LoginHistory> implements LoginHistoryService {
	@Autowired
	private LoginHistoryMapper loginHistoryMapper;
	@Override
	public LoginHistory get(Long id) {
		return loginHistoryMapper.selectByPrimaryKey(id);
	}
	@Override
	public void save(LoginHistory record) {
		loginHistoryMapper.save(record);
	}

	@Override
	public void update(LoginHistory record) {
		loginHistoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<LoginHistory> getList(Map<String, Object> querys) {
		Page page = this.getPage(querys);
		try {
			LoginHistoryExample example = getExample(querys);
			setPage(page);
			return loginHistoryMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public LoginHistory getSingle(Map<String, Object> querys) {
		if(querys==null) {
			querys=new HashMap<>();
		}
		querys.put(Page.PAGE, Page.FIRST_PAGE);
		querys.put(Page.LIMIT,1);
		List<LoginHistory> list=getList(querys);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	private LoginHistoryExample getExample(Map<String, Object> querys){
		if (!ObjectUtils.isEmpty(querys)) {
			LoginHistoryExample example = new LoginHistoryExample();
			Criteria criteria = example.createCriteria();
			setCriteria(criteria,querys);
			return example;
		}
		return null;
	}
	@Override
	public Long getCount(Map<String, Object> querys) {
		LoginHistoryExample example = getExample(querys);
		return loginHistoryMapper.countByExample(example);
	}
}
