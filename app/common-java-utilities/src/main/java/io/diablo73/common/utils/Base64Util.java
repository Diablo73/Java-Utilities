package io.diablo73.common.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Base64Util {

	public static String encrypt(String str, String key) {
		byte[] keyBytes = createKey(key);

		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		Cipher eCipher;
		byte[] enc = new byte[0];

		try {
			eCipher = Cipher.getInstance("AES");
			eCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
			enc = eCipher.doFinal(utf8);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		return Base64.getEncoder().encodeToString(enc);
	}

	public static String decrypt(String str, String key) {
		byte[] keyBytes = createKey(key);

		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		Cipher dCipher;
		byte[] utf8 = new byte[0];

		try {
			dCipher = Cipher.getInstance("AES");
			dCipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] dec = Base64.getDecoder().decode(str);
			utf8 = dCipher.doFinal(dec);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		return new String(utf8, StandardCharsets.UTF_8);
	}

	private static byte[] createKey(String key) {
		byte[] bytes = key.getBytes();
		byte[] keyBytes = new byte[16];

		int length = Math.min(bytes.length, keyBytes.length);
		System.arraycopy(bytes, 0, keyBytes, 0, length);

		return keyBytes;
	}

	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public static byte[] getBytesFromString(String s) {
		return Base64.getDecoder().decode(s);
	}
}
