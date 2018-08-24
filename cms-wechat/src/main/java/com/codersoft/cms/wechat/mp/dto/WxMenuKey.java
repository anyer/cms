package com.codersoft.cms.wechat.mp.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * 功能描述: 菜单dto对象
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年8月24日 上午11:14
 */
public class WxMenuKey {

  private String type;
  private String content;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public WxMenuKey() {

  }

  public WxMenuKey(String type, String content) {
    this.type = type;
    this.content = content;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
