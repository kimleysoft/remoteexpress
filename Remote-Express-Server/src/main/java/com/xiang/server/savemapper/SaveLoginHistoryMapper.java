package com.xiang.server.savemapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.xiang.bean.po.LoginHistory;
import java.math.BigDecimal;
import com.xiang.server.exmapper.ExLoginHistoryMapper;

public interface SaveLoginHistoryMapper extends ExLoginHistoryMapper{
	public int save(LoginHistory record);
}
