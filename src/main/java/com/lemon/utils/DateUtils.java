package com.lemon.utils;

import org.springframework.util.Assert;

import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by igotti on 14-8-27.
 */
public final class DateUtils {

    /**
     * Date format pattern used to parse HTTP date headers in RFC 1123 format.
     */
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    /**
     * Date format pattern used to parse HTTP date headers in RFC 1036 format.
     */
    public static final String PATTERN_RFC1036 = "EEE, dd-MMM-yy HH:mm:ss zzz";

    /**
     * Date format pattern used to parse HTTP date headers in ANSI C
     * {@code asctime()} format.
     */
    public static final String PATTERN_ASCTIME = "EEE MMM d HH:mm:ss yyyy";

    public static final String PATTERN_CHINATIME = "yyyy年MM月dd日 HH:mm";

    public static final String PATTERN_CHINESE_DATE_TIME = "MM月dd日 HH:mm";

    public static final String PATTERN_CHINATIME_SIMPLFIED = "yyyy/MM/dd HH:mm";

    public static final String PATTERN_GENERALTIME = "yyyy-MM-dd HH:mm";

    public static final String PATTERN_GENERALTIME_FULL = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_DOT_SEPERATED = "yyyy.MM.dd";

    public static final String CHINESE_YEAR_MONTH_DAY = "yyyy年MM月dd日";

    private static final String[] DEFAULT_PATTERNS = new String[]{
//            PATTERN_RFC1123,
//            PATTERN_RFC1036,
//            PATTERN_ASCTIME
            PATTERN_CHINATIME,
            PATTERN_CHINATIME_SIMPLFIED,
            PATTERN_GENERALTIME,
            PATTERN_GENERALTIME_FULL
    };

    private static final Date DEFAULT_TWO_DIGIT_YEAR_START;

    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");

    public static final TimeZone CST = TimeZone.getTimeZone("GMT+8:00");

    static {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(GMT);
        calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        DEFAULT_TWO_DIGIT_YEAR_START = calendar.getTime();
    }

    /**
     * Parses a date value.  The formats used for parsing the date value are retrieved from
     * the default http params.
     *
     * @param dateValue the date value to parse
     * @return the parsed date or null if input could not be parsed
     */
    public static Date parseDate(final String dateValue) {
        return parseDate(dateValue, null, null);
    }

    /**
     * Parses the date value using the given date formats.
     *
     * @param dateValue   the date value to parse
     * @param dateFormats the date formats to use
     * @return the parsed date or null if input could not be parsed
     */
    public static Date parseDate(final String dateValue, final String[] dateFormats) {
        return parseDate(dateValue, dateFormats, null);
    }

    /**
     * Parses the date value using the given date formats.
     *
     * @param dateValue   the date value to parse
     * @param dateFormats the date formats to use
     * @param startDate   During parsing, two digit years will be placed in the range
     *                    {@code startDate} to {@code startDate + 100 years}. This value may
     *                    be {@code null}. When {@code null} is given as a parameter, year
     *                    {@code 2000} will be used.
     * @return the parsed date or null if input could not be parsed
     */
    public static Date parseDate(final String dateValue, final String[] dateFormats, final Date startDate) {
        Assert.notNull(dateValue, "Date value");
        final String[] localDateFormats = dateFormats != null ? dateFormats : DEFAULT_PATTERNS;
        final Date localStartDate = startDate != null ? startDate : DEFAULT_TWO_DIGIT_YEAR_START;
        String v = dateValue;
        // trim single quotes around date if present
        // see issue #5279
        if (v.length() > 1 && v.startsWith("'") && v.endsWith("'")) {
            v = v.substring(1, v.length() - 1);
        }

        for (final String dateFormat : localDateFormats) {
            final SimpleDateFormat dateParser = DateFormatHolder.formatFor(dateFormat);
            dateParser.set2DigitYearStart(localStartDate);
            final ParsePosition pos = new ParsePosition(0);
            final Date result = dateParser.parse(v, pos);
            if (pos.getIndex() != 0) {
                return result;
            }
        }
        return null;
    }

    /**
     * Formats the given date according to the RFC 1123 pattern.
     *
     * @param date The date to format.
     * @return An RFC 1123 formatted date string.
     * @see #PATTERN_RFC1123
     */
    public static String formatDate(final Date date) {
        return formatDate(date, PATTERN_RFC1123);
    }

    /**
     * Formats the given date according to the specified pattern.  The pattern
     * must conform to that used by the {@link SimpleDateFormat simple date
     * format} class.
     *
     * @param date    The date to format.
     * @param pattern The pattern to use for formatting the date.
     * @return A formatted date string.
     * @throws IllegalArgumentException If the given date pattern is invalid.
     * @see SimpleDateFormat
     */
    public static String formatDate(final Date date, final String pattern) {
        Assert.notNull(date, "Date");
        Assert.notNull(pattern, "Pattern");
        final SimpleDateFormat formatter = DateFormatHolder.formatFor(pattern);
        return formatter.format(date);
    }

    /**
     * Clears thread-local variable containing {@link java.text.DateFormat} cache.
     *
     * @since 4.3
     */
    public static void clearThreadLocal() {
        DateFormatHolder.clearThreadLocal();
    }

    /**
     * This class should not be instantiated.
     */
    private DateUtils() {
    }

