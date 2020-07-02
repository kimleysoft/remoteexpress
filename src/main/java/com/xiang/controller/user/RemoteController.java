package com.xiang.controller.user;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xiang.restserver.LoginToken;
import com.xiang.restserver.OkRequest;
import com.xiang.bean.vo.XAuthToken;
import com.xiang.restserver.ErrorCodes;
import com.xiang.bean.bo.RemoteBo;
import com.xiang.bean.bo.UserRemoteQueryBo;
import com.xiang.server.RemoteServer;
import com.xiang.server.exmapper.ExRemoteMapper;

@RestController
@RequestMapping(value = "/remote")
public class RemoteController {
	@Resource
	private RemoteServer remoteServer;
	@Resource
	private ExRemoteMapper exRemoteMapper;
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(@LoginToken XAuthToken authToken, @RequestBody RemoteBo bo) throws Exception {
		return remoteServer.add(bo);
	}
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Object del(@LoginToken XAuthToken authToken,@RequestBody Long[] ids) throws Exception {
		remoteServer.delByIds(ids);
		return ErrorCodes.OK;
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(@LoginToken XAuthToken authToken,@RequestBody RemoteBo bo) throws Exception {
		remoteServer.update(bo);
		return ErrorCodes.OK;
	}
	@RequestMapping(value = "/list/unrelated", method = RequestMethod.POST)
	public Object unrelatedList(@LoginToken XAuthToken authToken,@RequestBody UserRemoteQueryBo query) {
		return remoteServer.queryUnrelatedList(query);
	}
	@RequestMapping(value = "/port/max", method = RequestMethod.GET)
	public Object maxPort(@LoginToken XAuthToken authToken) {
		return remoteServer.maxPort();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object list(@LoginToken XAuthToken authToken,@RequestBody(required=false) Map<String,Object> querys) {
		return remoteServer.queryList(querys);
	}
	@RequestMapping(value = "/get",method =RequestMethod.GET)
	public Object get(@RequestParam("id") Long id) {
		return remoteServer.get(id);
	}
	@RequestMapping(value = "/ip",method =RequestMethod.GET)
	public Object remoteIp() {
		return OkRequest.getRemoteIp();
	}
	
	@RequestMapping(value = "/offline", method = RequestMethod.POST)
	public Object offLine(@LoginToken XAuthToken authToken, @RequestBody Long[] ids) {
		remoteServer.setFlag("remote", "conne_break_flag" ,ids, true);
		return ErrorCodes.OK;
	}
	
	@RequestMapping(value = "/statisticsRemoteOnLine",method =RequestMethod.GET)
	public int statisticsRemoteOnLine(@LoginToken XAuthToken authToken,@RequestParam("conneBreakFlag") Boolean conneBreakFlag) {
		RemoteBo bo = new RemoteBo();
		bo.setConneBreakFlag(conneBreakFlag);
		int remoteOnLine = exRemoteMapper.statisticsRemoteOnLine(bo);
		
		return remoteOnLine;
	}
	
	
}
