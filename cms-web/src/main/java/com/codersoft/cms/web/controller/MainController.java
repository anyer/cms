package com.codersoft.cms.web.controller;

import com.codersoft.cms.dao.bean.MailInfo;
import com.codersoft.cms.service.common.SendEmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by wow-lj on 2017/12/28.
 */
@Controller
@RequestMapping()
public class MainController {

    private static final Logger logger = LogManager.getLogger(MainController.class);

//    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private SendEmailService sendEmailService;

    @RequestMapping("/index")
    public String index(){
        return "admin/login";
    }

    @RequestMapping("/mailTest")
    @ResponseBody
    public String emailTest() {

        mailSender = new JavaMailSenderImpl();

        //设置邮件服务器(mail server)主机地址
        mailSender.setHost("smtp.163.com");
//        mailSender.setHost("smtp.qq.com");

        //发送邮件的服务器的用户名和密码(实际上就是登录发送邮件服务器的用户名和密码)
//        mailSender.setUsername("1193477751@qq.com");
//        mailSender.setPassword("vzsuahsnrszvhbaf");
        mailSender.setUsername("maple_6392@163.com");
        mailSender.setPassword("2SzxadweS2");

        Properties properties=new Properties();
        //开启服务器认证，认证UserName和PassWord是否正确
        properties.setProperty("mail.smtp.auth", "true");
        //设置超时
        properties.setProperty("mail.smtp.timeout", "3000");
        //设置端口
        properties.setProperty("mail.smtp.port", "25");
        //开启debug模式，便于调试
        properties.put("mail.debug", "true");

        mailSender.setJavaMailProperties(properties);

        //邮件信息对象：用于封装：发送地址、接收地址、主题、文本内容等
        SimpleMailMessage mailMessage=new SimpleMailMessage();

        //设置邮件接收的地址
        mailMessage.setTo("maple6392@aliyun.com");
        //设置邮件发送的地址
        mailMessage.setFrom("maple_6392@163.com");
//        mailMessage.setFrom("1193477751@qq.com");
        //设置邮件发送的主题
        mailMessage.setSubject("CMS注册 - 邮箱验证");
        //设置邮件发送的文本内容
        mailMessage.setText("点击下面链接 http://localhost:8090/index ，验证邮箱");
//        mailMessage.setBcc("maple_6392@163.com");

//        mailMessage.setCc("maple_6392@163.com");

        try {
            //发送邮件
            mailSender.send(mailMessage);
        }catch (Exception e) {
            return "EXCEPTION：" + e.getMessage();
        }
        logger.info("邮件测试");

        return "SUCCESS";
    }

    @RequestMapping("/mail")
    @ResponseBody
    public String emailTest3() {

        MailInfo mailInfo = new MailInfo();
        mailInfo.setToMailArray(new String[]{"maple6392@aliyun.com"});
        mailInfo.setSubject("CMS注册 - 邮箱验证");
        mailInfo.setContent("点击下面链接 http://localhost:8090/index ，验证邮箱");

        try {
            sendEmailService.sendSimpleMail(mailInfo);
            return "SUCCESS";
        } catch (Exception ex) {
            return "EXCEPTION: " + ex.getMessage();
        }
    }

}
