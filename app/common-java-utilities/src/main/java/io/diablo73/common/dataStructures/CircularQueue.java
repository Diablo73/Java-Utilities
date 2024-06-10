package io.diablo73.common.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class CircularQueue<E> {

	private int currentIndex = 0;
	private final List<E> array = new ArrayList<>();

	public void add(E element) {
		array.add(element);
	}

	public int size() {
		return array.size();
	}

	public E next() {
		int returnIndex = currentIndex;
		currentIndex = (currentIndex + 1) % array.size();
		return array.get(returnIndex);
	}

	public boolean hasNext() {
		return !array.isEmpty();
	}
}
