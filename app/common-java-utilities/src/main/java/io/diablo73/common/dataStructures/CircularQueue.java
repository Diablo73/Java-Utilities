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

	public E get() {
		return array.get(currentIndex);
	}

	public E get(int i) {
		return array.get(i % array.size());
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public List<E> getArray() {
		return array;
	}

	public E next() {
		currentIndex = (currentIndex + 1) % array.size();
		return array.get(currentIndex);
	}

	public boolean hasNext() {
		return !array.isEmpty();
	}
}
