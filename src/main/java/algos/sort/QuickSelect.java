/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 * This code is tribute to Robert Sedgewick and Kevin Wayne Coursera class Algorithms, Part I © 2012
 */
package algos.sort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSelect {

    /**
     * Select top k (or bottom k) elements from an array
     *
     * @param array a source for search
     * @param k amount of elements to select
     * @param top true if top k elements, false if k bottom
     * @return a possible median element of the array
     * @param <C> a Comparable type
     */
    public static <C extends Comparable<C>> C rearrangeToSelectK(C[] array, int k, boolean top) {
        StdRandom.shuffle(array);
        int lo = 0;
        int hi = array.length - 1;

        while (lo < hi) {

            int j = QuickSort.<C>partition(array, lo, hi, !top);
            if (j < k) lo = j + 1;
            if (j > k) hi = j - 1;
            else return array[j];
        }

        return array[k];
     }
}