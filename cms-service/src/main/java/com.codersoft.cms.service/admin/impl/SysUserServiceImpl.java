package com.codersoft.cms.service.admin.impl;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.utils.MD5SaltUtils;
import com.codersoft.cms.dao.entity.*;
import com.codersoft.cms.dao.mapper.admin.system.SysUserMapper;
import com.codersoft.cms.dao.mapper.admin.system.SysUserRoleMapper;
import com.codersoft.cms.service.admin.SysUserService;
import com.codersoft.cms.service.common.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 获取分页数据集合
     *
     * @param sysUser
     * @return
     */
    @Override
    public Map<String, Object> selectPageList(SysUser sysUser) {

        List<SysUser> userList = null;
        Long count = null;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            userList = sysUserMapper.selectPageList(sysUser);
            count = sysUserMapper.selectCount(sysUser);

            if (userList != null && userList.size() > 0) {
                // 查询user的角色
                List<SysUserRole> userRoles = sysUserRoleMapper.selectUserRoleByUserIdList(getUserIds(userList));
                for (SysUser user : userList) {
                    List<SysRole> tempURs = new ArrayList<>();
                    for (SysUserRole userRole : userRoles) {
                        if (user.getUserId().equals(userRole.getUserId())) {
                            tempURs.add(new SysRole(userRole.getRoleId(), userRole.getRoleName()));
                        }
                    }
                    user.setRoleList(tempURs);
                }
            }

            map.put("code", 0);
            map.put("msg", MessageCode.SUCCESS.getMsg());
            map.put("count", count == null ? 0L : count);
            map.put("data", userList);
            return map;
        } catch (Exception ex) {
            map.put("msg", MessageCode.EXCEPTION.getMsg() + " : " + ex.getMessage());
            map.put("code", MessageCode.EXCEPTION.getCode());
            map.put("data", null);
            map.put("count", count == null ? 0L : count);
        }
        return map;
    }

    /**
     * 获取id集合
     *
     * @param userList
     * @return
     */
    private List<Long> getUserIds(List<SysUser> userList) {
        List<Long> userIds = new ArrayList<>();
        for (SysUser sysUser : userList) {
            userIds.add(sysUser.getUserId());
        }
        return userIds;
    }

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

    /**
     * 处理添加时密码加密
     *
     * @param sysUser
     * @return
     */
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

    /**
     * 更新时密码加密处理
     *
     * @param sysUser
     * @return
     */
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

        return super.updateByIdSelective(sysUser);
    }

    /**
     * 添加用户信息及分配角色
     *
     * @param sysUser
     * @return
     */
    @Override
    public int addAndRoleSelective(SysUser sysUser) {

        int res = addSelective(sysUser);
        if (res > 0) {
            SysUser user = sysUserMapper.findUserByUsername(sysUser.getUserName());
            res = sysUserRoleMapper.insertBatch(user.getUserId(), getRoleIdList(sysUser.getRoleIdStr()), sysUser.getModifyBy());
        } else {
            res = 0;
        }
        return res;
    }

    /**
     * 更新用户信息及分配角色
     *
     * @param sysUser 用户信息
     * @return
     */
    @Override
    public int updateAndRoleByIdSelective(SysUser sysUser) {

        sysUser.setModifyTime(new Date());
        int res = updateByIdSelective(sysUser);
        if (res > 0) {
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUserIdEqualTo(sysUser.getUserId());
            res = sysUserRoleMapper.deleteByExample(sysUserRoleExample);
            if(res >= 0) {
                res = sysUserRoleMapper.insertBatch(sysUser.getUserId(), getRoleIdList(sysUser.getRoleIdStr()), sysUser.getModifyBy());
            } else {
                res = 0;
            }
        } else {
            res = 0;
        }
        return res;
    }

    /**
     * 删除对应ID用户及用户角色关系
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public int deleteAndRoleById(Long userId) {

        int res = sysUserMapper.deleteByPrimaryKey(userId);
        if (res > 0) {
            SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
            sysUserRoleExample.createCriteria().andUserIdEqualTo(userId);
            res = sysUserRoleMapper.deleteByExample(sysUserRoleExample);
        } else {
            res = 0;
        }
        return res;
    }

    private List<Long> getRoleIdList(String roleIdStr) {
        List<Long> roleIdList = new ArrayList<>();
        String[] roleStrArray = roleIdStr.split(",");
        for (String roleId : roleStrArray) {
            roleIdList.add(Long.parseLong(roleId));
        }
        return roleIdList;
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
     * @param email    邮箱
     * @param password 密码
     * @return
     */
    @Override
    public int updatePasswordByEmail(String email, String password) {

        //查询对应邮箱的用户信息
        SysUser sysUser = sysUserMapper.findUserByEmail(email);

        if (sysUser == null) {
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
