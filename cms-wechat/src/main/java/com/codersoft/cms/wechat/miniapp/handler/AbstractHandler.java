package com.codersoft.cms.wechat.miniapp.handler;

import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * 功能描述: 处理基类
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:52
 */
public abstract class AbstractHandler implements WxMaMessageHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected final Gson gson = new Gson();

}
