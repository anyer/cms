package com.codersoft.cms.dao.mapper.admin.system;

import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.dao.entity.SysUserExample;
import java.util.List;

import com.codersoft.cms.dao.mapper.common.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser, Long> {

    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    List<SysUser> selectByExample(SysUserExample example);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    /**
     * 查找对应用户名的用户信息
     * @param username 登录用户名
     * @return
     */
    SysUser findUserByUsername(String username);

    /**
     *查找对应邮箱的用户信息
     * @param email
     * @return
     */
    SysUser findUserByEmail(String email);
}