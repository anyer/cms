package com.codersoft.cms.wechat.miniapp.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.codersoft.cms.wechat.miniapp.service.WeixinMaService;
import com.codersoft.cms.wechat.miniapp.util.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述: 小程序用户信息接口
 *
 * @param:
 * @return:
 * @auther: Yuanw
 * @email: wei.yuan04@mljr.com
 * @date: 2018年08月28日 下午3:53
 */
@RestController
@RequestMapping("/wechat/user")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WeixinMaService weixinMaService;

    /**
     * 登陆接口
     */
    @GetMapping("login")
    public String login(String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        try {
            WxMaJscode2SessionResult session = this.weixinMaService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return JsonUtils.toJson(session);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("info")
    public String info(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        // 用户信息校验
        if (!this.weixinMaService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.weixinMaService.getUserService().getUserInfo(sessionKey, encryptedData, iv);

        return JsonUtils.toJson(userInfo);
    }
    protected WxMaService getWxMaService() {
        return this.weixinMaService;
      }
}
