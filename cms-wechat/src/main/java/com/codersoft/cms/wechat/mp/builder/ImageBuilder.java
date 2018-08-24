package com.codersoft.cms.wechat.mp.builder;

import com.codersoft.cms.wechat.mp.service.WeixinService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 *
 * 功能描述: 图片消息处理
 *
 * @param: [content, wxMessage, service]
 * @return: me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年12月24日 上午11:12
 */
public class ImageBuilder extends AbstractBuilder {

  @Override
  public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage,
      WeixinService service) {
    WxMpXmlOutImageMessage m = WxMpXmlOutMessage.IMAGE().mediaId(content)
        .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
        .build();

    return m;
  }

}
