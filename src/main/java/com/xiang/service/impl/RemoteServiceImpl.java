package com.xiang.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.Remote;
import com.xiang.bean.po.RemoteExample;
import com.xiang.bean.po.RemoteExample.Criteria;
import com.xiang.bean.vo.RemotePortGroupVo;
import com.xiang.restserver.Page;
import com.xiang.server.mapper.RemoteMapper;
import com.xiang.service.RemoteService;

@Service("remoteService")
public class RemoteServiceImpl extends BaseServiceImpl<Remote> implements RemoteService {
	@Autowired
	private RemoteMapper remoteMapper;
	@Override
	public Remote get(Long id) {
		return remoteMapper.selectByPrimaryKey(id);
	}
	@Override
	public void save(Remote record) {
		remoteMapper.save(record);
	}

	@Override
	public void update(Remote record) {
		remoteMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Remote> getList(Map<String, Object> querys) {
		Page page = this.getPage(querys);
		try {
			RemoteExample example = getExample(querys);
			setPage(page);
			return remoteMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Remote getSingle(Map<String, Object> querys) {
		if(querys==null) {
			querys=new HashMap<>();
		}
		querys.put(Page.PAGE, Page.FIRST_PAGE);
		querys.put(Page.LIMIT,1);
		List<Remote> list=getList(querys);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	private RemoteExample getExample(Map<String, Object> querys){
		if (!ObjectUtils.isEmpty(querys)) {
			RemoteExample example = new RemoteExample();
			Criteria criteria = example.createCriteria();
			setCriteria(criteria,querys);
			return example;
		}
		return null;
	}
	@Override
	public Long getCount(Map<String, Object> querys) {
		RemoteExample example = getExample(querys);
		return remoteMapper.countByExample(example);
	}
	@Override
	public List<RemotePortGroupVo> groupbyPortCount(Long[] ids) {
		return remoteMapper.groupbyPortCount(ids);
	}
	@Override
	public List<Remote> queryUnrelatedList(String remark, Long groupid, Long userid) {
		return remoteMapper.unRelatedRemote(remark, userid, groupid);
	}
	@Override
	public Integer maxPort() {
		return remoteMapper.maxPort();
	}
	@Override
	public List<Integer> existPort(List<Integer> ports) {
		
		return remoteMapper.existPort(ports);
	}
	@Override
	public void batchAdd(List<Remote> data) {
		remoteMapper.batchAdd(data);
	}
}
