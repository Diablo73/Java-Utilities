package unitTests.utils;

import io.diablo73.common.utils.Base64Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Base64UtilTests {

	@Test
	public void cryptoUtil64TestCase() {

		String message = getMessage();
		String key = getKey();

//		System.out.println(message);
		String encryptedMessage = Base64Util.encrypt(message, key);
//		System.out.println(encryptedMessage);
		String decryptedMessage = Base64Util.decrypt(encryptedMessage, key);
//		System.out.println(decryptedMessage);
		Assert.assertEquals(message, decryptedMessage);
//		System.out.println();

	}


	private String getMessage() {
		return RandomStringUtils.randomAlphanumeric(10000 + new Random().nextInt(90000));
	}

	private String getKey() {
		return RandomStringUtils.randomAlphanumeric(10 + new Random().nextInt(90));
	}
}
