package io.diablo73.common.utils;

import java.util.List;

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
}
