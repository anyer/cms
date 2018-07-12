package com.codersoft.cms.service.admin;

import com.codersoft.cms.dao.dto.DirectoryPermissionDto;

import java.util.List;

/**
 * @program: SysPermissionService
 * @author: Alex.D
 * @create: 2018-07-12 15:16
 * @description: 权限相关业务操作接口
 **/
public interface SysPermissionService {

    /**
     * 获取目录类型权限集合
     * @return
     */
    List<DirectoryPermissionDto> selectDirectoryPermissionList();

    /**
     * 根据父Id查询各级菜单类型权限集合
     * @param parentId 父ID
     * @return
     */
    List<DirectoryPermissionDto> selectMenuPermissionListByParentId(Long parentId);
}
