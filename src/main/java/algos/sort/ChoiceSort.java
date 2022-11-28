package algos.sort;

import java.util.Arrays;

public class ChoiceSort {

	public static double[] sort(double[] array) {
		if (array.length == 0)
			return array;
		double[] result = new double[array.length];
		double[] workingArray = new double[array.length];
		System.arraycopy(array, 0, workingArray, 0, array.length);

		for (int y = 0; y < array.length; y++) {
			double max = Double.NEGATIVE_INFINITY;
			int maxIndex = -1;
			for (int i = 0; i < workingArray.length; i++) {

				if (workingArray[i] > max) {
					max = workingArray[i];
					maxIndex = i;
				}

			}
			
			result[y] = max;
			double[] beforeMaxArray = Arrays.copyOf(workingArray, maxIndex);
			double[] afterMaxArray = new double[array.length - maxIndex - 1];
			for (int u = maxIndex + 1; u < workingArray.length; u++) {
				int index = u - maxIndex - 1;
				afterMaxArray[index] = workingArray[u];
			}

			double[] nextWorkingArray = Arrays.copyOf(beforeMaxArray, beforeMaxArray.length + afterMaxArray.length);
			System.arraycopy(afterMaxArray, 0, nextWorkingArray, maxIndex, afterMaxArray.length);

			workingArray = nextWorkingArray;

		}

		return result;
	}
}