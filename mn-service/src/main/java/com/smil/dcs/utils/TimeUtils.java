package com.smil.dcs.utils;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间工具类.
 *
 * @author wjh
 * @since 2018-12-20 11:16:49
 */
public class TimeUtils {

    public static final String BEGIN_TIME_OF_DAY = " 00:00:00";

    public static final String END_TIME_OF_DAY = " 23:59:59";

    /**
     * Instantiates a new time utils.
     */
    private TimeUtils() {
        super();
    }

    /**
     * 判断时间区间是否存在.返回true:时间可用，false:时间区间已经存在
     *
     * @param newStartTime the new start time
     * @param newEndTime the new end time
     * @param startTime the start time
     * @param endTime the end time
     * @return true, if is effective date
     */
    public  static boolean isEffectiveDate(Date newStartTime, Date newEndTime, Date startTime, Date endTime){
        boolean result = false;
        if((newStartTime.getTime() < newEndTime.getTime()) && (newStartTime.getTime() > endTime.getTime() || newEndTime.getTime() < startTime.getTime())){
            result = true;
        }
        return result;
    }

    /**
     * 判断时间是否在有效区间.返回true:在有效区间，false:不在有效区间
     *
     * @param startTime the new start time
     * @param endTime the new end time
     * @param nowTime the now time
     * @return true, if is effective date
     */
    public  static boolean isEffectiveDate(Date startTime, Date endTime, Date nowTime){
        boolean result = false;
        if(startTime==null || endTime==null){
            return false;
        }
        if((startTime.getTime() <= nowTime.getTime()) && endTime.getTime() >= nowTime.getTime()){
            result = true;
        }
        return result;
    }

    /**
     * 功能描述: <br>
     * 将字符串日期、时间及时区转换为jvm对应时区的date对象.
     *
     * @param dateString 格式："yyyy/MM/dd"或者"yyyy-MM-dd"
     * @param timeString 格式："HH:mm:ss.SSS"
     * @param zone 时区
     * @return jvm所在时区的时间
     */
    public static Date date2JvmDate(String dateString, String timeString, ZoneId zone) {
        String datetimeString = dateString+"T"+timeString;
        String datetimePattern = dateString.contains("/") ? "yyyy/MM/dd" : "yyyy-MM-dd" + "'T'HH:mm:ss.SSS";
        LocalDateTime localDatetime = LocalDateTime.parse(datetimeString, DateTimeFormatter.ofPattern(datetimePattern));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDatetime, zone);
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 功能描述: <br>
     * 将日期字符串dateString参考另一日期字符串refDateStringWithZone的时区转为jvm所在时区的date对象.
     *
     * @param dateString 格式："yyyy/MM/dd"或者"yyyy-MM-dd"
     * @param refDateStringWithZone 参考客户端时区的日期字符串，由前端获取。格式：
     *            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"，例如中国时区时间：2018-12-12T00:00:00.000+0800
     * @return jvm所在时区的日期，时间为当天最早时间.
     */
    public static Date date2JvmDateBegin(String dateString, String refDateStringWithZone) {
        ZonedDateTime refDateWithZone = ZonedDateTime.parse(refDateStringWithZone, DateTimeFormatter.ofPattern(StdDateFormat.DATE_FORMAT_STR_ISO8601));
        ZoneId zone = refDateWithZone.getZone();
        return date2JvmDate(dateString, "00:00:00.000", zone);
    }
    
    /**
     * 功能描述: <br>
     * 将日期字符串dateString参考另一日期字符串refDateStringWithZone的时区转为jvm所在时区的date对象.
     *
     * @param dateString 格式："yyyy/MM/dd"或者"yyyy-MM-dd"
     * @param refDateStringWithZone 参考客户端时区的日期字符串，由前端获取。格式：
     *            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"，例如中国时区时间：2018-12-12T00:00:00.000+0800
     * @return jvm所在时区的日期，时间为当天最晚时间.
     */
    public static Date date2JvmDateEnd(String dateString, String refDateStringWithZone) {
        ZonedDateTime refDateWithZone = ZonedDateTime.parse(refDateStringWithZone, DateTimeFormatter.ofPattern(StdDateFormat.DATE_FORMAT_STR_ISO8601));
        ZoneId zone = refDateWithZone.getZone();
        return date2JvmDate(dateString, "23:59:59.999", zone);
    }
    
    /**
     * 功能描述: <br>
     * 将日期date参考另一日期字符串refDateStringWithZone的时区转为jvm所在时区的date对象.<br>
     * 参数date的时区将会忽略.
     *
     * @param date the date
     * @param refDateStringWithZone the ref date string with zone
     * @return the date
     */
    public static Date date2JvmDateBegin(Date date, String refDateStringWithZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return date2JvmDateBegin(dateString, refDateStringWithZone);
    }
    
