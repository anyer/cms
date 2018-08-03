package com.codersoft.cms.dao.mapper;

import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.dao.entity.SysPermissionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysPermissionMapper {

    long countByExample(SysPermissionExample example);

    int deleteByExample(SysPermissionExample example);

    int deleteByPrimaryKey(Long permissionId);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> selectByExample(SysPermissionExample example);

    SysPermission selectByPrimaryKey(Long permissionId);

    SysPermission selectPermissionByPrimaryKey(Long permissionId);

    int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionExample example);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    /**
     * 获取全部权限数量统计
     *
     * @param sysPermission
     * @return
     */
    Long selectPermissionCount(SysPermission sysPermission);

    /**
     * 获取分页的权限集合
     *
     * @param sysPermission
     * @return
     */
    List<SysPermission> selectPermissionPageList(SysPermission sysPermission);

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
}