package com.xiang.server;
import java.util.List;
import java.util.Map;

import com.xiang.bean.bo.SysGroupBo;
import com.xiang.bean.bo.SysGroupDelBo;
import com.xiang.bean.po.SysGroup;
import com.xiang.bean.vo.BaseListVo;
import com.xiang.bean.vo.SysGroupEntityVo;
import com.xiang.bean.vo.SysGroupVo;
public interface SysGroupServer extends BaseServer{
	public SysGroupVo add(SysGroupBo bo) throws Exception;
	public SysGroupVo update(SysGroupBo bo) throws Exception;
	public List<SysGroupVo> getList();
	public List<SysGroupVo> getList(Map<String,Object> querys);
	public BaseListVo<SysGroupVo> queryList(Map<String,Object> querys);
	public SysGroupVo get(Long id);
	public SysGroupEntityVo getEntity(Long id);
	public SysGroupVo view(Long id);
	public List<SysGroupVo> gets(List<Long> ids);
	public SysGroupVo getVo(SysGroupBo bo);
	public SysGroupBo getBo(SysGroupVo vo);
	public SysGroup exitOrNo(String alias);
	public List<SysGroup> getByAlias(List<String> alias, Integer typeflag);
	public void batchAdd(List<SysGroup> records);
	public void delGroups(SysGroupDelBo bo);
}
