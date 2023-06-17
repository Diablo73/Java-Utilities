package unitTests.utils;

import io.diablo73.common.exceptions.CommonException;
import io.diablo73.common.utils.MathUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Diablo73
 * @version 1.0 <br> 21/03/2022
 * @since 21/03/2022
 */

public class MathUtilTests {

	private static final int sizeFactor = 100;
	private static final int elementLimitFactor = 100000;

	private static final int arrListSize = sizeFactor + new Random().nextInt(9 * sizeFactor);
	private static final List<Float> arrList = new ArrayList<>() {{
		for (int i = 0; i < arrListSize; i++) {
			add((new Random().nextInt(elementLimitFactor) - (elementLimitFactor / 2)) + new Random().nextFloat());
		}
	}};


	@Test
	public void averageTestCase() {
		double averageByLoop = MathUtil.averageByLoop(arrList);
		double averageByStream = MathUtil.averageByStream(arrList);

		Assert.assertTrue(((Math.abs(averageByLoop - averageByStream) / averageByLoop) * 100) < 1);
	}

	@Test
	public void factorialTestCase() {
		int n = 20;
		long factorial20 = 2432902008176640000L;
		long factorial = MathUtil.factorial(n);

		Assert.assertEquals(factorial, factorial20);
	}

	@Test
	public void factorial1TestCase() {
		long factorialAns = 1;

		int n = 0;
		long factorial = MathUtil.factorial(n);
		Assert.assertEquals(factorial, factorialAns);

		n = 1;
		factorial = MathUtil.factorial(n);
		Assert.assertEquals(factorial, factorialAns);
	}

	@Test
	public void factorialExceptionTestCase() {
		int n = -1;

		try	{
			long factorial = MathUtil.factorial(n);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(e.getClass(), CommonException.class);
			Assert.assertTrue(e.getMessage().contains(String.valueOf(n)));
			Assert.assertTrue(e.getMessage().contains("non-negative integers"));
		}
	}

	@Test
	public void simpleInterestTestCase() {
		Assert.assertEquals(MathUtil.simpleInterest(100, 1, 1), 1);
	}

	@Test
	public void compoundInterestTestCase() {
		Assert.assertEquals(MathUtil.compoundInterest(100, 1, 1), 1);
	}

	@Test
	public void permutationsTestCase() {
		Assert.assertEquals(MathUtil.permutations(5, 2), 20);
	}

	@Test
	public void combinationsTestCase() {
		Assert.assertEquals(MathUtil.combinations(5, 2), 10);
		Assert.assertEquals(MathUtil.combinations(5, 2), MathUtil.combinations(5, 3));
	}

	@Test
	public void getMinTestCase() {
		Assert.assertEquals(MathUtil.getMin(arrList), Collections.min(arrList));
	}

	@Test
	public void getMaxTestCase() {
		Assert.assertEquals(MathUtil.getMax(arrList), Collections.max(arrList));
	}
}
