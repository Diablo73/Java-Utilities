package io.diablo73.common.utils;

import io.diablo73.common.enums.CommonInfoEnum;
import io.diablo73.common.exceptions.CommonException;

import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 21/03/2022
 * @since 21/03/2022
 */

public class MathUtil {

	public static <T extends Number> double averageByLoop(List<T> list) {
		double sum = 0;
		for (T i : list) {
			sum += i.doubleValue();
		}
		return sum / list.size();
	}

	public static <T extends Number> double averageByStream(List<T> list) {
		return list.stream().map(String::valueOf).mapToDouble(Double::parseDouble).average().getAsDouble();
	}

	public static long factorial(int n) {
		return factorial(Long.parseLong(String.valueOf(n)));
	}

	public static long factorial(long n) {
		if (n < 0) {
			throw new CommonException(
					CommonInfoEnum.INVALID_NUMBER
							.setResultInfoEnumWithMessage("Error", String.valueOf(n), "Factorial can be computed only for non-negative integers!!!"));
		} else if (n < 2) {
			return 1;
		}
		return n * factorial(n - 1);
	}

	public static <T extends Number> double simpleInterest(T principal, T ratePerYear, T timeInYears) {

		return (principal.doubleValue() * ratePerYear.doubleValue() * timeInYears.doubleValue()) / 100;
	}

	public static <T extends Number> double compoundInterest(T principal, T ratePerYear, T timeInYears) {

		return ((principal.doubleValue()
				* (Math.pow((1 + (ratePerYear.doubleValue() / 100)), timeInYears.doubleValue())))
				- principal.doubleValue());
	}

	public static <T extends Number> long permutations(T n, T r) {

		return factorial(n.longValue()) / factorial(n.longValue() - r.longValue());
	}

	public static <T extends Number> long combinations(T n, T r) {

		return factorial(n.longValue()) / (factorial(r.longValue()) * factorial(n.longValue() - r.longValue()));
	}

	public static <T extends Comparable<T>> T getMin(List<T> values) {
		T min = values.get(0);

		for(int i = 1; i < values.size(); i++) {
			min = min.compareTo(values.get(i)) < 0 ? min : values.get(i);
		}

		return min;
	}

	public static <T extends Comparable<T>> T getMax(List<T> values) {
		T max = values.get(0);

		for(int i = 1; i < values.size(); i++) {
			max = max.compareTo(values.get(i)) > 0 ? max : values.get(i);
		}

		return max;
	}

}
