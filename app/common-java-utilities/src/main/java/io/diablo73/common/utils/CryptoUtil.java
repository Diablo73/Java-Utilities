package io.diablo73.common.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 05/03/2022
 * @since 05/03/2022
 */

public class CryptoUtil {

	private static final List<String> LEVEL = List.of(
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,?!()'\"\n",
			//72 Chars
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,;:?!()'\"\n\t\\{}[]<>+-*/=@#$%^&_`~",
			//96 Chars
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,;:?!()'\"\n\t\\{}[]<>+-*/=@#$%^&_`~☺☻♥♦♣♠•◘○◙♂♀♪♫☼►◄↕‼¶§▬↨↑↓→←∟↔▲▼",
			//127 Chars
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,;:?!()'\"\n\t\\{}[]<>+-*/=@#$%^&_`~☺☻♥♦♣♠•◘○◙♂♀♪♫☼►◄↕‼¶§▬↨↑↓→←∟↔▲▼⌂₧ƒ⌐¬«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αßΓπΣσµτΦΘΩδ∞φε∩≡±≥≤⌠⌡÷≈°∙·√ⁿ¹²³■€‚„…†‡ˆ‰Š‹ŒŽ‘’“”–—˜™š›œžŸ¡¢£¤¥¦¨©ª­®¯´¸º¼½¾¿ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚÛÜÝÞàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ"
			//321 Chars
	);


	public static String encrypt(String message, String key, int level) {
		StringBuilder encryptedMessage = new StringBuilder();
		String secret = LEVEL.get(level - 1);

		for (int i = 0; i < message.length(); i++) {
			encryptedMessage.append(secret.charAt((secret.indexOf(message.charAt(i)) + secret.indexOf(key.charAt(i % key.length()))) % secret.length()));
		}

		return encryptedMessage.toString();
	}

	public static String decrypt(String message, String key, int level) {
		StringBuilder decryptedMessage = new StringBuilder();
		String secret = LEVEL.get(level - 1);

		for (int i = 0; i < message.length(); i++) {
			decryptedMessage.append(secret.charAt((secret.indexOf(message.charAt(i)) + secret.length() - secret.indexOf(key.charAt(i % key.length()))) % secret.length()));
		}

		return decryptedMessage.toString();
	}


	public static String encrypt(String str, String key) {
		byte[] keyBytes = createKey(key);

		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		Cipher ecipher;
		byte[] enc = new byte[0];

		try {
			ecipher = Cipher.getInstance("AES");
			ecipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
			enc = ecipher.doFinal(utf8);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}

		return Base64.getEncoder().encodeToString(enc);
	}

	public static String decrypt(String str, String key) {
		byte[] keyBytes = createKey(key);

		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		Cipher dcipher;
		byte[] utf8 = new byte[0];

		try {
			dcipher = Cipher.getInstance("AES");
			dcipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] dec = Base64.getDecoder().decode(str);
			utf8 = dcipher.doFinal(dec);
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
}
