package io.diablo73.common.utils;

import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 19/03/2022
 * @since 11/03/2022
 */

public class SwapUtil {

	public static <T> void swap(List<T> list, int i, int j) {
		T t = list.get(i);
		list.set(i, list.get(j));
		list.set(j, t);
	}

	public static <T extends Number> void swapNumber(List<T> list, int i, int j) {
		Class<? extends Number> clazz = list.get(0).getClass();
		list.set(i, casting(list.get(i).doubleValue() + list.get(j).doubleValue(), clazz));
		list.set(j, casting(list.get(i).doubleValue() - list.get(j).doubleValue(), clazz));
		list.set(i, casting(list.get(i).doubleValue() - list.get(j).doubleValue(), clazz));
	}

	private static <T extends Number> T casting(double d, Class<? extends Number> clazz) {
		switch (clazz.getSimpleName()) {
			case "Integer":
				return (T)(Number) (int) d;
			case "Float":
				return (T)(Number) (float) Float.valueOf(String.format("%.6f", d));
			case "Long":
				return (T)(Number) (long) d;
			case "Double":
			default:
				return (T) Double.valueOf(String.format("%.12f", d));
		}
	}
}
