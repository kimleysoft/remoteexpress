package com.xiang.server.savemapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.xiang.bean.po.User;
import java.math.BigDecimal;
import com.xiang.server.exmapper.ExUserMapper;

public interface SaveUserMapper extends ExUserMapper{
	public int save(User record);
}
