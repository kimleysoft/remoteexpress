package com.xiang.server.mapper;

import com.xiang.bean.po.SysGroup;
import com.xiang.bean.po.SysGroupExample;
import com.xiang.server.savemapper.SaveSysGroupMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysGroupMapper extends SaveSysGroupMapper {
    long countByExample(SysGroupExample example);

    List<SysGroup> selectByExample(SysGroupExample example);

    @Select({
        "select",
        "id, del, alias, typeflag, remark, gmt_modified, gmt_create",
        "from sys_group",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.xiang.server.mapper.SysGroupMapper.BaseResultMap")
    SysGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysGroup record, @Param("example") SysGroupExample example);

    int updateByExample(@Param("record") SysGroup record, @Param("example") SysGroupExample example);

    int updateByPrimaryKeySelective(SysGroup record);

    @Update({
        "update sys_group",
        "set del = #{del,jdbcType=BIT},",
          "alias = #{alias,jdbcType=VARCHAR},",
          "typeflag = #{typeflag,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysGroup record);
}