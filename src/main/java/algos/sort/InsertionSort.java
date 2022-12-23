/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

public class InsertionSort {

    public static String[] sortArray(String[] array) {
        for (int i = 1; i < array.length; i++) {
            String temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1].compareTo(temp) > 0) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = temp;
        }
        return array;
    }
}
