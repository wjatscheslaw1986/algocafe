/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import org.junit.jupiter.api.Test;

public class SortTests {
    @Test
    public void sortComparativeTest() {

        System.out.println("Now quicksort:");
        double[] inArray = new double[]{34565.65d, 5.0d, 2.6d, 7.9d, Double.POSITIVE_INFINITY, 267.8d};
        double[] outArray = QuickSort.quicksort(inArray);
        for (int i = 0; i < outArray.length; i++) System.out.println("->" + outArray[i]);

        //TODO why sorting is happening in different order?
        System.out.println("Now choice sort:");
        inArray = new double[]{34565.65d, 5.0d, 2.6d, 7.9d, Double.POSITIVE_INFINITY, 267.8d};
        outArray = ChoiceSort.sort(inArray);
        for (int i = 0; i < outArray.length; i++) System.out.println("->" + outArray[i]);

    }
}