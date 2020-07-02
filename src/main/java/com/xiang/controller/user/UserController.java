package com.xiang.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiang.bean.bo.UserBo;
import com.xiang.bean.bo.UserUpdateBo;
import com.xiang.bean.po.LoginHistory;
import com.xiang.bean.vo.UserVo;
import com.xiang.bean.vo.XAuthToken;
import com.xiang.constants.RolesConstants;
import com.xiang.jwtserver.JWTAuth;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.LoginToken;
import com.xiang.server.LoginHistoryServer;
import com.xiang.server.UserServer;
import com.xiang.server.exmapper.ExUserMapper;
import com.xiang.service.CacheService;
import com.xiang.shiro.EasyTypeToken;

/**
 * @author xiang
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private UserServer userServer;
	@Resource
	private CacheService cacheService;
	@Resource
	private HttpServletRequest httpServletRequest;
	@Resource
	private ExUserMapper exUserMapper;
	@Resource
	private LoginHistoryServer loginHistoryServer;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Object get(@RequestParam("id") Long id) {
		return userServer.get(id);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Object login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("operatingSystem") String operatingSystem) {
		UserBo user = new UserBo();
		user.setUserName(userName);
		user.setPassword(password);
		user.setOperatingSystem(operatingSystem);
		user.setLoginOutFlag(false);
		return login(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody UserBo user) {
		Subject subject = SecurityUtils.getSubject();
		UserVo userVo = null;
		try {
			userVo = userServer.getUser(user.getUserName());
		} catch (APIException ex) {
			if (ex.getErrorCode() == ErrorCodes.USER_NO_EXIST.getErrorCode()) {
				userVo = null;
			} else {
				throw ex;
			}
		}
		if (userVo == null || userVo.getDel()) {
			throw new APIException(ErrorCodes.AUTH);
		}
		EasyTypeToken token = new EasyTypeToken(user.getUserName(), user.getPassword());
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				cacheService.clearUserInfoCache(userVo.getId());
				Map<String, String> claims = new HashMap<String, String>();
				claims.put(JWTAuth.USERNAME, userVo.getUserName());
				claims.put(JWTAuth.USERID, userVo.getId().toString());
				claims.put(JWTAuth.NICK, userVo.getNick());
				claims.put(JWTAuth.ROLE, RolesConstants.USER_ROLE);
				XAuthToken xAuthToken = JWTAuth.createToken(claims);
				String remoteAddr = httpServletRequest.getRemoteAddr();
				UserUpdateBo updateBo = new UserUpdateBo();
				userVo.setLastLoginDate(new Date());
				userVo.setLastLoginIp(remoteAddr);
				userVo.setLoginOutFlag(user.getLoginOutFlag());
				BeanUtils.copyProperties(userVo, updateBo);
				update(xAuthToken, updateBo);
				xAuthToken.setIp(remoteAddr);
				// 日誌
				LoginHistory loginHistory = new LoginHistory();
				loginHistory.setUserid(userVo.getId());
				loginHistory.setOperatingSystem(user.getOperatingSystem());
				if (user.getLoginOutFlag()) { // 登出
					loginHistory.setRefType(2);
				} else {
					loginHistory.setRefType(1);
				}
				loginHistoryServer.add(loginHistory);
				return xAuthToken;

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new APIException(ErrorCodes.AUTH);
		}
		throw new APIException(ErrorCodes.AUTH);
	}

	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public Object loginOut(@LoginToken XAuthToken authToken, @RequestParam("operatingSystem") String operatingSystem) {
		try {
			cacheService.clearUserInfoCache(authToken.getId());
			String remoteAddr = httpServletRequest.getRemoteAddr();
			UserUpdateBo updateBo = new UserUpdateBo();
			updateBo.setId(authToken.getId());
			updateBo.setLastLoginDate(new Date());
			updateBo.setLastLoginIp(remoteAddr);
			updateBo.setLoginOutFlag(true);
			userServer.update(updateBo);
			// 日誌
			LoginHistory loginHistory = new LoginHistory();
			loginHistory.setUserid(authToken.getId());
			loginHistory.setOperatingSystem(operatingSystem);
			loginHistory.setRefType(2);
			loginHistoryServer.add(loginHistory);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new APIException(ErrorCodes.ERROR);
		}
		return ErrorCodes.OK;
	}

	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public Object getUserInfo(@LoginToken XAuthToken authToken) {
		return userServer.getUserInfo(authToken.getId());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@LoginToken XAuthToken authToken, @RequestBody UserBo userBo) {
		return userServer.addUser(userBo);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object del(@LoginToken XAuthToken authToken, @RequestBody Long[] ids) {
		userServer.setDelById("user", ids, true);
		return ErrorCodes.OK;
	}
	
	@RequestMapping(value = "/offline", method = RequestMethod.POST)
	public Object offLine(@LoginToken XAuthToken authToken, @RequestBody Long[] ids) {
		userServer.setFlag("user", "login_out_flag" ,ids, true);
		return ErrorCodes.OK;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(@LoginToken XAuthToken authToken, @RequestBody UserUpdateBo user) {
		userServer.update(user);
		return ErrorCodes.OK;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(@RequestBody(required = false) Map<String, Object> querys) {
		return userServer.queryList(querys);
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public Object changePassword(@LoginToken XAuthToken loginToken,
			@RequestParam("originPassword") String originPassword, @RequestParam("password") String password) {
//		String originPassword = map.getString("originPassword");
//		String password = map.getString("password");
		if (StringUtils.isBlank(originPassword) || StringUtils.isBlank(password)) {
			return ErrorCodes.ERROR_PARAM;
		}
		userServer.changePassword(loginToken.getId(), originPassword, password);
		return ErrorCodes.OK;
	}

	@RequestMapping(value = "/statisticsUserOnLine", method = RequestMethod.GET)
	public int statisticsUserOnLine(@LoginToken XAuthToken loginToken,
			@RequestParam("loginOutFlag") Boolean loginOutFlag) {
		UserBo bo = new UserBo();
		bo.setLoginOutFlag(loginOutFlag);
		int onLine = exUserMapper.statisticsUserOnLine(bo);

		return onLine;
	}

}
