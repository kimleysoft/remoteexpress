package com.xiang.server;

import java.util.List;
import java.util.Map;

import com.xiang.bean.bo.UserBo;
import com.xiang.bean.bo.UserUpdateBo;
import com.xiang.bean.po.User;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.UserEntityVo;
import com.xiang.bean.vo.UserVo;

public interface UserServer extends BaseServer{
	public UserVo addUser(UserBo userBo);
	public UserVo getUser(String userName);
	public UserVo getUserInfo(Long id);
	public UserVo get(Long id);
	public UserEntityVo getEntityVo(Long id);
	public List<UserVo> getList();
	public List<UserVo> getList(Map<String,Object> querys);
	public BaseListVo<UserVo> queryList(Map<String,Object> querys);
	public void update(UserUpdateBo userBo);
	public void changePassword(Long id,String originPassword,String password);
	public UserEntityVo getCurrentUser();
	public String getUserName(Long id);
	public void resetPassword(Long id, String password);
	public boolean isExitUser(String username);
	public List<String> isExistsUsers(List<String> usernames);
	public void batchSave(List<User> users);
}
