package com.codersoft.cms.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.mail.Session;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * @program: MailInfo
 * @author: Alex.D
 * @create: 2018-07-11 22:29
 * @description: 邮件模板实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {

    /**
     * 收件人
     */
    private String[] toMailArray;
    /**
     * 抄送人
     */
    private String[] ccMailArray;
    /**
     * 主题
     */
    private String subject;
    /**
     * 正文
     */
    private String content;
    /**
     * 正文是否是HTML
     */
    private boolean isHtml;
    /**
     * 附件路径
     */
    private Map<String, File> attachmentMap;
    /**
     * 是否有附件
     */
    private boolean isAttachment;

}
