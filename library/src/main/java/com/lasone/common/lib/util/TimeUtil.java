package com.lasone.common.lib.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author AsOne.L
 */
public final class TimeUtil {

    private TimeUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("can't be instantiated");
    }

    /**
     * 判断某一时间是否是今天
     */
    public static boolean isToday(long time) {
        Calendar today = Calendar.getInstance();
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(time);
        if (cal.get(Calendar.YEAR) == (today.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
            return diffDay == 0;
        }
        return false;
    }

    /**
     * 获取今天开始时间
     */
    public static long getTodayStartTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取今天结束时间
     */
    public static long getTodayEndTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某一时间当天的开始时间
     */
    public static long getDateStartTime(long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某一时间当天的结束时间
     */
    public static long getDateEndTime(long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(time);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 某一时间是否在本月内
     */
    public static boolean isThisMonth(long time) {
        Calendar curCal = Calendar.getInstance(TimeZone.getDefault());
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(time);
        return cal.get(Calendar.YEAR) == curCal.get(Calendar.YEAR)
                && cal.get(Calendar.MONTH) == curCal.get(Calendar.MONTH);
    }

    /**
     * 获取本月开始时间
     */
    public static long getThisMonthStartTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取本月结束时间
     */
    public static long getThisMonthEndTime() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某一时间所在月份的开始时间
     */
    public static long getMonthStartTime(long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某一时间所在月份的结束时间
     */
    public static long getMonthEndTime(long time) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTimeInMillis(time);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     * 获取某一时间距离当前时间的差值，单位：秒
     */
    public static long getDistance2CurrentTime(Date date) {
        return calculateTime(date);
    }

    /**
     * 获取某一时间距离当前时间的差值，单位：秒
     */
    public static long getDistance2CurrentTime(long time) {
        return calculateTime(new Date(time));
    }

    private static long calculateTime(Date date) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = date.getTime() - System.currentTimeMillis();
        // 计算差多少秒//输出结果
        return Math.abs(diff % nd % nh % nm / ns);
    }
}
