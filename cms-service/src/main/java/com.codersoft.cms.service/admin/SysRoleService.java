package com.codersoft.cms.service.admin;

import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.service.common.BaseService;

/**
 * @program: SysRoleService
 * @author: Alex.D
 * @create: 2018-07-07 17:12
 * @description: 角色业务处理接口
 **/
public interface SysRoleService extends BaseService<SysRole, Long> {

    /**
     * 验证角色名是否存在
     *
     * @param roleName 角色名
     * @return
     */
    SysRole checkRoleNameIsExist(String roleName);

}
