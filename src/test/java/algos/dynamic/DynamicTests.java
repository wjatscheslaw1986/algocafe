/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.dynamic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class DynamicTests {

    @Test
    public void backpackTest() {
        String[] items = new String[]{"Guitar","Player","Notebook"};
        HashMap<String, Double> itemsCosts = new HashMap<>();
        HashMap<String, Integer> itemsSizes = new HashMap<>();
        itemsCosts.put(items[0], 1500.0d);
        itemsCosts.put(items[1], 3000.0d);
        itemsCosts.put(items[2], 2000.0d);
        itemsSizes.put(items[0], 1);
        itemsSizes.put(items[1], 4);
        itemsSizes.put(items[2], 3);

        Backpack backpack = new Backpack(4, itemsCosts, itemsSizes);
        backpack.recalculate();
        Assertions.assertEquals(1500.0d, backpack.getStates()[0][2].getTotalCost());
        Assertions.assertEquals(1500.0d, backpack.getStates()[0][3].getTotalCost());
        Assertions.assertEquals(1500.0d, backpack.getStates()[1][2].getTotalCost());
        Assertions.assertEquals(3000.0d, backpack.getStates()[1][3].getTotalCost());
        Assertions.assertEquals(2000.0d, backpack.getStates()[2][2].getTotalCost());
        Assertions.assertEquals(3500.0d, backpack.getStates()[2][3].getTotalCost());
        Assertions.assertEquals(1, backpack.getStates()[0][2].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[0][3].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[1][2].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[1][3].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[2][2].getItemsInBackpack().size());
        Assertions.assertEquals(2, backpack.getStates()[2][3].getItemsInBackpack().size());

        backpack.addElement("iPhone");
        backpack.getItemsCosts().put(backpack.getItems()[3], 2000.0d);
        backpack.getItemsSizes().put(backpack.getItems()[3], 1);
        backpack.recalculate(3);

        Assertions.assertEquals(1500.0d, backpack.getStates()[0][2].getTotalCost());
        Assertions.assertEquals(1500.0d, backpack.getStates()[0][3].getTotalCost());
        Assertions.assertEquals(1500.0d, backpack.getStates()[1][2].getTotalCost());
        Assertions.assertEquals(3000.0d, backpack.getStates()[1][3].getTotalCost());
        Assertions.assertEquals(2000.0d, backpack.getStates()[2][2].getTotalCost());
        Assertions.assertEquals(3500.0d, backpack.getStates()[2][3].getTotalCost());
        Assertions.assertEquals(1, backpack.getStates()[0][2].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[0][3].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[1][2].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[1][3].getItemsInBackpack().size());
        Assertions.assertEquals(1, backpack.getStates()[2][2].getItemsInBackpack().size());
        Assertions.assertEquals(2, backpack.getStates()[2][3].getItemsInBackpack().size());

        Assertions.assertEquals(3500.0d, backpack.getStates()[3][1].getTotalCost());
        Assertions.assertEquals(3500.0d, backpack.getStates()[3][2].getTotalCost());
        Assertions.assertEquals(4000.0d, backpack.getStates()[3][3].getTotalCost());
        Assertions.assertEquals(2, backpack.getStates()[3][1].getItemsInBackpack().size());
        Assertions.assertEquals(2, backpack.getStates()[3][2].getItemsInBackpack().size());
        Assertions.assertEquals(2, backpack.getStates()[3][3].getItemsInBackpack().size());
        Assertions.assertTrue(backpack.getStates()[3][3].getItemsInBackpack().contains("iPhone"));
        Assertions.assertTrue(backpack.getStates()[3][3].getItemsInBackpack().contains("Notebook"));
        Assertions.assertFalse(backpack.getStates()[3][3].getItemsInBackpack().contains("Guitar"));
        Assertions.assertFalse(backpack.getStates()[3][3].getItemsInBackpack().contains("Player"));

        System.out.println(backpack.toString());
    }
}
