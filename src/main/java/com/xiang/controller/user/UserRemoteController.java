package com.xiang.controller.user;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiang.bean.bo.UserRemoteBo;
import com.xiang.bean.bo.UserRemoteQueryBo;
import com.xiang.bean.bo.UserRemoteUpdateBo;
import com.xiang.bean.vo.UserRemoteVo;
import com.xiang.bean.vo.XAuthToken;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.LoginToken;
import com.xiang.restserver.Page;
import com.xiang.server.RemoteServer;
import com.xiang.server.UserRemoteServer;
import com.xiang.server.UserServer;

@RestController
@RequestMapping(value = "/userremote")
public class UserRemoteController {
	@Resource
	private UserRemoteServer userRemoteServer;
	@Resource
	private RemoteController remoteController;
	@Resource
	private RemoteServer remoteServer;
	@Resource
	private HttpServletRequest httpServletRequest;
	@Resource
	private UserServer userServer;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@LoginToken XAuthToken authToken, @RequestBody UserRemoteBo bo) {
		List<UserRemoteVo> result = null;
		if (null != bo.getRemoteids() && bo.getRemoteids().length > 0 && bo.getUserid() != null) {
			result = userRemoteServer.addList(bo);
		}
		return result;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object del(@LoginToken XAuthToken authToken, @RequestBody Long[] ids) {
		userRemoteServer.delByIds(ids, httpServletRequest.getRemoteAddr());
		return ErrorCodes.OK;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(@LoginToken XAuthToken authToken, @RequestBody UserRemoteUpdateBo bo) {
		userRemoteServer.update(bo);
		return ErrorCodes.OK;
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(@LoginToken XAuthToken authToken, @RequestBody UserRemoteQueryBo query) {
		HashMap<String, Object> querys = new HashMap<String, Object>();
		querys.put("andUseridEqualTo", query.getUserid());
		querys.put(Page.PAGE, query.getPage());
		querys.put(Page.LIMIT, query.getLimit());
		querys.put(Page.SORT, query.getSort());
		return userRemoteServer.queryList(querys);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Object get(@RequestParam("id") Long id) {
		return userRemoteServer.get(id);
	}
	@RequestMapping(value = "/userRemoteList", method = RequestMethod.GET)
	public Object userRemoteList(@LoginToken XAuthToken authToken, @RequestParam("remoteid") Long remoteid,
			@RequestParam("userid") Long userid) {
		return userRemoteServer.queryUserRemoteDetailList(userid, remoteid);
	}
}
