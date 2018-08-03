package com.codersoft.cms.common.utils;

import java.util.UUID;

/**
 * @program: TokenUtils
 * @author: Alex.D
 * @create: 2018-07-16 09:15
 * @description: Token工具类
 **/
public class TokenUtils {

    /**
     * 将随机生成的UUID作为Token值
     * @return
     */
    public static String UUIDTokenGenerator() {
        return UUID.randomUUID().toString().replace("-","");
    }


    public static void main(String[] args) {
        System.out.printf(UUIDTokenGenerator());
    }

}
