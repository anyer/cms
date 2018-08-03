package com.codersoft.cms.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wow-lj on 2017/12/28.
 */
@Controller
@RequestMapping()
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

//    @Autowired
//    private SendEmailServiceImpl sendEmailServiceImpl;

    @RequestMapping("/index")
    public String index(){
        return "admin/login";
    }

    @RequestMapping("/mailTest")
    public void emailTest() {

//        MailInfo mailInfo = new MailInfo();
//        mailInfo.setToMailArray(new String[]{
//                "1193477751@qq.com"
//        });
//        mailInfo.setSubject("测试");
//        mailInfo.setContent("测试javax.mail邮件发送");
//        sendEmailServiceImpl.sendSimpleMail(mailInfo);

        logger.info("邮件测试");
    }

    //TODO log4j2测试
    @RequestMapping("/logTest")
    public void logTest() {

    }


}
