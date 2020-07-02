package com.xiang.controller.user;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiang.bean.vo.XAuthToken;
import com.xiang.restserver.LoginToken;
import com.xiang.server.LoginHistoryServer;

@RestController
@RequestMapping(value = "/loginhistory")
public class LoginHistoryController {
	@Resource
	private LoginHistoryServer loginHistoryServer;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(@LoginToken XAuthToken authToken,@RequestBody(required=false) Map<String,Object> querys) {
		return loginHistoryServer.queryList(querys);
	}
	
	@RequestMapping(value = "/get",method =RequestMethod.GET)
	public Object get(@RequestParam("id") Long id) {
		return loginHistoryServer.get(id);
	}
}
