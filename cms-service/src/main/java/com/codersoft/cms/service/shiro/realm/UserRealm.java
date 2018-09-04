package com.codersoft.cms.service.shiro.realm;

import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wow-lj on 2018/9/4.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        SysUser sysUser = sysUserService.checkUserNameIsExist(username);
        if(sysUser == null){
            throw new UnknownAccountException();
        }
        sysUser = sysUserService.checkLogin(username,password);
        if(sysUser == null){
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
