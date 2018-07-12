package com.codersoft.cms.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * @program: SimpleEmail
 * @author: Alex.D
 * @create: 2018-07-11 22:29
 * @description: 邮件模板实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleEmail {

    private Set<String> toSet;  //收件人
    private String subject;  //主题
    private String content;  //正文
    private boolean isHtml;  //正文是否是HTML
    private Map<String, File> attachments;  //附件路径
    private boolean isAttachment;  //是否有附件

}
