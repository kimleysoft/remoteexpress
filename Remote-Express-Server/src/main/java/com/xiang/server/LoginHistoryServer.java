package com.xiang.server;
import java.util.List;
import java.util.Map;

import com.xiang.bean.bo.LoginHistoryBo;
import com.xiang.bean.po.LoginHistory;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.LoginHistoryEntityVo;
import com.xiang.bean.vo.LoginHistoryVo;
public interface LoginHistoryServer extends BaseServer{
	public LoginHistoryVo add(LoginHistory po);
	public LoginHistoryVo update(LoginHistory po);
	public List<LoginHistoryVo> getList();
	public List<LoginHistoryVo> getList(Map<String,Object> querys);
	public BaseListVo<LoginHistoryVo> queryList(Map<String,Object> querys);
	public LoginHistoryVo get(Long id);
	public LoginHistoryEntityVo getEntity(Long id);
	public LoginHistoryVo view(Long id);
	public List<LoginHistoryVo> gets(List<Long> ids);
	public LoginHistoryVo getVo(LoginHistoryBo bo);
	public LoginHistoryBo getBo(LoginHistoryVo vo);
}
