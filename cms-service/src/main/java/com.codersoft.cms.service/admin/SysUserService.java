package com.codersoft.cms.service.admin;

import com.codersoft.cms.dao.entity.SysUser;

/**
 * @program: SysUserService
 * @author: Alex.D
 * @create: 2018-07-07 17:12
 * @description: 用户业务处理接口
 **/
public interface SysUserService {

    SysUser checkLogin(String username, String password);

}
