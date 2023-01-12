/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 * This code is tribute to Rama Hoetzlein https://stackoverflow.com/users/2434404/rama-hoetzlein
 */
package algos.sort;

public class MergeSortCycle<E extends Comparable<E>> {

    private E[] array;
    private E[] workspace;
    private final boolean reverse;

    public MergeSortCycle(E[] array, boolean reverse) {
        this.array = array;
        this.workspace = (E[]) new Comparable[array.length];
        this.reverse = reverse;
        sort();
    }

    private void sort() {
        for (int k = 1; k < array.length; k *= 2)
            for (int left = 0; left + k < array.length; left += k * 2)
                merge(workspace, left, left + k, left + k + k - 1, reverse);
    }

    private void merge(E[] workspace, int offset, int firstRightIndex, int lastRightIndex, boolean reverse) {
        if (lastRightIndex > array.length - 1) lastRightIndex = array.length - 1;

        int workspaceLeftCursor = offset;
        int arrayLeftCursor = offset;
        int arrayMiddleCursor = firstRightIndex;

        while (arrayLeftCursor < firstRightIndex && arrayMiddleCursor <= lastRightIndex)
            if (reverse) {
                if (array[arrayLeftCursor].compareTo(array[arrayMiddleCursor]) > 0)
                    workspace[workspaceLeftCursor++] = array[arrayLeftCursor++];
                else
                    workspace[workspaceLeftCursor++] = array[arrayMiddleCursor++];
            } else {
                if (array[arrayLeftCursor].compareTo(array[arrayMiddleCursor]) < 0)
                    workspace[workspaceLeftCursor++] = array[arrayLeftCursor++];
                else
                    workspace[workspaceLeftCursor++] = array[arrayMiddleCursor++];
            }

        while (arrayLeftCursor < firstRightIndex) workspace[workspaceLeftCursor++] = array[arrayLeftCursor++];

        while (arrayMiddleCursor <= lastRightIndex) workspace[workspaceLeftCursor++] = array[arrayMiddleCursor++];

        for (int i = offset; i <= lastRightIndex; i++) array[i] = workspace[i];
    }
}