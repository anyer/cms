package com.codersoft.cms.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: ResultMessage
 * @author: Alex.D
 * @create: 2018-07-10 13:55
 * @description: 返回信息类
 **/
@Setter
@Getter
public class ResultMessage implements Serializable {

    //返回Code
    private String returnCode;
    //返回描述
    private String returnMessage;
    //返回数据
    private Object returnData;
    //返回异常信息
    private String returnExpectionMsg;

}
