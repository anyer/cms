package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.dao.mapper.admin.system.SysRoleMapper;
import com.codersoft.cms.service.admin.SysRoleService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: SysRoleServiceImpl
 * @author: Alex.D
 * @create: 2018-07-07 17:14
 * @description: 角色业务处理实现类
 **/
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Long> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 验证角色名称是否存在
     *
     * @param roleName 角色名
     * @return
     */
    @Override
    public SysRole checkRoleNameIsExist(String roleName) {
        return sysRoleMapper.findRoleByRoleName(roleName);
    }

    @Override
    public int updateByIdSelective(SysRole sysRole) {
        sysRole.setModifyTime(new Date());
        return super.updateByIdSelective(sysRole);
    }
}
