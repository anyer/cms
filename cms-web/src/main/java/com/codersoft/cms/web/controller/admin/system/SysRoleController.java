package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysRoleService;
import com.codersoft.cms.web.controller.admin.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: SysRoleController
 * @author: Alex.D
 * @create: 2018-07-12 12:39
 * @description: 角色操作控制类
 **/
@Controller
@RequestMapping("/admin/role")
@Api(description = "角色相关操作")
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
    @ApiOperation(value = "验证角色名是否存在", notes = "后台添加角色信息时Ajax验证角色名称是否存在", httpMethod = "POST")
    @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, dataType = "String")
    @RequestMapping(value = "/checkRoleNameIsExist", method = RequestMethod.POST)
    @ResponseBody
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

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage updateRole (@RequestBody SysRole sysRole, HttpSession httpSession) {

        SysUser sysUser = (SysUser)httpSession.getAttribute("sysUser");
        sysRole.setModifyBy(sysUser.getUserName());

        return updateData(sysRole);
    }

    @RequestMapping(value = "/addPermission",method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage addRolePermission(@RequestParam("roleId") Long roleId, @RequestParam("perIDs")  String permissionIds, HttpSession httpSession) {
        try {
            SysUser sysUser = (SysUser)httpSession.getAttribute("sysUser");
            if (sysRoleService.addRolePermission(roleId, permissionIds, sysUser.getUserName()) < 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.SAVE_ROLE_PERMISSION_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception ex) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.SAVE_ROLE_PERMISSION_FAIL, ex.getMessage());
        }
    }



    @RequestMapping("/delAndPermission")
    @ResponseBody
    public ResultMessage delAndPermission(@RequestParam("id") Long id) {

        int deleteRes = 0;
        try {
            deleteRes = sysRoleService.deleteAndPermissionById(id);
            if (deleteRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.DELETE_ROLE_PERMISSION_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(getSuccessMsg());
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.DELETE_ROLE_PERMISSION_FAIL, e.getMessage());
        }
    }
}
