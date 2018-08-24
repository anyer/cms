package com.codersoft.cms.wechat.mp.handler;

import com.google.gson.Gson;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * 功能描述: 业务处理
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年8月24日 上午11:15
 */
public abstract class AbstractHandler implements WxMpMessageHandler {


    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final Gson gson = new Gson();

}
