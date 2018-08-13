package com.codersoft.cms.web.controller.admin;

import com.codersoft.cms.common.utils.CaptchaUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: CommonController
 * @author: Alex.D
 * @create: 2018-07-06 17:28
 * @description: 通用控制类
 **/
@Controller
@RequestMapping("/common")
@Api(description = "公共处理操作")
public class CommonController {

    /**
     * 生成验证码
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getCaptcha")
    @ApiOperation(value = "生成验证码", notes = "生成数字、字母、短线混合的验证码图片", httpMethod = "GET")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        CaptchaUtils captchaUtils = new CaptchaUtils(100,36,5,100);
        session.setAttribute("captcha", captchaUtils.getCode());
        captchaUtils.write(response.getOutputStream());
    }

    /**
     * 图标选择界面
     * @return
     */
    @RequestMapping(value="/icon")
    @ApiOperation(value = "图标选择页面", notes = "跳转到Larry-icon图标的选择页面", httpMethod = "GET")
    public String toIconList() {
        return "admin/common/icon";
    }

    /**
     * 图片上传界面
     * @return
     */
    @RequestMapping(value="/toImgUpload")
    @ApiOperation(value = "图片上传界面", notes = "跳转到图片上传页面", httpMethod = "GET")
    public String toImageUploadPage() {
        return "admin/common/upload";
    }
}
