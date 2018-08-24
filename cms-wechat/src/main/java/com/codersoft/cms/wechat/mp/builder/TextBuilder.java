package com.codersoft.cms.wechat.mp.builder;

import com.codersoft.cms.wechat.mp.service.WeixinService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 *
 * 功能描述: 文本消息处理
 *
 * @param: [content, wxMessage, service]
 * @return: me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年8月24日 上午11:12
 */
public class TextBuilder extends AbstractBuilder {

  @Override
  public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
      WeixinService service)   {

    WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content(content)
        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
        .build();
    return m;
  }

}
