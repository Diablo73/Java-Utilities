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

public class QuickSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList, SortEnum sortEnum) {

		List<T> sortedList = quickSort(new ArrayList<>(unsortedList), 0, unsortedList.size() - 1, sortEnum);

		return sortedList;
	}

	private static <T extends Comparable<T>> List<T> quickSort(List<T> sortedList, int begin, int end, SortEnum sortEnum) {
		if (begin < end) {
			int partitionIndex = partition(sortedList, begin, end, sortEnum);

			quickSort(sortedList, begin, partitionIndex - 1, sortEnum);
			quickSort(sortedList, partitionIndex + 1, end, sortEnum);
		}
		return sortedList;
	}

	private static <T extends Comparable<T>> int partition(List<T> sortedList, int begin, int end, SortEnum sortEnum) {
		T pivot = sortedList.get(end);
		int i = begin - 1;

		for (int j = begin; j < end; j++) {
			if (sortEnum.isAscending()) {
				if (sortedList.get(j).compareTo(pivot) < 0) {
					i++;
					SwapUtil.swap(sortedList, i, j);
				}
			} else if (sortEnum.isDescending()) {
				if (sortedList.get(j).compareTo(pivot) > 0) {
					i++;
					SwapUtil.swap(sortedList, i, j);
				}
			}
		}
		SwapUtil.swap(sortedList, i + 1, end);

		return i + 1;
	}

}
