package unitTests.sort;

import io.diablo73.common.enums.SortEnum;
import io.diablo73.common.sort.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

public class SortTests extends SortAbstract {


	private static final int sizeFactor = 1000;
	private static final int elementLimitFactor = 100000;
	private static final int threadPoolSize = 1;
	private static final int invocationCount = 1;

	protected static final int arrListSize = sizeFactor + new Random().nextInt(9 * sizeFactor);
	private static final List<Float> arrList = new ArrayList<>() {{
		for (int i = 0; i < arrListSize; i++) {
			add((new Random().nextInt(elementLimitFactor) - (elementLimitFactor / 2)) + new Random().nextFloat());
		}
	}};
	private static final List<Float> ascList = new ArrayList<>(arrList) {{sort(Comparator.naturalOrder());}};
	private static final List<Float> dscList = new ArrayList<>(arrList) {{sort(Comparator.reverseOrder());}};


	@BeforeMethod
	public void beforeMethod() {
		Assert.assertNotEquals(arrList, ascList);
		Assert.assertNotEquals(arrList, dscList);
		Assert.assertEqualsNoOrder(arrList, ascList);
		Assert.assertEqualsNoOrder(arrList, dscList);
		for (int i = 0; i < arrListSize; i++) {
			Assert.assertEquals(ascList.get(i), dscList.get(arrListSize - i - 1));
		}
	}

	@BeforeClass
	public void beforeClass() {

	}

	@Test(testName = "CollectionsSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void collectionsSortAscTestCase() {
		List<Float> aList = new ArrayList<>(List.copyOf(ascList));
		aList.sort(Comparator.naturalOrder());
		List<Float> sList = new ArrayList<>(List.copyOf(arrList));
		sList.sort(Comparator.naturalOrder());
		List<Float> dList = new ArrayList<>(List.copyOf(dscList));
		dList.sort(Comparator.naturalOrder());

		Assert.assertEquals(aList, ascList);
		Assert.assertEquals(sList, ascList);
		Assert.assertEquals(dList, ascList);
	}

	@Test(testName = "CollectionsSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void collectionsSortDscTestCase() {
		List<Float> dList = new ArrayList<>(List.copyOf(dscList));
		dList.sort(Comparator.reverseOrder());
		List<Float> sList = new ArrayList<>(List.copyOf(arrList));
		sList.sort(Comparator.reverseOrder());
		List<Float> aList = new ArrayList<>(List.copyOf(ascList));
		aList.sort(Comparator.reverseOrder());

		Assert.assertEquals(dList, dscList);
		Assert.assertEquals(sList, dscList);
		Assert.assertEquals(aList, dscList);
	}

	@Test(testName = "BubbleSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void bubbleSortAscTestCase() {
		Assert.assertEquals(BubbleSort.sort(ascList, SortEnum.ASC), ascList);
		Assert.assertEquals(BubbleSort.sort(arrList, SortEnum.ASC), ascList);
		Assert.assertEquals(BubbleSort.sort(dscList, SortEnum.ASC), ascList);
	}

	@Test(testName = "BubbleSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void bubbleSortDscTestCase() {
		Assert.assertEquals(BubbleSort.sort(dscList, SortEnum.DSC), dscList);
		Assert.assertEquals(BubbleSort.sort(arrList, SortEnum.DSC), dscList);
		Assert.assertEquals(BubbleSort.sort(ascList, SortEnum.DSC), dscList);
	}

	@Test(testName = "SelectionSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void selectionSortAscTestCase() {
		Assert.assertEquals(SelectionSort.sort(ascList, SortEnum.ASC), ascList);
		Assert.assertEquals(SelectionSort.sort(arrList, SortEnum.ASC), ascList);
		Assert.assertEquals(SelectionSort.sort(dscList, SortEnum.ASC), ascList);
	}

	@Test(testName = "SelectionSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void selectionSortDscTestCase() {
		Assert.assertEquals(SelectionSort.sort(dscList, SortEnum.DSC), dscList);
		Assert.assertEquals(SelectionSort.sort(arrList, SortEnum.DSC), dscList);
		Assert.assertEquals(SelectionSort.sort(ascList, SortEnum.DSC), dscList);
	}

