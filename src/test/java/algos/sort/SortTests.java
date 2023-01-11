/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import algos.datastructure.QuickSortStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTests {
    @Test
    public void sortBubbleTest() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(BubbleSort.sort(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(BubbleSort.sort(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Peter", names[4]);
        Assertions.assertEquals("Igor", names[10]);

    }

    @Test
    public void sortChoiceTest() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ChoiceSort.sort(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ChoiceSort.sort(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Alex", names[14]);

    }

    @Test
    public void sortInsertionTest() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(InsertionSort.sort(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(InsertionSort.sort(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Peter", names[4]);
        Assertions.assertEquals("Igor", names[10]);

    }

    @Test
    public void sortShellTest() {

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ShellSort.sort(names, false)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        System.out.println(Arrays.stream(ShellSort.sort(names, true)).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Alex", names[14]);

    }

    @Test
    public void partitionTest1() {

        QuickSort<Integer> numbers = new QuickSort<>(new Integer[]{324, 432, 23, 5, 2, 3542345, 34, 12, 33, 455, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 532, 721, 841, 8743, 7830});
        System.out.println(Arrays.toString(numbers.getArray()));
        int j = numbers.partition( 0, numbers.getArray().length-1, false);
        System.out.printf("Number of elements total : %d\n", numbers.getArray().length);
        System.out.println("Left group : " + Arrays.stream(numbers.getArray()).limit(j).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        System.out.println("Right group : " + Arrays.stream(numbers.getArray()).skip(j).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        Assertions.assertEquals(22, Arrays.stream(numbers.getArray()).limit(j).count());
        Assertions.assertEquals(3, Arrays.stream(numbers.getArray()).skip(j).count());

        QuickSort<String> names = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter"});
        System.out.println(Arrays.toString(names.getArray()));
        int i = names.partition(0, names.getArray().length-1, false);
        System.out.println(Arrays.toString(names.getArray()));
        Assertions.assertEquals(10, Arrays.stream(names.getArray()).limit(i).count());
        Assertions.assertEquals(5, Arrays.stream(names.getArray()).skip(i).count());
        System.out.printf("Number of elements total : %d\n", names.getArray().length);
        System.out.println("Left group : " + Arrays.stream(names.getArray()).limit(i).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names.getArray()).limit(i).count() + " elements");
        System.out.println("Right group : " + Arrays.stream(names.getArray()).skip(i).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names.getArray()).skip(i).count() + " elements");

    }

    @Test
    public void partitionTest2() {
        QuickSort<Integer> numbers = new QuickSort<>(new Integer[]{324, 432, 23, 5, 2, 3542345, 34, 12, 33, 455, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 532, 721, 841, 8743, 7830});
        System.out.println(Arrays.toString(numbers.getArray()));
        int j = numbers.partition( 3, 9, false);
        //System.out.printf("Number of elements total : %d\n", numbers.length);
        //System.out.println("Left group : " + Arrays.stream(numbers).limit(j).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        //System.out.println("Right group : " + Arrays.stream(numbers).skip(j).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        Assertions.assertEquals(8, Arrays.stream(numbers.getArray()).limit(j).count());
        Assertions.assertEquals(numbers.getArray().length-j, Arrays.stream(numbers.getArray()).skip(j).count());

        QuickSort<String> names = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter"});
        System.out.println(Arrays.toString(names.getArray()));
        int i = names.partition(5, 11, false);
        System.out.println(Arrays.toString(names.getArray()));
        Assertions.assertEquals(10, Arrays.stream(names.getArray()).limit(i).count());
        Assertions.assertEquals(names.getArray().length-10, Arrays.stream(names.getArray()).skip(i).count());
        //System.out.printf("Number of elements total : %d\n", names.length);
        //System.out.println("Left group : " + Arrays.stream(names).limit(i).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names).limit(i).count() + " elements");
        //System.out.println("Right group : " + Arrays.stream(names).skip(i).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names).skip(i).count() + " elements");

    }

    @Test
    public void manualSortTest() {
        //QuickSort<String> names = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"});
        //Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> QuickSort.manualSort3Elements(names.length));
        QuickSort<String> names2 = new QuickSort<>(new String[]{"Vladimir", "Dmitri", "Oleg"});
        Assertions.assertNotEquals("Dmitri", names2.getArray()[0]);
        Assertions.assertNotEquals("Oleg", names2.getArray()[1]);
        names2.manualSort3Elements(0, false);
        Assertions.assertEquals("Dmitri", names2.getArray()[0]);
        Assertions.assertEquals("Oleg", names2.getArray()[1]);
        Assertions.assertEquals("Vladimir", names2.getArray()[2]);
        QuickSort<String> names3 = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"});
        names3.manualSort3Elements(0, false);
        Assertions.assertEquals("Dmitri", names3.getArray()[0]);
        Assertions.assertEquals("Oleg", names3.getArray()[1]);
        Assertions.assertEquals("Vladimir", names3.getArray()[2]);
        names3.manualSort3Elements(3, false);
        Assertions.assertEquals("Alex", names3.getArray()[3]);
        Assertions.assertEquals("Evgen", names3.getArray()[4]);
        Assertions.assertEquals("Nikolay", names3.getArray()[5]);
        names3.manualSort3Elements(0, true);
        Assertions.assertEquals("Vladimir", names3.getArray()[0]);
        Assertions.assertEquals("Oleg", names3.getArray()[1]);
        Assertions.assertEquals("Dmitri", names3.getArray()[2]);
        names3.manualSort3Elements(3, true);
        Assertions.assertEquals("Nikolay", names3.getArray()[3]);
        Assertions.assertEquals("Evgen", names3.getArray()[4]);
        Assertions.assertEquals("Alex", names3.getArray()[5]);
    }

    @Test
    public void medianOf3Test() {
        QuickSort<String> names = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"});
        Assertions.assertEquals("Igor", names.medianOf3(0, names.getArray().length-1, false));
        Assertions.assertEquals("Igor", names.getArray()[names.getArray().length-1]);

        QuickSort<String> names1 = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"});
        Assertions.assertEquals("Evgen", names1.medianOf3(3, 5, false));
        Assertions.assertEquals("Evgen", names1.getArray()[5]);

        QuickSort<Integer> numbers = new QuickSort<>(new Integer[]{44, 3, 6, 66, 89, 86, 34, 41, 10, 9, 29});
        int center = (numbers.getArray().length - 1)/2;
        Assertions.assertEquals(44, numbers.getArray()[0]);
        Assertions.assertEquals(86, numbers.getArray()[center]);
        Assertions.assertEquals(29, numbers.getArray()[numbers.getArray().length-1]);
        numbers.medianOf3(0, numbers.getArray().length-1, false);
        Assertions.assertEquals(29, numbers.getArray()[0]);
        Assertions.assertEquals(86, numbers.getArray()[center]);
        Assertions.assertEquals(44, numbers.getArray()[numbers.getArray().length-1]);
        System.out.println(Arrays.toString(numbers.getArray()));
    }

    @Test
    public void quicksortTest() {
        QuickSort<Double> numbers = new QuickSort<>(new Double[]{44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d});
        Assertions.assertFalse(numbers.getArray()[0] < numbers.getArray()[1]);
        numbers.sort(0, numbers.getArray().length-1, false);
        for (int i = 0; i < numbers.getArray().length-1; i++)
            Assertions.assertTrue(numbers.getArray()[i] < numbers.getArray()[i+1]);
        numbers.sort(0, numbers.getArray().length-1, true);
        for (int i = 0; i < numbers.getArray().length-1; i++)
            Assertions.assertTrue(numbers.getArray()[i] > numbers.getArray()[i+1]);

        QuickSort<String> names = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter"});
        Assertions.assertNotEquals("Alex", names.getArray()[0]);
        Assertions.assertNotEquals("Igor", names.getArray()[4]);
        Assertions.assertNotEquals("Peter", names.getArray()[10]);
        Assertions.assertNotEquals("Xenomorph", names.getArray()[14]);
        Assertions.assertNotEquals("Alex", names.getArray()[0]);
        names.sort(0, names.getArray().length-1, false);
        System.out.println(Arrays.stream(names.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());
        Assertions.assertEquals("Alex", names.getArray()[0]);
        Assertions.assertEquals("Igor", names.getArray()[4]);
        Assertions.assertEquals("Peter", names.getArray()[10]);
        Assertions.assertEquals("Xenomorph", names.getArray()[14]);
        Assertions.assertEquals("Alex", names.getArray()[0]);

        QuickSort<String> narr3 = new QuickSort<>(new String[]{"Oleg", "Vladimir", "Dmitri"});
        System.out.println(Arrays.stream(narr3.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());
        narr3.sort(0, narr3.getArray().length-1, false);
        System.out.println(Arrays.stream(narr3.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());

        QuickSort<String> narr2 = new QuickSort<>(new String[]{"Vladimir", "Dmitri"});
        System.out.println(Arrays.stream(narr2.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());
        narr2.sort(0, narr2.getArray().length-1, false);
        System.out.println(Arrays.stream(narr2.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());

        QuickSort<String> narr4 = new QuickSort<>(new String[]{"Oleg", "Dmitri", "Vladimir"});
        System.out.println(Arrays.stream(narr4.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());
        narr4.sort(0, narr4.getArray().length-1, true);
        System.out.println(Arrays.stream(narr4.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());

        QuickSort<String> narr5 = new QuickSort<>(new String[]{"Dmitri", "Vladimir"});
        System.out.println(Arrays.stream(narr5.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());
        narr5.sort(0, narr5.getArray().length-1, true);
        System.out.println(Arrays.stream(narr5.getArray()).reduce((s1, s2) -> s1 + " " + s2).get());

    }

    @Test
    public void nonRecursiveQuickSortTest() {

        Double[] numbers = new Double[]{44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d};
        Assertions.assertFalse(numbers[0] < numbers[1]);

        QuickSortStack<Double> qs = new QuickSortStack<>(numbers, false);

        for (int i = 0; i < numbers.length-1; i++)
            Assertions.assertTrue(qs.getSortedArray()[i] < qs.getSortedArray()[i+1]);
        qs = new QuickSortStack<>(numbers, true);
        for (int i = 0; i < numbers.length-1; i++)
            Assertions.assertTrue(qs.getSortedArray()[i] > qs.getSortedArray()[i+1]);

        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter"};
        Assertions.assertNotEquals("Alex", names[0]);
        Assertions.assertNotEquals("Igor", names[4]);
        Assertions.assertNotEquals("Peter", names[10]);
        Assertions.assertNotEquals("Xenomorph", names[14]);
        Assertions.assertNotEquals("Alex", names[0]);
        QuickSortStack<String> qss = new QuickSortStack<>(names, false);
        Assertions.assertEquals("Alex", names[0]);
        Assertions.assertEquals("Igor", names[4]);
        Assertions.assertEquals("Peter", names[10]);
        Assertions.assertEquals("Xenomorph", names[14]);
        Assertions.assertEquals("Alex", names[0]);
        qss = new QuickSortStack<>(names, true);
        Assertions.assertEquals("Xenomorph", names[0]);
        Assertions.assertEquals("Vladimir", names[1]);
        Assertions.assertEquals("Timofey", names[2]);

    }

    @Test
    public void partitionTest() {
        QuickSort<Integer> numbers = new QuickSort<>(new Integer[]{324, 432, 23, 5, 2, 3542345, 34, 12, 33, 7830, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 721, 841, 8743, 455, 532});
        System.out.println(Arrays.toString(numbers.getArray()));
        int j = numbers.partition( 0, numbers.getArray().length-1, false);
        System.out.printf("Number of elements total : %d\n", numbers.getArray().length);
        System.out.println("Left group : " + Arrays.stream(numbers.getArray()).limit(j).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        System.out.println("Right group : " + Arrays.stream(numbers.getArray()).skip(j).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        Assertions.assertEquals(18, Arrays.stream(numbers.getArray()).limit(j).count());
        Assertions.assertEquals(7, Arrays.stream(numbers.getArray()).skip(j).count());

        QuickSort<String> names = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Boris", "Peter", "Mikhael"});
        System.out.println(Arrays.toString(names.getArray()));
        int i = names.partition(0, names.getArray().length-1, false);
        System.out.println(Arrays.toString(names.getArray()));
        Assertions.assertEquals(7, Arrays.stream(names.getArray()).limit(i).count());
        Assertions.assertEquals(8, Arrays.stream(names.getArray()).skip(i).count());
        System.out.printf("Number of elements total : %d\n", names.getArray().length);
        System.out.println("Left group : " + Arrays.stream(names.getArray()).limit(i).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names.getArray()).limit(i).count() + " elements");
        System.out.println("Right group : " + Arrays.stream(names.getArray()).skip(i).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names.getArray()).skip(i).count() + " elements");

        QuickSort<Integer> numbers2 = new QuickSort<>(new Integer[]{324, 432, 23, 5, 2, 3542345, 34, 12, 33, 7830, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 721, 841, 8743, 455, 532});
        System.out.println(Arrays.toString(numbers2.getArray()));
        int j2 = numbers2.partition( 0, numbers2.getArray().length-1, true);
        System.out.printf("Number of elements total : %d\n", numbers2.getArray().length);
        System.out.println("Left group : " + Arrays.stream(numbers2.getArray()).limit(j2).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        System.out.println("Right group : " + Arrays.stream(numbers2.getArray()).skip(j2).map(Object::toString).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
        Assertions.assertEquals(9, Arrays.stream(numbers2.getArray()).limit(j2).count());
        Assertions.assertEquals(16, Arrays.stream(numbers2.getArray()).skip(j2).count());

        QuickSort<String> names2 = new QuickSort<>(new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Xenomorph", "Leonide", "Timofey", "Boris", "Peter", "Mikhael"});
        System.out.println(Arrays.toString(names2.getArray()));
        int i2 = names2.partition(0, names2.getArray().length-1, true);
        System.out.println(Arrays.toString(names2.getArray()));
        Assertions.assertEquals(12, Arrays.stream(names2.getArray()).limit(i2).count());
        Assertions.assertEquals(3, Arrays.stream(names2.getArray()).skip(i2).count());
        System.out.printf("Number of elements total : %d\n", names2.getArray().length);
        System.out.println("Left group : " + Arrays.stream(names2.getArray()).limit(i2).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names2.getArray()).limit(i2).count() + " elements");
        System.out.println("Right group : " + Arrays.stream(names2.getArray()).skip(i2).reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)) + " in total " + Arrays.stream(names2.getArray()).skip(i2).count() + " elements");

    }
}