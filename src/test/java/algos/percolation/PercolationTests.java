/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.percolation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PercolationTests {

    @Test
    public void percTest() {
        Percolation percolation = new Percolation(10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.open(-1, 5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> percolation.open(11, 5));
    }
}
