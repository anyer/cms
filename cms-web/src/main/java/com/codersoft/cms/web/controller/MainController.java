package com.codersoft.cms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wow-lj on 2017/12/28.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("hello")
    @ResponseBody
    public String index(){

        return "hello";
    }

    @RequestMapping("test")
    @ResponseBody
    public String test(){

        return "test";
    }

}
