package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.dao.dto.DirectoryPermissionDto;
import com.codersoft.cms.service.admin.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

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
     * @return
     */
    @RequestMapping("/toListPage")
    public String toPermissionListPage() {
        return "admin/system/";
    }

    @ResponseBody
    @RequestMapping("/list")
    public String getPermissionList(){
        return null;
    }

    /**
     * 获取目录列表
     * @return
     */
    @RequestMapping("/directoryList")
    public @ResponseBody List<DirectoryPermissionDto> getDirectoryList() {
        return sysPermissionService.selectDirectoryPermissionList();
    }

    /**
     * 获取目录列表
     * @return
     */
    @RequestMapping(value = "/menuList", method = RequestMethod.POST)
    public @ResponseBody List<DirectoryPermissionDto>  getMenuList(@RequestParam("parentId") Long parentId) {
        return sysPermissionService.selectMenuPermissionListByParentId(parentId);
    }
}
