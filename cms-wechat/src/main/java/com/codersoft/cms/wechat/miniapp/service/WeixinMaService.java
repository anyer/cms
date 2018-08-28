package com.codersoft.cms.wechat.miniapp.service;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import com.codersoft.cms.wechat.miniapp.config.WxMaProperties;
import com.codersoft.cms.wechat.miniapp.handler.miniappKfSessionHandler;
import com.codersoft.cms.wechat.miniapp.handler.miniappLogHandler;
import com.codersoft.cms.wechat.miniapp.handler.miniappMsgHandler;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 *
 * 功能描述: 消息路由
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:48
 */
@Service
public class WeixinMaService extends WxMaServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	protected miniappLogHandler logHandler;

	@Autowired
	protected miniappKfSessionHandler kfSessionHandler;

	@Autowired
	private miniappMsgHandler msgHandler;

	@Autowired
	private WxMaProperties properties;

	private WxMaMessageRouter router;

	@PostConstruct
	public void init() {
		WxMaInMemoryConfig config = new WxMaInMemoryConfig();
		config.setAppid(this.properties.getAppid());
		config.setSecret(this.properties.getSecret());
		config.setToken(this.properties.getToken());
		config.setAesKey(this.properties.getAesKey());
		config.setMsgDataFormat(this.properties.getMsgDataFormat());
		super.setWxMaConfig(config);
		this.refreshRouter();
	}

	private void refreshRouter() {
		final WxMaMessageRouter newRouter = new WxMaMessageRouter(this);

		// 记录所有事件的日志user_enter_tempsession
		newRouter.rule().handler(this.logHandler).next();
		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event("user_enter_tempsession").handler(this.getKfSessionHandler()).end();

		newRouter.rule().async(false).msgType(XmlMsgType.EVENT).event(EventType.SCAN).handler(this.getMsgHandler()).end();

		// 默认
		newRouter.rule().async(false).handler(this.getMsgHandler()).end();

		this.router = newRouter;
	}

	public miniappLogHandler getLogHandler() {
		return logHandler;
	}

	public void setLogHandler(miniappLogHandler logHandler) {
		this.logHandler = logHandler;
	}

	public miniappKfSessionHandler getKfSessionHandler() {
		return kfSessionHandler;
	}

	public void setKfSessionHandler(miniappKfSessionHandler kfSessionHandler) {
		this.kfSessionHandler = kfSessionHandler;
	}

	public miniappMsgHandler getMsgHandler() {
		return msgHandler;
	}

	public void setMsgHandler(miniappMsgHandler msgHandler) {
		this.msgHandler = msgHandler;
	}

	public void route(WxMaMessage message) {
		try {
			this.router.route(message);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}

	}

}
