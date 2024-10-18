package io.diablo73.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckOverflowUtil {

	private static final BigDecimal MAX_LONG = new BigDecimal(9223372036854775807L);
	private static final BigDecimal MIN_LONG = new BigDecimal(-9223372036854775808L);

	private static void checkIfOverflow(boolean isOverflow) {
		if (isOverflow) {
			throw new ArithmeticException("CheckOverflow FAILED!!!");
		}
	}

	private static void checkIfNoOverflow(boolean isNotOverflow) {
		checkIfOverflow(!isNotOverflow);
	}

	public static long checkBigDecimal(BigDecimal b) {
		checkIfOverflow(MAX_LONG.compareTo(b) < 0 || MIN_LONG.compareTo(b) > 0);
		return b.longValue();
	}

	public static long rounding(BigDecimal val, RoundingMode roundingMode) {
		BigDecimal newVal = val.setScale(0, roundingMode);
		return checkBigDecimal(newVal);
	}

	public static long longCheckAddition(long a, long b) {
		long result = a + b;
		checkIfNoOverflow((a ^ b) < 0L | (a ^ result) >= 0L);
		return result;
	}

	public static long longCheckSubtraction(long a, long b) {
		long result = a - b;
		checkIfNoOverflow((a ^ b) >= 0L | (a ^ result) >= 0L);
		return result;
	}

	public static long longCheckMultiplication(long a, long b) {
		int leadingZeros = Long.numberOfLeadingZeros(a) + Long.numberOfLeadingZeros(~a)
				+ Long.numberOfLeadingZeros(b) + Long.numberOfLeadingZeros(~b);
		if (leadingZeros > 65) {
			return a * b;
		} else {
			checkIfNoOverflow(leadingZeros >= 64);
			checkIfNoOverflow(a >= 0L | b != MIN_LONG.longValue());
			long result = a * b;
			checkIfNoOverflow(a == 0L || result / a == b);
			return result;
		}
	}
}
