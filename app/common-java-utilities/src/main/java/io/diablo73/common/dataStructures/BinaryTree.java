package io.diablo73.common.dataStructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinaryTree<E> {

	private E value;
	private BinaryTree<E> leftBinaryTree;
	private BinaryTree<E> rightBinaryTree;

	public BinaryTree() {
	}

	public BinaryTree(E val) {
		this.value = val;
	}

	public BinaryTree(E val, BinaryTree<E> leftBinaryTree, BinaryTree<E> rightBinaryTree) {
		this.value = val;
		this.leftBinaryTree = leftBinaryTree;
		this.rightBinaryTree = rightBinaryTree;
	}

	public BinaryTree(List<E> arrayList) {
		if (Objects.nonNull(arrayList) && !arrayList.isEmpty()) {
			buildTree(arrayList);
		}
	}

	public BinaryTree(E[] array) {
		this(new ArrayList<E>(List.of(array)));
	}

	public void buildTree(List<E> arrayList) {
		buildBinaryTree(arrayList);
//		buildTree(arrayList, 0);
	}

	private void buildBinaryTree(List<E> arrayList) {
		this.value = arrayList.get(0);
		List<BinaryTree<E>> nodeList = new ArrayList<>();

		BinaryTree<E> currentBinaryTree = this;
		boolean isLeft = true;
		for (int i = 1; i < arrayList.size(); i++) {
			if (isLeft) {
				if (Objects.nonNull(arrayList.get(i))) {
					currentBinaryTree.leftBinaryTree = new BinaryTree<>(arrayList.get(i));
					nodeList.add(currentBinaryTree.leftBinaryTree);
				}
			} else {
				if (Objects.nonNull(arrayList.get(i))) {
					currentBinaryTree.rightBinaryTree = new BinaryTree<>(arrayList.get(i));
					nodeList.add(currentBinaryTree.rightBinaryTree);
					currentBinaryTree = nodeList.remove(0);
				}
			}
			isLeft = !isLeft;
		}
	}

//	private void buildTree(List<E> arrayList, int index) {
//		if (index < arrayList.size() && Objects.nonNull(arrayList.get(index))) {
//			this.value = arrayList.get(index);
//			int leftIndex = 2 * index + 1;
//			int rightIndex = 2 * index + 2;
//
//			if (leftIndex < arrayList.size() && Objects.nonNull(arrayList.get(leftIndex))) {
//				this.leftBinaryTree = new BinaryTree<E>();
//				this.leftBinaryTree.buildTree(arrayList, leftIndex);
//			}
//
//			if (rightIndex < arrayList.size() && Objects.nonNull(arrayList.get(rightIndex))) {
//				this.rightBinaryTree = new BinaryTree<E>();
//				this.rightBinaryTree.buildTree(arrayList, rightIndex);
//			}
//		}
//	}

	public E getValue() {
		return value;
	}

	public void setValue(E val) {
		value = val;
	}

	public BinaryTree<E> getLeftBinaryTree() {
		return leftBinaryTree;
	}

	public BinaryTree<E> getRightBinaryTree() {
		return rightBinaryTree;
	}

	public int getMaxHeight() {
		if (Objects.isNull(value)) {
			return 0;
		}

		int leftHeight = Objects.nonNull(getLeftBinaryTree()) ? getLeftBinaryTree().getMaxHeight() : 0;
		int rightHeight = Objects.nonNull(getRightBinaryTree()) ? getRightBinaryTree().getMaxHeight() : 0;

		return Math.max(leftHeight, rightHeight) + 1;
	}

	public List<List<E>> convertToLevelOrderTraversalListOfLists() {
		List<List<E>> result = new ArrayList<>();
		List<BinaryTree<E>> currentLevel = new ArrayList<>();
		currentLevel.add(this);

		while (!currentLevel.isEmpty()) {
			List<E> levelValues = new ArrayList<>();
			List<BinaryTree<E>> nextLevel = new ArrayList<>();

			for (BinaryTree<E> node : currentLevel) {
				if (Objects.nonNull(node)) {
					levelValues.add(node.value);
					nextLevel.add(Objects.nonNull(node.leftBinaryTree) ? node.leftBinaryTree : null);
					nextLevel.add(Objects.nonNull(node.rightBinaryTree) ? node.rightBinaryTree : null);
				} else {
					levelValues.add(null);
					nextLevel.add(null);
					nextLevel.add(null);
				}
			}

			boolean hasNonNull = false;
			for (BinaryTree<E> node : nextLevel) {
				if (Objects.nonNull(node)) {
					hasNonNull = true;
					break;
				}
			}

			result.add(levelValues);
			if (!hasNonNull) {
				break;
			}
			currentLevel = nextLevel;
		}

		return result;
	}

	public void printLevelOrderTraversal() {
		for (List<E> level : convertToLevelOrderTraversalListOfLists()) {
			for (E val : level) {
				System.out.print(val + "\t");
			}
			System.out.println();
		}
	}
}
