package com.xiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.AdminUser;
import com.xiang.bean.po.AdminUserExample;
import com.xiang.bean.po.AdminUserExample.Criteria;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.Page;
import com.xiang.server.mapper.AdminUserMapper;
import com.xiang.service.AdminUserService;

@Service("adminUserService")
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserService {
	@Resource
	private AdminUserMapper adminUserMapper;
	@Override
	public AdminUser get(Long id) {
		return adminUserMapper.selectByPrimaryKey(id);
	}
	@Override
	public void save(AdminUser record) {
		adminUserMapper.save(record);
	}

	@Override
	public void update(AdminUser record) {
		adminUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AdminUser> getList(Map<String, Object> querys) {
		Page page = this.getPage(querys);
		try {
			AdminUserExample example = getExample(querys);
			setPage(page);
			return adminUserMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public AdminUser getSingle(Map<String, Object> querys) {
		if(querys==null) {
			querys=new HashMap<>();
		}
		querys.put(Page.PAGE, Page.FIRST_PAGE);
		querys.put(Page.LIMIT,1);
		List<AdminUser> list=getList(querys);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	private AdminUserExample getExample(Map<String, Object> querys){
		if (!ObjectUtils.isEmpty(querys)) {
			AdminUserExample example = new AdminUserExample();
			Criteria criteria = example.createCriteria();
			setCriteria(criteria,querys);
			return example;
		}
		return null;
	}
	@Override
	public Long getCount(Map<String, Object> querys) {
		AdminUserExample example = getExample(querys);
		return adminUserMapper.countByExample(example);
	}
	
	@Override
	public AdminUser getUser(String userName) {
		AdminUser user=adminUserMapper.getUserByUserName(userName);
		if (user!=null) {
			return user;
		}
		throw new APIException(ErrorCodes.USER_NO_EXIST);
	}
}
