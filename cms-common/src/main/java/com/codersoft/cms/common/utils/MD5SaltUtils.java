package com.codersoft.cms.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @program: MD5SaltUtils
 * @author: Alex.D
 * @create: 2018-07-16 09:45
 * @description: MD5+Salt值工具类
 **/
public class MD5SaltUtils {

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * MD5增加特殊字符多次加密，增加破解难度
     * @param str
     * @return
     */
    public static String getMD5String(String str) {
        String MD5Str = getMD5Str(str + "!@#");
        MD5Str = getMD5Str(MD5Str + "$%^S");
        MD5Str = getMD5Str(MD5Str + "&*(");
        return MD5Str;
    }

    /**
     * 使用MD5复杂加密的UUID作为Salt值
     * @return
     */
    public static String getSalt() {
        return getMD5String(UUID.randomUUID().toString().replace("-", ""));
    }

    /**
     * 密码校验
     * @param dataBasePassword  数据库密码
     * @param checkPassword 用户密码
     * @param salt
     * @return
     */
    public static boolean cryptographicCheck(String dataBasePassword, String checkPassword, String salt) {
        return dataBasePassword.equals(getMD5String(getMD5String(checkPassword)+salt));
    }

    public static void main(String[] args) {
        String salt = getSalt();
        System.out.println(getMD5Str("admin"));
        System.out.println(getMD5String("admin"));
//        eb45e4a8080daa7823c2fc5446564cc7
        System.out.println(getMD5String(salt));
        System.out.println(getMD5String("admin" + salt));
        System.out.println(getMD5String(getMD5String("Test_123") + "25ec47d074ffdb7c68ffd1c81a8b3506"));

        System.out.println(cryptographicCheck("7eea66e311e662317f5e7a7e74ad2f30", "Test_123", "25ec47d074ffdb7c68ffd1c81a8b3506"));
    }
}
