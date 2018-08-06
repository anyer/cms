package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.service.admin.SysPermissionService;
import com.codersoft.cms.web.controller.admin.BaseController;
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
    @RequestMapping("/directoryList")
    public @ResponseBody
    List<DirectoryPermissionDto> getDirectoryList() {
        return sysPermissionService.selectDirectoryPermissionList();
    }

    /**
     * 获取目录列表
     *
     * @return
     */
    @RequestMapping(value = "/menuList", method = RequestMethod.POST)
    public @ResponseBody
    List<DirectoryPermissionDto> getMenuList(@RequestParam("parentId") Long parentId) {
        return sysPermissionService.selectMenuPermissionListByParentId(parentId);
    }

    /**
     * 加载父级权限列表
     */
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
