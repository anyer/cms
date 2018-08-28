package com.codersoft.cms.wechat.miniapp.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 功能描述: 小程序配置文件
 *
 * @param: 
 * @return: 
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:54
 */
@Configuration
public class WxMaProperties {
    /**
     * 设置微信小程序的appid
     */
	@Value("#{miniappProperties.miniapp_appid}")
    private String appid;

    /**
     * 设置微信小程序的Secret
     */
	@Value("#{miniappProperties.miniapp_appsecret}")
    private String secret;

    /**
     * 设置微信小程序的token
     */
	@Value("#{miniappProperties.miniapp_token}")
    private String token;

    /**
     * 设置微信小程序的EncodingAESKey
     */
	@Value("#{miniappProperties.miniapp_aeskey}")
    private String aesKey;

    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return this.aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getMsgDataFormat() {
        return msgDataFormat;
    }

    public void setMsgDataFormat(String msgDataFormat) {
        this.msgDataFormat = msgDataFormat;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
