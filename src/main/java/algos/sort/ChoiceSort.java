package algos.sort;

import java.util.Arrays;

public class ChoiceSort {

	public static String[] sortArray(String[] array, boolean reverse) {
		for (int i = 0; i < array.length - 1; i++) {
			int extremum = i;
			for (int j = i+1; j < array.length; j++) {
				if (reverse) {
					if (array[extremum].compareTo(array[j]) < 0) {
						extremum = j;
					}
				} else {
					if (array[extremum].compareTo(array[j]) > 0) {
						extremum = j;
					}
				}
			}
			String tmp = array[i];
			array[i] = array[extremum];
			array[extremum] = tmp;
		}
		return array;
	}
	private static int min(String[] array) {
		int min = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(array[min]) > 0) min = i;
		}
		return min;
	}

	private static int max(String[] array) {
		int max = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(array[max]) < 0) max = i;
		}
		return max;
	}
}