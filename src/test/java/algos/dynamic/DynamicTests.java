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

        // Dynamic programming is based on a step-by-step calculation of the final result, being based on a previous step result.
        // Dynamic programming's power is in absence of need of recalculation after an initial set of rules for the task has been changed.
        // Instead, after having modified the initial condition (i.e. adding new option into a list of all possible options, etc.),
        // you simply continue calculation from where it had been left.
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

        //System.out.println(backpack.toString());

        items = new String[]{"Water","Book","Food","Coat","Camera"};
        itemsCosts.clear();
        itemsSizes.clear();
        itemsCosts.put(items[0], 10.0d);
        itemsCosts.put(items[1], 3.0d);
        itemsCosts.put(items[2], 9.0d);
        itemsCosts.put(items[3], 5.0d);
        itemsCosts.put(items[4], 6.0d);
        itemsSizes.put(items[0], 3);
        itemsSizes.put(items[1], 1);
        itemsSizes.put(items[2], 2);
        itemsSizes.put(items[3], 2);
        itemsSizes.put(items[4], 1);
        backpack = new Backpack(6, itemsCosts, itemsSizes);
        backpack.recalculate();
        Assertions.assertEquals(25.0d, backpack.getStates()[4][5].getTotalCost());
        Assertions.assertEquals(3, backpack.getStates()[4][5].getItemsInBackpack().size());
    }
}
