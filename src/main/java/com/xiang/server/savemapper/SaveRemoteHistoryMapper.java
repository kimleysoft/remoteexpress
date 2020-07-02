package com.xiang.server.savemapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.xiang.bean.po.RemoteHistory;
import java.math.BigDecimal;
import com.xiang.server.exmapper.ExRemoteHistoryMapper;

public interface SaveRemoteHistoryMapper extends ExRemoteHistoryMapper{
	public int save(RemoteHistory record);
}
