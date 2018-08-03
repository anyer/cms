package com.codersoft.cms.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String[] toMailArray;  //收件人
    private String[] ccMailArray;  //抄送人
    private String subject;  //主题
    private String content;  //正文
    private boolean isHtml;  //正文是否是HTML
    private Map<String, File> attachmentMap;  //附件路径
    private boolean isAttachment;  //是否有附件

}
