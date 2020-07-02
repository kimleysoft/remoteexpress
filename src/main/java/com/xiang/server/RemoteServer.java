package com.xiang.server;
import java.util.List;
import java.util.Map;

import com.xiang.bean.bo.RemoteBo;
import com.xiang.bean.bo.UserRemoteQueryBo;
import com.xiang.bean.po.Remote;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.RemoteEntityVo;
import com.xiang.bean.vo.RemoteVo;
public interface RemoteServer extends BaseServer{
	public RemoteVo add(RemoteBo bo) throws Exception;
	public RemoteVo update(RemoteBo bo) throws Exception;
	public void delByIds(Long[] ids) throws Exception;
	public void unDelByIds(Long[] ids) throws Exception;
	public List<RemoteVo> getList();
	public List<RemoteVo> getList(Map<String,Object> querys);
	public BaseListVo<RemoteVo> queryList(Map<String,Object> querys);
	public RemoteVo get(Long id);
	public RemoteEntityVo getEntity(Long id);
	public RemoteVo view(Long id);
	public List<RemoteVo> gets(List<Long> ids);
	public RemoteVo getVo(RemoteBo bo);
	public RemoteBo getBo(RemoteVo vo);
	public List<RemoteVo> queryUnrelatedList(UserRemoteQueryBo query);
	Integer maxPort();
	Boolean addexcelremote(Remote bo) throws Exception;
	public Boolean preWrapperAndReturn(Remote po);
	public List<Integer> existPort(List<Integer> ports);
	public void batchAdd(List<Remote> data);
}
