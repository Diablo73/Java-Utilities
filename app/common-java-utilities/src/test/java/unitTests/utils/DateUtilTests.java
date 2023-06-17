package unitTests.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;
import io.diablo73.common.utils.DateUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 10/03/2022
 * @since 27/02/2022
 */

public class DateUtilTests {

	private static final Date 		currentDateInDate			=	new Date();
	private static final String 	currentDateInString			=	DateUtil.parseDateToString(currentDateInDate, DateUtil.DATE_TIME_18_DC_FORMAT);
	private static final int 		span						=	1;
	private static final String 	randomString				=	RandomStringUtils.randomAlphabetic(10);



	@Test
	public void parseDateToStringTestCase() {
		Assert.assertEquals(DateUtil.parseDateToString(currentDateInDate, DateUtil.DATE_TIME_18_DC_FORMAT), currentDateInString);
	}

	@Test
	public void parseStringToDateWithPatternTestCase() {
		Assert.assertEquals(DateUtil.parseStringToDate(currentDateInString, DateUtil.DATE_TIME_18_DC_FORMAT), currentDateInDate);
	}

	@Test
	public void parseStringToDateWithPatternParseErrorTestCase() {
		try {
			Date d = DateUtil.parseStringToDate(randomString, DateUtil.DATE_TIME_18_DC_FORMAT);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.DATE_PARSING_ERROR);
		}
	}

	@Test
	public void parseStringToDateWithoutPatternTestCase() {
		Assert.assertEquals(DateUtil.parseStringToDate(currentDateInString), currentDateInDate);
	}

	@Test
	public void parseStringToDateWithoutPatternParseErrorTestCase() {
		try {
			Date date = DateUtil.parseStringToDate(randomString);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.DATE_PARSING_ERROR);
		}
	}

	@Test
	public void compareDatesEqualTestCase() {
		Assert.assertEquals(DateUtil.compareDates(currentDateInDate, currentDateInDate), 0);
	}

	@Test
	public void compareDatesBigTestCase() {
		Assert.assertEquals(DateUtil.compareDates(DateUtils.addMilliseconds(currentDateInDate, 1), currentDateInDate), 1);
	}

	@Test
	public void compareDatesSmallTestCase() {
		Assert.assertEquals(DateUtil.compareDates(DateUtils.addMilliseconds(currentDateInDate, -1), currentDateInDate), -1);
	}

	@Test
	public void getStartTimeTestCase() {
		Date actual = DateUtil.getStartTime(currentDateInDate);
		Assert.assertEquals(actual.getHours(), 0);
		Assert.assertEquals(actual.getMinutes(), 0);
		Assert.assertEquals(actual.getSeconds(), 0);
		Assert.assertTrue(String.valueOf(actual.getTime()).endsWith("000"));
	}

	@Test
	public void getEndTimeTestCase() {
		Date actual = DateUtil.getEndTime(new Date());
		Assert.assertEquals(actual.getHours(), 23);
		Assert.assertEquals(actual.getMinutes(), 59);
		Assert.assertEquals(actual.getSeconds(), 59);
		Assert.assertTrue(String.valueOf(actual.getTime()).endsWith("999"));
	}

	@Test
	public void getDiffYearsTestCase() {
		Assert.assertEquals(DateUtil.getDiffYears(DateUtils.addYears(currentDateInDate, span), currentDateInDate), span);
	}

	@Test
	public void getDiffMonthsTestCase() {
		Assert.assertEquals(DateUtil.getDiffMonths(DateUtils.addMonths(currentDateInDate, span), currentDateInDate), span);
	}

	@Test
	public void getDiffWeeksTestCase() {
		Assert.assertEquals(DateUtil.getDiffWeeks(DateUtils.addWeeks(currentDateInDate, span), currentDateInDate), span);
	}

	@Test
	public void getDiffDaysTestCase() {
		Assert.assertEquals(DateUtil.getDiffDays(DateUtils.addDays(currentDateInDate, span), currentDateInDate), span);
	}

	@Test
	public void getDiffHoursTestCase() {
		Assert.assertEquals(DateUtil.getDiffHours(DateUtils.addHours(currentDateInDate, span), currentDateInDate), span);
	}

	@Test
	public void getDiffMinutesTestCase() {
		Assert.assertEquals(DateUtil.getDiffMinutes(DateUtils.addMinutes(currentDateInDate, span), currentDateInDate), span);
	}

	@Test
	public void getDiffSecondsTestCase() {
		Assert.assertEquals(DateUtil.getDiffSeconds(DateUtils.addSeconds(currentDateInDate, span), currentDateInDate), span);
	}

	@Test
	public void getDiffMillisecondsTestCase() {
		Assert.assertEquals(DateUtil.getDiffMilliseconds(DateUtils.addMilliseconds(currentDateInDate, span), currentDateInDate), span);
	}

//	@Test
//	public void getStartTimeTestCase() {
//		Assert.assertEquals(DateUtil.getStartTime(currentDateInDate), DateUtils.truncate(currentDateInDate, Calendar.DATE));
//	}
//
//	@Test
//	public void getEndTimeTestCase() {
//		Assert.assertEquals(DateUtil.getEndTime(new Date()), DateUtils.addMilliseconds(DateUtils.ceiling(currentDateInDate, Calendar.DATE), -1));
//	}

	@Test
	public void getMinDateDateTestCase() {
		List<Date> dateList = List.of(currentDateInDate,
				DateUtils.addDays(currentDateInDate, 1),
				DateUtils.addDays(currentDateInDate, -1));
		Assert.assertEquals(DateUtil.getMinDate(dateList), dateList.get(2));
	}

	@Test
	public void getMinDateStringTestCase() {
		List<String> dateList = List.of(currentDateInString,
				DateUtil.parseDateToString(DateUtils.addDays(currentDateInDate, 1), DateUtil.DATE_SHORT_FORMAT),
				DateUtil.parseDateToString(DateUtils.addDays(currentDateInDate, -1), DateUtil.DATE_SHORT_FORMAT));
		Assert.assertEquals(DateUtil.getMinDate(dateList), DateUtil.parseStringToDate(dateList.get(2)));
	}

	@Test
	public void getMinDateInvalidListTestCase() {
		try {
			Date d = DateUtil.getMinDate(List.of(span));
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.INVALID_LIST);
		}
	}

	@Test
	public void getMaxDateDateTestCase() {
		List<Date> dateList = List.of(currentDateInDate,
				DateUtils.addDays(currentDateInDate, 1),
				DateUtils.addDays(currentDateInDate, -1));
		Assert.assertEquals(DateUtil.getMaxDate(dateList), dateList.get(1));
	}

	@Test
	public void getMaxDateStringTestCase() {
		List<String> dateList = List.of(currentDateInString,
				DateUtil.parseDateToString(DateUtils.addDays(currentDateInDate, 1), DateUtil.DATE_SHORT_FORMAT),
				DateUtil.parseDateToString(DateUtils.addDays(currentDateInDate, -1), DateUtil.DATE_SHORT_FORMAT));
		Assert.assertEquals(DateUtil.getMaxDate(dateList), DateUtil.parseStringToDate(dateList.get(1)));
	}

	@Test
	public void getMaxDateInvalidListTestCase() {
		try {
			Date d = DateUtil.getMaxDate(List.of(span));
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.INVALID_LIST);
		}
	}



}
