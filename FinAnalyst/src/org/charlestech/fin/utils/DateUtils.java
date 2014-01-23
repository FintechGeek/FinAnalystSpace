package org.charlestech.fin.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/*
 * @Description Date time utilities
 * @author Charles Chen
 * @date 14-1-23
 * @version 1.0
 */
public class DateUtils {
    /* Define date time format */
    public final static String yyyy = "yyyy";
    public final static String yyyy_MM_dd = "yyyy-MM-dd";
    public final static String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public final static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public final static String yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss SSS";

	/* Define date time format */

    /**
     * Get current date time in specified pattern
     *
     * @return date time string
     */
    public static String now(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(Calendar.getInstance().getTime());
    }

    /**
     * Get current date time in pattern yyyy
     *
     * @return date time string
     */
    public static String now_yyyy() {
        return now(yyyy);
    }

    /**
     * Get current date time in pattern yyyy-MM-dd
     *
     * @return date time string
     */
    public static String now_yyyy_MM_dd() {
        return now(yyyy_MM_dd);
    }

    /**
     * Get current date time in pattern yyyy-MM-dd HH:mm:ss
     *
     * @return date time string
     */
    public static String now_yyyy_MM_dd_HH_mm_ss() {
        return now(yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * Get current date time in pattern yyyy-MM-dd HH:mm:ss:SSS
     *
     * @return date time string
     */
    public static String now_yyyy_MM_dd_HH_mm_ss_SSS() {
        return now(yyyy_MM_dd_HH_mm_ss_SSS);
    }

    /**
     * Convert date into string in specified pattern
     *
     * @param date
     * @param pattern
     * @return date time string
     */
    public static String dateToStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * Convert date into string in pattern yyyy-MM-dd
     *
     * @param date
     * @return date time string
     */
    public static String dateToStr_yyyy_MM_dd(Date date) {
        return dateToStr(date, yyyy_MM_dd);
    }

    /**
     * Convert date into string in pattern yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return date time string
     */
    public static String dateToStr_yyyy_MM_dd_HH_mm_ss(Date date) {
        return dateToStr(date, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * Convert string into date in English pattern
     *
     * @param dateStr
     * @return Date
     */
    public static Date strToDateEN(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "EEE MMM dd hh:mm:ss 'CST' yyyy", java.util.Locale.US);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * Convert string in specified pattern into date
     *
     * @param dateStr
     * @param pattern
     * @return Date
     */
    public static Date strToDateCN(String dateStr, String pattern) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            return date;
        }
        return date;
    }

    /**
     * Convert string in pattern yyyy-MM-dd into date
     *
     * @param datestr
     * @return Date
     */
    public static Date strToDateCN_yyyy_MM_dd(String dateStr) {
        return strToDateCN(dateStr, yyyy_MM_dd);
    }

    /**
     * Convert string in pattern yyyy-MM-dd HH:mm:ss into date
     *
     * @param dateStr
     * @return Date
     */
    public static Date strToDateCN_yyyy_MM_dd_HH_mm_ss(String dateStr) {
        return strToDateCN(dateStr, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * Convert java.util.Date into java.sql.Date
     *
     * @param date
     * @return
     */
    public static java.sql.Date utilDateToSqlDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        cal.set(year, month, day, 0, 0, 0);
        long result = cal.getTimeInMillis();
        result = result / 1000 * 1000;
        return new java.sql.Date(result);
    }

    /**
     * Convert date into Chinese
     *
     * @param date
     * @param type 0:Simplified Chinese,1:Traditional Chinese
     * @return
     */
    public static String dataToUpper(Date date, int type) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return NumberUtils.numBitToCN(year, type) + "年"
                + NumberUtils.numToCN(month, type) + "月"
                + NumberUtils.numToCN(day, type) + "日";
    }

    /**
     * Get next day
     *
     * @param date
     * @param ndays
     * @return Date
     */
    public static Date nextNDay(Date date, int n) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.DATE, n);
        return cal.getTime();
    }

    /**
     * Get next month date
     *
     * @param date
     * @return Date
     */
    public static Date nextMonth(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.MONTH, 1);// 在月份上加1
        return cal.getTime();
    }

    /**
     * Get next year date
     *
     * @param date
     * @return Date
     */
    public static Date nextYear(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(GregorianCalendar.YEAR, 1);// 在年上加1
        return cal.getTime();
    }

    /**
     * Get days between two dates
     *
     * @param dateStr_1
     * @param dateStr_2
     * @return -1:exception,others:days
     */
    public static long getDaysBetweenTwoDates(String dateStr_1, String dateStr_2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date_1 = sdf.parse(dateStr_1);
            java.util.Date date_2 = sdf.parse(dateStr_2);
            long days = 0;
            if (date_1.before(date_2))
                days = (date_2.getTime() - date_1.getTime())
                        / (24 * 60 * 60 * 1000);
            else
                days = (date_1.getTime() - date_2.getTime())
                        / (24 * 60 * 60 * 1000);
            return days;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get hours between two dates
     *
     * @param dateStr_1
     * @param dateStr_2
     * @return -1:exception,others:days
     */
    public static double getHoursBetweenTwoDays(String dateStr_1,
                                                String dateStr_2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            java.util.Date date_1 = sdf.parse(dateStr_1);
            java.util.Date date_2 = sdf.parse(dateStr_2);
            double days = 0;
            if (date_1.before(date_2))
                days = (date_2.getTime() - date_1.getTime())
                        / (60.0 * 60.0 * 1000.0);
            else
                days = (date_1.getTime() - date_2.getTime())
                        / (60.0 * 60.0 * 1000.0);
            return days;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Get Chinese week day
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDateCN_yyyy_MM_dd(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * Get the first day of current month
     *
     * @return date string
     */
    public String getFirstDayOfMonth() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// Set 1st of this month
        str = sdf.format(lastDate.getTime());
        return str;
    }

    /**
     * Get the last day of current month
     *
     * @return date string
     */
    public String getLastDayOfMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1); // Set 1st of this month
        lastDate.add(Calendar.MONTH, 1); // Add one month
        lastDate.add(Calendar.DATE, -1);// Minus one day

        return sdf.format(lastDate.getTime());
    }

    /**
     * Get day distance from Monday this week
     *
     * @param cn 1: Chinese tradition,0:standard
     * @return
     */
    public static int getMonDist(boolean cn) {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (cn) {
            if (dayOfWeek == 1)
                return -6;
            return 2 - dayOfWeek;
        }
        return 2 - dayOfWeek;
    }

    /**
     * Get day distance from Sunday this week
     *
     * @param cn 1: Chinese tradition,0:standard
     * @return
     */
    public static int getSunDist(boolean cn) {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (cn) {
            if (dayOfWeek == 1)
                return 0;
            return 8 - dayOfWeek;
        }
        return 1 - dayOfWeek;
    }

    /**
     * Get Monday date of this week
     *
     * @param cn 1: Chinese tradition,0:standard
     * @return
     */
    public static Date getMondayCurWeek(boolean cn) {
        Calendar cd = Calendar.getInstance();
        cd.add(cd.DATE, getMonDist(cn));
        return cd.getTime();
    }

    /**
     * Get Sunday date of this week
     *
     * @param cn 1: Chinese tradition,0:standard
     * @return
     */
    public static Date getSundayCurWeek(boolean cn) {
        Calendar cd = Calendar.getInstance();
        cd.add(cd.DATE, getSunDist(cn));
        return cd.getTime();
    }
}