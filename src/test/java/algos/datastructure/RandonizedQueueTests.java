/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class RandonizedQueueTests {

    @Test
    public void randomnessTest() {
        RandomizedQueue<String> deque = new RandomizedQueue<>();
        String[] literals = {"ant", "bear", "cat", "dog", "emu", "fox", "goat", "horse"};
            for(String s : literals) deque.enqueue(s);
        Iterator<String> iterator = deque.iterator();
        for (int i = 0; i < deque.size(); i++)
            if (iterator.hasNext()) System.out.println(iterator.next());
    }
}
