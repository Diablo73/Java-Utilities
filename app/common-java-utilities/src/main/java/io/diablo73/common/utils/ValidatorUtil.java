package io.diablo73.common.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;

import java.util.List;
import java.util.Objects;

/**
 * @author Diablo73
 * @version 1.0 <br> 13/03/2022
 * @since 28/02/2022
 */

public class ValidatorUtil {


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
}
