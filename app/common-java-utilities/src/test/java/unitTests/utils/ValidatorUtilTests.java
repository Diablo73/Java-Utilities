package unitTests.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;
import io.diablo73.common.utils.DateUtil;
import io.diablo73.common.utils.ValidatorUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Diablo73
 * @version 1.0 <br> 19/03/2022
 * @since 28/02/2022
 */

public class ValidatorUtilTests {


	@Test
	public void checkDateTestCase() {
		try {
			ValidatorUtil.checkDate(new Date(), null);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.DATE_PARSING_ERROR);
		}
	}

	@Test
	public void checkDatePatternTestCase() {
		ValidatorUtil.checkDatePattern(List.copyOf(DateUtil.PATTERN).get(new Random().nextInt(DateUtil.PATTERN.size())));
	}

	@Test
	public void checkDatePatternExceptionTestCase() {
		try {
			ValidatorUtil.checkDatePattern("abc");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.DATE_PARSING_ERROR);
		}
	}

	@Test
	public void checkStringNotEmptyTestCase() {
		ValidatorUtil.checkStringNotEmpty(RandomStringUtils.randomAlphanumeric(10), null);
		ValidatorUtil.checkStringNotEmpty(" ", null);
	}

	@Test
	public void checkStringNotEmptyExceptionTestCase() {
		String field = RandomStringUtils.randomAlphanumeric(10);
		try {
			ValidatorUtil.checkStringNotEmpty(StringUtils.EMPTY, field);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.INVALID_STRING);
			Assert.assertTrue(e.getMessage().contains(field));
		}
	}

	@Test
	public void checkListNotEmptyNullExceptionTestCase() {
		try {
			ValidatorUtil.checkListNotEmpty(null);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.INVALID_LIST);
		}
	}

	@Test
	public void checkListNotEmptyTestCase() {
		ValidatorUtil.checkListNotEmpty(List.of(RandomStringUtils.randomAlphanumeric(10)));
	}

	@Test
	public void checkListNotEmptyExceptionTestCase() {
		try {
			ValidatorUtil.checkListNotEmpty(new ArrayList<>());
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertEquals(((CommonException) e).getCommonInfoEnum(), CommonInfoEnum.INVALID_LIST);
		}
	}
}
