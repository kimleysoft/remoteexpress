package com.xiang.server.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.robert.vesta.service.intf.IdService;
import com.xiang.bean.bo.UserBo;
import com.xiang.bean.bo.UserUpdateBo;
import com.xiang.bean.po.SysGroup;
import com.xiang.bean.po.User;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.UserEntityVo;
import com.xiang.bean.vo.UserVo;
import com.xiang.jwtserver.JWTToken;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.Page;
import com.xiang.server.UserServer;
import com.xiang.server.exmapper.ExUserRemoteMapper;
import com.xiang.service.CacheService;
import com.xiang.service.SysGroupService;
import com.xiang.service.UserService;

/**
 * @author xiang
 * @createDate 2018年12月20日 下午2:18:51
 */
@Service("userServer")
public class UserServerImpl extends BaseServerImpl implements UserServer {
	@Resource
	private UserService userService;
	@Resource
	private SysGroupService sysGroupService;
	@Resource
	private IdService idService;
	@Resource
	private CacheService cacheService;
	@Resource
	private ExUserRemoteMapper exUserRemoteMapper;
	@Resource
	private HttpServletRequest httpServletRequest;

	@Override
	public UserVo getUser(String userName) {
		User user = userService.getUser(userName);
		return getUserVo(user);
	}

	@Override
	public UserEntityVo getEntityVo(Long id) {
		User user = userService.getUser(id);
		return this.copyModel(user, UserEntityVo.class);
	}

	@Override
	public UserVo get(Long id) {
		User user = userService.getUser(id);
		UserVo vo = this.copyModel(user, UserVo.class);
		return vo;
	}

	private UserVo getUserVo(User user) {
		UserVo userVo = new UserVo();
		BeanUtils.copyProperties(user, userVo);
		return userVo;
	}

	private User getUser(UserBo userBo) {
		User user = new User();
		BeanUtils.copyProperties(userBo, user);
		return user;
	}

	@Override
	public List<UserVo> getList(Map<String, Object> querys) {
		List<User> users = userService.getList(querys);
		List<UserVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(users)) {
			Map<String, Object> gquerys = new HashMap<String, Object>();
			gquerys.put("andTypeflagEqualTo", 1);
			List<SysGroup> groups = sysGroupService.getList(gquerys);
			for (User user : users) {
				UserVo vo = getUserVo(user);
				groups.stream().forEach(g ->{
					if(vo.getGroupId().longValue() == g.getId().longValue())
						vo.setGroupname(g.getAlias());
				});
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public BaseListVo<UserVo> queryList(Map<String, Object> querys) {
		List<UserVo> list = getList(querys);
		Page page = userService.getPage(querys);
		BaseListVo<UserVo> baseListVo = new BaseListVo<UserVo>();
		baseListVo.setPage(page);
		baseListVo.setResult(list);
		if (!ObjectUtils.isEmpty(list)) {
			baseListVo.setTotal(userService.getCount(querys).intValue());
		}
		return baseListVo;
	}

	@Transactional
	@Override
	public UserVo addUser(UserBo userBo) {
		try {
			User user = getUser(userBo);
			if (userService.existUser(userBo.getUserName())) {
				throw new APIException(ErrorCodes.USER_EXIST);
			}
			user.setPassword(DigestUtils.md5Hex(userBo.getPassword()));
			long id = idService.genId();
			user.setId(id);
			userService.saveUser(user);
			return getUserVo(user);
		} catch (APIException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new APIException(ErrorCodes.ERROR);
		}
	}

	@Transactional
	@Override
	public void update(UserUpdateBo userBo) {
		try {
			User user = this.copyModel(userBo, User.class);
			if(!StringUtils.isEmpty(userBo.getPassword())) {
				user.setPassword(DigestUtils.md5Hex(userBo.getPassword()));
			}
			userService.update(user);
			cacheService.clearUserInfoCache(user.getId());
			cacheService.clearUserInfoCache(user.getUserName());
		} catch (APIException ex) {
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new APIException(ErrorCodes.ERROR);
		}
	}

	@Transactional
	@Override
	public void changePassword(Long id, String originPassword, String password) {
		User user = userService.getUser(id);
//		originPassword = DigestUtils.md5Hex(originPassword);
		if (user.getPassword().equals(originPassword)) {
			User userUpdate = new User();
			userUpdate.setId(user.getId());
			userUpdate.setPassword(DigestUtils.md5Hex(password));
			userService.update(userUpdate);
			cacheService.clearUserInfoCache(user.getId());
			cacheService.clearUserInfoCache(user.getUserName());
		} else {
			throw new APIException(ErrorCodes.PASSWORD);
		}
	}

	@Override
	public List<UserVo> getList() {
		Map<String, Object> querys = new HashMap<String, Object>();
		querys.put(Page.SORT, "-addTime");
		querys.put("andDelEqualTo", false);
		return getList(querys);
	}

	@Override
	@Cacheable(value = "userCache", key = "T(com.xiang.constants.CachePrefixConstants).USER_INFO + +#id", unless = "#result == null")
	public UserVo getUserInfo(Long id) {
		return get(id);
	}

	@Override
	public UserEntityVo getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			Object principal = subject.getPrincipal();
			if (principal instanceof JWTToken) {
				JWTToken jwtToken = (JWTToken) principal;
				UserEntityVo vo = new UserEntityVo();
				vo.setId(jwtToken.getUserId());
				vo.setUserName(jwtToken.getUserName());
				vo.setNick(jwtToken.getNick());
				return vo;
			}
		}
		return null;
	}

	@Override
	public String getUserName(Long id) {
		return userService.getUserName(id);
	}
	
	
	
	public void resetPassword(Long id, String password) {
		User user = userService.getUser(id);
		if (null != user) {
			User userUpdate = new User();
			userUpdate.setId(id);
			userUpdate.setPassword(DigestUtils.md5Hex(password));
			userService.update(userUpdate);
			cacheService.clearUserInfoCache(user.getId());
			cacheService.clearUserInfoCache(user.getUserName());
		} else {
			throw new APIException(ErrorCodes.USER_EXIST);
		}
	}

	@Override
	public boolean isExitUser(String username) {
		boolean existUser = userService.isExitUser(username);
		return existUser;
	}

	@Override
	public List<String> isExistsUsers(List<String> usernames) {
		return userService.isExistsUsers(usernames);
	}

	@Transactional
	@Override
	public void batchSave(List<User> users) {
		userService.batchSave(users);
	}
	
}
