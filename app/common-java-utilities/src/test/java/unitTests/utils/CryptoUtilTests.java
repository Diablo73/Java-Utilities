package unitTests.utils;

import io.diablo73.common.utils.CryptoUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class CryptoUtilTests {

	@Test(invocationCount = 2)
	public void cryptoUtilTestCase() {
		for (int level = 1; level < 5; level++) {
			String message = getMessage();
			String key = getKey();

//			System.out.println(message);
			String encryptedMessage = CryptoUtil.encrypt(message, key, level);
//			System.out.println(encryptedMessage);
			String decryptedMessage = CryptoUtil.decrypt(encryptedMessage, key, level);
//			System.out.println(decryptedMessage);
			Assert.assertEquals(message, decryptedMessage);
//			System.out.println();
		}
	}

	private String getMessage() {
		return RandomStringUtils.randomAlphanumeric(10000 + new Random().nextInt(90000));
	}

	private String getKey() {
		return RandomStringUtils.randomAlphanumeric(10 + new Random().nextInt(90));
	}

}
