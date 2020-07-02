package com.xiang.server.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.robert.vesta.service.intf.IdService;
import com.xiang.bean.bo.AdminUserBo;
import com.xiang.bean.po.AdminUser;
import com.xiang.bean.vo.AdminUserEntityVo;
import com.xiang.bean.vo.AdminUserVo;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.XAuthToken;
import com.xiang.constants.RolesConstants;
import com.xiang.restserver.APIException;
import com.xiang.restserver.ErrorCodes;
import com.xiang.restserver.Page;
import com.xiang.server.AdminUserServer;
import com.xiang.service.AdminUserService;
import com.xiang.service.CacheService;
import com.xiang.service.UserService;
/**
 * @author xiang
 * @createDate 2020-03-19 17:32:46
 */
@Service("adminUserServer")
public class AdminUserServerImpl extends BaseServerImpl implements AdminUserServer {
	@Resource
	private IdService idService;
	@Resource
	private AdminUserService adminUserService;
	@Resource
	private CacheService cacheService;
	@Resource
	private UserService userService;
	
	@Transactional
	@Override
	public AdminUserVo add(AdminUserBo bo) {
	    preWrapperBo(bo);
		AdminUser po = getPo(bo);
		long id = idService.genId();
		po.setId(id);
		if (bo.getPassword() != null) {
			String md5Hex = DigestUtils.md5Hex(bo.getPassword());
			po.setPassword(md5Hex);
		} else {
			throw new APIException(ErrorCodes.ERROR_PARAM);
		}
		po.setRoles("user");
		po.setGmtCreate(new Date());
		adminUserService.save(po);
		return getVo(po);
	}

	@Transactional
	@Override
	public AdminUserVo update(AdminUserBo bo) {
		AdminUser po = getPo(bo);
		po.setRoles("user");
		adminUserService.update(po);
		return getVo(po);
	}

	private AdminUser getPo(AdminUserBo bo) {
		AdminUser po = new AdminUser();
		BeanUtils.copyProperties(bo, po);
		return po;
	}

	private AdminUserVo getVo(AdminUser po) {
		AdminUserVo vo = new AdminUserVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}
	private void preWrapperVo(AdminUserVo vo) {

	}
    private void preWrapperBo(AdminUserBo bo) {
    	// 不能添加重複的賬戶
    	Map<String, Object> querys = new HashMap<String,Object>();
    	querys.put("andUserNameEqualTo", bo.getUserName());
    	List<AdminUser> list = adminUserService.getList(querys);
    	if (list != null && list.size() > 0) {
    		throw new APIException(ErrorCodes.USER_EXIST);
		}
	}
	@Override
	public List<AdminUserVo> getList(Map<String, Object> querys) {
		List<AdminUser> poList = adminUserService.getList(querys);
		List<AdminUserVo> list = new ArrayList<>();
		if (!ObjectUtils.isEmpty(poList)) {
			for (AdminUser po : poList) {
			AdminUserVo vo=getVo(po);
			    preWrapperVo(vo);
				list.add(vo);
			}
		}
		return list;
	}

	@Override
	public BaseListVo<AdminUserVo> queryList(Map<String, Object> querys) {
		List<AdminUserVo> list = getList(querys);
		Page page = adminUserService.getPage(querys);
		BaseListVo<AdminUserVo> baseListVo = new BaseListVo<AdminUserVo>();
		baseListVo.setPage(page);
		baseListVo.setResult(list);
		if (!ObjectUtils.isEmpty(list)) {
			baseListVo.setTotal(adminUserService.getCount(querys).intValue());
		}
		return baseListVo;
	}

	@Override
	public AdminUserVo get(Long id) {
		AdminUser po = adminUserService.get(id);
		AdminUserVo vo = this.getVo(po);
		vo.setAdmin(true);
		return vo;
	}
	@Override
	public AdminUserEntityVo getEntity(Long id) {
		AdminUser po = adminUserService.get(id);
		return this.copyModel(po, AdminUserEntityVo.class);
	}
	@Override
	public List<AdminUserVo> gets(List<Long> ids) {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put("andIdIn", ids);
		return getList(querys);
	}
	@Override
	public AdminUserBo getBo(AdminUserVo vo) {
		AdminUserBo bo = new AdminUserBo();
		BeanUtils.copyProperties(vo, bo);
		return bo;
	}
	@Override
	public AdminUserVo getVo(AdminUserBo bo) {
		AdminUserVo vo = new AdminUserVo();
		BeanUtils.copyProperties(bo, vo);
		return vo;
	}
	@Override
	public List<AdminUserVo> getList() {
		Map<String, Object>  querys=new HashMap<String,Object>();
		querys.put(Page.SORT, "-gmtCreate");
		querys.put("andDelEqualTo", false);
		return getList(querys);
	}

	@Override
	public AdminUserVo getUser(String userName) {
		AdminUser user = adminUserService.getUser(userName);
		return getVo(user);
	}
	@Transactional
	@Override
	public void changePassword(Long id, String originPassword, String password) {
		AdminUser user = adminUserService.get(id);
		originPassword = DigestUtils.md5Hex(originPassword);
		if (user.getPassword().equals(originPassword)) {
			String newpass = DigestUtils.md5Hex(password);
			if(newpass.equals(originPassword)) {
				throw new APIException(ErrorCodes.NEWPASSWORD_NOT_EQUALS);
			}
			AdminUser userUpdate = new AdminUser();
			userUpdate.setId(user.getId());
			userUpdate.setPassword(DigestUtils.md5Hex(password));
			adminUserService.update(userUpdate);
		} else {
			throw new APIException(ErrorCodes.PASSWORD);
		}
	}
	
	@Override
	@Cacheable(value = "userCache", key = "T(com.xiang.constants.CachePrefixConstants).ADMIN_USER_INFO + +#id", unless = "#result == null")
	public AdminUserVo getUserInfo(Long id) {
		AdminUserVo vo = get(id);
		List<String> menus = null;
		List<String> perms = null;
		menus = new ArrayList<>(1);
		menus.add("all");
		perms = new ArrayList<>(1);
		perms.add("all");
		vo.setMenus(menus);
		vo.setPerms(new HashSet<String>(perms));
		return vo;
	}

	@Override
	public ErrorCodes delById(XAuthToken authToken, Long[] ids) {
		AdminUserVo tokenUser = get(authToken.getId());
		if(!tokenUser.getRoles().contains(RolesConstants.ADMIN_ROLE)) {
			return ErrorCodes.FORBIDDEN;
		}
		List<Long> idsArr = Arrays.asList(ids);
		if(idsArr.contains(authToken.getId())) {
			return ErrorCodes.NONSUPPORT;
		}
		
		adminUserService.setDelById("admin_user", ids, true);
		return ErrorCodes.OK;
	}

	@Override
	public void resetPassword(Long id, String password) {
		AdminUser vo = adminUserService.get(id);
		if(null != vo) {
			AdminUser record = new AdminUser();
			record.setId(id);
			record.setPassword(DigestUtils.md5Hex(password));
			adminUserService.update(record);
		} else {
			throw new APIException(ErrorCodes.USER_EXIST);
		}
	}

}
