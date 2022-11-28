package algos.recursion;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecursiveFunctionsTests {

	@Test
	public void fibbonacciMyOwn() {		
		LocalDateTime start = LocalDateTime.now();

		Assertions.assertEquals(483944808890094715L, new RecursiveFunctions.Fibbonacci().iterativeFibbonacci(4000));
		LocalDateTime finish = LocalDateTime.now();
		System.out.println("Iterative Fibbonacci from book took " + Duration.between(start, finish).toMillis() + " millis");
		start = LocalDateTime.now();
		Assertions.assertEquals(483944808890094715L, new RecursiveFunctions.Fibbonacci().recursiveFibbonacciWithMemoization(4000));
		finish = LocalDateTime.now();
		System.out.println("Recursive Fibbonacci with memoization took " + Duration.between(start, finish).toMillis() + " millis");
		start = LocalDateTime.now();
		Assertions.assertEquals(102334155L, new RecursiveFunctions.Fibbonacci().recursiveFibbonacci(40));
		finish = LocalDateTime.now();
		System.out.println("Recursive Fibbonacci took " + Duration.between(start, finish).toMillis() + " millis");


		System.out.println("Num: " + (1 << 6));
		System.out.println("Num: " + 0x0f);
	}

	@Test
	public void recursiveFibbonacciTest() {
		Assertions.assertEquals(8L, new RecursiveFunctions.Fibbonacci().recursiveFibbonacci(6));
		LongStream fibStream = new RecursiveFunctions.FibbonacciStream().stream();
		fibStream.limit(6).forEach(System.out::println);
	}
}