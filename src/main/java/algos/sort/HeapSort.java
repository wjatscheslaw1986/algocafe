/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

/**
 * HeapSort is a fully in-place sorting algorithm, which needs neither additional workspace
 * array (like MergeSort) nor iterative/recursive calculation step state (like QuickSort).
 * For this reason, we can implement this class as a utility class of static utility methods.
 */
public final class HeapSort {

    public static <T extends Comparable<T>> void sort(T[] heap, boolean reverse) {
        int n = heap.length;
        for (int k = n / 2; k >= 1; k--) HeapSort.sink(heap, k, n, reverse); // heap-ordering
        while (n > 1) { // sorting
            swap(heap, 1, n--);
            sink(heap, 1, n, reverse);
        }
    }

    // Top-down reheapifying
    public static <T extends Comparable<T>> void sink(T[] heap, int k, int n, boolean reverse) {
        while (2 * k <= n) {
            int j = 2 * k; // It is index arithmetic here. Each parent with index k may have only 2 children with indices 2k and 2k + 1, correspondingly. This is how we traverse the heap.
            if (reverse) {
                if (j < n && greater(heap, j, j + 1))
                    j++; // when sinking, we must swap the parent element with the 'oldest' of its two current children. This is why we compare elements with 'j' and 'j + 1' indices here. It must be guaranteed that for both 'j' and 'j + 1', their parent element with index j / 2 has a higher priority (bigger value) than any of its two children.
                if (!greater(heap, k, j)) break;
            } else {
                if (j < n && less(heap, j, j + 1))
                    j++; // when sinking, we must swap the parent element with the 'oldest' of its two current children. This is why we compare elements with 'j' and 'j + 1' indices here. It must be guaranteed that for both 'j' and 'j + 1', their parent element with index j / 2 has a higher priority (smaller value) than any of its two children.
                if (!less(heap, k, j)) break;
            }
            HeapSort.swap(heap, k, j);
            k = j;
        }
    }

    // Bottom-up reheapifying (swim up until you are either in top priority position or your parent has larger priority than yours)
    public static <T extends Comparable<T>> void swim(T[] heap, int k, boolean reverse) {
        if (reverse) {
            while (k > 0 && greater(heap, k / 2, k)) {
                HeapSort.swap(heap, k, k / 2);
                k = k / 2;
            }
        } else {
            while (k > 0 && less(heap, k / 2, k)) {
                HeapSort.swap(heap, k, k / 2);
                k = k / 2;
            }
        }
    }

    private static <T extends Comparable<T>> boolean less(T[] heap, int i, int j) {
        return heap[i - 1].compareTo(heap[j - 1]) < 0;
    }

    private static <T extends Comparable<T>> boolean greater(T[] heap, int i, int j) {
        return heap[i - 1].compareTo(heap[j - 1]) > 0;
    }

    private static <T extends Comparable<T>> void swap(T[] heap, int i, int j) {
        T temp = heap[i - 1];
        heap[i - 1] = heap[j - 1];
        heap[j - 1] = temp;
    }
}