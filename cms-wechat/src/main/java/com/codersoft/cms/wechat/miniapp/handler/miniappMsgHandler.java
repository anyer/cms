package com.codersoft.cms.wechat.miniapp.handler;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codersoft.cms.wechat.miniapp.util.HttpRequestUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 *
 * 功能描述: 消息类型处理
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:51
 */
@Component
public class miniappMsgHandler extends AbstractHandler {

	@Override
	public void handle(WxMaMessage message, Map<String, Object> context, WxMaService service, WxSessionManager sessionManager) throws WxErrorException {
		// TODO Auto-generated method stub
		String Url = "http://www.tuling123.com/openapi/api";
		String param  ="key=3284c64a7eee4040b539b331f4cf81d7&info="+message.getContent()+"&userid="+message.getFromUser();
		String resstr =  HttpRequestUtil.doPost(Url, param);
		JSONObject jsonstr = JSON.parseObject(resstr,JSONObject.class);
		String textcontent = "无匹配结果";
		System.out.println(jsonstr);
		if (null!=jsonstr) {
			textcontent = jsonstr.getString("text");
		}
		service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content(textcontent).toUser(message.getFromUser()).build());
	System.out.println(WxMaKefuMessage.newTextBuilder().content(textcontent).toUser(message.getFromUser()).build().toString());
	}


}
