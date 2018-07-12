package com.codersoft.cms.dao.mapper;

import com.codersoft.cms.dao.entity.SysOrganization;
import com.codersoft.cms.dao.entity.SysOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOrganizationMapper {
    long countByExample(SysOrganizationExample example);

    int deleteByExample(SysOrganizationExample example);

    int deleteByPrimaryKey(Long orgId);

    int insert(SysOrganization record);

    int insertSelective(SysOrganization record);

    List<SysOrganization> selectByExample(SysOrganizationExample example);

    SysOrganization selectByPrimaryKey(Long orgId);

    int updateByExampleSelective(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    int updateByExample(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    int updateByPrimaryKeySelective(SysOrganization record);

    int updateByPrimaryKey(SysOrganization record);
}