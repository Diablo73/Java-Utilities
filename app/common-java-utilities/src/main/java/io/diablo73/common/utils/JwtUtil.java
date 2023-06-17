package io.diablo73.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Diablo73
 * @version 1.0 <br> 27/02/2022
 * @since 27/02/2022
 */

public class JwtUtil {

	public static final String EXPIRY 		= "expiry";
	public static final String SECRET 		= "secret";


	public static String createToken(Map<String, Object> payloadClaims) {
		Date expiry;
		String signature;

		if (payloadClaims.containsKey(EXPIRY)) {
			expiry = (Date) payloadClaims.get(EXPIRY);
			payloadClaims.remove(EXPIRY);
		} else {
			expiry = DateUtils.addDays(new Date(), 7);
		}

		if (payloadClaims.containsKey(SECRET)) {
			signature = (String) payloadClaims.get(SECRET);
			payloadClaims.remove(SECRET);
		} else {
			signature = System.getenv("JWT_SECRET");
		}


		return JWT.create()
				.withPayload(payloadClaims)
				.withIssuedAt(new Date())
				.withExpiresAt(expiry)
				.sign(Algorithm.HMAC512(signature));
	}

	public static boolean validateToken(String token, String signature) {

		DecodedJWT value = JWT.decode(token);
		if (DateUtil.compareDates(value.getExpiresAt(), new Date()) >= 0) {
			return checkAlgorithmToken(token, signature);
		} else {
			return false;
		}
	}

	public static boolean checkAlgorithmToken(String token, String signature) {
		try {
			JWT.require(Algorithm.HMAC512(signature)).build().verify(token);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Map<String, Object> getPayloadToken(String token) {

		Map<String, Claim> claims = JWT.decode(token).getClaims();
		Map<String, Object> payload = new HashMap<>();
		for (String key : claims.keySet()) {
			payload.put(key, claims.get(key).toString().replaceAll("\"", StringUtils.EMPTY));
		}

		return payload;
	}

}
