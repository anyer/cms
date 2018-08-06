package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.service.admin.SysRoleService;
import com.codersoft.cms.web.controller.admin.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SysRoleController
 * @author: Alex.D
 * @create: 2018-07-12 12:39
 * @description: 角色操作控制类
 **/
@Controller
@RequestMapping("/admin/role")
public class SysRoleController extends BaseController<SysRole, Long> {

    public SysRoleController() {
        super.setSessionKey("sysRole");
        super.setListPagePath("admin/system/role/role_list");
        super.setAddPagePath("admin/system/role/role_add");
        super.setEditPagePath("admin/system/role/role_edit");
        super.setSuccessMsg(MessageCode.SUCCESS);
        super.setSaveFailMsg(MessageCode.ROLE_SAVE_FAIL);
        super.setUpdateFailMsg(MessageCode.ROLE_UPDATE_FAIL);
        super.setDeleteFailMsg(MessageCode.ROLE_DELETE_FAIL);
    }

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 验证角色名是否存在
     *
     * @param roleName 角色名
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkRoleNameIsExist")
    public ResultMessage checkRoleNameIsExist(@RequestParam("roleName") String roleName) {
        SysRole sysRole = sysRoleService.checkRoleNameIsExist(roleName);
        try {
            if (sysRole != null) {
                return ResultMessageUtils.returnResultMessage(MessageCode.ROLE_NAME_EXIST);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception ex) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.ROLE_NAME_EXIST, ex.getMessage());
        }
    }
}
