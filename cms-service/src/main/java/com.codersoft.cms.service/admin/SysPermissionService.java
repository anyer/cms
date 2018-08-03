package com.codersoft.cms.service.admin;

import com.codersoft.cms.common.bean.ResultPages;
import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;

import java.util.List;
import java.util.Map;

/**
 * @program: SysPermissionService
 * @author: Alex.D
 * @create: 2018-07-12 15:16
 * @description: 权限相关业务操作接口
 **/
public interface SysPermissionService {

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
     * 获取分页的权限集合
     *
     * @param sysPermission
     * @return
     */
    Map<String, Object> selectPermissionPageList(SysPermission sysPermission);

    /**
     * 添加权限信息
     *
     * @param sysPermission
     * @return
     */
    int addSysPermission(SysPermission sysPermission);

    /**
     * 获取对应ID的权限信息
     *
     * @param permissionId 权限ID
     * @return
     */
    SysPermission selectSysPermissionById(Long permissionId);

    /**
     * 获取菜单权限级别对应的父权限
     *
     * @param perLevel 权限级别
     * @return
     */
    List<SysPermission> selectParentPermissionListByPerLevel(Integer perLevel);

    /**
     * 更新权限信息
     *
     * @param sysPermission
     * @return
     */
    int updatePermission(SysPermission sysPermission);

    /**
     * 删除权限信息
     *
     * @param permissionId 权限ID
     * @return
     */
    int deletePermission(Long permissionId);
}
