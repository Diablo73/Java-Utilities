package unitTests.utils;

import io.diablo73.common.utils.JwtUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Diablo73
 * @version 1.0 <br> 21/03/2022
 * @since 27/02/2022
 */

public class JwtUtilTests {

	private static final Map<String, Object> claims =
			Map.of("key", RandomStringUtils.randomAlphanumeric(10),
					JwtUtil.SECRET, RandomStringUtils.randomAlphanumeric(10));


	@Test
	public void nullSecretTestCase() {
		Map<String, Object> payloadClaims = new HashMap<>(claims);
		payloadClaims.remove(JwtUtil.SECRET);
		try {
			Map<String, Object> actual = JwtUtil.getPayloadToken(JwtUtil.createToken(payloadClaims));
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
			Assert.assertEquals(e.getMessage(), "The Secret cannot be null");
		}
	}

	@Test
	public void validateTokenTestCase() {
		Map<String, Object> payloadClaims = new HashMap<>(claims);
		Assert.assertTrue(JwtUtil.validateToken(JwtUtil.createToken(payloadClaims), (String) claims.get(JwtUtil.SECRET)));
	}

	@Test
	public void validateTokenExpireTestCase() {
		Map<String, Object> payloadClaims = new HashMap<>(claims);
		payloadClaims.put(JwtUtil.EXPIRY, DateUtils.addDays(new Date(), -7));
		Assert.assertFalse(JwtUtil.validateToken(JwtUtil.createToken(payloadClaims), (String) claims.get(JwtUtil.SECRET)));
	}

	@Test
	public void validateTokenWrongSecretTestCase() {
		Map<String, Object> payloadClaims = new HashMap<>(claims);
		Assert.assertFalse(JwtUtil.validateToken(JwtUtil.createToken(payloadClaims), RandomStringUtils.randomAlphanumeric(5)));
	}

	@Test
	public void getPayloadTokenTestCase() {
		Map<String, Object> payloadClaims = new HashMap<>(claims);
		Map<String, Object> actual = JwtUtil.getPayloadToken(JwtUtil.createToken(payloadClaims));
		Assert.assertEquals(actual.get("key"), claims.get("key"));
	}

}
