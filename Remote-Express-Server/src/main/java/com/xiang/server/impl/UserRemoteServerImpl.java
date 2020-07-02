package com.xiang.server.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.xiang.bean.bo.UserRemoteBo;
import com.xiang.bean.bo.UserRemoteUpdateBo;
import com.xiang.bean.po.UserRemote;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.RemoteVo;
import com.xiang.bean.vo.UserRemoteRelationVo;
import com.xiang.bean.vo.UserRemoteEntityVo;
import com.xiang.bean.vo.UserRemoteVo;
import com.xiang.bean.vo.UserVo;
import com.xiang.restserver.Page;
import com.xiang.server.RemoteServer;
import com.xiang.server.UserRemoteServer;
import com.xiang.server.UserServer;
import com.xiang.service.UserRemoteService;

/**
 * @author xiang
 * @createDate 2020-03-26 10:03:34
 */
@Service("userRemoteServer")
public class UserRemoteServerImpl extends BaseServerImpl implements UserRemoteServer {
	@Resource
	private IdService idService;
	@Resource
	private UserRemoteService userRemoteService;
	@Resource
	private RemoteServer remoteServer;
	@Resource
	private UserServer userServer;

	@Transactional
	@Override
	public UserRemoteVo add(UserRemote po) {
		preWrapperPo(po);
		po.setGmtCreate(new Date());
		userRemoteService.save(po);
		return getVo(po);
	}

	@Transactional
	@Override
	public List<UserRemoteVo> addList(UserRemoteBo bo) {
		List<UserRemote> records = new ArrayList<>();
		List<UserRemoteVo> result = new ArrayList<>();
		UserVo userVo = userServer.get(bo.getUserid());
		List<UserRemoteVo> existsRel = queryRemotesByUserid(bo.getUserid());
		List<Long> existsIds = new ArrayList<>();
		List<Integer> ports = new ArrayList<Integer>();
		for (UserRemoteVo e : existsRel) {
			existsIds.add(e.getRemoteid());
		}
		List<RemoteVo> remotes = remoteServer.gets(Arrays.asList(bo.getRemoteids()));
		List<RemoteVo> nRemotes = new ArrayList<>();
		for (RemoteVo remoteVo : remotes) {
			if (!existsIds.contains(remoteVo.getId())) {
				UserRemote po = new UserRemote();
				po.setDel(false);
				po.setId(idService.genId());
				po.setUserid(bo.getUserid());
				po.setRemoteid(remoteVo.getId());
				po.setUserName(userVo.getUserName());
				po.setUserip(remoteVo.getIp());
				po.setRemark(remoteVo.getRemark());
				po.setAccount(remoteVo.getAccount());
				po.setPassword(remoteVo.getPassword());
				preWrapperPo(po);
				po.setGmtCreate(new Date());
				result.add(getVo(po));
				records.add(po);
				ports.add(remoteVo.getPort());
				nRemotes.add(remoteVo);
			}
		}
		if (!records.isEmpty()) {
			userRemoteService.batchSave(records);
		}
		return result;
	}

	@Transactional
	@Override
	public UserRemoteVo update(UserRemoteUpdateBo bo) {
		UserRemote po = new UserRemote();
		po.setId(bo.getId());
		po.setAccount(bo.getAccount());
		po.setPassword(bo.getPassword());
		po.setGmtModified(new Date());
		userRemoteService.update(po);
		return getVo(po);
	}

	private UserRemoteVo getVo(UserRemote po) {
		UserRemoteVo vo = new UserRemoteVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}

	private void preWrapperVo(UserRemoteVo vo) {

	}

	private void preWrapperPo(UserRemote po) {
	}

	@Override
	public List<UserRemoteVo> getList(Map<String, Object> querys) {
		querys.put("andDelEqualTo", false);
		List<UserRemote> poList = userRemoteService.getList(querys);
		List<UserRemoteVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(poList)) {
			for (UserRemote po : poList) {
				UserRemoteVo vo = getVo(po);
				preWrapperVo(vo);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public BaseListVo<UserRemoteVo> queryList(Map<String, Object> querys) {
		List<UserRemoteVo> list = getList(querys);
		Page page = userRemoteService.getPage(querys);
		BaseListVo<UserRemoteVo> baseListVo = new BaseListVo<UserRemoteVo>();
		baseListVo.setPage(page);
		baseListVo.setResult(list);
		if (!ObjectUtils.isEmpty(list)) {
			baseListVo.setTotal(userRemoteService.getCount(querys).intValue());
		}
		return baseListVo;
	}

	@Override
	public List<UserRemoteVo> queryRemotesByUserid(Long userid) {
		Map<String, Object> querys = new HashMap<String, Object>();
		querys.put("andUseridEqualTo", userid);
		return getList(querys);
	}

	@Override
	public UserRemoteVo get(Long id) {
		return view(id);
	}

	@Override
	public UserRemoteVo view(Long id) {
		UserRemote po = userRemoteService.get(id);
		if (po.getDel()) {
			return null;
		}
		UserRemoteVo vo = this.getVo(po);
		return vo;
	}

	@Override
	public UserRemoteEntityVo getEntity(Long id) {
		UserRemote po = userRemoteService.get(id);
		return this.copyModel(po, UserRemoteEntityVo.class);
	}

	@Override
	public List<UserRemoteVo> gets(List<Long> ids) {
		Map<String, Object> querys = new HashMap<String, Object>();
		querys.put("andIdIn", ids);
		return getList(querys);
	}

	@Override
	public UserRemoteBo getBo(UserRemoteVo vo) {
		UserRemoteBo bo = new UserRemoteBo();
		BeanUtils.copyProperties(vo, bo);
		return bo;
	}

	@Override
	public UserRemoteVo getVo(UserRemoteBo bo) {
		UserRemoteVo vo = new UserRemoteVo();
		BeanUtils.copyProperties(bo, vo);
		return vo;
	}

	@Override
	public List<UserRemoteVo> getList() {
		Map<String, Object> querys = new HashMap<String, Object>();
		querys.put(Page.SORT, "-gmtCreate");
		querys.put("andDelEqualTo", false);
		return getList(querys);
	}

	@Transactional
	@Override
	public void delByIds(Long[] ids, String remoteAddr) {
		setDelById("user_remote", ids, true);
	}

	@Override
	public List<UserRemoteRelationVo> queryUserRemoteDetailList(Long userid, Long remoteid) {
		Map<String, Object> querys = new HashMap<String, Object>();
		querys.put("andUseridEqualTo", userid);
		querys.put("andDelEqualTo", false);
		if(null != remoteid) {
			querys.put("andRemoteidEqualTo", remoteid);
		}
		List<UserRemoteVo> relateVo = getList(querys);
		List<UserRemoteRelationVo> result = new ArrayList<>();
		if(!relateVo.isEmpty()) {
			UserVo userVo = userServer.get(userid);
			List<Long> rids = new ArrayList<>();
			for(UserRemoteVo ur : relateVo) {
				rids.add(ur.getRemoteid());
			}
			List<RemoteVo> list = remoteServer.gets(rids);
			for(RemoteVo v : list) {
				result.add(UserRemoteRelationVo.convert(userVo, v));
			}
		}
		return result;
	}
}
