package com.codersoft.cms.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: DateAndTimestampUtils
 * @author: Alex.D
 * @create: 2018-08-10 16:28
 * @description: 时间及时间戳工具类
 **/
public class DateAndTimestampUtils {

    /**
     * 获取当前时间后指定分钟数的时间戳
     *
     * @param mintes 分钟数
     * @return
     */
    public static Timestamp getAfterMinutesTimestamp(int mintes) {
        return new Timestamp(getAfterMinutesTimestampValue(mintes));
    }

    /**
     * 获取当前时间后指定分钟数的时间戳值
     *
     * @param minutes 分钟数
     * @return
     */
    public static Long getAfterMinutesTimestampValue(int minutes){
        return DateUtils.addMinutes(new Date(), minutes).getTime();
    }

    /**
     * 获取当前时间后指定小时数的时间戳
     *
     * @param hours 小时数
     * @return
     */
    public static Timestamp getAfterHoursTimestamp(int hours) {
        return new Timestamp(getAfterHoursTimestampValue(hours));
    }

    /**
     * 获取当前时间后指定小时数的时间戳值
     *
     * @param hours 小时数
     * @return
     */
    public static Long getAfterHoursTimestampValue(int hours){
        return DateUtils.addHours(new Date(), hours).getTime();
    }

    /**
     * 获取当前时间后指定天数的时间戳
     *
     * @param days 天数
     * @return
     */
    public static Timestamp getAfterDaysTimestamp(int days) {
        return new Timestamp(getAfterDaysTimestampValue(days));
    }

    /**
     * 获取当前时间后指定天数的时间戳值
     *
     * @param days 天数
     * @return
     */
    public static Long getAfterDaysTimestampValue(int days){
        return DateUtils.addDays(new Date(), days).getTime();
    }

}
