/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectionTests {

    @Test
    public void selectKMaxTest() {
        String[] names = new String[]{"Dmitri", "Vladimir", "Oleg", "Evgen", "Nikolay", "Alex", "Robert", "Igor", "Konstantine", "Leonide", "Timofey", "Mikhael", "Boris", "Peter", "Xenomorph"};
        QuickSelect.rearrangeToSelectK(names, 3, true);
        //Arrays.stream(names).forEach(System.out::print);
        Assertions.assertTrue("Alex".equals(names[0]) || "Alex".equals(names[1]) || "Alex".equals(names[2]));
        Assertions.assertTrue("Boris".equals(names[0]) || "Boris".equals(names[1]) || "Boris".equals(names[2]));
        Assertions.assertTrue("Dmitri".equals(names[0]) || "Dmitri".equals(names[1]) || "Dmitri".equals(names[2]));
        QuickSelect.rearrangeToSelectK(names, 5, false);
        Assertions.assertTrue("Xenomorph".equals(names[0]) || "Xenomorph".equals(names[1]) || "Xenomorph".equals(names[2]) || "Xenomorph".equals(names[3]) || "Xenomorph".equals(names[4]));
        Assertions.assertTrue("Vladimir".equals(names[0]) || "Vladimir".equals(names[1]) || "Vladimir".equals(names[2]) || "Vladimir".equals(names[3]) || "Vladimir".equals(names[4]));
        Assertions.assertTrue("Peter".equals(names[0]) || "Peter".equals(names[1]) || "Peter".equals(names[2]) || "Peter".equals(names[3]) || "Peter".equals(names[4]));
        Assertions.assertTrue("Timofey".equals(names[0]) || "Timofey".equals(names[1]) || "Timofey".equals(names[2]) || "Timofey".equals(names[3]) || "Timofey".equals(names[4]));
        Assertions.assertTrue("Robert".equals(names[0]) || "Robert".equals(names[1]) || "Robert".equals(names[2]) || "Robert".equals(names[3]) || "Robert".equals(names[4]));
        //System.out.println();
        //Arrays.stream(names).forEach(System.out::print);
    }
}