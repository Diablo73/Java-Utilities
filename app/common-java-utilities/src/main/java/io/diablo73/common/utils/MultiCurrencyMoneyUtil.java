package io.diablo73.common.utils;

import io.diablo73.common.dataStructures.MultiCurrencyMoney;

public class MultiCurrencyMoneyUtil {

	public static boolean equals(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		return m1.getCent() == m2.getCent() && m1.getCurrencyNumericCode().equals(m2.getCurrencyNumericCode());
	}

	public static MultiCurrencyMoney clone(MultiCurrencyMoney m1) {
		return new MultiCurrencyMoney(m1.getAmount(), m1.getCurrency());
	}

	public static boolean isSameCurrency(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		return m1.getCurrencyNumericCode().equals(m2.getCurrencyNumericCode());
	}

	public static boolean isNotSameCurrency(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		return !m1.getCurrencyNumericCode().equals(m2.getCurrencyNumericCode());
	}

	public static int compare(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		if (isNotSameCurrency(m1, m2)) {
			throw new IllegalArgumentException("Currency mismatch");
		}
		return Long.compare(m1.getCent(), m2.getCent());
	}

	public static boolean isFirstGreaterThanSecond(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		return compare(m1, m2) > 0;
	}

	public static boolean isFirstSmallerThanSecond(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		return compare(m1, m2) < 0;
	}

	public static MultiCurrencyMoney add(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		return new MultiCurrencyMoney(CheckOverflowUtil.longCheckAddition(m1.getCent(), m2.getCent()), m1.getCurrency());
	}

	public static MultiCurrencyMoney subtract(MultiCurrencyMoney m1, MultiCurrencyMoney m2) {
		return new MultiCurrencyMoney(CheckOverflowUtil.longCheckSubtraction(m1.getCent(), m2.getCent()), m1.getCurrency());
	}

	public static MultiCurrencyMoney multiply(MultiCurrencyMoney m1, int factor) {
		return new MultiCurrencyMoney(CheckOverflowUtil.longCheckMultiplication(m1.getCent(), factor), m1.getCurrency());
	}
}
