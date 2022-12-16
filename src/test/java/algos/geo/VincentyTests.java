/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VincentyTests {

    @Test
    public void testVincenty() {
        Assertions.assertEquals(162, Vincenty.getDistance(59.942385d, 30.276066d, 59.941651d, 30.273564d), .1d);
    }
}
