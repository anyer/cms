package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.common.utils.MD5SaltUtils;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.dao.entity.SysUserExample;
import com.codersoft.cms.dao.mapper.admin.system.SysUserMapper;
import com.codersoft.cms.service.admin.SysUserService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

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
        SysUser sysUser = checkUserNameIsExist(username);
        //密码（含Salt值）校验
        if (sysUser != null && MD5SaltUtils.cryptographicCheck(sysUser.getPassword(), password, sysUser.getSalt())) {
            return sysUser;
        }
        return null;
    }

    @Override
    public SysUser checkUserNameIsExist(String userName) {

        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(userName);
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);

        return sysUserList != null && sysUserList.size() > 0 ? sysUserList.get(0) : null;
    }

    @Override
    public SysUser checkEmailIsExist(String email) {

        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andEmailEqualTo(email);
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);

        return sysUserList != null && sysUserList.size() > 0 ? sysUserList.get(0) : null;
    }

    @Override
    public int register(SysUser sysUser) {

        String salt = MD5SaltUtils.getSalt();
        //密码加密：  MD5(MD5(用户密码)+salt)
        String password = MD5SaltUtils.getMD5String(MD5SaltUtils.getMD5String(sysUser.getPassword()) + salt);

        sysUser.setSalt(salt);
        sysUser.setPassword(password);
        sysUser.setCreateBy(sysUser.getUserName());

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
        if (passwordLength >= 6 && passwordLength <= 20) {
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

    /**
     * 更新最后登录时间
     *
     * @param sysUser
     * @return
     */
    @Override
    public int updateLastLoginTime(SysUser sysUser) {
        sysUser.setLastLoginTime(new Date());
        return super.updateByIdSelective(sysUser);
    }

    /**
     * 邮箱激活后更新对应用户名的用户信息状态
     *
     * @param userName 用户名
     * @param status   状态值
     * @return
     */
    @Override
    public int emailActiveUpdateStatusByName(String userName, int status) {
//        return sysUserMapper.updateStatusByName(userName, status);
        SysUser sysUser = new SysUser();
        sysUser.setStatus((byte) status);
        sysUser.setModifyBy(userName);
        sysUser.setModifyTime(new Date());
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(userName);
        return sysUserMapper.updateByExampleSelective(sysUser, sysUserExample);
    }

    /**
     * 更新对应邮箱地址的用户密码
     *
     * @param email 邮箱
     * @param password   密码
     * @return
     */
    @Override
    public int updatePasswordByEmail(String email, String password) {

        //查询对应邮箱的用户信息
        SysUser sysUser = sysUserMapper.findUserByEmail(email);

        if(sysUser == null) {
            return 0;
        }
        String salt = MD5SaltUtils.getSalt();
        //密码加密：  MD5(MD5(用户密码)+salt)
        String encryptPassword = MD5SaltUtils.getMD5String(MD5SaltUtils.getMD5String(sysUser.getPassword()) + salt);
        //更新密码及盐值
        sysUser.setSalt(salt);
        sysUser.setPassword(encryptPassword);
        sysUser.setModifyBy(sysUser.getCreateBy());
        sysUser.setModifyTime(new Date());

        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }
}
