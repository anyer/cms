package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.common.utils.MD5SaltUtils;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.dao.mapper.admin.system.SysUserMapper;
import com.codersoft.cms.service.admin.SysUserService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
/**
 * @program: SysUserServiceImpl
 * @author: Alex.D
 * @create: 2018-07-07 17:14
 * @description: 用户业务处理实现类
 **/
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Long> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser checkLogin(String username, String password) {

        //获取对应用户名的用户信息
        SysUser sysUser = sysUserMapper.findUserByUsername(username);
        //密码（含Salt值）校验
        if(sysUser != null && MD5SaltUtils.cryptographicCheck(sysUser.getPassword(),password,sysUser.getSalt())) {
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser checkUserNameIsExist(String userName) {
        //获取对应用户名的用户信息
        SysUser sysUser = sysUserMapper.findUserByUsername(userName);
        return sysUser != null ? sysUser : null;
    }

    @Override
    public SysUser checkEmailIsExist(String email) {
        //获取对应邮箱的用户信息
        SysUser sysUser = sysUserMapper.findUserByEmail(email);
        return sysUser != null ? sysUser : null;
    }

    @Override
    public int register(SysUser sysUser) {

        String salt = MD5SaltUtils.getSalt();
        //密码加密：  MD5(MD5(用户密码)+salt)
        String password = MD5SaltUtils.getMD5String(MD5SaltUtils.getMD5String(sysUser.getPassword())+salt);

        sysUser.setSalt(salt);
        sysUser.setPassword(password);

        return sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public int addSelective(SysUser sysUser) {
        String salt = MD5SaltUtils.getSalt();
        //密码加密：  MD5(MD5(用户密码)+salt)
        String password = MD5SaltUtils.getMD5String(MD5SaltUtils.getMD5String(sysUser.getPassword()) + salt);
        if (StringUtils.isEmpty(sysUser.getHeaderImg())) {
            sysUser.setHeaderImg("face.jpg");
        }
        sysUser.setSalt(salt);
        sysUser.setPassword(password);
        return super.addSelective(sysUser);
    }

    @Override
    public int updateByIdSelective(SysUser sysUser) {

        int passwordLength = sysUser.getPassword().length();
        //密码修改
        if(passwordLength >= 6 && passwordLength <= 20) {
            String salt = MD5SaltUtils.getSalt();
            //密码加密：  MD5(MD5(用户密码)+salt)
            String password = MD5SaltUtils.getMD5String(MD5SaltUtils.getMD5String(sysUser.getPassword()) + salt);
            //更新密码及盐值
            sysUser.setSalt(salt);
            sysUser.setPassword(password);
        }

        if (StringUtils.isEmpty(sysUser.getHeaderImg())) {
            sysUser.setHeaderImg("face.jpg");
        }
        sysUser.setModifyTime(new Date());
        return super.updateByIdSelective(sysUser);
    }

    @Override
    public int updateLastLoginTime(SysUser sysUser) {
        sysUser.setLastLoginTime(new Date());
        return super.updateByIdSelective(sysUser);
    }
}
