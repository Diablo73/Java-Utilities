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

public class BubbleSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList, SortEnum sortEnum) {

		List<T> sortedList = bubbleSort(new ArrayList<>(unsortedList), unsortedList.size(), sortEnum);

		return sortedList;
	}

	private static <T extends Comparable<T>> List<T> bubbleSort(List<T> sortedList, int size, SortEnum sortEnum) {
		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - i - 1; j++) {
				if (sortEnum.isAscending()) {
					if (sortedList.get(j).compareTo(sortedList.get(j + 1)) > 0) {
						SwapUtil.swap(sortedList, j, j + 1);
					}
				} else if (sortEnum.isDescending()) {
					if (sortedList.get(j).compareTo(sortedList.get(j + 1)) < 0) {
						SwapUtil.swap(sortedList, j, j + 1);
					}
				}
			}
		}
		return sortedList;
	}

}
