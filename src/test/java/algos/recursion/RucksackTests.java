/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.recursion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class RucksackTests {

    @Test
    public void rucksackTest() {
        Rucksack rucksack = new Rucksack(Map.of("Ammunition", 11, "Rifle", 8, "Tent", 7, "Clothes", 6, "Food", 5), 20);
        List<String> r = rucksack.packToWeight();
        Assertions.assertTrue(r.contains("Food"));
        Assertions.assertTrue(r.contains("Rifle"));
        Assertions.assertTrue(r.contains("Tent"));
        Assertions.assertFalse(r.contains("Clothes"));
        Assertions.assertFalse(r.contains("Ammunition"));
    }

    @Test
    public void rucksackNotEnoughWeightTest() {
        Rucksack rucksack = new Rucksack(Map.of("Ammunition", 11, "Rifle", 8, "Tent", 7, "Clothes", 6, "Food", 5), 2);
        List<String> r = rucksack.packToWeight();
        Assertions.assertNull(r);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Rucksack(Map.of("Ammunition", 11, "Rifle", 8, "Tent", 7, "Clothes", 6, "Food", 5), 200));
        rucksack = new Rucksack(Map.of("Ammunition", 11, "Rifle", 8, "Tent", 7, "Clothes", 6, "Food", 5), 0);
        r = rucksack.packToWeight();
        Assertions.assertNotNull(r);
        Assertions.assertTrue(r.isEmpty());
        rucksack = new Rucksack(Map.of("Ammunition", 11, "Rifle", 8, "Tent", 7, "Clothes", 6, "Food", 5), 5);
        r = rucksack.packToWeight();
        Assertions.assertEquals(1, r.size());
        Assertions.assertEquals("Food", r.get(0));
        rucksack = new Rucksack(Map.of("Ammunition", 11, "Rifle", 8, "Tent", 7, "Clothes", 6, "Food", 5), 11);
        r = rucksack.packToWeight();
        Assertions.assertEquals(1, r.size());
        Assertions.assertEquals("Ammunition", r.get(0));
    }
}
