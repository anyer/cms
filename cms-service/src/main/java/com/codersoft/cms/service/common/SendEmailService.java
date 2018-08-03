package com.codersoft.cms.service.common;

import com.codersoft.cms.common.bean.MailInfo;

/**
 * @program: SendEmailService
 * @author: Alex.D
 * @create: 2018-07-16 20:00
 * @description: 邮件业务处理操作接口
 **/
public interface SendEmailService {

    void sendSimpleMail(MailInfo mailInfo);

    void sendComplexMail(MailInfo mailInfo);

//    void sendHtmlMail(MailInfo mailInfo);
//
//    void sendPictureMail(MailInfo mailInfo);
//
//    void sendMailTakeAccessory(MailInfo mailInfo);

}
