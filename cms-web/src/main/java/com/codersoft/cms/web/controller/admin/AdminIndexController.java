package com.codersoft.cms.web.controller.admin;

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
public class AdminIndexController {

    @RequestMapping("/index")
    public String index(){
        return "admin/index";
    }

    @RequestMapping("/home")
    public String home(){
        return "admin/home";
    }

}
