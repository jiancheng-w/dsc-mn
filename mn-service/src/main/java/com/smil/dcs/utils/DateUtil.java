/**
 * 
 */
package com.smil.dcs.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author duankk
 *
 */
public class DateUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static int daysBetween (Date beforeDate, Date afterDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String beforeString = sdf.format(beforeDate);
		String afterString = sdf.format(afterDate);
		try {
			Date start = sdf.parse(beforeString);
			Date end = sdf.parse(afterString);
			return (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
		    LOGGER.error("Exception:", e);
		}
		return 0;
	}

	public static boolean isSameMonth(Date date1, Date date2) {
		try {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);

			boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
					.get(Calendar.YEAR);
			boolean isSameMonth = isSameYear
					&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
			return isSameMonth;
		} catch (Exception e) {
		    LOGGER.error("Exception:", e);
		}
		return false;
	}

	public static String formatDate(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateStr = formatter.format(date);
		return dateStr;
	}

	public static Date parseDate(String dateStr, String format) {
		return parseDate(dateStr, format, TimeZone.getDefault());
	}

	public static Date parseDate(String dateStr, String format, TimeZone timeZone) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			formatter.setTimeZone(timeZone);
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getSMMEFromDay(Date date){
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		// 前一天
		cale.add(Calendar.DATE, -1);
		String firstday = formatDate(cale.getTime(), "yyyy-MM-dd")+" 20:00:00";
		return parseDate(firstday, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getSMMEEndDay(Date date){
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		String lastday = formatDate(cale.getTime(), "yyyy-MM-dd")+" 19:59:59";
		return parseDate(lastday, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getMonthFirstDay(Date date){
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, 5);
		String firstday = formatDate(cale.getTime(), "yyyy-MM-dd")+" 00:00:00";
		return parseDate(firstday, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getMonthLastDay(Date date){
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		// 获取前月的最后一天
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);
		String lastday = formatDate(cale.getTime(), "yyyy-MM-dd")+" 23:59:59";
		return parseDate(lastday, "yyyy-MM-dd HH:mm:ss");
	}
}
