/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SelectionTests {

    @Test
    public void selectKMaxTest() {
        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        QuickSelect.rearrangeToSelectK(names, 3, true);
        System.out.println(Arrays.toString(names));
        QuickSelect.rearrangeToSelectK(names, 3, false);
        System.out.println(Arrays.toString(names));
    }
}