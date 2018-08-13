package com.codersoft.cms.web.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: AdminIndexController
 * @author: Alex.D
 * @create: 2018-07-11 16:40
 * @description: 后台首页控制类
 **/
@Controller
@RequestMapping("/admin")
@Api(description = "后台首页相关操作")
public class AdminIndexController {

    @ApiOperation(value = "后台主页界面", notes = "加载后台主页界面", httpMethod = "GET")
    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    @ApiOperation(value = "后台主页Frame界面", notes = "加载后台主页Frame内的界面内容", httpMethod = "GET")
    @RequestMapping("/home")
    public String home(){
        return "admin/home";
    }

}
