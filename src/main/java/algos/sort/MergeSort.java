/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

public class MergeSort<T extends Comparable<T>> {

    private T[] array;
    private int lastElementIndex;

    public MergeSort(T[] array, boolean reverse) {
        this.array = array;
        this.lastElementIndex = array.length - 1;
        mergeSort(reverse);
    }

    private void mergeSort(boolean reverse) {
        T[] workspace = (T[]) new Comparable[lastElementIndex + 1];
        recMergeSort(workspace, 0, lastElementIndex, reverse);
    }

    private void recMergeSort(T[] workSpace, int lowerBound, int upperBound, boolean reverse) {
        if (lowerBound == upperBound);
        else {
            int mid = (upperBound + lowerBound) / 2;
            recMergeSort(workSpace, lowerBound, mid, reverse);
            recMergeSort(workSpace, mid + 1, upperBound, reverse);
            merge(workSpace, lowerBound, mid + 1, upperBound, reverse);
        }
    }

    private void merge(T[] workspace, int loPtr, int hiPtr, int upperBound, boolean reverse) {
        int j = 0;
        int lowerBound = loPtr;
        int mid = hiPtr - 1;
        int n = upperBound - lowerBound + 1; // amount of elements
        while (loPtr <= mid && hiPtr <= upperBound)
            if (reverse) {
                if (array[loPtr].compareTo(array[hiPtr]) > 0)
                    workspace[j++] = array[loPtr++];
                else
                    workspace[j++] = array[hiPtr++];
            } else {
                if (array[loPtr].compareTo(array[hiPtr]) < 0)
                    workspace[j++] = array[loPtr++];
                else
                    workspace[j++] = array[hiPtr++];
            }

        while (loPtr <= mid)
            workspace[j++] = array[loPtr++];

        while (hiPtr <= upperBound)
            workspace[j++] = array[hiPtr++];

        for (j = 0; j < n; j++)
            array[lowerBound + j] = workspace[j];
    }

}