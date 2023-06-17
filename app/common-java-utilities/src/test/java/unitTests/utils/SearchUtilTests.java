package unitTests.utils;

import io.diablo73.common.utils.SearchUtil;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import unitTests.printBeautify.PrintBeautify;

import java.util.*;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

public class SearchUtilTests {

	private static final int sizeFactor = 1000;
	private static final int elementLimitFactor = 10000;
	private static final int threadPoolSize = 1;
	private static final int invocationCount = 1;

	private static final int arrListSize = sizeFactor + new Random().nextInt(9 * sizeFactor);
	private static final List<Float> arrList = new ArrayList<>() {{
		for (int i = 0; i < arrListSize; i++) {
			add((new Random().nextInt(elementLimitFactor) - (elementLimitFactor / 2)) + new Random().nextFloat());
		}
		this.sort(Comparator.naturalOrder());
	}};
	private static final int index = new Random().nextInt(arrListSize);
	private static final float randomKey = arrList.get(index);


	private static final Map<String, List<Long>> searchAlgorithmsMapList = new HashMap<>();


	@AfterMethod
	public void afterMethod(ITestResult result) {
		String algo = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
		searchAlgorithmsMapList.putIfAbsent(algo, new ArrayList<>());
		searchAlgorithmsMapList.get(algo).add(result.getEndMillis() - result.getStartMillis());
	}

	@AfterClass
	public void afterClass() {
		PrintBeautify.printBeautify(searchAlgorithmsMapList, arrListSize, "Searching Algorithms");
	}


	@Test(testName = "IndexOf", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void indexOfTestCase() {
		for (int i = 0 ; i < arrListSize ; i++) {
			float key = arrList.get(i);
			if (i != 0 && key == arrList.get(i - 1)) {
				continue;
			}
			Assert.assertEquals(arrList.indexOf(key), i);
		}
		Assert.assertEquals(arrList.indexOf(randomKey), index);
	}

	@Test(testName = "LinearSearch", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void linearSearchTestCase() {
		for (int i = 0 ; i < arrListSize ; i++) {
			float key = arrList.get(i);
			if (i != 0 && key == arrList.get(i - 1)) {
				continue;
			}
			Assert.assertEquals(SearchUtil.linearSearch(arrList, key), i);
		}
		Assert.assertEquals(SearchUtil.linearSearch(arrList, randomKey), index);
	}

	@Test(testName = "LinearSearch", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void linearSearchFailTestCase() {
		Assert.assertEquals(SearchUtil.linearSearch(arrList, -1f), -1);
	}

	@Test(testName = "BinarySearch", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void binarySearchTestCase() {
		for (int i = 0 ; i < arrListSize ; i++) {
			float key = arrList.get(i);
			if (i != 0 && key == arrList.get(i - 1)) {
				continue;
			}
			int actual = SearchUtil.binarySearch(arrList, key);
			Assert.assertTrue(actual == i || arrList.get(actual) == key);
		}
		int actual = SearchUtil.binarySearch(arrList, randomKey);
		Assert.assertTrue(actual == index || arrList.get(actual) == randomKey);
	}

	@Test(testName = "BinarySearch", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void binarySearchFailTestCase() {
		Assert.assertEquals(SearchUtil.binarySearch(arrList, -randomKey, 0, -1), -1);
	}

}
