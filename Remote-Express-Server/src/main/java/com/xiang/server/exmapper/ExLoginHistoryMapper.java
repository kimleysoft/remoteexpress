package com.xiang.server.exmapper;

import java.util.List;

import com.xiang.bean.bo.LoginHistoryBo;
import com.xiang.bean.vo.LoginHistoryVo;

public interface ExLoginHistoryMapper {
	
	List<LoginHistoryVo> selectByConditionDO(LoginHistoryBo bo);
}


