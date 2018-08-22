package com.codersoft.cms.service.admin;

import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.service.common.BaseService;

import java.util.List;

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

    /**
     * 添加对应ID角色的角色权限关系
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID集合字符串
     * @param userName      用户名称
     * @return
     */
    int addRolePermission(Long roleId, String permissionIds, String userName);

    /**
     * 删除角色信息及角色权限对应关系
     *
     * @param roleId 角色ID
     * @return
     */
    int deleteAndPermissionById(Long roleId);

    /**
     * 获取对应用户ID的角色集合
     *
     * @param userId 用户ID
     * @return
     */
    List<SysRole> selectRoleListByUserId(Long userId);
}