	@Test(testName = "InsertionSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void insertionSortAscTestCase() {
		Assert.assertEquals(InsertionSort.sort(ascList, SortEnum.ASC), ascList);
		Assert.assertEquals(InsertionSort.sort(arrList, SortEnum.ASC), ascList);
		Assert.assertEquals(InsertionSort.sort(dscList, SortEnum.ASC), ascList);
	}

	@Test(testName = "InsertionSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void insertionSortDscTestCase() {
		Assert.assertEquals(InsertionSort.sort(dscList, SortEnum.DSC), dscList);
		Assert.assertEquals(InsertionSort.sort(arrList, SortEnum.DSC), dscList);
		Assert.assertEquals(InsertionSort.sort(ascList, SortEnum.DSC), dscList);
	}

	@Test(testName = "MergeSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void mergeSortAscTestCase() {
		Assert.assertEquals(MergeSort.sort(ascList, SortEnum.ASC), ascList);
		Assert.assertEquals(MergeSort.sort(arrList, SortEnum.ASC), ascList);
		Assert.assertEquals(MergeSort.sort(dscList, SortEnum.ASC), ascList);
	}

	@Test(testName = "MergeSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void mergeSortDscTestCase() {
		Assert.assertEquals(MergeSort.sort(dscList, SortEnum.DSC), dscList);
		Assert.assertEquals(MergeSort.sort(arrList, SortEnum.DSC), dscList);
		Assert.assertEquals(MergeSort.sort(ascList, SortEnum.DSC), dscList);
	}

	@Test(testName = "QuickSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void quickSortAscTestCase() {
		Assert.assertEquals(QuickSort.sort(ascList, SortEnum.ASC), ascList);
		Assert.assertEquals(QuickSort.sort(arrList, SortEnum.ASC), ascList);
		Assert.assertEquals(QuickSort.sort(dscList, SortEnum.ASC), ascList);
	}

	@Test(testName = "QuickSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void quickSortDscTestCase() {
		Assert.assertEquals(QuickSort.sort(dscList, SortEnum.DSC), dscList);
		Assert.assertEquals(QuickSort.sort(arrList, SortEnum.DSC), dscList);
		Assert.assertEquals(QuickSort.sort(ascList, SortEnum.DSC), dscList);
	}

	@Test(testName = "HeapSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void heapSortAscTestCase() {
		Assert.assertEquals(HeapSort.sort(ascList, SortEnum.ASC), ascList);
		Assert.assertEquals(HeapSort.sort(arrList, SortEnum.ASC), ascList);
		Assert.assertEquals(HeapSort.sort(dscList, SortEnum.ASC), ascList);
	}

	@Test(testName = "HeapSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void heapSortDscTestCase() {
		Assert.assertEquals(HeapSort.sort(dscList, SortEnum.DSC), dscList);
		Assert.assertEquals(HeapSort.sort(arrList, SortEnum.DSC), dscList);
		Assert.assertEquals(HeapSort.sort(ascList, SortEnum.DSC), dscList);
	}

	@Test(testName = "ShellSortAsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void shellSortAscTestCase() {
		Assert.assertEquals(ShellSort.sort(ascList, SortEnum.ASC), ascList);
		Assert.assertEquals(ShellSort.sort(arrList, SortEnum.ASC), ascList);
		Assert.assertEquals(ShellSort.sort(dscList, SortEnum.ASC), ascList);
	}

	@Test(testName = "ShellSortDsc", threadPoolSize = threadPoolSize, invocationCount = invocationCount)
	public void shellSortDscTestCase() {
		Assert.assertEquals(ShellSort.sort(dscList, SortEnum.DSC), dscList);
		Assert.assertEquals(ShellSort.sort(arrList, SortEnum.DSC), dscList);
		Assert.assertEquals(ShellSort.sort(ascList, SortEnum.DSC), dscList);
	}

}
