package io.diablo73.common.sort;

import io.diablo73.common.enums.SortEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 1/03/2022
 * @since 11/03/2022
 */

public class InsertionSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList, SortEnum sortEnum) {

		List<T> sortedList = insertionSort(new ArrayList<>(unsortedList), unsortedList.size(), sortEnum);

		return sortedList;
	}

	public static <T extends Comparable<T>> List<T> insertionSort(List<T> sortedList, int size, SortEnum sortEnum) {
		for (int i = 1; i < size; i++) {
			T key = sortedList.get(i);
			int j = i - 1;

			if (sortEnum.isAscending()) {
				while (j >= 0 && key.compareTo(sortedList.get(j)) < 0) {
					sortedList.set(j + 1, sortedList.get(j));
					j--;
				}
			} else if (sortEnum.isDescending()) {
				while (j >= 0 && key.compareTo(sortedList.get(j)) > 0) {
					sortedList.set(j + 1, sortedList.get(j));
					j--;
				}
			}

			sortedList.set(j + 1, key);
		}
		return sortedList;
	}

}
