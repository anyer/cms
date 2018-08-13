package com.codersoft.cms.service.common;

import com.codersoft.cms.dao.bean.MailInfo;
import com.codersoft.cms.dao.entity.SysUser;

/**
 * @program: SendEmailService
 * @author: Alex.D
 * @create: 2018-07-16 20:00
 * @description: 邮件业务处理操作接口
 **/
public interface SendEmailService {

    void sendSimpleMail(MailInfo mailInfo);

    void sendComplexMail(MailInfo mailInfo);

    void sendHtmlMail(MailInfo mailInfo);

    void sendPictureMail(MailInfo mailInfo);

    void sendMailTakeAccessory(MailInfo mailInfo);

    /**
     * 发送邮箱激活邮件
     *
     * @param userName 用户名
     * @param toEmail  激活邮箱
     */
    void sendEmailActive(String userName, String toEmail);

    /**
     * 发送密码找回邮件（发送验证码）
     *
     * @param code    验证码
     * @param toEmail 邮箱
     */
    void sendPasswordRecoveryEmail(String code, String toEmail);
}
