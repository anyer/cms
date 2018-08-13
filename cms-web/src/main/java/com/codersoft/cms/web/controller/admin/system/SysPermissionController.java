package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.service.admin.SysPermissionService;
import com.codersoft.cms.web.controller.admin.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @program: SysPermissionController
 * @author: Alex.D
 * @create: 2018-07-12 12:38
 * @description: 权限操作控制类
 **/
@Controller
@RequestMapping("/admin/permission")
@Api(description = "权限相关操作")
public class SysPermissionController extends BaseController<SysPermission, Long> {

    public SysPermissionController() {
        super.setSessionKey("sysPermission");
        super.setListPagePath("admin/system/permission/permission_list");
        super.setAddPagePath("admin/system/permission/permission_add");
        super.setEditPagePath("admin/system/permission/permission_edit");
        super.setSuccessMsg(MessageCode.SUCCESS);
        super.setSaveFailMsg(MessageCode.PER_SAVE_FAIL);
        super.setUpdateFailMsg(MessageCode.PER_UPDATE_FAIL);
        super.setDeleteFailMsg(MessageCode.PER_DELETE_FAIL);
    }

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 获取目录列表
     *
     * @return
     */
    @ApiOperation(value = "获取目录列表", notes = "获取目录类型权限列表", httpMethod = "GET")
    @RequestMapping("/directoryList")
    public @ResponseBody
    List<DirectoryPermissionDto> getDirectoryList() {
        return sysPermissionService.selectDirectoryPermissionList();
    }

    /**
     * 获取对应父IDd的权限菜单列表
     * @param parentId 父权限ID
     * @return
     */
    @ApiOperation(value = "父级菜单权限列表", notes = "加载对应父ID的父类菜单权限列表", httpMethod = "POST")
    @ApiImplicitParam(name = "parentId", value = "父权限ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/menuList", method = RequestMethod.POST)
    public @ResponseBody
    List<DirectoryPermissionDto> getMenuList(@RequestParam("parentId") Long parentId) {
        return sysPermissionService.selectMenuPermissionListByParentId(parentId);
    }

    /**
     * 加载对应权限级别的父级权限列表
     *
     * @param perLevel 权限级别
     * @return
     */
    @ApiOperation(value = "父级权限列表", notes = "加载对应权限级别的父类权限列表", httpMethod = "POST")
    @ApiImplicitParam(name = "perLevel", value = "权限级别", required = true, dataType = "Integer")
    @RequestMapping(value = "/parentList", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage loadParentPermission(@RequestParam("perLevel") Integer perLevel) {

        List<SysPermission> sysPermissionList = null;
        try {
            sysPermissionList = sysPermissionService.selectParentPermissionListByPerLevel(perLevel);
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS, sysPermissionList);
        } catch (Exception ex) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.PER_DATA_LOAD_FAIL, ex.getMessage());
        }
    }
}
