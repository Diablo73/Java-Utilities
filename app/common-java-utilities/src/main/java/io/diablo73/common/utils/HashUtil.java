package io.diablo73.common.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtil {

	public static String generateSHA256Hash(String data) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexadecimalString = new StringBuilder();
			for (byte b : hash) {
				String hexadecimal = Integer.toHexString(0xff & b);
				hexadecimalString.append(hexadecimal);
			}
			return hexadecimalString.toString();
		} catch (Exception e) {
			throw new CommonException(CommonInfoEnum.HASH_ERROR, e);
		}
	}
}
