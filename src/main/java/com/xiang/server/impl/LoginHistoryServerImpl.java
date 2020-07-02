package com.xiang.server.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.robert.vesta.service.intf.IdService;
import com.xiang.bean.bo.LoginHistoryBo;
import com.xiang.bean.po.LoginHistory;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.LoginHistoryEntityVo;
import com.xiang.bean.vo.LoginHistoryVo;
import com.xiang.bean.vo.UserVo;
import com.xiang.restserver.Page;
import com.xiang.server.LoginHistoryServer;
import com.xiang.server.UserServer;
import com.xiang.service.LoginHistoryService;
/**
 * @author xiang
 * @createDate 2020-03-26 10:02:45
 */
@Service("loginHistoryServer")
public class LoginHistoryServerImpl extends BaseServerImpl implements LoginHistoryServer {
	@Resource
	private IdService idService;
	@Resource
	private LoginHistoryService loginHistoryService;
	@Resource
	private HttpServletRequest  httpServletRequest;
	@Resource
	private UserServer userServer;
	
	@Transactional
	@Override
	public LoginHistoryVo add(LoginHistory po) {
	    preWrapperPo(po);
	    po.setDel(false);
	    po.setId(idService.genId());
	    po.setLoginip(httpServletRequest.getRemoteAddr());
	    po.setGmtCreate(new Date());
		UserVo userVo = userServer.get(po.getUserid());
		po.setNick(userVo.getNick());
		po.setUserName(userVo.getUserName());
		loginHistoryService.save(po);
		return getVo(po);
	}

	@Transactional
	@Override
	public LoginHistoryVo update(LoginHistory po) {
	    preWrapperPo(po);
		loginHistoryService.update(po);
		return getVo(po);
	}

//	private LoginHistory getPo(LoginHistoryBo bo) {
//		LoginHistory po = new LoginHistory();
//		BeanUtils.copyProperties(bo, po);
//		return po;
//	}

	private LoginHistoryVo getVo(LoginHistory po) {
		LoginHistoryVo vo = new LoginHistoryVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}
	private void preWrapperVo(LoginHistoryVo vo) {

	}
    private void preWrapperPo(LoginHistory po) {
	}
	@Override
	public List<LoginHistoryVo> getList(Map<String, Object> querys) {
		querys.put("andDelEqualTo", false);
		List<LoginHistory> poList = loginHistoryService.getList(querys);
		List<LoginHistoryVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(poList)) {
			for (LoginHistory po : poList) {
			LoginHistoryVo vo=getVo(po);
			    preWrapperVo(vo);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public BaseListVo<LoginHistoryVo> queryList(Map<String, Object> querys) {
		List<LoginHistoryVo> list = getList(querys);
		Page page = loginHistoryService.getPage(querys);
		BaseListVo<LoginHistoryVo> baseListVo = new BaseListVo<LoginHistoryVo>();
		baseListVo.setPage(page);
		baseListVo.setResult(list);
		if (!ObjectUtils.isEmpty(list)) {
			baseListVo.setTotal(loginHistoryService.getCount(querys).intValue());
		}
		return baseListVo;
	}

	@Override
	public LoginHistoryVo get(Long id) {
		return view(id);
	}
	@Override
	public LoginHistoryVo view(Long id) {
		LoginHistory po = loginHistoryService.get(id);
		LoginHistoryVo vo = this.getVo(po);
		return vo;
	}
	@Override
	public LoginHistoryEntityVo getEntity(Long id) {
		LoginHistory po = loginHistoryService.get(id);
		return this.copyModel(po, LoginHistoryEntityVo.class);
	}
	@Override
	public List<LoginHistoryVo> gets(List<Long> ids) {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put("andIdIn", ids);
		return getList(querys);
	}
	@Override
	public LoginHistoryBo getBo(LoginHistoryVo vo) {
		LoginHistoryBo bo = new LoginHistoryBo();
		BeanUtils.copyProperties(vo, bo);
		return bo;
	}
	@Override
	public LoginHistoryVo getVo(LoginHistoryBo bo) {
		LoginHistoryVo vo = new LoginHistoryVo();
		BeanUtils.copyProperties(bo, vo);
		return vo;
	}
	@Override
	public List<LoginHistoryVo> getList() {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put(Page.SORT, "-gmtCreate");
		querys.put("andDelEqualTo", false);
		return getList(querys);
	}
}
