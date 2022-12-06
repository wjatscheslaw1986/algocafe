package algos;

import algos.recursion.RecursiveFunctions;
import algos.sort.ChoiceSort;
import algos.sort.QuickSort;

public class Main {

	public static void main(String... argz) {
		System.out.println("Factorial");
		int input = 9;
		System.out.println("Factorial of " + input + " is " + RecursiveFunctions.factorial(input));

		System.out.println("Now quicksort:");
		double[] inArray = new double[] { 34565.65d, 5.0d, 2.6d, 7.9d, Double.POSITIVE_INFINITY, 267.8d };
		double[] outArray = QuickSort.quicksort(inArray);

		for (int i = 0; i < outArray.length; i++) {
			System.out.println("->" + outArray[i]);
		}

		System.out.println("Now choice sort:");
		inArray = new double[] { 34565.65d, 5.0d, 2.6d, 7.9d, Double.POSITIVE_INFINITY, 267.8d };
		outArray = ChoiceSort.sort(inArray);

		for (int i = 0; i < outArray.length; i++) {
			System.out.println("->" + outArray[i]);
		}

		System.out.print("Ok, now Fibbonacci recursive with memoization: ");
		System.out.println(new RecursiveFunctions.Fibbonacci().recursiveFibbonacciWithMemoization(4941));
		System.out.print("Iterative (non-recursive) Fibbonacci: ");
		System.out.println(new RecursiveFunctions.Fibbonacci().iterativeFibbonacci(4941));

	}
}