package algos.sort;

public class QuickSort<C extends Comparable<C>> {

	C[] arr;

	public QuickSort(C[] array) {
		this.arr = array;
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
	public int partition(int left, int right, boolean reverse) {
		if (left < 0 || right >= arr.length) throw new IllegalArgumentException("the 'left' and 'right' arguments must be indices of an array");
		int leftCursor, rightCursor;
		if (reverse) {
			leftCursor = left; // for purposes of QuickSort, we exclude the very right element from partitioning and consider it to be a pivot of partitioning
			rightCursor = right + 1;
		} else {
			leftCursor = left - 1;
			rightCursor = right; // for purposes of QuickSort, we exclude the very right element from partitioning and consider it to be a pivot of partitioning
		}
		while (true) {
			if (reverse) {
				while (rightCursor > left && arr[--rightCursor].compareTo(arr[left]) < 0) ;
				while (leftCursor < right && arr[++leftCursor].compareTo(arr[left]) > 0) ;
				if (leftCursor >= rightCursor) break;
				else swap(leftCursor, rightCursor);
			} else {
				while (leftCursor < right && arr[++leftCursor].compareTo(arr[right]) < 0) ;
				while (rightCursor > left && arr[--rightCursor].compareTo(arr[right]) > 0) ;
				if (leftCursor >= rightCursor) break;
				else swap(leftCursor, rightCursor);
			}
		}
		if (reverse) {
			swap(rightCursor, left); // by the end of partitioning, 'leftCursor' is the index of the most left element of the right (larger) segment. We swap it with the pivot. Now, the pivot is the only sorted element by now (in the range between 'left' and 'right' indices, inclusive), and holds its final position (i.e. array index) till the end of QuickSort
			return rightCursor;
		}
		else {
			swap(leftCursor, right); // by the end of partitioning, 'leftCursor' is the index of the most left element of the right (larger) segment. We swap it with the pivot. Now, the pivot is the only sorted element by now (in the range between 'left' and 'right' indices, inclusive), and holds its final position (i.e. array index) till the end of QuickSort
			return leftCursor;
		}
	}


	public C[] manualSort3Elements(int from, boolean reverse) {
		if (arr.length <= from)
			throw new IllegalArgumentException("Array index out of bounds: " + from + " from array length " + arr.length);
		if (arr.length - from == 1) return arr;
		if (arr.length - from == 2) {
			if (reverse) {
				if (arr[from].compareTo(arr[from + 1]) > 0) swap(from, from + 1);
			} else {
				if (arr[from].compareTo(arr[from + 1]) < 0) swap(from, from + 1);
			}
		}
		if (arr.length - from > 2) {
			if (reverse) {
				if (arr[from].compareTo(arr[from + 1]) < 0)
					swap(from, from + 1);
				if (arr[from].compareTo(arr[from + 2]) < 0)
					swap(from, from + 2);
				if (arr[from + 1].compareTo(arr[from + 2]) < 0)
					swap(from + 1, from + 2);
			} else {
				if (arr[from].compareTo(arr[from + 1]) > 0)
					swap(from, from + 1);
				if (arr[from].compareTo(arr[from + 2]) > 0)
					swap(from, from + 2);
				if (arr[from + 1].compareTo(arr[from + 2]) > 0)
					swap(from + 1, from + 2);
			}
		}
		return arr;
	}

	public C medianOf3(int leftInd, int rightInd, boolean reverse) {
		if (arr.length < 3)
			throw new IllegalArgumentException("Median of 3 must be found on an array of min length=3");
		if (rightInd - leftInd < 2) {
			if (reverse) return arr[leftInd];
			return arr[rightInd];
		}
		int centerInd = (rightInd + leftInd) / 2;
		if (reverse) {
			C left = arr[leftInd], right = arr[rightInd], center = arr[centerInd];
			if (right.compareTo(center) > 0)
				swap(rightInd, centerInd);
			if (right.compareTo(left) > 0)
				swap(leftInd, rightInd);
			if (center.compareTo(left) > 0)
				swap(centerInd, leftInd);
			swap(centerInd, leftInd); // we forcefully put median in the left sector as it is one of those on the left side anyway
		} else {
			C left = arr[leftInd], right = arr[rightInd], center = arr[centerInd];
			if (left.compareTo(center) > 0)
				swap(leftInd, centerInd);
			if (left.compareTo(right) > 0)
				swap(leftInd, rightInd);
			if (center.compareTo(right) > 0)
				swap(centerInd, rightInd);
			swap(centerInd, rightInd); // we forcefully put median in the right sector as it is one of those on the right side anyway
		}
		if (reverse) return arr[leftInd];
		return arr[rightInd];
	}

	/**
	 * The QuickSort algorithm.
	 * During initial invocation, 'left' must be an index of first element of the 'arr', and the 'right' must be the last one.
	 *
	 * @param left left cursor (index, inclusive) for sorting algorithm
	 * @param right right cursor (index, inclusive) for sorting algorithm
	 * @param reverse order
	 * @return a sorted array
	 */
	public void sort(int left, int right, boolean reverse) {
		if (arr.length < 2) return;
		if (right-left < 1) return;
		if (right-left == 1) {
			if (reverse) {
				if (arr[left].compareTo(arr[right]) < 0) swap(left, right);
			} else {
				if (arr[left].compareTo(arr[right]) > 0) swap(left, right);
			}
			return;
		}
		int pivot = partition(left, right, reverse);
		sort(left, pivot - 1, reverse);
		sort(pivot + 1, right, reverse);
	}

	private void swap(int i, int j) {
		C temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}