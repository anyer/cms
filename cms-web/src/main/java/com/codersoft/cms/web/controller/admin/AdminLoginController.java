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
import java.util.Date;

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
    public String toLogin(){
        return "admin/login";
    }

    /**
     * 登陆验证(用户名验证）
     *
     * @param sysUser
     * @param httpSession
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userNameLogin", method = RequestMethod.POST)
    public ResultMessage checkLoginByUserName(@RequestBody SysUser sysUser, HttpSession httpSession){

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
                //判断账号是否未激活，未激活重新发送激活邮件
                if(user.getStatus() == 1) {
                    //Todo 未激活邮箱账户，发送激活邮件
                }
                //判断账号是否停用
                if(user.getStatus() == 2) {
                    return ResultMessageUtils.returnResultMessage(MessageCode.ACCOUNT_DISABLE);
                }
                //验证成功，用户名存放session
                httpSession.setAttribute("sysUser", user);
                user.setLastLoginTime(new Date());
                int updateRes = sysUserService.updateByIdSelective(user);
                if(updateRes == 0) {
                    return ResultMessageUtils.returnResultMessage(MessageCode.UPDATE_DATE_FAIL);
                }
                return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_FAIL);
        }catch (Exception e) {
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_ERROR, "登陆时异常：" + e.getMessage());
        }
    }

    /**
     * 后台锁屏的解锁操作
     * @return
     */
    @ResponseBody
    @RequestMapping("/unlock")
    public ResultMessage unlock() {

        //TODO 后台锁屏的解锁操作
        return null;
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
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "admin/register";
    }

    /**
     * 注册时验证用户名是否存在
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUserNameIsExist")
    public ResultMessage checkUserNameIsExist(@RequestParam("userName") String userName) {
        SysUser sysUser = sysUserService.checkUserNameIsExist(userName);
        if(sysUser != null) {
            return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_USER_NAME_IS_EXIST);
        }
        return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
    }

    /**
     * 注册时验证邮箱是否存在
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping("/emailIsExist")
    public ResultMessage emailIsExist(@RequestParam("email") String email) {
        SysUser user = sysUserService.checkEmailIsExist(email);
        if(user != null) {
            return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_EMAIL_IS_EXIST);
        }
        return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
    }

    /**
     * 注册
     * @param sysUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public ResultMessage register(@RequestBody SysUser sysUser) {

        try {
            int res = sysUserService.register(sysUser);

            if(res == 1) {
                return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
            } else {
                return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_FAIL);
            }
        }catch (Exception ex) {
            return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_ERROR, "注册时异常：" + ex.getMessage());
        }
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
     * 跳转到密码找回页面
     * @return
     */
    @RequestMapping("/toForgetPassword")
    public String toForgetPassword() {
        return "admin/forget_password";
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
