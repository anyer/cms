package com.codersoft.cms.wechat.mp.handler;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * 功能描述: 客服消息处理
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年15月24日 上午11:15
 */
@Component
public class KfSessionHandler extends AbstractHandler{

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
      Map<String, Object> context, WxMpService wxMpService,
            WxSessionManager sessionManager) {
    //TODO 对会话做处理
    return null;
  }

}
