package unitTests.utils;

import io.diablo73.common.utils.SwapUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Diablo73
 * @version 1.0 <br> 19/03/2022
 * @since 19/03/2022
 */

public class SwapUtilTests {


	private static final int sizeFactor = 1000;

	private static final int arrListSize = sizeFactor + new Random().nextInt(9 * sizeFactor);
	private static final int INDEX_I = new Random().nextInt(arrListSize);
	private static int INDEX_K = INDEX_I;
	static {
		while (INDEX_I == INDEX_K) {
			INDEX_K = new Random().nextInt(arrListSize);
		}
	}
	private static final int INDEX_J = INDEX_K;


	@Test
	public void swapNumberIntTestCase() {
		List<Integer> intList = new ArrayList<>();
		for (int i = 0; i < arrListSize; i++) {
			intList.add(new Random().nextInt(100000));
		}
		int old_I = intList.get(INDEX_I);
		int old_J = intList.get(INDEX_J);

		SwapUtil.swapNumber(intList, INDEX_I, INDEX_J);

		Assert.assertEquals(intList.get(INDEX_I), old_J);
		Assert.assertEquals(intList.get(INDEX_J), old_I);
	}

	@Test
	public void swapNumberFloatTestCase() {
		List<Float> intList = new ArrayList<>();
		for (int i = 0; i < arrListSize; i++) {
			intList.add(Float.valueOf(String.format("%.6f", new Random().nextFloat())));
		}
		float old_I = intList.get(INDEX_I);
		float old_J = intList.get(INDEX_J);

		SwapUtil.swapNumber(intList, INDEX_I, INDEX_J);

		Assert.assertEquals(intList.get(INDEX_I), old_J);
		Assert.assertEquals(intList.get(INDEX_J), old_I);
	}

	@Test
	public void swapNumberLongTestCase() {
		List<Long> longList = new ArrayList<>();
		for (int i = 0; i < arrListSize; i++) {
			longList.add(((long) new Random().nextInt()));
		}
		long old_I = longList.get(INDEX_I);
		long old_J = longList.get(INDEX_J);

		SwapUtil.swapNumber(longList, INDEX_I, INDEX_J);

		Assert.assertEquals(longList.get(INDEX_I), old_J);
		Assert.assertEquals(longList.get(INDEX_J), old_I);
	}

	@Test
	public void swapNumberDoubleTestCase() {
		List<Double> doubleList = new ArrayList<>();
		for (int i = 0; i < arrListSize; i++) {
			doubleList.add(Double.valueOf(String.format("%.12f", new Random().nextDouble())));
		}
		double old_I = doubleList.get(INDEX_I);
		double old_J = doubleList.get(INDEX_J);

		SwapUtil.swapNumber(doubleList, INDEX_I, INDEX_J);

		Assert.assertEquals(doubleList.get(INDEX_I), old_J);
		Assert.assertEquals(doubleList.get(INDEX_J), old_I);
	}
}
