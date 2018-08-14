package com.codersoft.cms.service.common.impl;

import com.codersoft.cms.common.utils.DateAndTimestampUtils;
import com.codersoft.cms.common.utils.RandomUtils;
import com.codersoft.cms.dao.bean.MailInfo;
import com.codersoft.cms.service.common.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
    private SimpleMailMessage simpleMailMessage;

    /**
     * 发送邮箱激活邮件
     *
     * @param userName 用户名
     * @param toEmail  激活邮箱
     */
    @Override
    public void sendEmailActive(String userName, String toEmail) {
        Long timestamp = DateAndTimestampUtils.getAfterMinutesTimestampValue(30);
        //激活连接
        String valicationURL = "<a href='http://localhost:8090/admin/activeEmail?name=" + userName + "&timestamp=" + timestamp + "'>点击此处激活</a>";
        MailInfo mailInfo = new MailInfo();
        mailInfo.setToMailArray(new String[]{toEmail});
        mailInfo.setSubject("CMS注册 - 邮箱激活");
        mailInfo.setContent("请点击连接激活邮箱：" + valicationURL + "。<br />链接有效时间30分钟，请尽快激活！！！");

        //发送注册激活邮件
        sendHtmlMail(mailInfo);
    }

    /**
     * 发送密码找回邮件（发送验证码）
     *
     * @param toEmail 邮箱
     * @param code    验证码
     */
    @Override
    public void sendPasswordRecoveryEmail(String toEmail, Integer code) {
        Long timestamp = DateAndTimestampUtils.getAfterMinutesTimestampValue(30);
//        int code = RandomUtils.randomCreateNumberOfDigits(6);
        MailInfo mailInfo = new MailInfo();
        mailInfo.setToMailArray(new String[]{toEmail});
        mailInfo.setSubject("CMS - 忘记密码");
        mailInfo.setContent("验证码：<b>" + code + "</b><br />该验证码有效时间30分钟！！！");

        sendHtmlMail(mailInfo);
    }

    /**
     * 普通文本邮件发送
     *
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
     *
     * @param mailInfo
     */
    @Override
    public void sendComplexMail(MailInfo mailInfo) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            mimeMessage.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
        } catch (MessagingException me) {

        }
    }

    /**
     * HTML格式邮件发送
     *
     * @param mailInfo
     */
    @Override
    public void sendHtmlMail(MailInfo mailInfo) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        try {
            messageHelper.setFrom(simpleMailMessage.getFrom());
            messageHelper.setTo(mailInfo.getToMailArray());
            messageHelper.setSubject(mailInfo.getSubject());
            messageHelper.setText("<html><head></head><body>" + mailInfo.getContent() + "</body></html>", true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带图片的邮件发送
     *
     * @param mailInfo 邮件对象
     */
    @Override
    public void sendPictureMail(MailInfo mailInfo) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mailMessage, true);
            messageHelper.setFrom(simpleMailMessage.getFrom());
            messageHelper.setTo(mailInfo.getToMailArray());
            messageHelper.setSubject(mailInfo.getSubject());
            messageHelper.setText(mailInfo.getContent(), true);
            FileSystemResource img = new FileSystemResource(mailInfo.getAttachmentMap().get("picturePath"));
            messageHelper.addInline("aaa", img);
            //发送邮件
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 带附件邮件发送
     *
     * @param mailInfo
     */
    @Override
    public void sendMailTakeAccessory(MailInfo mailInfo) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
            messageHelper.setTo(mailInfo.getToMailArray());
            messageHelper.setSubject(mailInfo.getSubject());
            messageHelper.setText("<html><head></head><body><h1>" + mailInfo.getContent() + "</h1></body></html>", true);
            FileSystemResource file = new FileSystemResource(mailInfo.getAttachmentMap().get("accessoryPath"));
            messageHelper.addAttachment("name", file);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
