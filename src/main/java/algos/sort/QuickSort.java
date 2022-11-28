package algos.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class QuickSort {
	
	public static double[] quicksort(double[] array) {
		if (array.length < 2) return array;
		else {
			double pivot = array[0];
			double[] pivotArr = new double[1];
			pivotArr[0] = pivot;
			List<Double> lesser = new LinkedList<>();
			List<Double> greater = new LinkedList<>();
			for (int i = 1; i < array.length; i++) {
				if (array[i] <= pivot) lesser.add(array[i]);
				if (array[i] > pivot) greater.add(array[i]);
			}
			double[] lesserArr = quicksort(lesser.stream().mapToDouble(e->e).toArray());
			double[] greaterArr = quicksort(greater.stream().mapToDouble(r->r).toArray());
			
			var lesserAndPivotArray = Arrays.copyOf(lesserArr, lesserArr.length + 1);
			System.arraycopy(pivotArr, 0, lesserAndPivotArray, lesserArr.length, 1);
			
			var fullArray = Arrays.copyOf(lesserAndPivotArray, lesserAndPivotArray.length + greaterArr.length);
			System.arraycopy(greaterArr, 0, fullArray, lesserAndPivotArray.length, greaterArr.length);
			
			return fullArray;
		}
		
	}

}
