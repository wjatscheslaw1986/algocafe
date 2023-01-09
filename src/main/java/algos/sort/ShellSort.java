/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

public class ShellSort {

    /**
     * Complexity cannot be theoretically explained. Experimental estimations vary from O(N^(3/2)) to O(N^(7/6)) (Robert W. Lafore "Data Structures and Algorithms in Java", Chapter 7. 2003)
     *
     * @param array an array to sort
     * @param reverse order to sort - 'true' if reverse, else 'false'
     * @return a sorted array
     */
    public static String[] sort(String[] array, boolean reverse) {
        int h = 1;
        while (h <= array.length/3) h = h*3 + 1; // Knuth
        while (h > 0) {
            for (int i = h; i < array.length; i++) {
                String tmp = array[i];
                int j = i;
                if (reverse) {
                    while (j > h-1 && array[j - h].compareTo(tmp) < 0) {
                        array[j] = array[j - h];
                        j -= h;
                    }
                } else {
                    while (j > h-1 && array[j - h].compareTo(tmp) > 0) {
                        array[j] = array[j - h];
                        j -= h;
                    }
                }
                array[j] = tmp;
            }
            h = (h - 1) / 3; // Knuth
        }
        return array;
    }
}