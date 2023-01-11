// MODIFIED BY VIACHESLAV MIKHAILOV
// From Classic Computer Science Problems in Java
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
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

        public long iterativeFibbonacci(long n) {
            long next = 1, current = 0, previous;
            for (int i = 0; i < n - 1; i++) {
                previous = current;
                current = next;
                next = current + previous;
            }
            return next;
        }

        public long recursiveFibbonacciWithMemoization(long n) {
            if (!memo.containsKey(n))
                memo.put(n, recursiveFibbonacciWithMemoization(n - 1L) + recursiveFibbonacciWithMemoization(n - 2L));
            return memo.get(n);
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

    public static int triangleNumberRecursive(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        else return n + triangleNumberRecursive(n-1);
    }

    public static int triangleNumber(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int result = 0;
        for (; n > 0; n--) result = result + n;
        return result;
    }

}