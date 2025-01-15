/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SortTests {

	@Test
	public void sortBubbleTest() {

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph" };
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

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph" };
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

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph" };
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

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" };
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

		QuickSort<? extends Comparable<?>> numbers = new QuickSort<>(new Integer[] { 324, 432, 23, 5, 2, 3542345, 34,
				12, 33, 455, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 532, 721, 841, 8743, 7830 });
		System.out.println(Arrays.toString(numbers.getArray()));
		int j = numbers.partition(numbers.getArray(), 0, numbers.getArray().length - 1, false);
		System.out.printf("Number of elements total : %d\n", numbers.getArray().length);
		System.out.println("Left group : " + Arrays.stream(numbers.getArray()).limit(j).map(Object::toString).reduce("",
				(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
		System.out.println("Right group : " + Arrays.stream(numbers.getArray()).skip(j).map(Object::toString).reduce("",
				(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
		Assertions.assertEquals(j, Arrays.stream(numbers.getArray()).limit(j).count());
		Assertions.assertEquals(numbers.getArray().length - j, Arrays.stream(numbers.getArray()).skip(j).count());

		QuickSort<? extends Comparable<?>> names = new QuickSort<>(
				new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
						"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" });
		System.out.println(Arrays.toString(names.getArray()));
		int i = names.partition(names.getArray(), 0, names.getArray().length - 1, false);
		System.out.println(Arrays.toString(names.getArray()));
		Assertions.assertEquals(i, Arrays.stream(names.getArray()).limit(i).count());
		Assertions.assertEquals(names.getArray().length - i, Arrays.stream(names.getArray()).skip(i).count());
		System.out.printf("Number of elements total : %d\n", names.getArray().length);
		System.out.println("Left group : "
				+ Arrays.stream(names.getArray()).limit(i).map(Object::toString).reduce("",
						(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2))
				+ " in total " + Arrays.stream(names.getArray()).limit(i).count() + " elements");
		System.out.println("Right group : "
				+ Arrays.stream(names.getArray()).skip(i).map(Object::toString).reduce("",
						(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2))
				+ " in total " + Arrays.stream(names.getArray()).skip(i).count() + " elements");

	}

	@Test
	public void partitionTest2() {
		QuickSort<? extends Comparable<?>> numbers = new QuickSort<>(new Integer[] { 324, 432, 23, 5, 2, 3542345, 34,
				12, 33, 455, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 532, 721, 841, 8743, 7830 });
		System.out.println(Arrays.toString(numbers.getArray()));
		int j = numbers.partition(numbers.getArray(), 3, 9, false);
		Assertions.assertEquals(j, Arrays.stream(numbers.getArray()).limit(j).count());
		Assertions.assertEquals(numbers.getArray().length - j, Arrays.stream(numbers.getArray()).skip(j).count());

		QuickSort<? extends Comparable<?>> names = new QuickSort<>(
				new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
						"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" });
		System.out.println(Arrays.toString(names.getArray()));
		int i = names.partition(names.getArray(), 5, 11, false);
		System.out.println(Arrays.toString(names.getArray()));
		Assertions.assertEquals(i, Arrays.stream(names.getArray()).limit(i).count());
		Assertions.assertEquals(names.getArray().length - i, Arrays.stream(names.getArray()).skip(i).count());
	}

	@Test
	public void manualSortTest() {
		QuickSort<? extends Comparable<?>> names2 = new QuickSort<>(new String[] { "Vladimir", "Dmitri", "Oleg" });
		QuickSort.manualSort3Elements(names2.getArray(), 0, false);
		Assertions.assertEquals("Dmitri", names2.getArray()[0]);
		Assertions.assertEquals("Oleg", names2.getArray()[1]);
		Assertions.assertEquals("Vladimir", names2.getArray()[2]);
	}

	@Test
	public void medianOf3Test() {
		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph" };
		Assertions.assertEquals("Igor", QuickSort.medianOf3(names, 0, names.length - 1, false));
		Assertions.assertEquals("Igor", names[names.length - 1]);

		QuickSort<? extends Comparable<?>> names1 = new QuickSort<>(
				new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
						"Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph" });
		names1.sort(0, names1.getArray().length - 1, false);
		Assertions.assertEquals("Igor", QuickSort.medianOf3(names1.getArray(), 3, 5, false));
		Assertions.assertEquals("Igor", names1.getArray()[5]);

		QuickSort<? extends Comparable<?>> numbers = new QuickSort<>(
				new Integer[] { 44, 3, 6, 66, 89, 86, 34, 41, 10, 9, 29 });
		int center = (numbers.getArray().length - 1) / 2;
		numbers.sort(0, numbers.getArray().length - 1, false);
		numbers.medianOf3(numbers.getArray(), 0, numbers.getArray().length - 1, false);
		Assertions.assertEquals(3, numbers.getArray()[0]);
		Assertions.assertEquals(89, numbers.getArray()[center]);
		Assertions.assertEquals(34, numbers.getArray()[numbers.getArray().length - 1]); // this is the median, moved to
																						// the very left, because this
																						// is where we take it from in
																						// ascending quick sorting
		System.out.println(Arrays.toString(numbers.getArray()));
	}

	@Test
	public void quicksortTest() {
		QuickSort<? extends Comparable<?>> numbers = new QuickSort<>(
				new Double[] { 44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d });
		// At this point, values of the array must be shuffled
		numbers.sort(0, numbers.getArray().length - 1, false);
		for (int i = 0; i < numbers.getArray().length - 1; i++)
			Assertions.assertTrue((Double) numbers.getArray()[i] < (Double) numbers.getArray()[i + 1]);
		numbers.sort(0, numbers.getArray().length - 1, true);
		for (int i = 0; i < numbers.getArray().length - 1; i++)
			Assertions.assertTrue((Double) numbers.getArray()[i] > (Double) numbers.getArray()[i + 1]);

		QuickSort<? extends Comparable<?>> names = new QuickSort<>(
				new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
						"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" });
		// At this point, values of the array must be shuffled
		names.sort(0, names.getArray().length - 1, false);
		System.out
				.println(Arrays.stream(names.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		Assertions.assertEquals("Alex", names.getArray()[0].toString());
		Assertions.assertEquals("Igor", names.getArray()[4].toString());
		Assertions.assertEquals("Peter", names.getArray()[10].toString());
		Assertions.assertEquals("Xenomorph", names.getArray()[14].toString());
		Assertions.assertEquals("Alex", names.getArray()[0].toString());

		QuickSort<? extends Comparable<?>> narr3 = new QuickSort<>(new String[] { "Oleg", "Vladimir", "Dmitri" });
		System.out
				.println(Arrays.stream(narr3.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr3.sort(0, narr3.getArray().length - 1, false);
		System.out
				.println(Arrays.stream(narr3.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

		QuickSort<? extends Comparable<?>> narr2 = new QuickSort<>(new String[] { "Vladimir", "Dmitri" });
		System.out
				.println(Arrays.stream(narr2.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr2.sort(0, narr2.getArray().length - 1, false);
		System.out
				.println(Arrays.stream(narr2.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

		QuickSort<? extends Comparable<?>> narr4 = new QuickSort<>(new String[] { "Oleg", "Dmitri", "Vladimir" });
		System.out
				.println(Arrays.stream(narr4.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr4.sort(0, narr4.getArray().length - 1, true);
		System.out
				.println(Arrays.stream(narr4.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

		QuickSort<? extends Comparable<?>> narr5 = new QuickSort<>(new String[] { "Dmitri", "Vladimir" });
		System.out
				.println(Arrays.stream(narr5.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr5.sort(0, narr5.getArray().length - 1, true);
		System.out
				.println(Arrays.stream(narr5.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

	}

	@Test
	public void quicksort3WayTest() {
		QuickSort<? extends Comparable<?>> numbers = new QuickSort<>(
				new Double[] { 44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d });
		numbers.sort3way(0, numbers.getArray().length - 1, false);
		for (int i = 0; i < numbers.getArray().length - 1; i++)
			Assertions.assertTrue(
					Double.compare((Double) numbers.getArray()[i], (Double) numbers.getArray()[i + 1]) <= 0);
		numbers.sort3way(0, numbers.getArray().length - 1, true);
		for (int i = 0; i < numbers.getArray().length - 1; i++)
			Assertions.assertTrue(
					Double.compare((Double) numbers.getArray()[i], (Double) numbers.getArray()[i + 1]) >= 0);

		QuickSort<? extends Comparable<?>> names = new QuickSort<>(
				new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
						"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" });
		names.sort3way(0, names.getArray().length - 1, false);
		System.out
				.println(Arrays.stream(names.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		Assertions.assertEquals("Alex", names.getArray()[0]);
		Assertions.assertEquals("Igor", names.getArray()[4]);
		Assertions.assertEquals("Peter", names.getArray()[10]);
		Assertions.assertEquals("Xenomorph", names.getArray()[14]);
		Assertions.assertEquals("Alex", names.getArray()[0]);

		QuickSort<? extends Comparable<?>> narr3 = new QuickSort<>(new String[] { "Oleg", "Vladimir", "Dmitri" });
		System.out
				.println(Arrays.stream(narr3.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr3.sort3way(0, narr3.getArray().length - 1, false);
		System.out
				.println(Arrays.stream(narr3.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

		QuickSort<? extends Comparable<?>> narr2 = new QuickSort<>(new String[] { "Vladimir", "Dmitri" });
		System.out
				.println(Arrays.stream(narr2.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr2.sort3way(0, narr2.getArray().length - 1, false);
		System.out
				.println(Arrays.stream(narr2.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

		QuickSort<? extends Comparable<?>> narr4 = new QuickSort<>(new String[] { "Oleg", "Dmitri", "Vladimir" });
		System.out
				.println(Arrays.stream(narr4.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr4.sort3way(0, narr4.getArray().length - 1, true);
		System.out
				.println(Arrays.stream(narr4.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

		QuickSort<? extends Comparable<?>> narr5 = new QuickSort<>(new String[] { "Dmitri", "Vladimir" });
		System.out
				.println(Arrays.stream(narr5.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());
		narr5.sort3way(0, narr5.getArray().length - 1, true);
		System.out
				.println(Arrays.stream(narr5.getArray()).map(Object::toString).reduce((s1, s2) -> s1 + " " + s2).get());

	}

	@Test
	public void nonRecursiveQuickSortTest() {

		Double[] numbers = new Double[] { 44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d };
		Assertions.assertFalse(numbers[0] < numbers[1]);

		StackQuickSort<Double> qs = new StackQuickSort<>(numbers, false);

		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] < numbers[i + 1]);
		qs = new StackQuickSort<>(numbers, true);
		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] > numbers[i + 1]);

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" };
		Assertions.assertNotEquals("Alex", names[0]);
		Assertions.assertNotEquals("Igor", names[4]);
		Assertions.assertNotEquals("Peter", names[10]);
		Assertions.assertNotEquals("Xenomorph", names[14]);
		Assertions.assertNotEquals("Alex", names[0]);
		StackQuickSort<String> qss = new StackQuickSort<>(names, false);
		Assertions.assertEquals("Alex", names[0]);
		Assertions.assertEquals("Igor", names[4]);
		Assertions.assertEquals("Peter", names[10]);
		Assertions.assertEquals("Xenomorph", names[14]);
		Assertions.assertEquals("Alex", names[0]);
		qss = new StackQuickSort<>(names, true);
		Assertions.assertEquals("Xenomorph", names[0]);
		Assertions.assertEquals("Vladimir", names[1]);
		Assertions.assertEquals("Timofey", names[2]);

	}

	@Test
	public void partitionTest() {
		QuickSort<? extends Comparable<?>> numbers = new QuickSort<>(new Integer[] { 324, 432, 23, 5, 2, 3542345, 34,
				12, 33, 7830, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 721, 841, 8743, 455, 532 });
		System.out.println(Arrays.toString(numbers.getArray()));
		int j = QuickSort.partition(numbers.getArray(), 0, numbers.getArray().length - 1, false);
		System.out.printf("Number of elements total : %d\n", numbers.getArray().length);
		System.out.println("Left group : " + Arrays.stream(numbers.getArray()).limit(j).map(Object::toString).reduce("",
				(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
		System.out.println("Right group : " + Arrays.stream(numbers.getArray()).skip(j).map(Object::toString).reduce("",
				(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
		Assertions.assertEquals(j, Arrays.stream(numbers.getArray()).limit(j).count());
		Assertions.assertEquals(numbers.getArray().length - j, Arrays.stream(numbers.getArray()).skip(j).count());

		QuickSort<? extends Comparable<?>> names = new QuickSort<>(
				new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
						"Konstantine", "Xenomorph", "Leonide", "Timofey", "Boris", "Peter", "Mikhael" });
		System.out.println(Arrays.toString(names.getArray()));
		int i = QuickSort.partition(names.getArray(), 0, names.getArray().length - 1, false);
		System.out.println(Arrays.toString(names.getArray()));
		Assertions.assertEquals(i, Arrays.stream(names.getArray()).map(Object::toString).limit(i).count());
		Assertions.assertEquals(names.getArray().length - i,
				Arrays.stream(names.getArray()).map(Object::toString).skip(i).count());
		System.out.printf("Number of elements total : %d\n", names.getArray().length);
		System.out.println("Left group : "
				+ Arrays.stream(names.getArray()).map(Object::toString).limit(i).reduce("",
						(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2))
				+ " in total " + Arrays.stream(names.getArray()).limit(i).count() + " elements");
		System.out.println("Right group : "
				+ Arrays.stream(names.getArray()).map(Object::toString).skip(i).reduce("",
						(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2))
				+ " in total " + Arrays.stream(names.getArray()).skip(i).count() + " elements");

		QuickSort<? extends Comparable<?>> numbers2 = new QuickSort<>(new Integer[] { 324, 432, 23, 5, 2, 3542345, 34,
				12, 33, 7830, 6, 12, 1, 0, 56, 89, 88, 665, 55, 4, 721, 841, 8743, 455, 532 });
		System.out.println(Arrays.toString(numbers2.getArray()));
		int j2 = QuickSort.partition(numbers2.getArray(), 0, numbers2.getArray().length - 1, true);
		System.out.printf("Number of elements total : %d\n", numbers2.getArray().length);
		System.out.println("Left group : " + Arrays.stream(numbers2.getArray()).limit(j2).map(Object::toString)
				.reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
		System.out.println("Right group : " + Arrays.stream(numbers2.getArray()).skip(j2).map(Object::toString)
				.reduce("", (String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2)));
		Assertions.assertEquals(j2, Arrays.stream(numbers2.getArray()).map(Object::toString).limit(j2).count());
		Assertions.assertEquals(numbers.getArray().length - j2,
				Arrays.stream(numbers2.getArray()).map(Object::toString).skip(j2).count());

		QuickSort<? extends Comparable<?>> names2 = new QuickSort<>(
				new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
						"Konstantine", "Xenomorph", "Leonide", "Timofey", "Boris", "Peter", "Mikhael" });
		System.out.println(Arrays.toString(names2.getArray()));
		int i2 = QuickSort.partition(names2.getArray(), 0, names2.getArray().length - 1, true);
		System.out.println(Arrays.toString(names2.getArray()));
		Assertions.assertEquals(i2, Arrays.stream(names2.getArray()).limit(i2).count());
		Assertions.assertEquals(names2.getArray().length - i2, Arrays.stream(names2.getArray()).skip(i2).count());
		System.out.printf("Number of elements total : %d\n", names2.getArray().length);
		System.out.println("Left group : "
				+ Arrays.stream(names2.getArray()).map(Object::toString).limit(i2).reduce("",
						(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2))
				+ " in total " + Arrays.stream(names2.getArray()).limit(i2).count() + " elements");
		System.out.println("Right group : "
				+ Arrays.stream(names2.getArray()).map(Object::toString).skip(i2).reduce("",
						(String s1, String s2) -> " ".concat(s1).concat(" ").concat(s2))
				+ " in total " + Arrays.stream(names2.getArray()).skip(i2).count() + " elements");

	}

	@Test
	public void mergeSortTest() {
		Double[] numbers = new Double[] { 44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d };
		Assertions.assertFalse(numbers[0] < numbers[1]);

		MergeSort<Double> ms = new MergeSort<>(numbers, false);

		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] < numbers[i + 1]);
		ms = new MergeSort<>(numbers, true);
		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] > numbers[i + 1]);

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" };
		Assertions.assertNotEquals("Alex", names[0]);
		Assertions.assertNotEquals("Igor", names[4]);
		Assertions.assertNotEquals("Peter", names[10]);
		Assertions.assertNotEquals("Xenomorph", names[14]);
		Assertions.assertNotEquals("Nikolay", names[8]);
		MergeSort<String> qss = new MergeSort<>(names, false);
		Assertions.assertEquals("Alex", names[0]);
		Assertions.assertEquals("Igor", names[4]);
		Assertions.assertEquals("Peter", names[10]);
		Assertions.assertEquals("Xenomorph", names[14]);
		Assertions.assertEquals("Nikolay", names[8]);
		qss = new MergeSort<>(names, true);
		Assertions.assertEquals("Xenomorph", names[0]);
		Assertions.assertEquals("Vladimir", names[1]);
		Assertions.assertEquals("Timofey", names[2]);
	}

	@Test
	public void nonRecursiveMergeSortTest() {
		Double[] numbers = new Double[] { 44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d };
		// Double[] numbers = new Double[]{44d, 3d, 89d, 66d, 6d};
		Assertions.assertFalse(numbers[0] < numbers[1]);

		LoopMergeSort<Double> ms = new LoopMergeSort<>(numbers, false);

		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] < numbers[i + 1]);
		ms = new LoopMergeSort<>(numbers, true);
		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] > numbers[i + 1]);

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" };
		Assertions.assertNotEquals("Alex", names[0]);
		Assertions.assertNotEquals("Igor", names[4]);
		Assertions.assertNotEquals("Peter", names[10]);
		Assertions.assertNotEquals("Xenomorph", names[14]);
		Assertions.assertNotEquals("Alex", names[0]);
		LoopMergeSort<String> qss = new LoopMergeSort<>(names, false);
		Assertions.assertEquals("Alex", names[0]);
		Assertions.assertEquals("Igor", names[4]);
		Assertions.assertEquals("Peter", names[10]);
		Assertions.assertEquals("Xenomorph", names[14]);
		Assertions.assertEquals("Alex", names[0]);
		qss = new LoopMergeSort<>(names, true);
		Assertions.assertEquals("Xenomorph", names[0]);
		Assertions.assertEquals("Vladimir", names[1]);
		Assertions.assertEquals("Timofey", names[2]);
	}

	@Test
	public void nonRecursiveParallelMergeSortTest() {
		Double[] numbers = new Double[] { 44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d };
		// Double[] numbers = new Double[]{44d, 3d, 89d, 66d, 6d};
		Assertions.assertFalse(numbers[0] < numbers[1]);

		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		LoopMergeSort<Double> ms = new LoopMergeSort<>(numbers, false, pool);
		Assertions.assertEquals(0, pool.getActiveCount());

		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] < numbers[i + 1]);
		pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		ms = new LoopMergeSort<>(numbers, true, pool);
		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] > numbers[i + 1]);

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" };
		Assertions.assertNotEquals("Alex", names[0]);
		Assertions.assertNotEquals("Igor", names[4]);
		Assertions.assertNotEquals("Peter", names[10]);
		Assertions.assertNotEquals("Xenomorph", names[14]);
		Assertions.assertNotEquals("Alex", names[0]);
		pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		new LoopMergeSort<>(names, false, pool);
		Assertions.assertEquals(0, pool.getActiveCount());
		Assertions.assertEquals("Alex", names[0]);
		Assertions.assertEquals("Igor", names[4]);
		Assertions.assertEquals("Peter", names[10]);
		Assertions.assertEquals("Xenomorph", names[14]);
		Assertions.assertEquals("Alex", names[0]);
		pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		new LoopMergeSort<>(names, true, pool);
		Assertions.assertEquals(0, pool.getActiveCount());
		Assertions.assertEquals("Xenomorph", names[0]);
		Assertions.assertEquals("Vladimir", names[1]);
		Assertions.assertEquals("Timofey", names[2]);
	}

	@Test
	void compareParallelMergeSortWithParallelTimSortAndSomeOthersTest() {
		int linesScanned = 0;
		
		//Uncomment these lines to download a book
//		try {
//			linesScanned = downloadWarAndPeaceByLeoTolstoyToFile();
//			System.out.println("Downloaded the book of " + linesScanned + " words.");
//		} catch (IOException e) {
//			Assertions.fail("Failed to download e-book.\n" + e.getLocalizedMessage());
//		}

		ArrayList<String> words = new ArrayList<String>(linesScanned);
		try {
			int linesScannedFromFile = readWarAndPeaceByLeoTolstoyFromFileIntoArrayList(words);
//			Assertions.assertEquals(linesScanned, linesScannedFromFile);
		} catch (IOException e) {
			Assertions.fail("Failed to read e-book from disk.\n" + e.getLocalizedMessage());
		}

		// make sure it is not sorted first
		boolean sorted = true;
		String lastWord = null;
		for (String word : words) {
			if (!sorted)
				break;
			if (Objects.nonNull(lastWord) && lastWord.compareTo(word) > 0) {
				sorted = false;
				break;
			}
			lastWord = word;
		}
		boolean finSorted = sorted;
		Assertions.assertFalse(() -> finSorted, () -> "Array must not be sorted at this step!");

		String[] array1 = words.toArray(new String[words.size()]);
		String[] array2 = words.toArray(new String[words.size()]);
		String[] array3 = words.toArray(new String[words.size()]);
		String[] array4 = words.toArray(new String[words.size()]);
		String[] array5 = words.toArray(new String[words.size()]);
		String[] array6 = words.toArray(new String[words.size()]);
		String[] array7 = words.toArray(new String[words.size()]);
		String[] array8 = words.toArray(new String[words.size()]);
		String[] array9 = words.toArray(new String[words.size()]);

		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(32);
		long start = System.nanoTime();
		Arrays.parallelSort(array8);
		long end = System.nanoTime();
		System.out.println("(warming up) Parallel TimSort sort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		LoopMergeSort<String> ms = new LoopMergeSort<>(array1, false, pool);
		end = System.nanoTime();
		System.out.println("(warming up) Parallel loop merge sort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		Arrays.sort(array2);
		end = System.nanoTime();
		System.out.println("(warming up) Legasy merge sort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		LoopMergeSort<String> ms1 = new LoopMergeSort<>(array3, false);
		end = System.nanoTime();
		System.out.println("Loop merge sort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		Arrays.parallelSort(array4);
		end = System.nanoTime();
		System.out.println("Parallel TimSort sort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		StackQuickSort<String> qs = new StackQuickSort<>(array6, false);
		end = System.nanoTime();
		System.out.println("Non-Recursive QuickSort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		HeapSort.<String>sort(array5, false);
		end = System.nanoTime();
		System.out.println("HeapSort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		LoopMergeSort<String> ms2 = new LoopMergeSort<>(array7, false, pool);
		end = System.nanoTime();
		System.out.println("Parallel loop merge sort took " + ((end - start) / 1000000) + " milliseconds");
		start = System.nanoTime();
		Arrays.sort(array9);
		end = System.nanoTime();
		System.out.println("Legasy merge sort took " + ((end - start) / 1000000) + " milliseconds");

		Assertions.assertAll("Sorted arrays are actually sorted in ascending order", List.of(() -> {
			for (int n = 1; n < array1.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array1[finN].compareTo(array1[finN - 1]) >= 0,
						() -> "array1 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array2.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array2[finN].compareTo(array2[finN - 1]) >= 0,
						() -> "array2 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array3.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array3[finN].compareTo(array3[finN - 1]) >= 0,
						() -> "array3 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array4.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array4[finN].compareTo(array4[finN - 1]) >= 0,
						() -> "array4 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array5.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array5[finN].compareTo(array5[finN - 1]) >= 0,
						() -> "array5 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array6.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array6[finN].compareTo(array6[finN - 1]) >= 0,
						() -> "array6 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array7.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array7[finN].compareTo(array7[finN - 1]) >= 0,
						() -> "array7 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array8.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array8[finN].compareTo(array8[finN - 1]) >= 0,
						() -> "array8 is not sorted!");
			}
		}, () -> {
			for (int n = 1; n < array9.length; n++) {
				int finN = n;
				Assertions.assertTrue(() -> array9[finN].compareTo(array9[finN - 1]) >= 0,
						() -> "array9 is not sorted!");
			}
		}));

		Assertions.assertAll("Sorted arrays equality assertions",
				List.of(() -> Assertions.assertArrayEquals(array2, array1, "Arrays 2 and 1 aren't equal"),
						() -> Assertions.assertArrayEquals(array2, array3, "Arrays 2 and 3 aren't equal"),
						() -> Assertions.assertArrayEquals(array2, array4, "Arrays 2 and 4 aren't equal"),
						() -> Assertions.assertArrayEquals(array2, array5, "Arrays 2 and 5 aren't equal"),
						() -> Assertions.assertArrayEquals(array2, array6, "Arrays 2 and 6 aren't equal"),
						() -> Assertions.assertArrayEquals(array2, array7, "Arrays 2 and 7 aren't equal"),
						() -> Assertions.assertArrayEquals(array2, array8, "Arrays 2 and 8 aren't equal"),
						() -> Assertions.assertArrayEquals(array2, array9, "Arrays 2 and 9 aren't equal")
						));
	}

	private int downloadWarAndPeaceByLeoTolstoyToFile() throws IOException {
		int linesScanned = 0;
		try (Scanner scanner = new Scanner(new URL("https://gutenberg.org/cache/epub/2600/pg2600.txt").openStream());
				PrintWriter pw = new PrintWriter(new FileWriter(Paths.get("/tmp/war_and_peace.txt").toFile()))) {
			while (scanner.hasNext()) {
				pw.write(scanner.next() + " \n");
				linesScanned++;
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
		return linesScanned;
	}

	private int readWarAndPeaceByLeoTolstoyFromFileIntoArrayList(ArrayList<String> words) throws IOException {
		words.clear(); // <-- make sure it is empty
		int linesScanned = 0;
		try (Scanner scanner = new Scanner(new FileInputStream(new File("/tmp/war_and_peace.txt")))) {
			while (scanner.hasNext()) {
				words.add(scanner.next());
				linesScanned++;
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
		return linesScanned;
	}

	@Test
	public void heapSortTest() {

		Double[] empty = new Double[] {};
		// Assertions.assertFalse(numbers[0] < numbers[1]);
		HeapSort.sort(empty, false);
		for (int i = 0; i < empty.length - 1; i++)
			Assertions.assertTrue(empty[i] <= empty[i + 1]);
		HeapSort.sort(empty, true);
		for (int i = 0; i < empty.length - 1; i++)
			Assertions.assertTrue(empty[i] >= empty[i + 1]);

		Double[] number = new Double[] { 13d };
		// Assertions.assertFalse(numbers[0] < numbers[1]);
		HeapSort.sort(number, false);
		for (int i = 0; i < number.length - 1; i++)
			Assertions.assertTrue(number[i] <= number[i + 1]);
		HeapSort.sort(number, true);
		for (int i = 0; i < number.length - 1; i++)
			Assertions.assertTrue(number[i] >= number[i + 1]);

		Double[] twoNumbers = new Double[] { 13d, 44d };
		// Assertions.assertFalse(numbers[0] < numbers[1]);
		HeapSort.sort(twoNumbers, false);
		for (int i = 0; i < twoNumbers.length - 1; i++)
			Assertions.assertTrue(twoNumbers[i] <= twoNumbers[i + 1]);
		HeapSort.sort(twoNumbers, true);
		for (int i = 0; i < twoNumbers.length - 1; i++)
			Assertions.assertTrue(twoNumbers[i] >= twoNumbers[i + 1]);

		Double[] twoNumbersRev = new Double[] { 44d, 13d };
		// Assertions.assertFalse(numbers[0] < numbers[1]);
		HeapSort.sort(twoNumbersRev, false);
		for (int i = 0; i < twoNumbersRev.length - 1; i++)
			Assertions.assertTrue(twoNumbersRev[i] <= twoNumbersRev[i + 1]);
		HeapSort.sort(twoNumbersRev, true);
		for (int i = 0; i < twoNumbersRev.length - 1; i++)
			Assertions.assertTrue(twoNumbersRev[i] >= twoNumbersRev[i + 1]);

		Double[] twoNumbersEq = new Double[] { 44d, 44d };
		// Assertions.assertFalse(numbers[0] < numbers[1]);
		HeapSort.sort(twoNumbersEq, false);
		for (int i = 0; i < twoNumbersEq.length - 1; i++)
			Assertions.assertTrue(twoNumbersEq[i] <= twoNumbersEq[i + 1]);
		HeapSort.sort(twoNumbersEq, true);
		for (int i = 0; i < twoNumbersEq.length - 1; i++)
			Assertions.assertTrue(twoNumbersEq[i] >= twoNumbersEq[i + 1]);

		Double[] numbers = new Double[] { 13d, 44d, 3d, 6d, 66d, 89d, 86d, 34d, 41d, 10d, 9d, 29d };
		// Assertions.assertFalse(numbers[0] < numbers[1]);
		HeapSort.sort(numbers, false);
		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] <= numbers[i + 1]);
		HeapSort.sort(numbers, true);
		for (int i = 0; i < numbers.length - 1; i++)
			Assertions.assertTrue(numbers[i] >= numbers[i + 1]);

		int theMany = 1_999_999;
		Double[] manyNumbers = new Double[theMany];
		for (int i = 0; i < theMany; i++) {
			manyNumbers[i] = 1000d * ThreadLocalRandom.current().nextDouble(.0001d, 1_000.0d);
		}
		HeapSort.sort(manyNumbers, false);
		for (int i = 0; i < manyNumbers.length - 1; i++)
			Assertions.assertTrue(manyNumbers[i] <= manyNumbers[i + 1]);
		HeapSort.sort(manyNumbers, true);
		for (int i = 0; i < manyNumbers.length - 1; i++)
			Assertions.assertTrue(manyNumbers[i] >= manyNumbers[i + 1]);

		String[] names = new String[] { "Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor",
				"Konstantine", "Xenomorph", "Leonide", "Timofey", "Mikhael", "Boris", "Peter" };
		Assertions.assertNotEquals("Alex", names[0]);
		Assertions.assertNotEquals("Igor", names[4]);
		Assertions.assertNotEquals("Peter", names[10]);
		Assertions.assertNotEquals("Xenomorph", names[14]);
		Assertions.assertNotEquals("Nikolay", names[8]);
		HeapSort.sort(names, false);
		Assertions.assertEquals("Alex", names[0]);
		Assertions.assertEquals("Igor", names[4]);
		Assertions.assertEquals("Peter", names[10]);
		Assertions.assertEquals("Xenomorph", names[14]);
		Assertions.assertEquals("Nikolay", names[8]);
		HeapSort.sort(names, true);
		Assertions.assertEquals("Xenomorph", names[0]);
		Assertions.assertEquals("Vladimir", names[1]);
		Assertions.assertEquals("Timofey", names[2]);
	}
}