package com.xiang.server.impl;
import java.util.ArrayList;
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
import com.xiang.bean.bo.SysGroupBo;
import com.xiang.bean.bo.SysGroupDelBo;
import com.xiang.bean.po.Remote;
import com.xiang.bean.po.SysGroup;
import com.xiang.bean.po.User;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.SysGroupEntityVo;
import com.xiang.bean.vo.SysGroupVo;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.Page;
import com.xiang.server.SysGroupServer;
import com.xiang.service.RemoteService;
import com.xiang.service.SysGroupService;
import com.xiang.service.UserService;
/**
 * @author xiang
 * @createDate 2020-03-26 10:03:21
 */
@Service("sysGroupServer")
public class SysGroupServerImpl extends BaseServerImpl implements SysGroupServer {
	@Resource
	private IdService idService;
	@Resource
	private SysGroupService sysGroupService;
	@Resource
	private UserService userService;
	@Resource
	private RemoteService remoteService;
	
	@Transactional
	@Override
	public SysGroupVo add(SysGroupBo bo) throws Exception {
	    preWrapperBo(bo);
		SysGroup po = getPo(bo);
		long id = idService.genId();
		po.setId(id);
		po.setDel(false);
		po.setGmtCreate(new Date());
		sysGroupService.save(po);
		return getVo(po);
	}

	@Transactional
	@Override
	public SysGroupVo update(SysGroupBo bo) throws Exception{
	    preWrapperBo(bo);
		SysGroup po = getPo(bo);
		sysGroupService.update(po);
		return getVo(po);
	}

	private SysGroup getPo(SysGroupBo bo) {
		SysGroup po = new SysGroup();
		BeanUtils.copyProperties(bo, po);
		return po;
	}

	private SysGroupVo getVo(SysGroup po) {
		SysGroupVo vo = new SysGroupVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}
	private void preWrapperVo(SysGroupVo vo) {

	}
    private void preWrapperBo(SysGroupBo bo) throws Exception {
    	if(StringUtils.isBlank(bo.getAlias())) {
    		throw new APIException(ErrorCodes.ERROR_PARAM, "用戶組名稱不能為空");
    	}
    	if(bo.getTypeflag() == null) {
    		throw new APIException(ErrorCodes.ERROR_PARAM, "用戶組類型不能為空");
    	}
	}
	@Override
	public List<SysGroupVo> getList(Map<String, Object> querys) {
		List<SysGroup> poList = sysGroupService.getList(querys);
		List<SysGroupVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(poList)) {
			for (SysGroup po : poList) {
			SysGroupVo vo=getVo(po);
			    preWrapperVo(vo);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public BaseListVo<SysGroupVo> queryList(Map<String, Object> querys) {
		List<SysGroupVo> list = getList(querys);
		Page page = sysGroupService.getPage(querys);
		BaseListVo<SysGroupVo> baseListVo = new BaseListVo<SysGroupVo>();
		baseListVo.setPage(page);
		baseListVo.setResult(list);
		if (!ObjectUtils.isEmpty(list)) {
			baseListVo.setTotal(sysGroupService.getCount(querys).intValue());
		}
		return baseListVo;
	}

	@Override
	public SysGroupVo get(Long id) {
		return view(id);
	}
	@Override
	public SysGroupVo view(Long id) {
		SysGroup po = sysGroupService.get(id);
		SysGroupVo vo = this.getVo(po);
		return vo;
	}
	@Override
	public SysGroupEntityVo getEntity(Long id) {
		SysGroup po = sysGroupService.get(id);
		return this.copyModel(po, SysGroupEntityVo.class);
	}
	@Override
	public List<SysGroupVo> gets(List<Long> ids) {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put("andIdIn", ids);
		return getList(querys);
	}
	@Override
	public SysGroupBo getBo(SysGroupVo vo) {
		SysGroupBo bo = new SysGroupBo();
		BeanUtils.copyProperties(vo, bo);
		return bo;
	}
	@Override
	public SysGroupVo getVo(SysGroupBo bo) {
		SysGroupVo vo = new SysGroupVo();
		BeanUtils.copyProperties(bo, vo);
		return vo;
	}
	@Override
	public List<SysGroupVo> getList() {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put(Page.SORT, "-gmtCreate");
		querys.put("andDelEqualTo", false);
		return getList(querys);
	}

	@Override
	public SysGroup exitOrNo(String alias) {
		SysGroup group = sysGroupService.exitOrNo(alias);
		if(group != null) {
			return group;
		}else {
			return null;
		}
	}
	
	@Override
	public List<SysGroup> getByAlias(List<String> alias, Integer typeflag) {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put("andDelEqualTo", false);
		querys.put("andAliasIn", alias);
		querys.put("andTypeflagEqualTo", typeflag);
		return sysGroupService.getList(querys);
	}

	@Transactional
	@Override
	public void batchAdd(List<SysGroup> records) {
		sysGroupService.batchSave(records);
	}

	@Transactional
	@Override
	public void delGroups(SysGroupDelBo bo) {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put("andDelEqualTo", false);
		querys.put("page", 1);
		querys.put("limit", 1);
		if(bo.getTypeflag()) {
			querys.put("andGroupIdIn", bo.getIds());
			List<User> users = userService.getList(querys);
			if(!users.isEmpty()) {
				throw new APIException(ErrorCodes.HAD_DATA_IN_GROUP);
			}
		}else {
			querys.put("andGroupidIn", bo.getIds());
			List<Remote> remotes = remoteService.getList(querys);
			if(!remotes.isEmpty()) {
				throw new APIException(ErrorCodes.HAD_DATA_IN_GROUP);
			}
		}
		setDelById("sys_group", bo.getIds().toArray(new Long[2]), true);		
	}
}
