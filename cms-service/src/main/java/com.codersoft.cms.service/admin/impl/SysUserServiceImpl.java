package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.dao.mapper.SysUserMapper;
import com.codersoft.cms.service.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SysUserServiceImpl
 * @author: Alex.D
 * @create: 2018-07-07 17:14
 * @description: 用户业务处理实现类
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser checkLogin(String username, String password) {

        SysUser sysUser = sysUserMapper.findByUsername(username);
        if(sysUser != null && sysUser.getPassword().equals(password)) {
            return sysUser;
        }
        return null;
    }
}
