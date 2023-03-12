/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 * This code is tribute to Robert Sedgewick and Kevin Wayne Coursera class Algorithms, Part I © 2012
 */
package algos.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSelect {

    /**
     * Select top k (or bottom k) elements from an array
     *
     * @param array a source for search
     * @param k amount of elements to select
     * @param top true if top k elements, false if bottom k elements
     * @param <C> a Comparable type
     */
    public static <C extends Comparable<C>> void rearrangeToSelectK(C[] array, int k, boolean top) {
        List<C> list = Arrays.asList(array);
        Collections.shuffle(list);

        for(int i = 0; i < array.length; i++) array[i] = list.get(i);

        int lo = 0;
        int hi = array.length - 1;

        while (lo < hi) {
            int j = QuickSort.<C>partition(array, lo, hi, !top);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return;
        }
     }
}