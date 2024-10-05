package unitTests.utils;

import io.diablo73.common.utils.HashUtil;
import io.diablo73.common.utils.MathUtil;
import io.diablo73.common.utils.RandomUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class HashUtilTests {

	private static final int ITERATION_COUNT = 100000;
	private static final int DEVIATION_PERCENTAGE = 5;
	private static final int COUNT_OF_SHARDS = 10;

	@Test
	public void hashUtilTestCase() {
		Map<Integer, Integer> tableShardMap = new HashMap<>();
		Map<Integer, Integer> dbShardMap = new HashMap<>();
		for (int i = 0; i < ITERATION_COUNT; i++) {
			String bits = HashUtil.generate4DigitIntValueOfHash(RandomUtil.generateSnowflakeKey());
			int tableShard = Integer.parseInt(bits.substring(bits.length() - 2)) % COUNT_OF_SHARDS;
			int dbShard = Integer.parseInt(bits.substring(bits.length() - 4, bits.length() - 2)) % COUNT_OF_SHARDS;
			tableShardMap.put(tableShard, tableShardMap.getOrDefault(tableShard, 0) + 1);
			dbShardMap.put(dbShard, dbShardMap.getOrDefault(dbShard, 0) + 1);
		}
		System.out.println("tableShardMap : " + tableShardMap);
		System.out.println("dbShardMap : " + dbShardMap);
		Assert.assertTrue((ITERATION_COUNT * (100 - DEVIATION_PERCENTAGE)) / (100 * COUNT_OF_SHARDS) <= MathUtil.getMin(tableShardMap.values().stream().toList()));
		Assert.assertTrue((ITERATION_COUNT * (100 - DEVIATION_PERCENTAGE)) / (100 * COUNT_OF_SHARDS) <= MathUtil.getMin(dbShardMap.values().stream().toList()));
		Assert.assertTrue((ITERATION_COUNT * (100 + DEVIATION_PERCENTAGE)) / (100 * COUNT_OF_SHARDS) >= MathUtil.getMin(tableShardMap.values().stream().toList()));
		Assert.assertTrue((ITERATION_COUNT * (100 + DEVIATION_PERCENTAGE)) / (100 * COUNT_OF_SHARDS) >= MathUtil.getMin(dbShardMap.values().stream().toList()));
	}
}
