package com.codersoft.cms.dao.mapper;

import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.dao.entity.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    long countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);


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