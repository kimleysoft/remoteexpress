package com.xiang.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.robert.vesta.service.intf.IdService;
import com.xiang.bean.bo.AdminUserBo;
import com.xiang.bean.bo.UserBo;
import com.xiang.bean.vo.AdminUserVo;
import com.xiang.bean.vo.XAuthToken;
import com.xiang.constants.RolesConstants;
import com.xiang.jwtserver.JWTAuth;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.LoginToken;
import com.xiang.server.AdminUserServer;
import com.xiang.server.UserServer;
import com.xiang.service.CacheService;

/**
 * @author xiang
 *
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminUserController {
	@Resource
	private AdminUserServer adminUserServer;
	@Resource
	private CacheService cacheService;
	@Resource
	private IdService idService;
	@Resource
	private UserServer userServer;
	@RequestMapping(value = "/get",method =RequestMethod.GET)
	public Object get(@RequestParam("id") Long id) {
		return adminUserServer.get(id);
	}
	@RequestMapping(value = "/login",method =RequestMethod.GET)
	public Object login(@RequestParam("userName") String userName,
			@RequestParam("password") String password) {
		AdminUserBo user = new AdminUserBo();
		user.setUserName(userName);
		user.setPassword(password);
		return login(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@RequestBody AdminUserBo user) {
		Subject subject = SecurityUtils.getSubject();
		AdminUserVo userVo=null;
		try {
			 userVo=adminUserServer.getUser(user.getUserName());
		}catch(APIException ex) {
			 if(ex.getErrorCode()==ErrorCodes.USER_NO_EXIST.getErrorCode()) {
				 userVo=null;
			 }else {
				 throw ex;
			 }
		}
		if(userVo==null) {
			throw new APIException(ErrorCodes.AUTH);
		}
		if (userVo.getDel()) {
			throw new APIException(ErrorCodes.USER_HAD_DELETE);
		}
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				cacheService.clearAdminUserInfoCache(userVo.getId());
				Map<String, String> claims = new HashMap<String, String>();
				claims.put(JWTAuth.USERNAME, userVo.getUserName());
				claims.put(JWTAuth.USERID, userVo.getId().toString());
				claims.put(JWTAuth.NICK, userVo.getNick());
				claims.put(JWTAuth.ROLE, RolesConstants.ADMIN_ROLE);
				XAuthToken xAuthToken = JWTAuth.createToken(claims);
				return xAuthToken;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new APIException(ErrorCodes.AUTH);
		}
		throw new APIException(ErrorCodes.AUTH);
	}
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public Object getUserInfo(@LoginToken XAuthToken authToken) {
			return adminUserServer.getUserInfo(authToken.getId());
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@LoginToken XAuthToken authToken, @RequestBody AdminUserBo adminUserBo) {
		return adminUserServer.add(adminUserBo);
	}
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object del(@LoginToken XAuthToken authToken, @RequestBody Long[] ids) {
		
		return adminUserServer.delById(authToken, ids);
	}
	@RequestMapping(value = "/undel", method = RequestMethod.POST)
	public Object unDel(@LoginToken XAuthToken authToken, @RequestBody Long[] ids) {
		adminUserServer.setDelById("user", ids, false);
		return ErrorCodes.OK;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(@RequestBody AdminUserBo user) {
		adminUserServer.update(user);
		return ErrorCodes.OK;
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(@RequestBody(required=false) Map<String,Object> querys) {
		return adminUserServer.queryList(querys);
	}
	@RequestMapping(value = "/changepassword",method =RequestMethod.POST)
	public Object changePassword(@LoginToken XAuthToken loginToken, @RequestBody JSONObject map) {
		String originPassword = map.getString("originPassword");
		String password = map.getString("password");
		if(StringUtils.isBlank(originPassword) || StringUtils.isBlank(password)) {
			return ErrorCodes.ERROR_PARAM;
		}
		adminUserServer.changePassword(loginToken.getId(), originPassword, password);
		return ErrorCodes.OK;
	}
	
	@RequestMapping(value = "/reset",method =RequestMethod.POST)
	public Object resetPassword(@LoginToken XAuthToken loginToken, @RequestBody AdminUserBo bo) {
//		AdminUserVo admin = adminUserServer.get(loginToken.getId());
//		if(!Arrays.asList(admin.getRoles()).contains("admin")) {
//			return ErrorCodes.FORBIDDEN;
//		}
		if(bo.getId() == null || StringUtils.isBlank(bo.getPassword())) {
			return ErrorCodes.ERROR_PARAM;
		}
		adminUserServer.resetPassword(bo.getId(), bo.getPassword());
		return ErrorCodes.OK;
	}
	
	@RequestMapping(value = "/user/reset",method =RequestMethod.POST)
	public Object userResetPassword(@LoginToken XAuthToken loginToken, @RequestBody UserBo bo) {
		if(bo.getId() == null || StringUtils.isBlank(bo.getPassword())) {
			return ErrorCodes.ERROR_PARAM;
		}
		userServer.resetPassword(bo.getId(), bo.getPassword());
		return ErrorCodes.OK;
	}
}
