package com.codersoft.cms.dao.mapper.admin.system;

import com.codersoft.cms.dao.entity.SysUserRole;
import com.codersoft.cms.dao.entity.SysUserRoleExample;
import java.util.List;

import com.codersoft.cms.dao.mapper.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole, Long> {

    long countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    List<SysUserRole> selectUserRoleByUserIdList(@Param("userIdList") List<Long> userIdList);

    int insertBatch(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList, @Param("userName") String userName);

}