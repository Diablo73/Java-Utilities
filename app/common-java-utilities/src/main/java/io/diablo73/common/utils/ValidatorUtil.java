package io.diablo73.common.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Diablo73
 * @version 1.0 <br> 13/03/2022
 * @since 28/02/2022
 */

public class ValidatorUtil {

	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");


	public static void checkDate(Object... date) {

		for (Object o : date) {
			if (Objects.isNull(o)) {
				throw new CommonException(
						CommonInfoEnum.DATE_PARSING_ERROR
								.setResultInfoEnumWithMessage("Error", "Date cannot be null!!!"));
			}
		}
	}

	public static void checkDatePattern(String pattern) {

		if (!DateUtil.PATTERN.contains(pattern)) {
			throw new CommonException(
					CommonInfoEnum.DATE_PARSING_ERROR
							.setResultInfoEnumWithMessage("Error", "Pattern cannot be null!!!"));
		}
	}

	public static void checkStringNotEmpty(String s, String field) {
		if (s.isEmpty()) {
			throw new CommonException(
					CommonInfoEnum.INVALID_STRING
							.setResultInfoEnumWithMessage("Error", field, "Provided field cannot be empty!!!"));
		}
	}

	public static void checkListNotEmpty(List<?> list) {
		if (Objects.isNull(list) || list.isEmpty()) {
			throw new CommonException(
					CommonInfoEnum.INVALID_LIST
							.setResultInfoEnumWithMessage("Error", "List provided cannot be empty!!!"));
		}
	}


	public static boolean isValidEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}
}
