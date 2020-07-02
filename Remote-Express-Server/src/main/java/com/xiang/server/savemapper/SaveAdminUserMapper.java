package com.xiang.server.savemapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.xiang.bean.po.AdminUser;
import java.math.BigDecimal;
import com.xiang.server.exmapper.ExAdminUserMapper;

public interface SaveAdminUserMapper extends ExAdminUserMapper{
	public int save(AdminUser record);
}
