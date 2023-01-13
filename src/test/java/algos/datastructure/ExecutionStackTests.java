/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExecutionStackTests {

    @Test
    public void triangleNumberTest() {
        StackTriangleNumberCalc stackTriangleNumberCalc = new StackTriangleNumberCalc(2);
        Assertions.assertEquals(3, stackTriangleNumberCalc.getAnswer());
    }
}