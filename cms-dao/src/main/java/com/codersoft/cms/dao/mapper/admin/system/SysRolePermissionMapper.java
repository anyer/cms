package com.codersoft.cms.dao.mapper.admin.system;

import com.codersoft.cms.dao.entity.SysRolePermission;
import com.codersoft.cms.dao.entity.SysRolePermissionExample;
import java.util.List;

import com.codersoft.cms.dao.mapper.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission, Long> {

    long countByExample(SysRolePermissionExample example);

    int deleteByExample(SysRolePermissionExample example);

    List<SysRolePermission> selectByExample(SysRolePermissionExample example);

    int updateByExampleSelective(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    int updateByExample(@Param("record") SysRolePermission record, @Param("example") SysRolePermissionExample example);

    int insertBatch(@Param("roleId") Long userId, @Param("perIdList") List<Long> roleIdList, @Param("userName") String userName);

}