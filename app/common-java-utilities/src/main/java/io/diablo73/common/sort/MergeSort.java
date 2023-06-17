package io.diablo73.common.sort;

import io.diablo73.common.enums.SortEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diablo73
 * @version 1.0 <br> 11/03/2022
 * @since 11/03/2022
 */

public class MergeSort {

	public static <T extends Comparable<T>> List<T> sort(List<T> unsortedList, SortEnum sortEnum) {

		List<T> sortedList = mergeSort(new ArrayList<>(unsortedList), unsortedList.size(), sortEnum);

		return sortedList;
	}

	private static <T extends Comparable<T>> List<T> mergeSort(List<T> sortedList, int n, SortEnum sortEnum) {
		if (n < 2) {
			return sortedList;
		}
		int mid = n / 2;

		return merge(
				mergeSort(sortedList.subList(0, mid), mid, sortEnum),						//	leftList
				mergeSort(sortedList.subList(mid, n), n - mid, sortEnum),				//	rightList
				sortEnum);
	}

	private static <T extends Comparable<T>> List<T> merge(List<T> leftList, List<T> rightList, SortEnum sortEnum) {

		List<T> sortedList = new ArrayList<>();
		int leftSize = leftList.size();
		int rightSize = rightList.size();
		int i = 0;
		int j = 0;

		while (i < leftSize && j < rightSize) {
			if (sortEnum.isAscending()) {
				if (leftList.get(i).compareTo(rightList.get(j)) < 0) {
					sortedList.add(leftList.get(i));
					i++;
				} else {
					sortedList.add(rightList.get(j));
					j++;
				}
			} else if (sortEnum.isDescending()) {
				if (leftList.get(i).compareTo(rightList.get(j)) > 0) {
					sortedList.add(leftList.get(i));
					i++;
				} else {
					sortedList.add(rightList.get(j));
					j++;
				}
			}
		}

		while (i < leftSize) {
			sortedList.add(leftList.get(i));
			i++;
		}
		while (j < rightSize) {
			sortedList.add(rightList.get(j));
			j++;
		}

		return sortedList;
	}

}
