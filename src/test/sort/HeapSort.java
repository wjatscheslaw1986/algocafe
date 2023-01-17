/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

public final class HeapSort {

    public <T extends Comparable<T>> void sort() {

    }

    private <T extends Comparable<T>> void sink() {

    }

    private <T extends Comparable<T>> void swim() {

    }

    private <T extends Comparable<T>> void delete() {

    }

    private <T extends Comparable<T>> boolean less(T[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private <T extends Comparable<T>> void swap(T[] heap, int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}