package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.dao.entity.SysOrganization;
import com.codersoft.cms.dao.mapper.admin.system.SysOrganizationMapper;
import com.codersoft.cms.service.admin.SysOrganizationService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: SysOrganizationServiceImpl
 * @author: Alex.D
 * @create: 2018-07-07 17:14
 * @description: 组织业务处理实现类
 **/
@Service
public class SysOrganizationServiceImpl extends BaseServiceImpl<SysOrganization, Long> implements SysOrganizationService {

    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;

    @Override
    public int updateByIdSelective(SysOrganization sysOrganization) {
        sysOrganization.setModifyTime(new Date());
        return super.updateByIdSelective(sysOrganization);
    }

    /**
     * 验证组织名称是否存在
     *
     * @param orgName 角色名
     * @return
     */
    @Override
    public SysOrganization checkOrgNameIsExist(String orgName) {
        return sysOrganizationMapper.findOrganizationByOrgName(orgName);
    }
}