    /**
     * 获取指定日期月份的最大日期（当月最后一天的日期, 忽略时分秒毫秒）
     *
     * @param date
     * @return
     */
    public static Date getMaxDayOfMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }
        instance.set(Calendar.DATE, 1);
        instance.roll(Calendar.DATE, -1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * 获取指定日期月份的最小日期（当月第一天日期, 忽略时分秒毫秒）
     *
     * @param date
     * @return
     */
    public static Date getMinDayOfMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }
        instance.set(Calendar.DATE, 1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * A factory for {@link SimpleDateFormat}s. The instances are stored in a
     * threadlocal way because SimpleDateFormat is not threadsafe as noted in
     * {@link SimpleDateFormat its javadoc}.
     */
    final static class DateFormatHolder {

        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal<SoftReference<Map<String,
                SimpleDateFormat>>>() {

            @Override
            protected SoftReference<Map<String, SimpleDateFormat>> initialValue() {
                return new SoftReference<Map<String, SimpleDateFormat>>(new HashMap<String, SimpleDateFormat>());
            }

        };

        /**
         * creates a {@link SimpleDateFormat} for the requested format string.
         *
         * @param pattern a non-{@code null} format String according to
         *                {@link SimpleDateFormat}. The format is not checked against
         *                {@code null} since all paths go through
         *                {@link DateUtils}.
         * @return the requested format. This simple dateformat should not be used
         * to {@link SimpleDateFormat#applyPattern(String) apply} to a
         * different pattern.
         */
        public static SimpleDateFormat formatFor(final String pattern) {
            final SoftReference<Map<String, SimpleDateFormat>> ref = THREADLOCAL_FORMATS.get();
            Map<String, SimpleDateFormat> formats = ref.get();
            if (formats == null) {
                formats = new HashMap<String, SimpleDateFormat>();
                THREADLOCAL_FORMATS.set(new SoftReference<Map<String, SimpleDateFormat>>(formats));
            }

            SimpleDateFormat format = formats.get(pattern);
            if (format == null) {
                format = new SimpleDateFormat(pattern, Locale.CHINA);
                format.setTimeZone(DateUtils.CST);
                formats.put(pattern, format);
            }

            return format;
        }

        public static void clearThreadLocal() {
            THREADLOCAL_FORMATS.remove();
        }

    }

    /**
     * 得到指定的日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekDay(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 通过出行时间和偏移量得到行程内容对应的日期
     *
     * @param travelDate
     * @param offset
     * @return
     */
    public static Date getContentDate(Date travelDate, Integer offset) {
        return new Date(travelDate.toInstant().plus(offset, ChronoUnit.DAYS).toEpochMilli());
    }

    /**
     * 判断传来的日期是否是现在这一天(注意 contentDate的精度)
     *
     * @param date 2016年06月21日 : 00:00
     * @return
     */
    public static boolean dateIsCurrentDay(Date date) {

        Date nowDate = new Date();
        long nowTime = nowDate.getTime() / 1000;
        LocalDateTime time = LocalDateTime.ofEpochSecond(nowTime, 0, ZoneOffset.ofHours(8));

        long beginTime = new Date(nowTime * 1000L).toInstant().minusSeconds((time.getHour() * 60 *
                60) + (time.getMinute() * 60) + time.getSecond()).toEpochMilli();
        return date.getTime() == beginTime;
    }

    /**
     * 把一个秒数  转成 20小时08分的格式
     *
     * @param second
     * @return
     */
    public static String getHoursAndMin(Integer second) {
        int hour = second / 3600;
        int minute = (second % 3600) / 60;

        if (hour == 0 && minute != 0) {
            return minute + "分钟";
        }
        if (hour != 0 && minute == 0) {
            return hour + "小时";
        }
        if (hour != 0 && minute != 0) {
            double other = minute / 60.0;
            return String.format("%.2f", (hour + other)) + "小时";
        }
        return (second % 3600) % 60 + "秒";
    }

    /**
     * 通过传入的秒数判断是上午AM，还是下午PM
     *
     * @param time 相对某天凌晨 00:00 的秒数
     * @return
     */
    public static String getTimeSlot(Integer time) {
        return time.intValue() <= 12 * 60 * 60 ? "AM" : "PM";
    }

    /**
     * 修改时间
     * @param date
     * @param unit
     * @param min
     * @return
     */
    public static Date modify(Date date, int unit, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit, min);
        return calendar.getTime();
    }

    /**
     * date1是否在date2之前（可相等）
     * @param date1
     * @param date2
     * @return
     */
    public static Boolean beforeOrEquals(Date date1, Date date2){
        return date1.before(date2) || date1.equals(date2);
    }

    /**
     * date1是否在date2之后（可相等）
     * @param date1
     * @param date2
     * @return
     */
    public static Boolean afterOrEquals(Date date1, Date date2){
        return date1.after(date2) || date1.equals(date2);
    }

    /**
     * 计算两个日期之间相差的天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int daysBetween(Date startDate,Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 格式化为 2017年1月16日 17点50分
     * @param time
     * @return
     */
    public static String yearMonthDayTime(LocalDateTime time){
        return time.getYear()+"年"+time.getMonthValue()+"月"+
                time.getDayOfMonth()+"日 "+time.getHour()+"点"+time.getMinute()+"分";
    }

    /**
     * 格式化为 2017年1月16日
     * @param time
     * @return
     */
    public static String yearMonthDay(LocalDateTime time){
        return time.getYear()+"年"+time.getMonthValue()+"月"+ time.getDayOfMonth()+"日";
    }


}
