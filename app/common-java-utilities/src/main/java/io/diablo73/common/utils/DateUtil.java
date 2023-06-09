package io.diablo73.common.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Diablo73
 * @version 1.0 <br> 13/03/2022
 * @since 27/02/2022
 */

public class DateUtil {

	public static String IST_TIMEZONE						= "IST";
	public static String DATE_SHORT_FORMAT 					= "yyyyMMdd";
	public static String DATE_SHORT_SLASH_FORMAT 			= "yyyy/MM/dd";
	public static String DATE_SHORT_DASH_FORMAT 			= "yyyy-MM-dd";
	public static String DATE_TIME_12_FORMAT 				= "yyyyMMddHHmm";
	public static String DATE_TIME_14_FORMAT 				= "yyyyMMddHHmmSS";
	public static String DATE_TIME_15_FORMAT 				= "yyyyMMdd HHmmSS";
	public static String DATE_TIME_15_SC_FORMAT 			= "yyyy/MM/dd HH:mm:ss";
	public static String DATE_TIME_15_DC_FORMAT 			= "yyyy-MM-dd HH:mm:ss";
	public static String DATE_TIME_18_SC_FORMAT 			= "yyyy/MM/dd HH:mm:ss.SSS";
	public static String DATE_TIME_18_DC_FORMAT 			= "yyyy-MM-dd HH:mm:ss.SSS";

	public static final Set<String> PATTERN = new HashSet<>(){{
		add(DATE_SHORT_FORMAT);
		add(DATE_SHORT_SLASH_FORMAT);
		add(DATE_SHORT_DASH_FORMAT);
		add(DATE_TIME_12_FORMAT);
		add(DATE_TIME_14_FORMAT);
		add(DATE_TIME_15_FORMAT);
		add(DATE_TIME_15_SC_FORMAT);
		add(DATE_TIME_15_DC_FORMAT);
		add(DATE_TIME_18_SC_FORMAT);
		add(DATE_TIME_18_DC_FORMAT);
	}};


	public static String parseDateToString(Date date, String pattern) {
		ValidatorUtil.checkDate(date);
		ValidatorUtil.checkDatePattern(pattern);

		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date parseStringToDate(String date, String pattern) {
		ValidatorUtil.checkDate(date);
		ValidatorUtil.checkDatePattern(pattern);

		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			throw new CommonException(
					CommonInfoEnum.DATE_PARSING_ERROR
							.setResultInfoEnumWithMessage("Error", date, pattern, "Cannot parse date!!!"));
		}
	}

	public static Date parseStringToDate(String date) {
		ValidatorUtil.checkDate(date);

		try {
			return DateUtils.parseDate(date, DateUtil.PATTERN.toArray(new String[]{}));
		} catch (ParseException e) {
			throw new CommonException(
					CommonInfoEnum.DATE_PARSING_ERROR
							.setResultInfoEnumWithMessage("Error", date, "Cannot parse date!!!"));
		}
	}


//	public static Date addYears(Date date, int years) {
//		ValidatorUtil.checkDate(date);
//
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
//		calendar.setTime(date);
//		calendar.add(Calendar.YEAR, years);
//		return calendar.getTime();
//	}
//
//	public static Date addMonths(Date date, int months) {
//		ValidatorUtil.checkDate(date);
//
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
//		calendar.setTime(date);
//		calendar.add(Calendar.MONTH, months);
//		return calendar.getTime();
//	}
//
//	public static Date addWeeks(Date date, int weeks) {
//
//		return addDays(date, weeks * 7);
//	}
//
//	public static Date addDays(Date date, int days) {
//		ValidatorUtil.checkDate(date);
//
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
//		calendar.setTime(date);
//		calendar.add(Calendar.DATE, days);
//		return calendar.getTime();
//	}
//
//	public static Date addHours(Date date, int hours) {
//		ValidatorUtil.checkDate(date);
//
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
//		calendar.setTime(date);
//		calendar.add(Calendar.HOUR, hours);
//		return calendar.getTime();
//	}
//
//	public static Date addMinutes(Date date, int minutes) {
//		ValidatorUtil.checkDate(date);
//
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
//		calendar.setTime(date);
//		calendar.add(Calendar.MINUTE, minutes);
//		return calendar.getTime();
//	}
//
//	public static Date addSeconds(Date date, int seconds) {
//		ValidatorUtil.checkDate(date);
//
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
//		calendar.setTime(date);
//		calendar.add(Calendar.SECOND, seconds);
//		return calendar.getTime();
//	}
//
//	public static Date addMilliseconds(Date date, int milliseconds) {
//		ValidatorUtil.checkDate(date);
//
//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
//		calendar.setTime(date);
//		calendar.add(Calendar.MILLISECOND, milliseconds);
//		return calendar.getTime();
//	}
//
//	public static boolean areDatesEqual(Date date1, Date date2) {
//		ValidatorUtil.checkDate(date1, date2);
//
//		return date1.getTime() == date2.getTime();
//	}


	public static int compareDates(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		return DateUtils.isSameInstant(date1, date2) ? 0 : time1 > time2 ? 1 : -1;
	}

	public static Date getStartTime(Date date) {
		ValidatorUtil.checkDate(date);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndTime(Date date) {
		ValidatorUtil.checkDate(date);

		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(IST_TIMEZONE));
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static double getDiffYears(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime()) / 31536000000.00;
	}

	public static double getDiffMonths(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime()) / 2592000000.00;
	}

	public static double getDiffWeeks(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime()) / 604800000.00;
	}

	public static double getDiffDays(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime()) / 86400000.00;
	}

	public static double getDiffHours(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime()) / 3600000.00;
	}

	public static double getDiffMinutes(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime()) / 60000.00;
	}

	public static double getDiffSeconds(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime()) / 1000.00;
	}

	public static double getDiffMilliseconds(Date date1, Date date2) {
		ValidatorUtil.checkDate(date1, date2);

		return (date1.getTime() - date2.getTime());
	}

	public static Date getMinDate(List<?> list) {
		ValidatorUtil.checkListNotEmpty(list);

		if (list.get(0) instanceof Date) {
			return new Date(Collections.min(list.stream().map(date -> ((Date) date).getTime()).collect(Collectors.toList())));
		} else if (list.get(0) instanceof String) {
			return new Date(Collections.min(list.stream().map(date -> DateUtil.parseStringToDate((String) date).getTime()).collect(Collectors.toList())));
		}
		throw new CommonException(
				CommonInfoEnum.INVALID_LIST
						.setResultInfoEnumWithMessage("Error", String.valueOf(list.get(0).getClass()), "Invalid List parameters!!!"));
	}

	public static Date getMaxDate(List<?> list) {
		ValidatorUtil.checkListNotEmpty(list);

		if (list.get(0) instanceof Date) {
			return new Date(Collections.max(list.stream().map(date -> ((Date) date).getTime()).collect(Collectors.toList())));
		} else if (list.get(0) instanceof String) {
			return new Date(Collections.max(list.stream().map(date -> DateUtil.parseStringToDate((String) date).getTime()).collect(Collectors.toList())));
		}
		throw new CommonException(
				CommonInfoEnum.INVALID_LIST
						.setResultInfoEnumWithMessage("Error", String.valueOf(list.get(0).getClass()), "Invalid List parameters!!!"));
	}




}
