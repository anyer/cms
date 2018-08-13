package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysUserService;
import com.codersoft.cms.web.controller.admin.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SysUserController
 * @author: Alex.D
 * @create: 2018-07-12 12:38
 * @description: 用户操作控制类
 **/
@Controller
@RequestMapping("/admin/user")
@Api(description = "用户相关操作")
public class SysUserController extends BaseController<SysUser, Long> {

    public SysUserController() {
        super.setSessionKey("sysUser");
        super.setListPagePath("admin/system/user/user_list");
        super.setAddPagePath("admin/system/user/user_add");
        super.setEditPagePath("admin/system/user/user_edit");
        super.setSuccessMsg(MessageCode.SUCCESS);
        super.setSaveFailMsg(MessageCode.USER_SAVE_FAIL);
        super.setUpdateFailMsg(MessageCode.USER_UPDATE_FAIL);
        super.setDeleteFailMsg(MessageCode.USER_DELETE_FAIL);
    }

    @Autowired
    private SysUserService sysUserService;

}
