/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int rightCur; // index of the last element

    private static class Node<Item> {
        Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.array = (Item[]) new Object[1];
        this.rightCur = -1;
    }

    private RandomizedQueue(Item[] arr, int cursor) {
        this.array = arr;
        this.rightCur = cursor;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.rightCur < 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.rightCur + 1;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (rightCur == array.length - 1) {
            Item[] array2 = (Item[]) new Object[array.length * 2];
            for (int j = 0; j <= rightCur; j++) array2[j] = array[j];
            array = array2;
        }
        array[++rightCur] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int holeIndex = StdRandom.uniformInt(0, rightCur + 1);
        Item result = array[holeIndex];
        Item[] array2 = (Item[]) new Object[array.length];
        for (int h = 0; h < holeIndex; h++) {
            array2[h] = array[h];
        }
        for (int h = holeIndex; h < array2.length - 1; h++) {
            array2[h] = array[h + 1];
        }
        array = array2;
        if (--rightCur == array.length - (array.length / 4) - 1) {
            Item[] array3 = (Item[]) new Object[rightCur + 1];
            for (int j = 0; j < array3.length; j++) {
                array3[j] = array2[j];
            }
            array = array3;
        }
        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return array[StdRandom.uniformInt(0, rightCur + 1)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        if (isEmpty())
            return new Iterator<Item>() {

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public Item next() {
                    throw new NoSuchElementException();
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }

            };

        return new Iterator<Item>() {
            final Item[] arr = (Item[]) new Object[rightCur + 1];
            int counter;

            {
                counter = rightCur + 1;
                Item[] arrayCopy = (Item[]) new Object[array.length];
                for (int j = 0; j < counter; j++) arrayCopy[j] = array[j];
                RandomizedQueue<Item> copy = new RandomizedQueue<Item>(arrayCopy, rightCur);
                while (counter > 0) {
                    arr[--counter] = copy.dequeue();
                }
                counter = rightCur + 1;
            }

            @Override
            public boolean hasNext() {
                return counter > 0;
            }

            @Override
            public Item next() {
                if (counter == 0) throw new NoSuchElementException();
                return arr[--counter];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        System.out.println("RandomizedQueue has been created. RandomizedQueue size is " + queue.size() + " elements");
        System.out.println("Stack three words on the front");
        queue.enqueue("Vasyl'");
        queue.enqueue("Evgen");
        queue.enqueue("Dmytro");
        Iterator<String> i1 = queue.iterator();
        int counter = 0;
        while (i1.hasNext()) System.out.println("#" + ++counter + " element is \"" + i1.next() + "\"");
        counter = 0;
        System.out.println("Append three words on the back");
        queue.enqueue("Hania");
        queue.enqueue("Olesia");
        queue.enqueue("Christine");
        Iterator<String> i2 = queue.iterator();
        while (i2.hasNext()) System.out.println("#" + ++counter + " element is \"" + i2.next() + "\"");
        counter = 0;
        Iterator<String> i3 = queue.iterator();
        try {
            i3.remove();
        } catch (UnsupportedOperationException use) {
            System.out.println("Exception works");
        }
        try {
            queue.enqueue(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception works");
        }
        System.out.println("At this point, deque size is " + queue.size() + " elements");
        while (!queue.isEmpty()) {
            System.out.println("Removing " + queue.dequeue());
            while (!queue.isEmpty()) {
                System.out.println("Removing " + queue.dequeue());
            }
        }
        System.out.println("Size is " + queue.size());
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Exception works");
        }
        System.out.println("Size is " + queue.size());

        System.out.println("Adding names again");
        queue.enqueue("Vasyl'");
        queue.enqueue("Evgen");
        queue.enqueue("Dmytro");
        queue.enqueue("Hania");
        queue.enqueue("Olesia");
        queue.enqueue("Christine");

        System.out.println("Size is " + queue.size());
        while (!queue.isEmpty()) {
            System.out.println("Removing " + queue.dequeue());
            while (!queue.isEmpty()) {
                System.out.println("Removing " + queue.dequeue());
            }
        }
        System.out.println("Size is " + queue.size());
    }
}