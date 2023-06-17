package unitTests.sort;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import unitTests.printBeautify.PrintBeautify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

public class SortAbstract {

	private static final Map<String, List<Long>> sortingAlgorithmsMapList = new HashMap<>();


	@AfterMethod
	public void afterMethod(ITestResult result) {
		String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
		String algo = testName.substring(0, testName.length() - 3);
		sortingAlgorithmsMapList.putIfAbsent(algo, new ArrayList<>());
		sortingAlgorithmsMapList.get(algo).add(result.getEndMillis() - result.getStartMillis());
	}

	@AfterClass
	public void afterClass() {
		PrintBeautify.printBeautify(sortingAlgorithmsMapList, SortTests.arrListSize, "Sorting Algorithms");
	}

}
