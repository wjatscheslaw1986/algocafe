package algos.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

public class RecursiveFunctions {

    public static int factorial(int n) {
        if (n <= 1) return n;
        return n-- * factorial(n);
    }

    public static class Fibbonacci {
        private final Map<Long, Long> memo;

        public Fibbonacci() {
            this.memo = new HashMap<>(Map.of(0L, 0L, 1L, 1L));
        }

        public long recursiveFibbonacciWithMemoization(long n) {
            if (!memo.containsKey(n))
                memo.put(n, recursiveFibbonacciWithMemoization(n - 1L) + recursiveFibbonacciWithMemoization(n - 2L));
            return memo.get(n);
        }

        public long iterativeFibbonacci(long n) {
            long next = 1, current = 0, previous;
            for (int i = 0; i < n - 1; i++) {
                previous = current;
                current = next;
                next = current + previous;
            }
            return next;
        }

        public long recursiveFibbonacci(long n) {
            if (n < 2) return n;
            return recursiveFibbonacci(n - 1) + recursiveFibbonacci(n - 2);
        }

    }

    public static class FibbonacciStream {
        long next = 1, current = 0, previous;
        public LongStream stream() {
            return LongStream.generate(() -> {
                previous = current;
                current = next;
                next = current + previous;
                return previous;
            });
        }
    }

}