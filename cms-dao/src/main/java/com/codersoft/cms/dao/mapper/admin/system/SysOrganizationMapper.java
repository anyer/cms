package com.codersoft.cms.dao.mapper.admin.system;

import com.codersoft.cms.dao.entity.SysOrganization;
import com.codersoft.cms.dao.entity.SysOrganizationExample;
import java.util.List;

import com.codersoft.cms.dao.mapper.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysOrganizationMapper extends BaseMapper<SysOrganization, Long> {

    long countByExample(SysOrganizationExample example);

    int deleteByExample(SysOrganizationExample example);

    List<SysOrganization> selectByExample(SysOrganizationExample example);

    int updateByExampleSelective(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    int updateByExample(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    /**
     * 查找对应组织名的组织信息
     * @param orgName 组织名称
     * @return
     */
    SysOrganization findOrganizationByOrgName(String orgName);

}