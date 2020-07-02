package com.xiang.controller.user;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xiang.restserver.LoginToken;
import com.xiang.bean.vo.XAuthToken;
import com.robert.vesta.service.intf.IdService;
import com.xiang.server.RemoteHistoryServer;

@RestController
@RequestMapping(value = "/remotehistory")
public class RemoteHistoryController {
	@Resource
	private RemoteHistoryServer remoteHistoryServer;
	@Resource
	private IdService idService;
	@Resource
	private HttpServletRequest  httpServletRequest;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Object add(@LoginToken XAuthToken authToken,@RequestParam("userid") Long userid,@RequestParam("remoteid") Long remoteid) {
		String remoteAddr = httpServletRequest.getRemoteAddr();
		return remoteHistoryServer.add(userid, remoteid, remoteAddr, false);
	}
	
	@RequestMapping(value = "/addOut", method = RequestMethod.GET)
	public Object addOut(@LoginToken XAuthToken authToken,@RequestParam("userid") Long userid,@RequestParam("remoteid") Long remoteid) {
		String remoteAddr = httpServletRequest.getRemoteAddr();
		return remoteHistoryServer.add(userid, remoteid, remoteAddr, true);
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(@LoginToken XAuthToken authToken, @RequestBody Map<String, Object> querys) {
		return remoteHistoryServer.queryList(querys);
	}
	@RequestMapping(value = "/get",method =RequestMethod.GET)
	public Object get(@RequestParam("id") Long id) {
		return remoteHistoryServer.get(id);
	}
}
