package com.xiang.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.xiang.bean.po.User;
import com.xiang.bean.po.UserExample;
import com.xiang.bean.po.UserExample.Criteria;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.Page;
import com.xiang.server.mapper.UserMapper;
import com.xiang.service.UserService;

/**
 * @author xiang
 * @createDate 2018年12月20日 下午2:18:51
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	@Cacheable(value = "userCache", key = "T(com.xiang.constants.CachePrefixConstants).USER_NAME + #userName", unless = "#result == null")
	public User getUser(String userName) {
		User user=userMapper.getUserByUserName(userName);
		if (user!=null) {
			return user;
		}
		throw new APIException(ErrorCodes.USER_NO_EXIST);
	}

	@Override
	public void saveUser(User user) {
		userMapper.save(user);
	}

	@Override
	public List<User> getList(Map<String, Object> querys) {
		Page page = this.getPage(querys);
		UserExample example = getExample(querys);
		setPage(page);
		return userMapper.selectByExample(example);
	}

	@Override
	public Long getCount(Map<String, Object> querys) {
		UserExample example = getExample(querys);
		return userMapper.countByExample(example);
	}
	
	private UserExample getExample(Map<String, Object> querys){
		if (!ObjectUtils.isEmpty(querys)) {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			setCriteria(criteria,querys);
			return example;
		}
		return null;
	}

	@Override
	public void update(User user) {
		user.setGmtModified(new Date());
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public User getUser(Long id) {
		User user = userMapper.selectByPrimaryKey(id);
		if (Objects.isNull(user))
			throw new APIException(ErrorCodes.USER_NO_EXIST);
		return user;
	}

	@Override
	public boolean existUser(String userName) {
		try {
			getUser(userName);
		} catch (APIException ex) {
			if (ex.getErrorCode() == ErrorCodes.USER_NO_EXIST.getErrorCode()) {
				return false;
			} else {
				throw ex;
			}
		}
		return true;
	}

	@Override
	@Cacheable(value = "userCache", key = "T(com.xiang.constants.CachePrefixConstants).USER_NAME + #id", unless = "#result == null")
	public String getUserName(Long id) {
		return userMapper.getUserName(id);
	}

	@Override
	public boolean isExitUser(String username) {//向用戶表導入數據時為了防止文檔修改，不啟用緩存
		User user=userMapper.getUserByUserName(username);
		if(user != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<String> isExistsUsers(List<String> usernames) {
		return userMapper.isExistsUsers(usernames);
	}

	@Override
	public void batchSave(List<User> users) {
		userMapper.batchSave(users);
	}

	@Override
	public int countDuplicateRelate(String ip, Long remoteid) {
		int count = userMapper.CountDuplicateRelate(ip, remoteid);
		return count;
	}
}
