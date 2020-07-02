package com.xiang.service;

import java.util.List;

import com.xiang.bean.po.User;
import com.xiang.service.BaseService;

/**
* @author xiang
* @createDate 2018年12月20日 下午2:18:00
*/
public interface UserService extends BaseService<User>{
	public User getUser(Long id);
	public User getUser(String userName);
	public boolean existUser(String userName);
	public void saveUser(User user);
	public void update(User user);
	public String getUserName(Long id);
	public boolean isExitUser(String username);
	public List<String> isExistsUsers(List<String> usernames);
	public void batchSave(List<User> users);
	public int countDuplicateRelate(String ip, Long remoteid);
}
