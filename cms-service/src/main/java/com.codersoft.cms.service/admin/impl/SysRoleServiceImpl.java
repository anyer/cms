package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.dao.entity.SysRolePermissionExample;
import com.codersoft.cms.dao.mapper.admin.system.SysRoleMapper;
import com.codersoft.cms.dao.mapper.admin.system.SysRolePermissionMapper;
import com.codersoft.cms.service.admin.SysRoleService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

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

    /**
     * 获取id集合
     *
     * @param permissionIdStr
     * @return
     */
    private List<Long> getPermissionIdList(String permissionIdStr) {
        List<Long> perIdList = new ArrayList<>();
        String perIdStr = permissionIdStr.substring(1, permissionIdStr.length() - 1);
        String[] perStrArray = perIdStr.split(",");
        for (String permissionId : perStrArray) {
            perIdList.add(Long.parseLong(permissionId));
        }
        return perIdList;
    }

    /**
     * 添加对应ID角色的角色权限关系
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID集合字符串
     * @param userName      用户名称
     * @return
     */
    @Override
    public int addRolePermission(Long roleId, String permissionIds, String userName) {

        SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
        sysRolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
        int res = sysRolePermissionMapper.deleteByExample(sysRolePermissionExample);
        if (res >= 0) {
            res = sysRolePermissionMapper.insertBatch(roleId, getPermissionIdList(permissionIds), userName);
        } else {
            res = 0;
        }
        return res;
    }

    /**
     * 删除角色信息及角色权限对应关系
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public int deleteAndPermissionById(Long roleId) {
        int res = sysRoleMapper.deleteByPrimaryKey(roleId);
        if (res > 0) {
            SysRolePermissionExample sysRolePermissionExample = new SysRolePermissionExample();
            sysRolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
            res = sysRolePermissionMapper.deleteByExample(sysRolePermissionExample);
        } else {
            res = 0;
        }
        return res;
    }
}
