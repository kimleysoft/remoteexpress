package com.xiang.server.savemapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.xiang.bean.po.Remote;
import java.math.BigDecimal;
import com.xiang.server.exmapper.ExRemoteMapper;

public interface SaveRemoteMapper extends ExRemoteMapper{
	public int save(Remote record);
}
