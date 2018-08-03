package com.codersoft.cms.web.test.comoon;

import com.codersoft.cms.common.bean.MailInfo;
import com.codersoft.cms.service.common.impl.SendEmailServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: MailTester
 * @author: Alex.D
 * @create: 2018-07-17 10:35
 * @description: 邮件测试类
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class MailTester {

    @Autowired
    private SendEmailServiceImpl sendEmailServiceImpl;

    @Test
    public void test() {

        MailInfo mailInfo = new MailInfo();
        mailInfo.setToMailArray(new String[]{
                "1193477751@qq.com"
        });
        mailInfo.setSubject("测试");
        mailInfo.setContent("测试javax.mail邮件发送");
        sendEmailServiceImpl.sendSimpleMail(mailInfo);
    }
}
