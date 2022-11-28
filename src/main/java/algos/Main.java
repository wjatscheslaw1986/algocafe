package algos;

import algos.graph.Dijkstra;
import algos.recursion.RecursiveFunctions;
import algos.sort.ChoiceSort;
import algos.sort.QuickSort;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String... argz) {
		System.out.println("Factorial");
		int input = 9;
		System.out.println("Factorial of " + input + " is " + RecursiveFunctions.factorial(input));

		Map<String, Double> arcs = new HashMap<>();
		arcs.put("START->A", 6.0d);
		arcs.put("B->A", 3.0d);
		arcs.put("START->B", 2.0d);
		arcs.put("B->FINISH", 5.0d);
		arcs.put("A->FINISH", 1.0d);

		Dijkstra d = new Dijkstra(arcs);
		System.out.println(d.findShortestPath());

		Map<String, Double> arcs1 = new HashMap<>();
		arcs1.put("START->A", 5.0d);
		arcs1.put("START->B", 2.0d);
		arcs1.put("B->A", 8.0d);
		arcs1.put("B->D", 7.0d);
		arcs1.put("A->D", 2.0d);
		arcs1.put("A->C", 4.0d);
		arcs1.put("C->D", 6.0d);
		arcs1.put("D->FINISH", 1.0d);
		arcs1.put("C->FINISH", 3.0d);

		Dijkstra d1 = new Dijkstra(arcs1);
		System.out.println(d1.findShortestPath());

		Map<String, Double> arcs2 = new HashMap<>();
		arcs2.put("START->A", 10.0d);
		arcs2.put("B->A", 1.0d);
		arcs2.put("C->B", 1.0d);
		arcs2.put("C->FINISH", 30.0d);
		arcs2.put("A->C", 20.0d);

		Dijkstra d2 = new Dijkstra(arcs2);
		System.out.println(d2.findShortestPath());

		Map<String, Double> arcs3 = new HashMap<>();
		arcs3.put("START->A", 2.0d);
		arcs3.put("START->B", 2.0d);
		arcs3.put("B->A", 2.0d);
		arcs3.put("A->C", 2.0d);
		arcs3.put("C->B", -1.0d);
		arcs3.put("A->FINISH", 2.0d);
		arcs3.put("C->FINISH", 2.0d);

		Dijkstra d3 = new Dijkstra(arcs3);
		System.out.println(d3.findShortestPath());

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