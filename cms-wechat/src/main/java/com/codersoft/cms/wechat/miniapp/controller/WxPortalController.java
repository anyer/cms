package com.codersoft.cms.wechat.miniapp.controller;


import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.codersoft.cms.wechat.miniapp.service.WeixinMaService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 *
 * 功能描述: 小程序消息入口
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:53
 */
@RestController
@RequestMapping("/miniapp/portal")
public class WxPortalController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WeixinMaService weixinMaService;

	@GetMapping(produces = "text/plain;charset=utf-8")
	public String authGet(@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "timestamp", required = false) String timestamp, @RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "echostr", required = false) String echostr) {
		this.logger.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]", signature, timestamp, nonce, echostr);

		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalArgumentException("请求参数非法，请核实!");
		}

		if (this.weixinMaService.checkSignature(timestamp, nonce, signature)) {
			return echostr;
		}

		return "非法请求";
	}

	@PostMapping(produces = "application/xml; charset=UTF-8")
	public String post(@RequestBody String requestBody, @RequestParam("msg_signature") String msgSignature, @RequestParam("encrypt_type") String encryptType,
			@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
		this.logger.info("\n接收微信请求：[msg_signature=[{}], encrypt_type=[{}], signature=[{}]," + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				msgSignature, encryptType, signature, timestamp, nonce, requestBody);

		final boolean isJson = Objects.equals(this.weixinMaService.getWxMaConfig().getMsgDataFormat(), WxMaConstants.MsgDataFormat.JSON);
		if (StringUtils.isBlank(encryptType)) {
			// 明文传输的消息
			WxMaMessage inMessage;
			if (isJson) {
				inMessage = WxMaMessage.fromJson(requestBody);
			} else {// xml
				inMessage = WxMaMessage.fromXml(requestBody);
			}

			this.route(inMessage);
			return "success";
		}

		if ("aes".equals(encryptType)) {
			// 是aes加密的消息
			WxMaMessage inMessage;
			if (isJson) {
				inMessage = WxMaMessage.fromEncryptedJson(requestBody, this.weixinMaService.getWxMaConfig());

			} else {// xml

				inMessage = WxMaMessage.fromEncryptedXml(requestBody, this.weixinMaService.getWxMaConfig(), timestamp, nonce, msgSignature);

			}

			this.route(inMessage);
			return "success";
		}

		throw new RuntimeException("不可识别的加密类型：" + encryptType);
	}

	private void route(WxMaMessage message) {
		try {
			this.weixinMaService.route(message);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
	}

}
