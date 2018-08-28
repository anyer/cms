package com.codersoft.cms.wechat.miniapp.handler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * 功能描述: 客服消息弹出
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:52
 */
@Component
public class miniappKfSessionHandler extends AbstractHandler {

	@Override
	public void handle(WxMaMessage message, Map<String, Object> context, WxMaService service, WxSessionManager sessionManager) throws WxErrorException {
		// TODO Auto-generated method stub
		// service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("客服进入").toUser(message.getFromUser()).build());
		String postData = "{\"touser\": \""+message.getFromUser()+"\",\"msgtype\": \"link\",\"link\": {  "
				+ "         \"title\": \"阳光保险集团\",           \"description\": \"阳光保险集团客户服务\",           \"url\": \"https://m.sinosig.com/\",           \"thumb_url\": \"https://product.sinosig.com/upload/150914/1509141247014020.jpg\"     } }";
		service.post("https://api.weixin.qq.com/cgi-bin/message/custom/send", postData);
		
	}

}
