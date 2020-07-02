package com.xiang.service;

import java.util.List;

import com.xiang.bean.po.Remote;
import com.xiang.bean.vo.RemotePortGroupVo;

public interface RemoteService extends BaseService<Remote>{
	public Remote get(Long id);
	public void save(Remote record);
	public void update(Remote record);
	public List<RemotePortGroupVo> groupbyPortCount(Long[] ids);
	public List<Remote> queryUnrelatedList(String remark, Long groupid, Long userid);
	public Integer maxPort();
	public List<Integer> existPort(List<Integer> ports);
	public void batchAdd(List<Remote> data);
}
