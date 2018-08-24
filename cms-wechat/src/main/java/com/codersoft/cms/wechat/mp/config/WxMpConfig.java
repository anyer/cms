package com.codersoft.cms.wechat.mp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 功能描述: 基础配置
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年8月24日 上午11:13
 */
@Configuration
public class WxMpConfig {

  @Value("#{wxProperties.wx_token}")
  private String token;

  @Value("#{wxProperties.wx_appid}")
  private String appid;

  @Value("#{wxProperties.wx_appsecret}")
  private String appsecret;

  @Value("#{wxProperties.wx_aeskey}")
  private String aesKey;

  public String getToken() {
    return this.token;
  }

  public String getAppid() {
    return this.appid;
  }

  public String getAppsecret() {
    return this.appsecret;
  }

  public String getAesKey() {
    return this.aesKey;
  }

}
