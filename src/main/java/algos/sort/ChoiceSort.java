package algos.sort;

public class ChoiceSort {

	public static String[] sort(String[] array, boolean reverse) {
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
}