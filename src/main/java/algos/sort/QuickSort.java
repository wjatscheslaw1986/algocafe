/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QuickSort<C extends Comparable<C>> {

	private final C[] arr;

	public QuickSort(C[] array) {
		C[] arry = (C[]) new Comparable[array.length];
		System.arraycopy(array, 0, arry, 0,  array.length);
		ArrayList<C> tmp = new ArrayList<>(List.of(arry));
		Collections.shuffle(tmp); // shuffling the array before we sort. It is necessary in QuickSort, in order to avoid quadratic time degradation. Degradaton to quadratic time occurs when the array is already sorted in the opposite direction.
		this.arr = (C[]) tmp.toArray(Comparable[]::new);
	}

	public C[] getArray() {
		return arr;
	}

	/**
	 * Partition algorithm has complexity of O(N). It moves all the lesser than the pivot elements of the given array to left,
	 * and all the larger than the pivot elements of the given array to right.
	 *
	 * @param left from index, inclusive
	 * @param right to index, inclusive
	 * @return amount of the elements in the left group of the 'array'. Also, it is an index of an already sorted element in the 'array' after partitioning
	 */
	public static <W extends Comparable<W>> int partition(W[] arr, int left, int right, boolean reverse) {
		if (left < 0 || right >= arr.length) throw new IllegalArgumentException("the 'left' and 'right' arguments must be indices of an array");
		int leftCursor, rightCursor;
		if (reverse) {
			leftCursor = left; // for purposes of QuickSort, we exclude the very left element from partitioning and consider it to be a pivot of partitioning
			rightCursor = right + 1;
		} else {
			leftCursor = left - 1;
			rightCursor = right; // for purposes of QuickSort, we exclude the very right element from partitioning and consider it to be a pivot of partitioning
		}
		while (true) {
			if (reverse) {
				while (rightCursor > left && arr[--rightCursor].compareTo(arr[left]) < 0) ;
				while (leftCursor < right && arr[++leftCursor].compareTo(arr[left]) > 0) ;
			} else {
				while (leftCursor < right && arr[++leftCursor].compareTo(arr[right]) < 0) ;
				while (rightCursor > left && arr[--rightCursor].compareTo(arr[right]) > 0) ;
			}
			if (leftCursor >= rightCursor) break;
			else swap(arr, leftCursor, rightCursor);
		}
		if (reverse) {
			swap(arr, rightCursor, left); // by the end of partitioning, 'rightCursor' will be the index of the most right element of the left (larger values) segment. We swap it with the pivot. Now, the former pivot is the only sorted element by now (in the range between 'left' and 'right' indices, inclusive), and now holds its final position (i.e. array till the end of QuickSort
			return rightCursor;
		}
		else {
			swap(arr, leftCursor, right); // by the end of partitioning, 'leftCursor' will be the index of the most left element of the right (larger values) segment. We swap it with the pivot. Now, the former pivot is the only sorted element by now (in the range between 'left' and 'right' indices, inclusive), and now holds its final position (i.e. array index) till the end of QuickSort
			return leftCursor;
		}
	}


	public static <W extends Comparable<W>> void manualSort3Elements(W[] arr, int from, boolean reverse) {
		if (arr.length <= from)
			throw new IllegalArgumentException("Array index out of bounds: " + from + " from array length " + arr.length);
		if (arr.length - from == 1) return;
		if (arr.length - from == 2) {
			if (reverse) {
				if (arr[from].compareTo(arr[from + 1]) > 0) swap(arr, from, from + 1);
			} else {
				if (arr[from].compareTo(arr[from + 1]) < 0) swap(arr, from, from + 1);
			}
		}
		if (arr.length - from > 2) {
			if (reverse) {
				if (arr[from].compareTo(arr[from + 1]) < 0)
					swap(arr, from, from + 1);
				if (arr[from].compareTo(arr[from + 2]) < 0)
					swap(arr, from, from + 2);
				if (arr[from + 1].compareTo(arr[from + 2]) < 0)
					swap(arr, from + 1, from + 2);
			} else {
				if (arr[from].compareTo(arr[from + 1]) > 0)
					swap(arr, from, from + 1);
				if (arr[from].compareTo(arr[from + 2]) > 0)
					swap(arr, from, from + 2);
				if (arr[from + 1].compareTo(arr[from + 2]) > 0)
					swap(arr, from + 1, from + 2);
			}
		}
	}

	public static <W extends Comparable<W>> W medianOf3(W[] arr, int leftInd, int rightInd, boolean reverse) {
		if (arr.length < 3)
			throw new IllegalArgumentException("Median of 3 must be found on an array of min length=3");
		if (rightInd - leftInd < 2) {
			if (reverse) return arr[leftInd];
			return arr[rightInd];
		}
		int centerInd = (rightInd + leftInd) / 2;
		if (reverse) {
			W left = arr[leftInd], right = arr[rightInd], center = arr[centerInd];
			if (right.compareTo(center) > 0)
				swap(arr, rightInd, centerInd);
			if (right.compareTo(left) > 0)
				swap(arr, leftInd, rightInd);
			if (center.compareTo(left) > 0)
				swap(arr, centerInd, leftInd);
			swap(arr, centerInd, leftInd); // we forcefully put median in the left sector as it is one of those on the left side anyway
		} else {
			W left = arr[leftInd], right = arr[rightInd], center = arr[centerInd];
			if (left.compareTo(center) > 0)
				swap(arr, leftInd, centerInd);
			if (left.compareTo(right) > 0)
				swap(arr, leftInd, rightInd);
			if (center.compareTo(right) > 0)
				swap(arr, centerInd, rightInd);
			swap(arr, centerInd, rightInd); // we forcefully put median in the right sector as it is one of those on the right side anyway
		}
		if (reverse) return arr[leftInd];
		return arr[rightInd];
	}

	/**
	 * The QuickSort algorithm. Average 2NlogN
	 * During initial invocation, 'left' must be an index of first element of the 'arr', and the 'right' must be the last one.
	 * BEWARE: it degrades to 1/2*N^2 time if elements are sorted, so you must shuffle the array first in order to avoid it.
	 * BEWARE: its complexity is O(N^2) in worst case which is when there are a lot of duplicate elements. In order to avoid it, prefer 3-way quicksort instead of this implementation.
	 *
	 * @param left left cursor (index, inclusive) for sorting algorithm
	 * @param right right cursor (index, inclusive) for sorting algorithm
	 * @param reverse order
	 */
	public void sort(int left, int right, boolean reverse) {
		if (arr.length < 2) return;
		if (right-left < 1) return;
		if (right-left == 1) {
			if (reverse) {
				if (arr[left].compareTo(arr[right]) < 0) swap(arr, left, right);
			} else {
				if (arr[left].compareTo(arr[right]) > 0) swap(arr, left, right);
			}
			return;
		}
		if (right-left == 2) {
			manualSort3Elements(arr, left, reverse);
			return;
		}
		int pivot = partition(arr, left, right, reverse);
		sort(left, pivot - 1, reverse);
		sort(pivot + 1, right, reverse);
	}

	/**
	 * The 3-way QuickSort algorithm. Its best case is N. Average 2NlogN
	 * During initial invocation, 'left' must be an index of first element of the 'arr', and the 'right' must be the last one.
	 *
	 * @param left left cursor (index, inclusive) for sorting algorithm
	 * @param right right cursor (index, inclusive) for sorting algorithm
	 * @param reverse order
	 */
	public void sort3way(int left, int right, boolean reverse) {
		if (arr.length < 2) return;
		if (right-left < 1) return;
		if (right-left == 1) {
			if (reverse) {
				if (arr[left].compareTo(arr[right]) < 0) swap(arr, left, right);
			} else {
				if (arr[left].compareTo(arr[right]) > 0) swap(arr, left, right);
			}
			return;
		}
		if (right-left == 2) {
			manualSort3Elements(arr, left, reverse);
			return;
		}
		int leftCursor = left, middleCursor = left, rightCursor = right;

		C pivot = arr[left];
		while (middleCursor <= rightCursor) {
			int cmp = arr[middleCursor].compareTo(pivot);
			if (reverse) {
				if (cmp > 0) swap(arr, leftCursor++, middleCursor++);
				else if (cmp < 0) swap(arr, middleCursor, rightCursor--);
				else middleCursor++;

			} else {
				if (cmp < 0) swap(arr, leftCursor++, middleCursor++);
				else if (cmp > 0) swap(arr, middleCursor, rightCursor--);
				else middleCursor++;
			}
		}

		sort(left, leftCursor - 1, reverse);
		sort(rightCursor + 1, right, reverse);
	}

	public static <W extends Comparable<W>> void swap(W[] arr, int i, int j) {
		W temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}