package com.xiang.server.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.robert.vesta.service.intf.IdService;
import com.xiang.bean.bo.RemoteBo;
import com.xiang.bean.bo.UserRemoteQueryBo;
import com.xiang.bean.po.Remote;
import com.xiang.bean.po.SysGroup;
import com.xiang.bean.po.UserRemote;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.RemoteEntityVo;
import com.xiang.bean.vo.RemoteVo;
import com.xiang.constants.RegExpFilter;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.Page;
import com.xiang.server.RemoteServer;
import com.xiang.service.RemoteService;
import com.xiang.service.SysGroupService;
import com.xiang.service.UserRemoteService;
/**
 * @author xiang
 * @createDate 2020-03-26 10:02:56
 */
@Service("remoteServer")
public class RemoteServerImpl extends BaseServerImpl implements RemoteServer {
	@Resource
	private IdService idService;
	@Resource
	private RemoteService remoteService;
	@Resource
	private UserRemoteService userRemoteService;
	@Resource
	private SysGroupService sysGroupService;

	@Transactional
	@Override
	public RemoteVo add(RemoteBo bo) throws Exception {
	    preWrapperBo(bo);
		Remote po = getPo(bo);
		long id = idService.genId();
		po.setId(id);
		po.setDel(false);
		po.setGmtCreate(new Date());
		remoteService.save(po);
		return getVo(po);
	}
	
	@Transactional
	@Override
	public Boolean addexcelremote(Remote po) throws Exception {
		boolean tof = preWrapperAndReturn(po);
		if(!tof) {
			return false;
		}
		long id = idService.genId();
		po.setId(id);
		po.setDel(false);
		po.setGmtCreate(new Date());
		remoteService.save(po);
		return true;
	}

	@Transactional
	@Override
	public RemoteVo update(RemoteBo bo) throws Exception{
		bo.setPort(null);
		bo.setLocalip(null);
		bo.setLocalport(null);
		if(StringUtils.isNotEmpty(bo.getIp()) && !RegExpFilter.validIp(bo.getIp())) {
    		throw new APIException(ErrorCodes.ILLEGAL_REMOTE_IP);
    	}
		Remote po = getPo(bo);
		remoteService.update(po);
		return getVo(po);
	}

	private Remote getPo(RemoteBo bo) {
		Remote po = new Remote();
		BeanUtils.copyProperties(bo, po);
		return po;
	}

	private RemoteVo getVo(Remote po) {
		RemoteVo vo = new RemoteVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}
	private void preWrapperVo(RemoteVo vo) {

	}
    private void preWrapperBo(RemoteBo bo) {
    	if((bo.getPort() <33000 || bo.getPort() > 34000)) {
    		throw new APIException(ErrorCodes.ERROR_PORT);
    	}
    	Map<String, Object>  querys=new HashMap<String,Object>();
    	querys.put("andPortEqualTo", bo.getPort());
    	querys.put("andDelEqualTo", false);
    	if(remoteService.getCount(querys) > 0) {
    		throw new APIException(ErrorCodes.ERROR_PORT_ALREADY);
    	}
    	if(StringUtils.isNotEmpty(bo.getIp()) && !RegExpFilter.validIp(bo.getIp())) {
    		throw new APIException(ErrorCodes.ILLEGAL_REMOTE_IP);
    	}
    	if(StringUtils.isNotEmpty(bo.getLocalip()) && !RegExpFilter.validIp(bo.getLocalip())) {
    		throw new APIException(ErrorCodes.ILLEGAL_LOCAL_IP);
    	}
	}
    
