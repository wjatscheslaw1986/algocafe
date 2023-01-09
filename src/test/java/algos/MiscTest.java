/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class MiscTest {

    public static class PiCalc {
        public static double pi(long digits) {
            double pi = .0d, base = 4.0d, divisor = 1.0d;
            boolean sign = false;
            for (long j = 0; j < digits; j++) {
                pi = pi + (sign ? (-1.0d) : 1.0d) * (base / divisor);
                sign = !sign;
                divisor += 2.0d;
            }
            return pi;
        }
    }

    @Test
    public void testPi() {
        double pi = PiCalc.pi(Integer.MAX_VALUE);
        Assertions.assertNotEquals(Math.PI, pi, 0.0000000003);
        Assertions.assertEquals(Math.PI, pi, 0.000000001);
        pi = PiCalc.pi((long) 2L * Integer.MAX_VALUE);
        Assertions.assertEquals(Math.PI, pi, 0.0000000003);
    }

    @Test
    public void charTest() {
        char letter = 65;
        System.out.println(Character.valueOf(letter).toString());
    }

    @Test
    public void isInteger() {
        double n = 3.3;
        Assertions.assertNotEquals(0, n % 1, 0.0);
        Assertions.assertEquals(0.3, n % 1, 0.0000001);
    }

    @Test
    public void indexTest() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int n = 10, N=n*n, inter = 0;
        int[][] arr = new int[n][n];
        System.out.println("-----------------");
        Arrays.stream(arr).forEach((a) -> System.out.println(Arrays.toString(a)));
        for (int i = 0; i < N; i++) {
            int row = (int) i/n;
            int col = i - row*n;
            arr[row][col] = i+1;
        }
        System.out.println("-----------------");
        Arrays.stream(arr).forEach((a) -> System.out.println(Arrays.toString(a)));
        Assertions.assertEquals(4, arr[0][3]);
        Assertions.assertEquals(findElementNumberByIndices(arr, 0, 3), arr[0][3]);
        setValueByElementNumber(arr, 4, 991);
        Assertions.assertNotEquals(4, arr[0][3]);
        Assertions.assertEquals(991, arr[0][3]);
        Assertions.assertEquals(25, arr[2][4]);
        setValueByElementNumber(arr, 25, 991);
        Assertions.assertNotEquals(25, arr[2][4]);
        Assertions.assertEquals(991, arr[2][4]);
        System.out.println("-----------------");
        Arrays.stream(arr).forEach((a) -> System.out.println(Arrays.toString(a)));
    }

    private void setValueByElementNumber(int[][] arr, int elementN, int value) {
        int rowIndex = (elementN - 1)/arr.length;
        int colIndex = elementN - 1 - rowIndex*arr.length;
        arr[rowIndex][colIndex] = value;
    }

    private int findElementNumberByIndices(int[][] arr, int rowIndex, int colIndex) {
        return rowIndex*arr.length + colIndex + 1;
    }

}