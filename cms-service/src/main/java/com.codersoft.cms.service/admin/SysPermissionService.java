package com.codersoft.cms.service.admin;

import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.service.common.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @program: SysPermissionService
 * @author: Alex.D
 * @create: 2018-07-12 15:16
 * @description: 权限相关业务操作接口
 **/
public interface SysPermissionService extends BaseService<SysPermission, Long> {

    /**
     * 获取对应用户ID的权限集合
     *
     * @param userId
     * @return
     */
    List<SysPermission> selectPermissionListByUserId(Long userId);

    /**
     * 获取目录类型权限集合
     *
     * @return
     */
    List<DirectoryPermissionDto> selectDirectoryPermissionList();

    /**
     * 根据父Id查询各级菜单类型权限集合
     *
     * @param parentId 父ID
     * @return
     */
    List<DirectoryPermissionDto> selectMenuPermissionListByParentId(Long parentId);

    /**
     * 获取菜单权限级别对应的父权限
     *
     * @param perLevel 权限级别
     * @return
     */
    List<SysPermission> selectParentPermissionListByPerLevel(Integer perLevel);

    /**
     * 获取对应角色ID的权限树
     *
     * @param roleId 角色ID
     * @return
     */
    List<Map<String, Object>> permissionTree(Long roleId);

    /**
     * 删除对应权限ID的权限及权限角色关系
     *
     * @param permissionId
     * @return
     */
    int deleteAndRoleById(Long permissionId);
}
