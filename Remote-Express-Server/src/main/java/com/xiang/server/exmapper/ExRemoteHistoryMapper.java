package com.xiang.server.exmapper;

import java.util.List;

import com.xiang.bean.bo.RemoteHistoryBo;
import com.xiang.bean.vo.RemoteHistoryVo;


public interface ExRemoteHistoryMapper {
	
	List<RemoteHistoryVo> selectByConditionDO(RemoteHistoryBo remoteHistory);
}
