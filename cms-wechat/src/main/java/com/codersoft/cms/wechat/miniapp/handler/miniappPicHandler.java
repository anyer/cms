package com.codersoft.cms.wechat.miniapp.handler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public  class miniappPicHandler extends AbstractHandler {

	@Override
	public void handle(WxMaMessage message, Map<String, Object> context, WxMaService service, WxSessionManager sessionManager) throws WxErrorException {
		// TODO Auto-generated method stub
		
	}

}
