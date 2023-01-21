/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import java.util.NoSuchElementException;

public final class BinaryHeap<T extends Comparable<T>> {

    private T[] priorityQueue;
    private int minPriorityElementIndex;

    public BinaryHeap() {
        this.priorityQueue = (T[]) new Object[1];
        this.minPriorityElementIndex = -1;
    }

    public void insert(T key) {
        if (key == null) throw new IllegalArgumentException();
        if (minPriorityElementIndex == priorityQueue.length - 1) {
            T[] biggerPriorityQueue = (T[]) new Object[priorityQueue.length * 2];
            for (int j = 0; j <= minPriorityElementIndex; j++) biggerPriorityQueue[j] = priorityQueue[j];
            priorityQueue = biggerPriorityQueue;
        }

        priorityQueue[++minPriorityElementIndex] = key;
        swim(minPriorityElementIndex);
    }

    /**
     * The idea behind swimming up of an element with a higher priority is that
     * the higher priority, the 'lighter' the element is, which is why it floats up until
     * it is heavy/dense enough to float (keep its position)
     * @param index an index of element we're about to promote
     */
    private void swim(int index) {
        while (index > 0 && priorityQueue[index / 2].compareTo(priorityQueue[index]) < 0) { // swim up until you are either in top priority position or your parent has larger priority than yours
            swap(index, index / 2);
            index = index / 2;
        }
    }

    /**
     * The idea behind putting an element into its final position in a binary heap from the top
     * is that the recently inserted element sinks (drawns) until
     * it is light enough to float (keep its position)
     *
     * @param index
     */
    private void sink (int index) {
        while (2 * index <= minPriorityElementIndex) {
            int j = 2 * index; // It is index arithmetic here. Each parent with index k may have only 2 children with indices 2k and 2k + 1, correspondingly. This is how we traverse the heap.
            if (j < minPriorityElementIndex && priorityQueue[j].compareTo(priorityQueue[j + 1]) < 0) j++; // when sinking, we must swap the parent element with the biggest of its two children. This is why we compare elements with 'j' and 'j + 1' indices here. It must be guaranteed that for both 'j' and 'j + 1', their parent element with index j / 2 has a higher priority than any of its two children.
            if (priorityQueue[index].compareTo(priorityQueue[j]) > 0) break;
            swap(index, j);
            index = j;
        }
    }

    /**
     * Deleting an element from to top of the heap (popping) is being done in three steps:
     * 1. Save the popped out max priority element to temp variable 'max'
     * 2. Swap it with the minimum priority element
     * 3. Let the element from the bottom of the priority queue sink from the top priority position
     * all the way down to a position of its belonging
     * We also may choose to free memory by nulling the vacant queue position of the
     * least priority element which has been moved
     *
     * @return the popped out top priority element
     */
    public T delMax() {
        if (minPriorityElementIndex < 0) throw new NoSuchElementException();
        T max = priorityQueue[0];
        swap(0, minPriorityElementIndex--);
        sink(0);
        priorityQueue[minPriorityElementIndex + 1] = null; // prevent loitering

        if (--minPriorityElementIndex == priorityQueue.length - (priorityQueue.length / 4) - 1) {
            T[] smallerPriorityQueue = (T[]) new Object[minPriorityElementIndex + 1];
            System.arraycopy(priorityQueue, 0, smallerPriorityQueue, 0, smallerPriorityQueue.length);
            priorityQueue = smallerPriorityQueue;
        }

        return max;
    }

    public void swap (int i, int j) {
        T temp = priorityQueue[i];
        priorityQueue[j] = priorityQueue[i];
        priorityQueue[i] = temp;
    }
}