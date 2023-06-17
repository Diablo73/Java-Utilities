package io.diablo73.common.sort;

import io.diablo73.common.enums.SortEnum;
import io.diablo73.common.utils.SwapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

public class SelectionSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList, SortEnum sortEnum) {

		List<T> sortedList = selectionSort(new ArrayList<>(unsortedList), unsortedList.size(), sortEnum);

		return sortedList;
	}

	public static <T extends Comparable<T>> List<T> selectionSort(List<T> sortedList, int size, SortEnum sortEnum) {
		for (int i = 0; i < size - 1; i++) {
			int index = i;
			for (int j = i + 1; j < size; j++) {
				if (sortEnum.isAscending()) {
					if (sortedList.get(j).compareTo(sortedList.get(index)) < 0) {
						index = j;
					}
				} else if (sortEnum.isDescending()) {
					if (sortedList.get(j).compareTo(sortedList.get(index)) > 0) {
						index = j;
					}
				}
			}
			SwapUtil.swap(sortedList, i, index);
		}
		return sortedList;
	}

}
