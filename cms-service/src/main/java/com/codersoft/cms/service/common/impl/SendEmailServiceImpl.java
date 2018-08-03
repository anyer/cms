package com.codersoft.cms.service.common.impl;

import com.codersoft.cms.common.bean.MailInfo;
import com.codersoft.cms.service.common.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @program: SendEmailServiceImpl
 * @author: Alex.D
 * @create: 2018-07-16 20:07
 * @description: 邮件业务操作处理实现类
 **/
@Service
@Component
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Resource
    SimpleMailMessage simpleMailMessage;

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /**
     * 普通文本邮件发送
     * @param mailInfo 邮件对象
     */
    @Override
    public void sendSimpleMail(MailInfo mailInfo) {

        simpleMailMessage.setSubject(mailInfo.getSubject());
        simpleMailMessage.setText(mailInfo.getContent());
        simpleMailMessage.setTo(mailInfo.getToMailArray());
        mailSender.send(simpleMailMessage);

    }

    /**
     * 复杂邮件发送（含图片、附件,使用HTML、模板）
     * @param mailInfo
     */
    @Override
    public void sendComplexMail(MailInfo mailInfo){
        MimeMessage mimeMessage = mailSender.createMimeMessage();

    }

    /**
     * HTML格式邮件发送
     * @param mimeMessage   MIME信息
     * @param subject   主题
     * @param content   正文
     * @param toMail    收件人邮箱
     */
    public void sendHtmlMail(MimeMessage mimeMessage,String subject,String content,String... toMail) {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setTo(toMail);
            messageHelper.setSubject(subject);
            messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带图片的邮件发送
     * @param subject   主题
     * @param content   正文
     * @param toMail    收件人邮箱
     * @param picturePath   图片路径
     */
    public void sendPictureMail(String subject,String content,String toMail,String picturePath){
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mailMessage,true);
            messageHelper.setTo(toMail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,true);
            FileSystemResource img = new FileSystemResource(new File(picturePath));
            messageHelper.addInline("aaa",img);
            //发送邮件
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带附件邮件发送
     * @param subject   主题
     * @param content   正文
     * @param toMail    收件人邮箱
     * @param accessoryPath 附件路径
     * @param accessoryName 附件名称
     */
    public void sendMailTakeAccessory(String subject,String content,String toMail,String accessoryPath,String accessoryName){
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
            messageHelper.setTo(toMail);
            messageHelper.setSubject(subject);
            messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);
            FileSystemResource file = new FileSystemResource(new File(accessoryPath));
            messageHelper.addAttachment(accessoryName,file);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
