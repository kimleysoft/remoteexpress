package com.xiang.server.savemapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.xiang.bean.po.SysGroup;
import java.math.BigDecimal;
import com.xiang.server.exmapper.ExSysGroupMapper;

public interface SaveSysGroupMapper extends ExSysGroupMapper{
	public int save(SysGroup record);
}
