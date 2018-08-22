package com.codersoft.cms.dao.mapper.admin.system;

import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.dao.entity.SysRoleExample;

import java.util.List;

import com.codersoft.cms.dao.mapper.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper extends BaseMapper<SysRole, Long> {

    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    List<SysRole> selectByExample(SysRoleExample example);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    /**
     * 查找对应角色名的角色信息
     *
     * @param roleName 角色名称
     * @return
     */
    SysRole findRoleByRoleName(String roleName);

    /**
     * 获取对应用户ID的角色集合
     *
     * @param userId
     * @return
     */
    List<SysRole> selectByUserId(@Param("userId") Long userId);

}