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
import com.xiang.restserver.ErrorCodes;
import com.xiang.bean.bo.SysGroupBo;
import com.xiang.bean.bo.SysGroupDelBo;
import com.xiang.server.SysGroupServer;

@RestController
@RequestMapping(value = "/sysgroup")
public class SysGroupController {
	@Resource
	private SysGroupServer sysGroupServer;
	@Resource
	private HttpServletRequest  httpServletRequest;
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@LoginToken XAuthToken authToken, @RequestBody SysGroupBo bo) throws Exception {
		return sysGroupServer.add(bo);
	}
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object del(@LoginToken XAuthToken authToken,@RequestBody SysGroupDelBo bo) {
		if(null == bo.getIds() || null == bo.getTypeflag()) {
			return ErrorCodes.ERROR_PARAM;
		}
		sysGroupServer.delGroups(bo);
		return ErrorCodes.OK;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(@LoginToken XAuthToken authToken, @RequestBody SysGroupBo sysGroupBo) throws Exception {
		sysGroupServer.update(sysGroupBo);
		return ErrorCodes.OK;
	}
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(@LoginToken XAuthToken authToken,@RequestBody(required=false) Map<String,Object> querys) {
		return sysGroupServer.queryList(querys);
	}
	@RequestMapping(value = "/get",method =RequestMethod.GET)
	public Object get(@RequestParam("id") Long id) {
		return sysGroupServer.get(id);
	}
}