    /**
     * 功能描述: <br>
     * 将日期date参考另一日期字符串refDateStringWithZone的时区转为jvm所在时区的date对象.<br>
     * 参数date的时区将会忽略.
     *
     * @param date the date
     * @param refDateStringWithZone the ref date string with zone
     * @return the date
     */
    public static Date date2JvmDateEnd(Date date, String refDateStringWithZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        return date2JvmDateEnd(dateString, refDateStringWithZone);
    }
    
    
    /**
     * 功能描述: <br>
     * 将dateTimeString转为refDateStringWithZone时区的日期时间
     *
     * @param dateTimeString 格式：yyyy-MM-dd HH:mm:ss或者yyyy/MM/dd HH:mm:ss
     * @param refDateStringWithZone 参考客户端时区的日期字符串，由前端获取。格式：
     *            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"，例如中国时区时间：2018-12-12T00:00:00.000+0800
     * @return
     */
    public static LocalDateTime dateTimeString2zonedDateTime(String dateTimeString, String refDateStringWithZone) {
        ZonedDateTime refDateWithZone = ZonedDateTime.parse(refDateStringWithZone, DateTimeFormatter.ofPattern(StdDateFormat.DATE_FORMAT_STR_ISO8601));
        ZoneId zoneId = refDateWithZone.getZone();
        String[] split = dateTimeString.split(" ");
        String datetimeString = split[0]+"T"+split[1]+".000+0000";
        String datetimePattern = split[0].contains("/") ? "yyyy/MM/dd" : "yyyy-MM-dd" + "'T'HH:mm:ss.SSSZ";
        LocalDateTime localDatetime = LocalDateTime.parse(datetimeString, DateTimeFormatter.ofPattern(datetimePattern));
        ZonedDateTime of = ZonedDateTime.of(localDatetime, ZoneId.systemDefault());
        return LocalDateTime.ofInstant(of.toInstant(), zoneId);
    }
    
    /**
     * 功能描述: <br>
     * 将dateTimeString转为refDateStringWithZone时区的日期
     *
     * @param dateTimeString 格式：yyyy-MM-dd HH:mm:ss或者yyyy/MM/dd HH:mm:ss
     * @param refDateStringWithZone 参考客户端时区的日期字符串，由前端获取。格式：
     *            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"，例如中国时区时间：2018-12-12T00:00:00.000+0800
     * @return 格式：yyyy-MM-dd
     */
    public static String dateTimeString2zonedDateString(String dateTimeString, String refDateStringWithZone) {
        LocalDateTime localDateTime = dateTimeString2zonedDateTime(dateTimeString, refDateStringWithZone);
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
    /**
     * 功能描述: <br>
     * 将date转为refDateStringWithZone时区的日期.
     *
     * @param date the date
     * @param refDateStringWithZone 参考客户端时区的日期字符串，由前端获取。格式：
     *            "yyyy-MM-dd'T'HH:mm:ss.SSSZ"，例如中国时区时间：2018-12-12T00:00:00.000+0800
     * @return 格式：yyyy-MM-dd
     */
    public static String date2zonedDateString(Date date, String refDateStringWithZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        LocalDateTime localDateTime = dateTimeString2zonedDateTime(dateString, refDateStringWithZone);
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
    
    public static String date2zonedDateString2(Date date, String refDateStringWithZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        LocalDateTime localDateTime = dateTimeString2zonedDateTime(dateString, refDateStringWithZone);
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 日期时间
     * @param date
     * @param refDateStringWithZone
     * @return
     */
    public static String date2zonedDateTimeString(Date date, String refDateStringWithZone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        LocalDateTime localDateTime = dateTimeString2zonedDateTime(dateString, refDateStringWithZone);
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * 根据日期字符串，日期格式和时区生成起始时间日期
     * 对于格式为yyyy的，补齐为yyyy-01-01
     * 格式为yyyy-MM的，补齐为yyyy-MM-01
     * 格式为yyyy-MM-dd的，仅补齐时间
     * 时间根据timezoneOffset计算，如timezoneOffset = 0 时分秒为00:00:00)
     * @param dateStr
     * @param dateFormat
     * @param timezoneOffset
     * @return
     */
    public static Date getStartTime(String dateStr, String dateFormat, int timezoneOffset) {
        if(dateFormat.equalsIgnoreCase("yyyy")){
            dateStr += "-01-01";
        }else if(dateFormat.equalsIgnoreCase("yyyy-MM")){
            dateStr += "-01";
        }

        return transferDateByTimeZone(dateStr, BEGIN_TIME_OF_DAY, timezoneOffset);
    }

    //dateString:yyyy-MM-dd
    public static Date transferDateByTimeZone(String dateString, String timeString, int timezoneOffset){
        Date date = null;

        if(timeString.equals(BEGIN_TIME_OF_DAY)){
            dateString += "T" + BEGIN_TIME_OF_DAY;
        }

        if(timeString.equals(END_TIME_OF_DAY)){
            dateString += "T" + END_TIME_OF_DAY;
        }

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            date = isoFormat.parse(dateString);
            date = new Date(date.getTime() - timezoneOffset * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    /**
     * 根据日期字符串，日期格式和时区生成结束时间日期
     * 对于格式为yyyy的，补齐为yyyy-12-31
     * 格式为yyyy-MM的，补齐为yyyy-MM-（28-31）
     * 格式为yyyy-MM-dd的，仅补齐时间
     * 时间根据timezoneOffset计算，如timezoneOffset = 0 时分秒为23:59:59)
     * @param dateStr
     * @param dateFormat
     * @param timezoneOffset
     * @return
     */
    public static Date getEndTime(String dateStr, String dateFormat, int timezoneOffset) {
        if(dateFormat.equalsIgnoreCase("yyyy")){
            dateStr += "-12-31";
        }else if(dateFormat.equalsIgnoreCase("yyyy-MM")){
            String[] dateArray = dateStr.split("-");
            String year = dateArray[0];
            String month = dateArray[1];
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(year));
            calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
            int lastDay = calendar.getActualMaximum(Calendar.DATE);
            dateStr += "-" + lastDay;
        }

        return transferDateByTimeZone(dateStr, END_TIME_OF_DAY, timezoneOffset);
    }

    public static int getTimezoneOffset(String dateStringWithZone){
        int indexOfPlus = dateStringWithZone.indexOf("+");
        return Integer.parseInt(dateStringWithZone.substring(indexOfPlus + 1, indexOfPlus + 3));
    }

}
