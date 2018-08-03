package com.codersoft.cms.common.utils;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.bean.ResultPages;

import java.util.List;

/**
 * @program: ResultMessageUtils
 * @author: Alex.D
 * @create: 2018-07-10 14:06
 * @description: 返回分页信息工具类
 **/
public class ResultPagesUtils {

    /**
     * 返回分页结果信息
     * @param messageCode
     * @param returnData
     * @param count
     * @param list
     * @return
     */
    public static ResultPages returnResultPage(MessageCode messageCode, Object returnData, Long count, List list) {

        ResultPages resultPages = new ResultPages();
        resultPages.setReturnCode(messageCode.getCode());
        resultPages.setReturnMessage(messageCode.getMsg());
        resultPages.setReturnData(returnData);
        resultPages.setCount(count);
        resultPages.setList(list);

        return resultPages;
    }

    /**
     * 返回分页结果异常信息
     * @param messageCode
     * @param returnExpectionMsg
     * @return
     */
    public static ResultPages returnExpectionResultMessage(MessageCode messageCode, String returnExpectionMsg, Long count, List list) {

        ResultPages resultPages = new ResultPages();
        resultPages.setReturnCode(messageCode.getCode());
        resultPages.setReturnExpectionMsg(messageCode.getMsg() + ":" + returnExpectionMsg);
        resultPages.setCount(count);
        resultPages.setList(list);

        return resultPages;
    }

    /**
     * 返回分页结果信息
     * @param messageCode
     * @param count
     * @param list
     * @return
     */
    public static ResultPages returnResultPage(MessageCode messageCode, Long count, List list) {
        return returnResultPage(messageCode, null, count, list);
    }

}
