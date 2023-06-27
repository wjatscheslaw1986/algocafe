/*
 * Copyright © 2023. Viacheslav Mikhailov https://github.com/vjatscheslaw
 */
package algos.datastructure;

import java.util.NoSuchElementException;

/**
 * As in Jim Mischel's answer 
 * (https://stackoverflow.com/questions/22900388/why-in-a-heap-implemented-by-array-the-index-0-is-left-unused)
 * we have two options for out root element in an array-based heap: start at index 1 or start at index 0.
 * The difference is in heap traversing formulas:
 *                   root at 0       root at 1
 * Left child        index*2 + 1     index*2
 * Right child       index*2 + 2     index*2 + 1
 * Parent            (index-1)/2     index/2
 * 
 * This implementation has its' root element at index 1 (so the element at index 0 must never be used/accessed).
 * 
 * @author Viacheslav Mikhailow
 *
 * @param <T> the comparable type of a heap element
 */
public final class BinaryHeap<T extends Comparable<T>> {

	private T[] priorityQueue;
	private int minPriorityElementIndex;

	public BinaryHeap() {
		this.priorityQueue = (T[]) new Comparable[1];
		this.minPriorityElementIndex = 0;
	}
	
	public boolean hasNext() {
		return minPriorityElementIndex > 0;
	}

	public void insert(T key) {

		if (key == null)
			throw new IllegalArgumentException();

		// This block of code executes only when index of element with the lowest
		// priority
		// equals to index of last available position in the priority queue.
		// It simply adds space in the priority queue.
		if (minPriorityElementIndex == priorityQueue.length - 1) {
			T[] biggerPriorityQueue = (T[]) new Comparable[priorityQueue.length * 2];
//			for (int j = 1; j <= minPriorityElementIndex; j++)
//				biggerPriorityQueue[j] = priorityQueue[j];
			System.arraycopy(priorityQueue, 1, biggerPriorityQueue, 1, priorityQueue.length - 1);
			priorityQueue = biggerPriorityQueue;
		}

		priorityQueue[++minPriorityElementIndex] = key; // all new elements add to the lowest priority position in the
														// heap, ...
		swim(minPriorityElementIndex); // ... then they swim up to the level of their belonging
	}

	/**
	 * The idea behind swimming up of an element with a higher priority is that the
	 * higher priority, the 'lighter' the element is, which is why it floats up
	 * until it is heavy/dense enough to float (keep its position)
	 * 
	 * @param index an index of element we're about to promote
	 */
	private void swim(int index) {
		while (index > 1 && priorityQueue[index / 2].compareTo(priorityQueue[index]) < 0) { // swim up until you are
																							// either in top priority
																							// (index 0)
																							// position or your parent
																							// has larger priority than
																							// the one of yours
			swap(index, index / 2);
			index = index / 2;
		}
	}

	/**
	 * The idea behind putting an element into its final position in a binary heap
	 * from the top is that the recently inserted element sinks (drawns) until it is
	 * light enough to float (keep its position)
	 *
	 * @param index
	 */
	private void sink(int index) {
		while (2 * index <= minPriorityElementIndex) {
			int j = 2 * index; // It is index arithmetic here. Each parent with index k may have only 2
								// children with indices 2k and 2k + 1, correspondingly. This is how we traverse
								// the heap.
			if (j < minPriorityElementIndex && priorityQueue[j].compareTo(priorityQueue[j + 1]) < 0)
				j++; // when sinking, we must swap the parent element with the biggest of its two
						// children. This is why we compare elements with 'j' and 'j + 1' indices here.
						// It must be guaranteed that for both 'j' and 'j + 1', their parent element
						// with index j / 2 has a higher priority than any of its two children.
			if (priorityQueue[index].compareTo(priorityQueue[j]) > 0)
				return;
			swap(index, j);
			index = j;
		}
	}

	/**
	 * Deleting an element from to top of the heap (popping) is being done in three
	 * main steps: 1. Save the popped out max priority element to temp variable
	 * 'max' 2. Swap it with the current minimum priority element 3. Let the element
	 * from the bottom of the priority queue sink down from the top priority
	 * position to where it belongs. 4. (Optional) Free up memory by nulling the
	 * vacant array position (prevent loitering)
	 *
	 * @return the popped out top priority element
	 */
	public T delMax() {
		if (minPriorityElementIndex < 1)
			throw new NoSuchElementException();
		T max = priorityQueue[1]; // Step 1
		
		if (minPriorityElementIndex == 1) {
			minPriorityElementIndex = 0;
			priorityQueue[minPriorityElementIndex + 1] = null;
			return max;
		}
		
		swap(1, minPriorityElementIndex--); // Step 2
		sink(1); // Step 3
		priorityQueue[minPriorityElementIndex + 1] = null; // Step 4

		// This block of code shrinks the array of the heap if it has too many free
		// cells
		if (minPriorityElementIndex == priorityQueue.length - (priorityQueue.length / 4) - 1) {
			T[] smallerPriorityQueue = (T[]) new Comparable[minPriorityElementIndex + 1];
			System.arraycopy(priorityQueue, 1, smallerPriorityQueue, 1, smallerPriorityQueue.length - 1);
			priorityQueue = smallerPriorityQueue;
		}

		return max;
	}

	private void swap(int i, int j) {
		T temp = priorityQueue[j];
		priorityQueue[j] = priorityQueue[i];
		priorityQueue[i] = temp;
	}
}