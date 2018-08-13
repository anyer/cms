package com.codersoft.cms.common.utils;

import java.util.Random;

/**
 * @program: RandomUtils
 * @author: Alex.D
 * @create: 2018-08-10 16:42
 * @description: 随机数工具类
 **/
public class RandomUtils {

    /**
     * 生成指定位数的数字随机数(字符串转数字）
     *
     * @param num 位数[1-10)
     * @return
     */
    public static int createNumberOfStrToDigits(int num) {

        Random random = new Random();
        String randomDigitsStr = "";
        int randomDigits = 0;
        if (num <= 0 || num > 10) {
            return -1;
        }
        for (int i = 0; i < num; i++) {
            randomDigitsStr+=random.nextInt(10);
        }
        randomDigits = Integer.valueOf(randomDigitsStr).intValue();
        return randomDigits;
    }

    /**
     * 生成指定位数的数字随机数（Ranom函数）
     *
     * @param num 位数[1-10)
     * @return
     */
    public static int randomCreateNumberOfDigits(int num) {

        if (num <= 0 || num > 10) {
            return -1;
        }
        int number = (int)Math.pow(10,num);
        return new Random().nextInt(number - 1);

    }

    public static void main(String[] args) {

        System.out.println("res1:" + createNumberOfStrToDigits(9));
        System.out.println("res2:" + randomCreateNumberOfDigits(9));

    }
}
