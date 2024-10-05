package io.diablo73.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;

public class RandomUtil {

	private static final SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
	private volatile long LAST_TIMESTAMP = -1L;

	public static String generate8DigitBits() {
		return RandomStringUtils.randomNumeric(8);
	}

	public static String generateSnowflakeKey() {
		return snowflakeShardingKeyGenerator.generateKey().toString();
	}
}
