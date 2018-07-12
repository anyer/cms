package com.codersoft.cms.web.controller.admin;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: LoginController
 * @author: Alex.D
 * @create: 2018-07-07 17:17
 * @description: 登陆相关控制类
 **/
@Controller
@RequestMapping("/admin")
//@SessionAttributes("sysUser")
public class AdminLoginController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 正常访问登陆页面
     * @return
     */
    @RequestMapping("/toLogin")
    public String login(){
        return "admin/login";
    }

    /**
     * 登陆验证
     *
     * @param sysUser
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public ResultMessage checkLogin(@RequestBody SysUser sysUser, HttpSession httpSession){

        //Todo 邮箱名登陆

        //用户名登陆
        //用户名不为空
        if(StringUtils.isEmpty(sysUser.getUserName())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_NAME_IS_NULL);
        }
        //密码不为空
        if(StringUtils.isEmpty(sysUser.getPassword())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_PASSWORD_IS_NULL);
        }
        //验证码不为空
        if(StringUtils.isEmpty(sysUser.getCaptcha())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.CAPTCHA_IS_NULL);
        }
        //获取Session内存储的验证码
        String sessionCaptcha = (String)httpSession.getAttribute("captcha");
        //验证码校验
        if(!sysUser.getCaptcha().trim().toLowerCase().equals(sessionCaptcha.toLowerCase())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.CAPTCHA_IS_ERROR);
        }

        try {
            //登陆校验
            SysUser user = sysUserService.checkLogin(sysUser.getUserName(), sysUser.getPassword());
            //若有user则添加到model里并且跳转到成功页面
            if(user != null){
//                model.addAttribute("sysUser", user);
                //验证成功，用户名存放session
                httpSession.setAttribute("sysUser", user);
                return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_FAIL);
        }catch (Exception e) {
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_ERROR, e.getMessage());
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){

        //通过session.invalidata()方法来注销当前的session
        session.removeAttribute("sysUser");
        return "redirect:/admin/toLogin";
    }

    /**
     * Todo 发送邮箱激活链接
     * @return
     */
    public ResultMessage sendEmailActivationLink() {
        return null;
    }

    /**
     * Todo 邮箱激活
     * @return
     */
    public ResultMessage emailActivation() {
        return null;
    }

    /**
     * Todo 忘记密码 - 邮件验证处理
     * @return
     */
    @RequestMapping("/validationEmail")
    public ResultMessage validationEmail(@RequestParam("email") String email){
        return null;
    }

}
