package io.diablo73.common.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtil {

	private static final int PADDING_SIZE = 4;
	private static final int HASH_SIZE = 64;

	public static String generateSHA256Hash(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexadecimalString = new StringBuilder();
			for (byte b : hash) {
				String hexadecimal = Integer.toHexString(0xff & b);
				if (hexadecimal.length() == 1) {
					hexadecimalString.append("0");
				}
				hexadecimalString.append(hexadecimal);
			}
			return hexadecimalString.toString();
		} catch (Exception e) {
			throw new CommonException(CommonInfoEnum.HASH_ERROR, e);
		}
	}

	public static String generate4DigitIntValueOfHash(String data) {
		return generate4DigitIntValueOfHash(data, HASH_SIZE - PADDING_SIZE);
	}

	public static String generate4DigitIntValueOfHash(String data, int startIndex) {
		return generateIntValueOfHash(data, startIndex, startIndex + PADDING_SIZE);
	}

	public static String generateIntValueOfHash(String data, int startIndex, int endIndex) {
		try {
			return StringUtils.leftPad(
					String.valueOf(
							Integer.parseInt(
									generateSHA256Hash(data)
											.substring(startIndex, endIndex), 16)), PADDING_SIZE, "0");
		} catch (Exception e) {
			throw new CommonException(CommonInfoEnum.HASH_ERROR, e);
		}
	}
}
