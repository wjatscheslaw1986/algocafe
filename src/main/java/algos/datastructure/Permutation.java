/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = 0;

        if (args.length == 0) throw new IllegalArgumentException("Please provide a number of elements to print");

        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Please provide a number of elements to print in a numeric (integer) form");
        }

        if (k < 0) throw new IllegalArgumentException("number is out of range");

        RandomizedQueue<String> deque = new RandomizedQueue<>();

        while (!StdIn.isEmpty())
            for (String s : StdIn.readString().split(" ")) deque.enqueue(s);

        if (k > deque.size()) throw new IllegalArgumentException("number is out of range");

        Iterator<String> iterator = deque.iterator();

        for (int i = 0; i < k; i++)
            if (iterator.hasNext()) System.out.println(iterator.next());
    }
}
