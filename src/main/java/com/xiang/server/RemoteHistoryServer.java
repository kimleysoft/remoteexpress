package com.xiang.server;
import java.util.List;
import java.util.Map;

import com.xiang.bean.bo.RemoteHistoryBo;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.RemoteHistoryVo;
public interface RemoteHistoryServer extends BaseServer{
	public RemoteHistoryVo add(Long userid,Long remoteid, String remoteAddr, boolean connectBreakFlag);
	public List<RemoteHistoryVo> getList(Map<String,Object> querys);
	public BaseListVo<RemoteHistoryVo> queryList(Map<String,Object> querys);
	public RemoteHistoryVo get(Long id);
	public RemoteHistoryVo view(Long id);
	public List<RemoteHistoryVo> gets(List<Long> ids);
	public RemoteHistoryVo getVo(RemoteHistoryBo bo);
	public RemoteHistoryBo getBo(RemoteHistoryVo vo);
}
