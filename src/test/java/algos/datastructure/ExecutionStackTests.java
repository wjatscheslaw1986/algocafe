/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExecutionStackTests {

    @Test
    public void triangleNumberTest() {
        TriangleNumberNCalcualtor triangleNumberNCalcualtor = new TriangleNumberNCalcualtor(2);
        Assertions.assertEquals(3, triangleNumberNCalcualtor.getAnswer());
    }
}