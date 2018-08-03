package com.codersoft.cms.common.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: ResultPages
 * @author: Alex.D
 * @create: 2018-07-19 14:37
 * @description: 返回分页数据
 **/
@Setter
@Getter
public class ResultPages<T> extends ResultMessage {

    //总数
    private Long count;
    //分页数据集合
    private List<T> list;

}
