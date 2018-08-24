package com.codersoft.cms.wechat.mp.builder;

import com.codersoft.cms.wechat.mp.service.WeixinService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 功能描述: 处理类
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年11月24日 上午11:11
 */
public abstract class AbstractBuilder {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public abstract WxMpXmlOutMessage build(String content,
      WxMpXmlMessage wxMessage, WeixinService service) ;
}
