package com.xiang.server.savemapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import com.xiang.bean.po.UserRemote;
import java.math.BigDecimal;
import com.xiang.server.exmapper.ExUserRemoteMapper;

public interface SaveUserRemoteMapper extends ExUserRemoteMapper{
	public int save(UserRemote record);
}
