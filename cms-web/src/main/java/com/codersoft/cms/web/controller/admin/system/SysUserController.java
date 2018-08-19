package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.entity.SysRole;
import com.codersoft.cms.dao.entity.SysRoleExample;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.dao.mapper.admin.system.SysRoleMapper;
import com.codersoft.cms.service.admin.SysRoleService;
import com.codersoft.cms.service.admin.SysUserService;
import com.codersoft.cms.web.controller.admin.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<SysRole> roles = sysRoleMapper.selectByExample(new SysRoleExample());
        model.addAttribute("roles", roles);
        return getAddPagePath();
    }

    @RequestMapping("/addAndRole")
    @ResponseBody
    public ResultMessage addAndRole(@RequestBody SysUser sysUser) {
        int addRes = 0;
        try {
            addRes = sysUserService.addAndRoleSelective(sysUser);
            if (addRes == 0) {
                return ResultMessageUtils.returnResultMessage(getSaveFailMsg());
            }
            return ResultMessageUtils.returnResultMessage(getSuccessMsg());
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(getSaveFailMsg(), e.getMessage());
        }
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model) {
        List<SysRole> roles = sysRoleMapper.selectByExample(new SysRoleExample());
        model.addAttribute("roles", roles);
        model.addAttribute("pageFlag", "edit");
        return getEditPagePath();
    }

    @RequestMapping("/updateAndRole")
    @ResponseBody
    public ResultMessage updateAndRole(@RequestBody SysUser sysUser, HttpSession httpSession) {

        int updateRes = 0;
        try {
            SysUser user = (SysUser) httpSession.getAttribute(getSessionKey());
            sysUser.setModifyBy(user.getUserName());

            updateRes = sysUserService.updateAndRoleByIdSelective(sysUser);
            if (updateRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.SAVE_USER_ROLE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(getSuccessMsg());
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.SAVE_USER_ROLE_FAIL, e.getMessage());
        }
    }

    /**
     * 跳转到数据详情页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toDetail")
    public String toDetail(Model model) {

        List<SysRole> roles = sysRoleMapper.selectByExample(new SysRoleExample());
        model.addAttribute("roles", roles);
        model.addAttribute("pageFlag", "detail");

        return getEditPagePath();
    }

    @RequestMapping(value = "/delAndRole", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage delAndRole(@RequestParam("id") Long id) {

        int deleteRes = 0;
        try {
            deleteRes = sysUserService.deleteAndRoleById(id);
            if (deleteRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.DELETE_USER_ROLE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(getSuccessMsg());
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.DELETE_USER_ROLE_FAIL, e.getMessage());
        }
    }

}
