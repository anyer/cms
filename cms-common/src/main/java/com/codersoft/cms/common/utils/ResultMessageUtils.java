package com.codersoft.cms.common.utils;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;

/**
 * @program: ResultMessageUtils
 * @author: Alex.D
 * @create: 2018-07-10 14:06
 * @description: 返回信息工具类
 **/
public class ResultMessageUtils {

    /**
     * 返回结果信息
     * @param messageCode
     * @param returnData
     * @return
     */
    public static ResultMessage returnResultMessage(MessageCode messageCode, Object returnData) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setReturnCode(messageCode.getCode());
        resultMessage.setReturnMessage(messageCode.getMsg());
        resultMessage.setReturnData(returnData);
        return resultMessage;
    }

    /**
     * 返回结果异常信息
     * @param messageCode
     * @param returnExpectionMsg
     * @return
     */
    public static ResultMessage returnExpectionResultMessage(MessageCode messageCode, String returnExpectionMsg) {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setReturnCode(messageCode.getCode());
        resultMessage.setReturnExpectionMsg(messageCode.getMsg() + ":" + returnExpectionMsg);
        resultMessage.setReturnMessage(resultMessage.getReturnExpectionMsg());
        return resultMessage;
    }

    /**
     * 返回结果信息
     * @param messageCode
     * @return
     */
    public static ResultMessage returnResultMessage(MessageCode messageCode) {
        return returnResultMessage(messageCode, null);
    }

}
