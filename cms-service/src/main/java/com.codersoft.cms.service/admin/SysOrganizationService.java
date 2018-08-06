package com.codersoft.cms.service.admin;

import com.codersoft.cms.dao.entity.SysOrganization;
import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.service.common.BaseService;

/**
 * @program: SysOrganizationService
 * @author: Alex.D
 * @create: 2018-07-07 17:12
 * @description: 组织业务处理接口
 **/
public interface SysOrganizationService extends BaseService<SysOrganization, Long> {

    /**
     * 验证组织名称是否存在
     *
     * @param orgName 角色名
     * @return
     */
     SysOrganization checkOrgNameIsExist(String orgName);

}
