package com.codersoft.cms.dao.mapper.admin.system;

import com.codersoft.cms.dao.entity.SysLog;
import com.codersoft.cms.dao.entity.SysLogExample;
import java.util.List;

import com.codersoft.cms.dao.mapper.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysLogMapper extends BaseMapper<SysLog, Long> {

    long countByExample(SysLogExample example);

    int deleteByExample(SysLogExample example);

    List<SysLog> selectByExample(SysLogExample example);

    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

}