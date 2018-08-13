package com.codersoft.cms.service.admin;

import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.common.BaseService;

/**
 * @program: SysUserService
 * @author: Alex.D
 * @create: 2018-07-07 17:12
 * @description: 用户业务处理接口
 **/
public interface SysUserService extends BaseService<SysUser, Long> {

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    SysUser checkLogin(String username, String password);

    /**
     * 验证用户名是否存在
     *
     * @param userName 用户名
     * @return
     */
    SysUser checkUserNameIsExist(String userName);

    /**
     * 验证邮箱是否存在
     *
     * @param email 邮箱
     * @return
     */
    SysUser checkEmailIsExist(String email);

    /**
     * 用户注册
     *
     * @param sysUser 用户对象
     * @return
     */
    int register(SysUser sysUser);

    /**
     * 更新最后登录时间
     *
     * @param sysUser
     * @return
     */
    int updateLastLoginTime(SysUser sysUser);

    /**
     * 邮箱激活后更新对应用户名的用户信息状态
     *
     * @param userName 用户名
     * @param status   状态值
     * @return
     */
    int emailActiveUpdateStatusByName(String userName, int status);

    /**
     * 更新对应邮箱地址的用户密码
     *
     * @param email    邮箱
     * @param password 密码
     * @return
     */
    int updatePasswordByEmail(String email, String password);
}
