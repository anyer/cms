package com.codersoft.cms.dao.mapper.admin.system;

import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.dao.entity.SysPermissionExample;

import java.util.List;

import com.codersoft.cms.dao.mapper.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionMapper extends BaseMapper<SysPermission, Long> {

    long countByExample(SysPermissionExample example);

    int deleteByExample(SysPermissionExample example);

    List<SysPermission> selectByExample(SysPermissionExample example);

    int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    /**
     * 通过ID查询对应权限（含父权限名称）
     * @param permissionId
     * @return
     */
    SysPermission selectPermissionAndParentNameByPrimaryKey(Long permissionId);

    /**
     * 获取目录类型权限集合
     *
     * @return
     */
    List<DirectoryPermissionDto> selectDirectoryPermissionList();

    /**
     * 获取对应父ID的菜单类型权限集合
     *
     * @param parentId 父级权限ID
     * @return
     */
    List<SysPermission> selectMenuPermissionListByParentId(@Param("parentId") Long parentId);

    /**
     * 获取菜单权限级别对应的父权限
     *
     * @param perLevel     权限级别
     * @return
     */
    List<SysPermission> selectParentListByPerLevel(@Param("perLevel") Integer perLevel);

    /**
     * 查询对应用户ID的权限集合
     *
     * @param userId 用户ID
     * @return
     */
    List<SysPermission> selectListByUserId(@Param("userId") Long userId);

    /**
     * 查询对应角色ID的权限集合
     *
     * @param roleId
     * @return
     */
    List<SysPermission> selectListByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询对应角色ID集合的权限集合
     *
     * @param roleIdList
     * @return
     */
    List<SysPermission> selectListByRoleIdList(@Param("roleIdList") List<Long> roleIdList);
}