    public Boolean preWrapperAndReturn(Remote bo) {
    	if((bo.getPort() <33000 || bo.getPort() > 34000)) {
    		return false;
    	}
    	Map<String, Object>  querys=new HashMap<String,Object>();
    	querys.put("andPortEqualTo", bo.getPort());
    	querys.put("andDelEqualTo", false);
    	if(remoteService.getCount(querys) > 1) {
    		return false;
    	}
    	if(StringUtils.isNotEmpty(bo.getIp()) && !RegExpFilter.validIp(bo.getIp())) {
    		return false;
    	}
    	if(StringUtils.isNotEmpty(bo.getLocalip()) && !RegExpFilter.validIp(bo.getLocalip())) {
    		return false;
    	}
    	return true;
    }
	@Override
	public List<RemoteVo> getList(Map<String, Object> querys) {
		List<Remote> poList = remoteService.getList(querys);
		List<RemoteVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(poList)) {
			Map<String, Object> gquerys = new HashMap<String, Object>();
			gquerys.put("andTypeflagEqualTo", 2);
			List<SysGroup> groups = sysGroupService.getList(gquerys);
			for (Remote po : poList) {
				RemoteVo vo=getVo(po);
				groups.stream().forEach(g ->{
					if(vo.getGroupid().longValue() == g.getId().longValue())
						vo.setGroupname(g.getAlias());
				});
			    preWrapperVo(vo);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public BaseListVo<RemoteVo> queryList(Map<String, Object> querys) {
		List<RemoteVo> list = getList(querys);
		Page page = remoteService.getPage(querys);
		BaseListVo<RemoteVo> baseListVo = new BaseListVo<RemoteVo>();
		baseListVo.setPage(page);
		baseListVo.setResult(list);
		if (!ObjectUtils.isEmpty(list)) {
			baseListVo.setTotal(remoteService.getCount(querys).intValue());
		}
		return baseListVo;
	}

	@Override
	public RemoteVo get(Long id) {
		return view(id);
	}
	@Override
	public RemoteVo view(Long id) {
		Remote po = remoteService.get(id);
		RemoteVo vo = this.getVo(po);
		return vo;
	}
	@Override
	public RemoteEntityVo getEntity(Long id) {
		Remote po = remoteService.get(id);
		return this.copyModel(po, RemoteEntityVo.class);
	}
	@Override
	public List<RemoteVo> gets(List<Long> ids) {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put("andIdIn", ids);
		return getList(querys);
	}
	@Override
	public RemoteBo getBo(RemoteVo vo) {
		RemoteBo bo = new RemoteBo();
		BeanUtils.copyProperties(vo, bo);
		return bo;
	}
	@Override
	public RemoteVo getVo(RemoteBo bo) {
		RemoteVo vo = new RemoteVo();
		BeanUtils.copyProperties(bo, vo);
		return vo;
	}
	@Override
	public List<RemoteVo> getList() {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put(Page.SORT, "-gmtCreate");
		querys.put("andDelEqualTo", false);
		return getList(querys);
	}
	
	@Override
	public List<RemoteVo> queryUnrelatedList(UserRemoteQueryBo query) {
		List<Remote> poList = remoteService.queryUnrelatedList(query.getRemark(), query.getGroupid(), query.getUserid());
		List<RemoteVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(poList)) {
			for (Remote po : poList) {
			RemoteVo vo=getVo(po);
			    preWrapperVo(vo);
				list.add(vo);
			}
		}
		return list;
	}

	@Transactional
	@Override
	public void delByIds(Long[] ids) throws Exception {
		remoteService.setDelById("remote", ids, true);
		UserRemote record = new UserRemote();
		record.setDel(true);
		Map<String, Object> example = new HashMap<String,Object>();
		example.put("andRemoteidIn", Arrays.asList(ids));
		userRemoteService.updateByExampleSelective(record, example);
	}
	
	@Transactional
	@Override
	public void unDelByIds(Long[] ids) throws Exception {
		remoteService.setDelById("remote", ids, false);
    	List<RemoteVo> list = gets(Arrays.asList(ids));
		for(RemoteVo vo : list) {
			Remote po = new Remote();
			BeanUtils.copyProperties(vo, po);
		}
	}
	
	@Override
	public Integer maxPort() {
		return remoteService.maxPort();
	}
	
	@Override
	public List<Integer> existPort(List<Integer> ports) {
		
		return remoteService.existPort(ports);
	}

	@Transactional
	@Override
	public void batchAdd(List<Remote> data) {
		remoteService.batchAdd(data);
	}

}
