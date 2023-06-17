package io.diablo73.common.sort;

import io.diablo73.common.enums.SortEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

public class ShellSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList, SortEnum sortEnum) {

		List<T> sortedList = shellSort(new ArrayList<>(unsortedList), unsortedList.size(), sortEnum);

		return sortedList;
	}

	private static <T extends Comparable<T>> List<T> shellSort(List<T> sortedList, int size, SortEnum sortEnum) {

		for (int interval = size / 2; interval > 0; interval /= 2) {
			for (int i = interval; i < size; i++) {
				T temp = sortedList.get(i);
				int j = 0;
				if (sortEnum.isAscending()) {
					for (j = i; j >= interval && sortedList.get(j - interval).compareTo(temp) > 0; j -= interval) {
						sortedList.set(j, sortedList.get(j - interval));
					}
				} else if (sortEnum.isDescending()) {
					for (j = i; j >= interval && sortedList.get(j - interval).compareTo(temp) < 0; j -= interval) {
						sortedList.set(j, sortedList.get(j - interval));
					}
				}
				sortedList.set(j, temp);
			}
		}
		return sortedList;
	}

}
