package com.codersoft.cms.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: IpAddressUtils
 * @author: Alex.D
 * @create: 2018-07-10 15:20
 * @description: Ip地址工具类
 **/
public class IpAddressUtils {

    private final static String UNKNOWN = "unknown";

    /**
     * 获取发起请求的真实IP地址
     * @param request 请求对象
     * @return 请求方IP地址
     */
    public static String getRequestIPAddress(HttpServletRequest request)
    {
        if (request == null)
        {
            return UNKNOWN;
        }

        String ip = request.getHeader("x-forwarded-for");
        //过滤代理和反向代理
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

}
