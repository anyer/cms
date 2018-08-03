package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SysUserController
 * @author: Alex.D
 * @create: 2018-07-12 12:38
 * @description: 用户操作控制类
 **/
@Controller
@RequestMapping("/admin/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 跳转到用户管理界面
     * @return
     */
    @RequestMapping("/toListPage")
    public String toPermissionListPage() {
        return "admin/system/user_list";
    }

    /**
     * 加载用户列表
     * @param sysUser
     * @return
     */
    @RequestMapping("/ajax_res_list.do")
    @ResponseBody
    public String ajaxResourceList(SysUser sysUser){
//        return sysUserService.selectResourceResultPageList(resource);
        return null;
    }


}
