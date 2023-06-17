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

public class HeapSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList, SortEnum sortEnum) {

		List<T> sortedList = heapSort(new ArrayList<>(unsortedList), unsortedList.size(), sortEnum);

		return sortedList;
	}

	private static <T extends Comparable<T>> List<T> heapSort(List<T> sortedList, int size, SortEnum sortEnum) {

		for (int i = size / 2 - 1; i >= 0; i--) {
			createHeap(sortedList, size, i, sortEnum);
		}

		for (int i = size - 1; i >= 0; i--) {
			SwapUtil.swap(sortedList, 0, i);
			createHeap(sortedList, i, 0, sortEnum);
		}
		return sortedList;
	}

	private static <T extends Comparable<T>> void createHeap(List<T> sortedList, int size, int i, SortEnum sortEnum) {
		int index = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;

		if (sortEnum.isAscending()) {
			if (l < size && sortedList.get(l).compareTo(sortedList.get(index)) > 0) {
				index = l;
			}
			if (r < size && sortedList.get(r).compareTo(sortedList.get(index)) > 0) {
				index = r;
			}
		} else if (sortEnum.isDescending()) {
			if (l < size && sortedList.get(l).compareTo(sortedList.get(index)) < 0) {
				index = l;
			}
			if (r < size && sortedList.get(r).compareTo(sortedList.get(index)) < 0) {
				index = r;
			}
		}

		if (index != i) {
			SwapUtil.swap(sortedList, i, index);
			createHeap(sortedList, size, index, sortEnum);
		}
	}

}
