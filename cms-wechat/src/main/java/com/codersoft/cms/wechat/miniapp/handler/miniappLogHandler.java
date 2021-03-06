package com.codersoft.cms.wechat.miniapp.handler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * 功能描述: 日志记录
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:51
 */
@Component
public class miniappLogHandler extends AbstractHandler {

	@Override
	public void handle(WxMaMessage message, Map<String, Object> context, WxMaService service, WxSessionManager sessionManager) throws WxErrorException {
		// TODO Auto-generated method stub
		System.out.println("收到消息：" + message.toString());

	}
}
