package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.MD5SaltUtils;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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
     *
     * @return
     */
    @RequestMapping("/toListPage")
    public String toPermissionListPage() {
        return "admin/system/user/user_list";
    }

    /**
     * 获取用户列表
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUserList(SysUser sysUser) {
        return sysUserService.selectPageList(sysUser);
    }

    /**
     * 跳转到用户添加界面
     *
     * @return
     */
    @RequestMapping("/toAddPage")
    public String toAddUserPage() {
        return "admin/system/user/user_add";
    }

    /**
     * 添加用户信息
     *
     * @param sysUser 用户
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultMessage addUser(@RequestBody SysUser sysUser) {
        int addRes = 0;
        try {
            String salt = MD5SaltUtils.getSalt();
            //密码加密：  MD5(MD5(用户密码)+salt)
            String password = MD5SaltUtils.getMD5String(MD5SaltUtils.getMD5String(sysUser.getPassword())+salt);
            if(StringUtils.isEmpty(sysUser.getHeaderImg())) {
                sysUser.setHeaderImg("face.jpg");
            }
            sysUser.setSalt(salt);
            sysUser.setPassword(password);
            addRes = sysUserService.addSelective(sysUser);
            if(addRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.USER_SAVE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.USER_SAVE_FAIL, e.getMessage());
        }
    }

    /**
     * 跳转到用户详情页面
     *
     * @return
     */
    @RequestMapping("/toDetailPage")
    public String toDetailUserPage(Model model, @RequestParam("userId") Long userId) {

        SysUser sysUser = sysUserService.selectBytId(userId);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("pageFlag", "detail");

        return "admin/system/user/user_edit";
    }

    /**
     * 跳转到用户修改页面
     *
     * @return
     */
    @RequestMapping("/toEditPage")
    public String toEditUserPage(Model model, @RequestParam("userId") Long userId) {

        SysUser sysUser = sysUserService.selectBytId(userId);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("pageFlag", "edit");

        return "admin/system/user/user_edit";
    }

    /**
     * 更新用户信息
     *
     * @param sysUser 用户
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResultMessage updateUser(@RequestBody SysUser sysUser) {

        int updateRes = 0;
        try {
            sysUser.setModifyTime(new Date());
            updateRes = sysUserService.updateByIdSelective(sysUser);
            if(updateRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.USER_UPDATE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.USER_UPDATE_FAIL, e.getMessage());
        }
    }

    /**
     * 删除权限信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage deleteUser(@RequestParam("userId") Long userId) {

        int deleteRes = 0;
        try {
            deleteRes = sysUserService.deleteById(userId);
            if(deleteRes == 0) {
                return ResultMessageUtils.returnResultMessage(MessageCode.USER_DELETE_FAIL);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.USER_DELETE_FAIL, e.getMessage());
        }
    }

}
