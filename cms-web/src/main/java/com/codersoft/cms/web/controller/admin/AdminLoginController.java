package com.codersoft.cms.web.controller.admin;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.DateAndTimestampUtils;
import com.codersoft.cms.common.utils.MD5SaltUtils;
import com.codersoft.cms.common.utils.RandomUtils;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.entity.SysUser;
import com.codersoft.cms.service.admin.SysUserService;
import com.codersoft.cms.service.common.SendEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: LoginController
 * @author: Alex.D
 * @create: 2018-07-07 17:17
 * @description: 登陆注册等相关控制类
 **/
@Controller
@RequestMapping("/admin")
//@SessionAttributes("sysUser")
@Api(description = "登陆注册等控制相关操作")
public class AdminLoginController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SendEmailService sendEmailService;

    /**
     * 正常访问登陆页面
     *
     * @return
     */
    @ApiOperation(value = "登陆页面", notes = "跳转到后台的用户登陆界面", httpMethod = "GET")
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 登陆验证(用户名验证）
     *
     * @param sysUser     用户对象
     * @param httpSession
     * @return
     */
    @ApiOperation(value = "登陆验证", notes = "用户名登陆时的登陆验证", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUser", value = "用户对象", required = true, dataType = "SysUser"),
            @ApiImplicitParam(name = "httpSession", value = "session", required = true, dataType = "HttpSession")
    })
    @ResponseBody
    @RequestMapping(value = "/userNameLogin", method = RequestMethod.POST)
    public ResultMessage checkLoginByUserName(@RequestBody SysUser sysUser, HttpSession httpSession) {

        //用户名登陆
        //用户名不为空
        if (StringUtils.isEmpty(sysUser.getUserName())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_NAME_IS_NULL);
        }
        //密码不为空
        if (StringUtils.isEmpty(sysUser.getPassword())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_PASSWORD_IS_NULL);
        }
        //验证码不为空
        if (StringUtils.isEmpty(sysUser.getCaptcha())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.CAPTCHA_IS_NULL);
        }
        //获取Session内存储的验证码
        String sessionCaptcha = (String) httpSession.getAttribute("captcha");
        //验证码校验
        if (!sysUser.getCaptcha().trim().toLowerCase().equals(sessionCaptcha.toLowerCase())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.CAPTCHA_IS_ERROR);
        }

        try {
            //登陆校验
            SysUser user = sysUserService.checkLogin(sysUser.getUserName(), sysUser.getPassword());
            //若有user则添加到model里并且跳转到成功页面
            if (user != null) {
                String activeFlag = user.getUserName() + "-active";
                //判断账号是否未激活，未激活重新发送激活邮件
                if (user.getStatus() == 1) {
                    //未激活邮箱账户，发送激活邮件
                    if (httpSession.getAttribute(activeFlag) == null) {
                        sendEmailService.sendEmailActive(user.getUserName(), user.getEmail());
                        //发送成功后，session内存入标识，防止恶意操作。。
                        httpSession.setAttribute(activeFlag, user.getUserName() + ":" + user.getEmail());
                    }
                    return ResultMessageUtils.returnResultMessage(MessageCode.EMAIL_SEND_SUCCESS, user.getEmail());
                }
                //判断账号是否停用
                if (user.getStatus() == 2) {
                    return ResultMessageUtils.returnResultMessage(MessageCode.ACCOUNT_DISABLE);
                }
                //验证成功，用户名存放session
                httpSession.setAttribute("sysUser", user);

                //删除激活邮件发送标识
                if (httpSession.getAttribute(activeFlag) != null) {
                    httpSession.removeAttribute(activeFlag);
                }
                user.setLastLoginTime(new Date());
                int updateRes = sysUserService.updateLastLoginTime(user);
                if (updateRes == 0) {
                    return ResultMessageUtils.returnResultMessage(MessageCode.UPDATE_DATE_FAIL);
                }
                return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_FAIL);
        } catch (Exception e) {
            return ResultMessageUtils.returnResultMessage(MessageCode.LOGIN_ERROR, "登陆时异常：" + e.getMessage());
        }
    }

    /**
     * 后台锁屏的解锁操作
     *
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
     *
     * @param session
     * @return
     */
    @ApiOperation(value = "注销操作", notes = "后台注销操作", httpMethod = "GET")
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session) {

        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "redirect:/admin/toLogin";
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @ApiOperation(value = "注册页面", notes = "跳转到后台注册页面", httpMethod = "GET")
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "admin/register";
    }

    /**
     * 注册时验证用户名是否存在
     *
     * @param userName 用户名称
     * @return
     */
    @ApiOperation(value = "验证用户名是否存在", notes = "后台注册时Ajax验证用户名是否存在", httpMethod = "POST")
    @ApiImplicitParam(name = "userName", value = "用户名称", required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/checkUserNameIsExist", method = RequestMethod.POST)
    public ResultMessage checkUserNameIsExist(@RequestParam("userName") String userName) {
        SysUser sysUser = sysUserService.checkUserNameIsExist(userName);
        if (sysUser != null) {
            return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_USER_NAME_IS_EXIST);
        }
        return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
    }

    /**
     * 注册时验证邮箱是否存在
     *
     * @param email
     * @return
     */
    @ApiOperation(value = "验证邮箱是否存在", notes = "后台注册时Ajax验证邮箱是否存在", httpMethod = "POST")
    @ApiImplicitParam(name = "email", value = "邮箱地址", required = true, dataType = "String")
    @ResponseBody
    @RequestMapping(value = "/emailIsExist", method = RequestMethod.POST)
    public ResultMessage emailIsExist(@RequestParam("email") String email) {
        SysUser user = sysUserService.checkEmailIsExist(email);
        if (user != null) {
            return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_EMAIL_IS_EXIST);
        }
        return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
    }

    /**
     * 注册
     *
     * @param sysUser 用户对象
     * @return
     */
    @ApiOperation(value = "注册操作", notes = "后台用户注册", httpMethod = "POST")
    @ApiImplicitParam(name = "sysUser", value = "用户对象", required = true, dataType = "SysUser")
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultMessage register(@RequestBody SysUser sysUser) {

        try {
            int res = sysUserService.register(sysUser);

            sendEmailService.sendEmailActive(sysUser.getUserName(), sysUser.getEmail());

            if (res == 1) {
                return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
            } else {
                return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_FAIL);
            }
        } catch (Exception ex) {
            return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_ERROR, "注册时异常：" + ex.getMessage());
        }
    }

    /**
     * 发送邮箱激活链接
     *
     * @param httpSession
     * @return
     */
    @ApiOperation(value = "邮箱激活链接", notes = "点击发送邮箱激活链接，用于激活账号", httpMethod = "GET")
    @ApiImplicitParam(name = "httpSession", value = "session对象（存储登录的用户信息）", required = true, dataType = "HttpSession")
    @RequestMapping(value = "/activeLink", method = RequestMethod.GET)
    @ResponseBody()
    public ResultMessage sendEmailActivationLink(HttpSession httpSession) {

        try {
            SysUser currentSysUser = (SysUser) httpSession.getAttribute("sysUser");
            sendEmailService.sendEmailActive(currentSysUser.getUserName(), currentSysUser.getEmail());
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception ex) {
            return ResultMessageUtils.returnResultMessage(MessageCode.REGISTER_ERROR, "邮箱激活邮件发送异常：" + ex.getMessage());
        }
    }

    /**
     * 邮箱激活
     *
     * @param model
     * @param userName  用户名称
     * @param timestamp 时间戳
     * @return
     */
    @ApiOperation(value = "邮箱激活", notes = "邮件点击激活链接，用于激活账号", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", required = true, dataType = "Long")
    })
    @RequestMapping("/activeEmail")
    public String activationEmail(Model model, @RequestParam("name") String userName, @RequestParam("timestamp") Long timestamp) {

        int updateRes = 0;
        ResultMessage resultMessage = ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);

        try {
            Long currentTimestamp = System.currentTimeMillis();
            //判断激活时间是否在有效范围内
            if (currentTimestamp >= timestamp) {
                resultMessage = ResultMessageUtils.returnResultMessage(MessageCode.EMAIL_ACTIVE_TIMEOUT);
                model.addAttribute("result", resultMessage);
                return "admin/login";
            }
            updateRes = sysUserService.emailActiveUpdateStatusByName(userName, 0);

            if (updateRes <= 0) {
                resultMessage = ResultMessageUtils.returnResultMessage(MessageCode.EMAIL_ACTIVE_FAIL);
            }

        } catch (Exception e) {
            resultMessage = ResultMessageUtils.returnExpectionResultMessage(MessageCode.EMAIL_SEND_ERROR, e.getMessage());
        }
        model.addAttribute("result", resultMessage);

        return "admin/login";
    }

    /**
     * 跳转到密码找回页面
     *
     * @return
     */
    @ApiOperation(value = "密码找回页面", notes = "跳转到后台密码找回页面", httpMethod = "GET")
    @RequestMapping("/toForgetPassword")
    public String toForgetPassword() {
        return "admin/forget_password";
    }

    /**
     * 发送密码找回验证码
     *
     * @param email       邮箱地址
     * @param captcha     验证码
     * @param httpSession
     * @return
     */
    @ApiOperation(value = "发送验证码", notes = "密码找回，发送验证码", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "httpSession", value = "session", required = true, dataType = "HttpSession")
    })
    @RequestMapping(value = "/passwordEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage recoveryPasswordEmail(@RequestParam("email") String email, @RequestParam("captcha") String captcha, HttpSession httpSession) {

        ResultMessage resultMessage = ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        //验证码不为空
        if (StringUtils.isEmpty(captcha)) {
            return ResultMessageUtils.returnResultMessage(MessageCode.CAPTCHA_IS_NULL);
        }
        //获取Session内存储的验证码
        String sessionCaptcha = (String) httpSession.getAttribute("captcha");
        //验证码校验
        if (!captcha.toLowerCase().equals(sessionCaptcha.toLowerCase())) {
            return ResultMessageUtils.returnResultMessage(MessageCode.CAPTCHA_IS_ERROR);
        }
        //生成验证码
        int code = RandomUtils.randomCreateNumberOfDigits(6);
        long timestamp = DateAndTimestampUtils.getAfterMinutesTimestampValue(30);

        //存储验证码到session中，用户后续校验
        httpSession.setAttribute("recoveryPasswordCode", code + "");
        httpSession.setAttribute("recoveryPasswordTimestamp", timestamp);

        try {
            sendEmailService.sendPasswordRecoveryEmail(email, code);
            resultMessage.setReturnData(email);
            httpSession.setAttribute("email", email);
            return resultMessage;
        } catch (Exception ex) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.EMAIL_SEND_ERROR, ex.getMessage());
        }
    }

    /**
     * 跳转到密码找回页面
     *
     * @return
     */
    @ApiOperation(value = "密码重置页面", notes = "跳转到后台密码重置页面", httpMethod = "GET")
    @RequestMapping("/toResetPassword")
    public String toResetPassword() {
        return "admin/reset_password";
    }

    /**
     * 密码重置处理
     *
     * @param email       邮箱地址
     * @param code        验证码
     * @param password    密码
     * @param httpSession
     * @return
     */
    @ApiOperation(value = "密码重置", notes = "通过验证码实现对应邮箱账号的密码重置", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "captcha", value = "验证码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "httpSession", value = "session", required = true, dataType = "HttpSession")
    })
    @RequestMapping(value = "/recovery", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage recoveryPassword(@RequestParam("email") String email, @RequestParam("code") String code, @RequestParam("pwd") String password, HttpSession httpSession) {

        ResultMessage resultMessage = ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        //校验验证码
        try {
            if (code != null && code.equals((String) httpSession.getAttribute("recoveryPasswordCode"))) {
                Long currentTimestamp = System.currentTimeMillis();
                Long timestamp = (Long) httpSession.getAttribute("recoveryPasswordTimestamp");
                //判断激活时间是否在有效范围内
                if (currentTimestamp >= timestamp) {
                    return ResultMessageUtils.returnResultMessage(MessageCode.EMAIL_ACTIVE_TIMEOUT);
                }
                int updateRes = sysUserService.updatePasswordByEmail(email, password);
                if (updateRes != 0) {
                    return resultMessage;
                }
                resultMessage = ResultMessageUtils.returnResultMessage(MessageCode.USER_UPDATE_FAIL);
            } else {
                resultMessage = ResultMessageUtils.returnResultMessage(MessageCode.CODE_NULL_OR_WARNNING);
            }
        } catch (Exception ex) {
            resultMessage = ResultMessageUtils.returnExpectionResultMessage(MessageCode.USER_UPDATE_FAIL, ex.getMessage());
        }
        return resultMessage;
    }

}
