package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.utils.MD5SaltUtils;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.dao.mapper.admin.system.SysUserMapper;
import com.codersoft.cms.service.admin.SysUserService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        sysUser.setCreateBy(sysUser.getUserName());
//        sysUser.setModifyBy(sysUser.getUserName());

        return sysUserMapper.insertSelective(sysUser);
    }


    /**
     * 获取分页的用户集合
     *
     * @param sysUser
     * @return
     */
    @Override
    public Map<String, Object> selectUserPageList(SysUser sysUser) {

        List<SysUser> sysUserList = null;
        int count = 0;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if(sysUserList != null) {
                count = sysUserList.size();
            }
//            sysUserList = sysUserMapper.selectUserPageList(sysUser);

            map.put("code", 0);
            map.put("msg", MessageCode.SUCCESS.getMsg());
            map.put("count", count);
            map.put("data", sysUserList);
            return map;
        } catch (Exception ex) {
            map.put("msg", MessageCode.EXCEPTION.getMsg() + " : " + ex.getMessage());
            map.put("code", MessageCode.EXCEPTION.getCode());
            map.put("data", null);
            map.put("count", count);
            return map;
        }
    }
}
