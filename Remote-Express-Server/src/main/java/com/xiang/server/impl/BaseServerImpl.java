package com.xiang.server.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.xiang.server.BaseServer;
import com.xiang.service.BaseService;
import com.xiang.service.CacheService;

public class BaseServerImpl implements BaseServer {
	@Resource
	private BaseService baseService;
	@Resource
	private CacheService cacheService;

	@Transactional
	@Override
	public void setDelById(String table, Long[] ids, Boolean del) {
		baseService.setDelById(table, ids, del);
	}
	@Transactional
	@Override
	public void setoffLineUser(String table, Long[] ids, Boolean outFlag) {
		baseService.setoffLineUser(table, ids, outFlag);
	}

	@Override
	public void setFlag(String table, String field, Long[] ids, Object flag) {
		baseService.setFlag(table, field, ids, flag);

	}

	@Override
	public Long getMax(String table, String field) {
		// TODO Auto-generated method stub
		return baseService.getMax(table, field);
	}

	public <T> T copyModel(Object po, Class<T> cls, String... ignoreProperties) {
		if (po == null)
			return null;
		try {
			T vo = cls.newInstance();
			BeanUtils.copyProperties(po, vo, ignoreProperties);
			return vo;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
