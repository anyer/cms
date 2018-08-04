package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.dao.entity.SysPermission;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.beans.Expression;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: SysPermissionController
 * @author: Alex.D
 * @create: 2018-07-12 12:38
 * @description: 权限操作控制类
 **/
@Controller
@RequestMapping("/admin/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 跳转到权限界面
     *
     * @return
     */
    @RequestMapping("/toListPage")
    public String toPermissionListPage() {
        return "admin/system/permission/permission_list";
    }

    /**
     * 获取权限列表
     *
     * @param sysPermission
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPermissionList(SysPermission sysPermission) {
        return sysPermissionService.selectPageList(sysPermission);
    }

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
     * 跳转到权限添加界面
     *
     * @return
     */
    @RequestMapping("/toAddPage")
    public String toAddPermissionPage() {
        return "admin/system/permission/permission_add";
    }

    /**
     * 添加权限信息
     *
     * @param sysPermission 权限
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultMessage addPermission(@RequestBody SysPermission sysPermission) {
        int addRes = 0;
        try {
            if(StringUtils.isEmpty(sysPermission.getUri())) {
                sysPermission.setUri("#");
            }
            addRes = sysPermissionService.addSelective(sysPermission);
            if(addRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.PER_SAVE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.PER_SAVE_FAIL, e.getMessage());
        }
    }

    /**
     * 跳转到权限详情页面
     *
     * @return
     */
    @RequestMapping("/toDetailPage")
    public String toDetailPermissionPage(Model model, @RequestParam("permissionId") Long permissionId) {

        SysPermission sysPermission = sysPermissionService.selectSysPermissionById(permissionId);
        model.addAttribute("permission", sysPermission);
        model.addAttribute("pageFlag", "detail");

        return "admin/system/permission/permission_edit";
    }

    /**
     * 跳转到权限修改页面
     *
     * @return
     */
    @RequestMapping("/toEditPage")
    public String toEditPermissionPage(Model model, @RequestParam("permissionId") Long permissionId) {

        SysPermission sysPermission = sysPermissionService.selectSysPermissionById(permissionId);
        model.addAttribute("permission", sysPermission);
        model.addAttribute("pageFlag", "edit");

        return "admin/system/permission/permission_edit";
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

    /**
     * 更新权限信息
     *
     * @param sysPermission 权限
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultMessage updatePermission(@RequestBody SysPermission sysPermission) {

        int updateRes = 0;
        try {
            sysPermission.setModifyTime(new Date());
            updateRes = sysPermissionService.updateByIdSelective(sysPermission);
            if(updateRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.PER_UPDATE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.PER_UPDATE_FAIL, e.getMessage());
        }
    }

    /**
     * 删除权限信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage deletePermission(@RequestParam("perId") Long permissionId) {

        int deleteRes = 0;
        try {
            deleteRes = sysPermissionService.deleteById(permissionId);
            if(deleteRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.PER_DELETE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.PER_DELETE_FAIL, e.getMessage());
        }
    }
}
