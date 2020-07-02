package com.xiang.server.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.robert.vesta.service.intf.IdService;
import com.xiang.bean.bo.RemoteHistoryBo;
import com.xiang.bean.po.Remote;
import com.xiang.bean.po.RemoteHistory;
import com.xiang.bean.po.User;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.RemoteHistoryVo;
import com.xiang.restserver.Page;
import com.xiang.server.RemoteHistoryServer;
import com.xiang.service.RemoteHistoryService;
import com.xiang.service.RemoteService;
import com.xiang.service.UserService;
/**
 * @author xiang
 * @createDate 2020-03-26 10:03:07
 */
@Service("remoteHistoryServer")
public class RemoteHistoryServerImpl extends BaseServerImpl implements RemoteHistoryServer {
	@Resource
	private IdService idService;
	@Resource
	private RemoteHistoryService remoteHistoryService;
	@Resource
	private UserService userService;
	@Resource
	private RemoteService remoteService;
	@Transactional
	@Override
	public RemoteHistoryVo add(Long userid,Long remoteid, String remoteAddr, boolean connectBreakFlag) {
		User user = userService.getUser(userid);
		Remote remote = remoteService.get(remoteid);

		RemoteHistory po = new RemoteHistory();
		po.setDel(false);
		po.setId(idService.genId());
		po.setRemoteid(remoteid);
		po.setUserid(userid);
		po.setUserip(remoteAddr);
		po.setGmtCreate(new Date());
		po.setUserName(user.getUserName());
		po.setRemark(remote.getRemark());
		po.setAccount(remote.getAccount());
		po.setConnectBreakFlag(connectBreakFlag);
		preWrapperPo(po);
		remoteHistoryService.save(po);
		
		Remote record = new Remote();
		record.setId(remoteid);
		record.setConneBreakFlag(connectBreakFlag);
		remoteService.update(record);

		return getVo(po);
	}

	private RemoteHistoryVo getVo(RemoteHistory po) {
		RemoteHistoryVo vo = new RemoteHistoryVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}
	private void preWrapperVo(RemoteHistoryVo vo) {

	}
    private void preWrapperPo(RemoteHistory po) {
	}
	/*@Override
	public List<RemoteHistoryVo> getList(Map<String, Object> querys) {
		Long userId = (Long) querys.get("userId");
		Boolean userDel = (Boolean) querys.get("userDel");
		Long remoteGroupId = (Long) querys.get("remoteGroupId");
		String remoteip = (String) querys.get("remoteip");
		String userName = (String) querys.get("userName");
		String userNick = (String) querys.get("userNick");
		String userRoles = (String) querys.get("userRoles");
		Long userTenantId = (Long) querys.get("userTenantId");
		Long groupId = (Long) querys.get("groupId");
		Long id = (Long) querys.get("id");
		Boolean del = (Boolean) querys.get("del");
		String remoteid = (String) querys.get("remoteid");
		String userip = (String) querys.get("userip");
		String remoteaccount = (String) querys.get("remoteaccount");
		Boolean remotedel = (Boolean) querys.get("remotedel");
		String remotelocalip = (String) querys.get("remotelocalip");
		String remotelocalport = (String) querys.get("remotelocalport");
		String remotepassword = (String) querys.get("remotepassword");
		String remoteport = (String) querys.get("remoteport");
		String remoteremark = (String) querys.get("remoteremark");
		
		List<RemoteHistoryVo> list = remoteHistoryMapper.selectByUserRemote( userId, userDel, remoteGroupId, remoteip, userName, userNick,
	    		 userRoles, userTenantId, groupId, id, del, remoteid, userip, remotedel, remoteaccount,
	    		 remotelocalip, remotelocalport, remotepassword, remoteport, remoteremark);
		return list;
	}*/
    @Override
	public List<RemoteHistoryVo> getList(Map<String, Object> querys) {
		List<RemoteHistory> poList = remoteHistoryService.getList(querys);
		List<RemoteHistoryVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(poList)) {
			for (RemoteHistory po : poList) {
			RemoteHistoryVo vo=getVo(po);
			    preWrapperVo(vo);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public BaseListVo<RemoteHistoryVo> queryList(Map<String, Object> querys) {
		if (querys != null) {
    		String account = (String) querys.get("andAccountLike");
    		if (account != null) {
    			querys.put("andAccountLike", account.replaceAll("\\\\", "\\\\\\\\\\\\"));
			}
    	}
		List<RemoteHistoryVo> list = getList(querys);
		Page page = remoteHistoryService.getPage(querys);
		BaseListVo<RemoteHistoryVo> baseListVo = new BaseListVo<RemoteHistoryVo>();
		baseListVo.setPage(page);
		baseListVo.setResult(list);
		if (!ObjectUtils.isEmpty(list)) {
			baseListVo.setTotal(remoteHistoryService.getCount(querys).intValue());
		}
		return baseListVo;
	}

	@Override
	public RemoteHistoryVo get(Long id) {
		return view(id);
	}
	@Override
	public RemoteHistoryVo view(Long id) {
		RemoteHistory po = remoteHistoryService.get(id);
		RemoteHistoryVo vo = this.getVo(po);
		return vo;
	}
	@Override
	public List<RemoteHistoryVo> gets(List<Long> ids) {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put("andIdIn", ids);
		return getList(querys);
	}
	@Override
	public RemoteHistoryBo getBo(RemoteHistoryVo vo) {
		RemoteHistoryBo bo = new RemoteHistoryBo();
		BeanUtils.copyProperties(vo, bo);
		return bo;
	}
	@Override
	public RemoteHistoryVo getVo(RemoteHistoryBo bo) {
		RemoteHistoryVo vo = new RemoteHistoryVo();
		BeanUtils.copyProperties(bo, vo);
		return vo;
	}

}
