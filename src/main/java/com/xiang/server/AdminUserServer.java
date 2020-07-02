package com.xiang.server;

import java.util.List;
import java.util.Map;

import com.xiang.bean.bo.AdminUserBo;
import com.xiang.bean.vo.AdminUserEntityVo;
import com.xiang.bean.vo.AdminUserVo;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.XAuthToken;
import com.xiang.restserver.ErrorCodes;
public interface AdminUserServer extends BaseServer{
	public AdminUserVo add(AdminUserBo bo);
	public AdminUserVo update(AdminUserBo bo);
	public List<AdminUserVo> getList();
	public List<AdminUserVo> getList(Map<String,Object> querys);
	public BaseListVo<AdminUserVo> queryList(Map<String,Object> querys);
	public AdminUserVo get(Long id);
	public AdminUserEntityVo getEntity(Long id);
	public List<AdminUserVo> gets(List<Long> ids);
	public AdminUserVo getVo(AdminUserBo bo);
	public AdminUserBo getBo(AdminUserVo vo);
	public AdminUserVo getUser(String userName);
	public void changePassword(Long id,String originPassword,String password);
	public AdminUserVo getUserInfo(Long id);
	public ErrorCodes delById(XAuthToken authToken, Long[] ids);
	public void resetPassword(Long id, String password);
}
