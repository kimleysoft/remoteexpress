package com.xiang.server;
import java.util.List;
import java.util.Map;

import com.xiang.bean.bo.UserRemoteBo;
import com.xiang.bean.bo.UserRemoteUpdateBo;
import com.xiang.bean.po.UserRemote;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.UserRemoteRelationVo;
import com.xiang.bean.vo.UserRemoteEntityVo;
import com.xiang.bean.vo.UserRemoteVo;
public interface UserRemoteServer extends BaseServer{
	public UserRemoteVo add(UserRemote po);
	public UserRemoteVo update(UserRemoteUpdateBo bo);
	public List<UserRemoteVo> getList();
	public List<UserRemoteVo> getList(Map<String,Object> querys);
	public BaseListVo<UserRemoteVo> queryList(Map<String,Object> querys);
	public UserRemoteVo get(Long id);
	public UserRemoteEntityVo getEntity(Long id);
	public UserRemoteVo view(Long id);
	public List<UserRemoteVo> gets(List<Long> ids);
	public UserRemoteVo getVo(UserRemoteBo bo);
	public UserRemoteBo getBo(UserRemoteVo vo);
	
	public List<UserRemoteVo> addList(UserRemoteBo bo);
	public List<UserRemoteVo> queryRemotesByUserid(Long userid);
	public void delByIds(Long[] ids, String remoteAddr);
	public List<UserRemoteRelationVo> queryUserRemoteDetailList(Long userid, Long remoteid);
}
