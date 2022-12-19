/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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


}