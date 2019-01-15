package com.lynn.blog.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期的工具类
 * @author liyi
 */
public final class DateUtils {

    public static boolean isLegalDate(String str, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Date parseString2Date(String str, String pattern) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Calendar parseString2Calendar(String str, String pattern) {
        return parseDate2Calendar(parseString2Date(str, pattern));
    }
    public static String parseLong2DateString(long date,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String sd = sdf.format(new Date(date));
        return sd;
    }

    public static Calendar parseDate2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date parseCalendar2Date(Calendar calendar) {
        return calendar.getTime();
    }

    public static String parseCalendar2String(Calendar calendar, String pattern) {
        return parseDate2String(parseCalendar2Date(calendar), pattern);
    }

    public static String parseDate2String(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    public static String formatTime(long time) {
        long nowTime = System.currentTimeMillis();
        long interval = nowTime - time;
        long hours = 3600 * 1000;
        long days = hours * 24;
        long fiveDays = days * 5;
        if (interval < hours) {
            long minute = interval / 1000 / 60;
            if (minute == 0) {
                return "刚刚";
            }
            return minute + "分钟前";
        } else if (interval < days) {
            return interval / 1000 / 3600 + "小时前";
        } else if (interval < fiveDays) {
            return interval / 1000 / 3600 / 24 + "天前";
        } else {
            Date date = new Date(time);
            return parseDate2String(date, "MM-dd");
        }
    }

    /**
     * 计算任意时间是星期几
     * 利用蔡勒公式
     * 公式：（年份代码+月份代码+日期）mod 7
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getWeek(int year, int month, int day) {
        int lastYear = 1700;
        int nextYear = 2099;
        while (year < lastYear || year > nextYear) {
            if (year < lastYear) {
                year += 400;
            } else if (year > nextYear) {
                year -= 400;
            }
        }
        int x = year % 1000 % 100;
        int srcYear = (x / 4 + x) % 7;
        if (year >= lastYear && year <= 1799) {
            srcYear += 5;
        } else if (year >= 1800 && year <= 1899) {
            srcYear += 3;
        } else if (year >= 1900 && year <= 1999) {
            srcYear += 1;
        }
        int srcMonth = 0;
        switch (month) {
            case 5:
                srcMonth = 0;
                break;
            case 8:
                srcMonth = 1;
                break;
            case 2:
            case 3:
            case 11:
                srcMonth = 2;
                break;
            case 6:
                srcMonth = 3;
                break;
            case 9:
            case 12:
                srcMonth = 4;
                break;
            case 4:
            case 7:
                srcMonth = 5;
                break;
            case 1:
            case 10:
                srcMonth = 6;
                break;
            default:
                break;
        }
        if (year % 4 == 0 && year % 100 != 0) {
            if(year % 400 == 0){
                switch (month) {
                    case 1:
                        srcMonth = 5;
                        break;
                    case 2:
                        srcMonth = 1;
                        break;
                    default:
                        break;

                }
            }
        }
        int week = (srcYear + srcMonth + day) % 7;
        return week;
    }

    private DateUtils() {
        throw new AssertionError();
    }

    /**
     * 获取当前日期的字符串表达式
     * @return
     */
    public static String getCurrentSimpleDate(){
        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ndate=df.format(date);
        return ndate;
    }

    /**
     * 获取当前日期的字符串表达式(只需要年月日)
     * @return
     */
    public static String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        Date date=calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String ndate=df.format(date);
        return ndate;
    }
    public static String getStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return sdf.format(todayStart.getTime());
    }

    public static String getEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return sdf.format(todayEnd.getTime());
    }
}
