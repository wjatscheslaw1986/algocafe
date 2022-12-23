/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTests {
    @Test
    public void sortBubble() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(BubbleSort.sortArray(names)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);

    }

    @Test
    public void sortInsertion() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(InsertionSort.sortArray(names)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);

    }

    @Test
    public void sortInsertionTest() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(insertionSort(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(insertionSort(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Alex", names[14]);

    }

    @Test
    public void sortChoice() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ChoiceSort.sortArray(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ChoiceSort.sortArray(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Alex", names[14]);

    }

    @Test
    public void sortChoiceTest() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(choiceSort(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(choiceSort(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Alex", names[14]);

    }

    private String[] choiceSort(String[] arr, boolean reverse) {
        for (int i = 0; i < arr.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < arr.length; j++)
                if (reverse) {
                    if (arr[max].compareTo(arr[j]) < 0) max = j;
                } else {
                    if (arr[max].compareTo(arr[j]) > 0) max = j;
                }
            String temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    private String[] insertionSort(String[] arr, boolean reverse) {
        for (int i = 1; i < arr.length; i++) {
            String temp = arr[i];
            int j = i;
            while (reverse && j > 0 && temp.compareTo(arr[j - 1]) > 0) {
                arr[j] = arr[j - 1];
                --j;
            }
            while (j > 0 && temp.compareTo(arr[j - 1]) < 0) {
                arr[j] = arr[j - 1];
                --j;
            }
            arr[j] = temp;
        }
        return arr;
    }
}