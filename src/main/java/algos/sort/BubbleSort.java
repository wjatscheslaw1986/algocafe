/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

/**
 * BubbleSort is a quadratic time sorting algorithm
 */
public class BubbleSort {

    /**
     * An inner cycle goes from the beginning to the cursor of outer cycle.
     * While the outer cycle moves the cursor closer to the beginning with each iteration
     *
     * @param array the array to sort
     * @param reverse true, if sorting order is reversed, otherwise false
     * @return the sorted array
     */
    public static String[] sort(String[] array, boolean reverse) {
        for (int i = array.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (reverse) {
                    if (array[j].compareTo(array[i]) < 0) {
                        swap(array, i, j);
                    }
                } else if (array[j].compareTo(array[i]) > 0) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }
    private static void swap(String[] array, int i, int j) {
        String i_ = array[i];
        array[i] = array[j];
        array[j] = i_;
    }
}