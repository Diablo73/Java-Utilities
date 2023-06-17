package io.diablo73.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

public class SearchUtil {

	public static <T extends Comparable<T>> int linearSearch(List<T> list, T key) {

		for (int i = 0; i < list.size() ; i++) {
			if (list.get(i).compareTo(key) == 0) {
				return i;
			}
		}
		return -1;
	}

	public static <T extends Comparable<T>> int binarySearch(List<T> list, T key) {

		List<T> newList = new ArrayList<>(list);

		return binarySearch(newList, key, 0, newList.size());
	}

	public static <T extends Comparable<T>> int binarySearch(List<T> list, T key, int start, int end) {

		if (end >= start) {
			int mid = start + ((end - start) / 2);

			if (list.get(mid).compareTo(key) == 0) {
				return mid;
			}
			if (list.get(mid).compareTo(key) > 0)
				return binarySearch(list, key, start, mid - 1);

			return binarySearch(list, key, mid + 1, end);
		}
		return -1;
	}

}
