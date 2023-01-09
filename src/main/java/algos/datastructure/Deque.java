/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item> {
        Node<Item> parent;
        Node<Item> child;
        Item item;
        public Node(Node<Item> parent, Node<Item> child, Item item) {
            this.parent = parent;
            this.child = child;
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldFirst = first;
        first = new Node<>(oldFirst, null, item);
        if (oldFirst != null) oldFirst.child = first;
        else last = first;
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> oldLast = last;
        last = new Node<>(null, oldLast, item);
        if (oldLast != null) oldLast.parent = last;
        else first = last;
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (!isEmpty()) {
            Item i = first.item;
            if (size > 1) {
                first = first.parent;
                first.child = null;
            } else {
                last = null;
                first = null;
            }
            --size;
            return i;
        } else {
            throw new NoSuchElementException();
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (!isEmpty()) {
            Item i = last.item;
            if (size > 1) {
                last = last.child;
                last.parent = null;
            } else {
                last = null;
                first = null;
            }
            --size;
            return i;
        } else {
            throw new NoSuchElementException();
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        Item[] items = (Item[]) new Object[size];
        Node<Item> cursor = first;
        for (int i = size-1; i >= 0; i--) {
            items[i] = cursor.item;
            cursor = cursor.parent;
        }
        return new Iterator<Item>() {
            int counter = size;
            Item[] itemz = items;
            @Override
            public boolean hasNext() {
                return counter > 0;
            }

            @Override
            public Item next() {
                if (counter == 0) throw new NoSuchElementException();
                return itemz[--counter];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        System.out.println("Deque has been created. Deque size is " + deque.size() + " elements");
        System.out.println("Stack three words on the front");
        deque.addFirst("Vasyl'");
        deque.addFirst("Evgen");
        deque.addFirst("Dmytro");
        Iterator<String> i1 = deque.iterator();
        int counter = 0;
        while (i1.hasNext()) System.out.println("#" + ++counter + " element is \"" + i1.next() + "\"");
        counter = 0;
        System.out.println("Append three words on the back");
        deque.addLast("Hania");
        deque.addLast("Olesia");
        deque.addLast("Christine");
        Iterator<String> i2 = deque.iterator();
        while (i2.hasNext()) System.out.println("#" + ++counter + " element is \"" + i2.next() + "\"");
        counter = 0;
        Iterator<String> i3 = deque.iterator();
        try {
            i3.remove();
        } catch (UnsupportedOperationException use) {
            System.out.println("Exception works");
        }
        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception works");
        }
        try {
            deque.addLast(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception works");
        }
        System.out.println("At this point, deque size is " + deque.size() + " elements");
        while (!deque.isEmpty()) {
            System.out.println("Removing " + deque.removeFirst());
            while (!deque.isEmpty()) {
                System.out.println("Removing " + deque.removeLast());
            }
        }
        try {
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Exception works");
        }
        try {
            deque.removeLast();
        } catch (NoSuchElementException e) {
            System.out.println("Exception works");
        }

        System.out.println("Adding names again");
        deque.addFirst("Vasyl'");
        deque.addFirst("Evgen");
        deque.addFirst("Dmytro");
        deque.addLast("Hania");
        deque.addLast("Olesia");
        deque.addLast("Christine");

        System.out.println("Size is " + deque.size());
        while (!deque.isEmpty()) {
            System.out.println("Removing " + deque.removeLast());
            while (!deque.isEmpty()) {
                System.out.println("Removing " + deque.removeFirst());
            }
        }
        System.out.println("Size is " + deque.size());

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Deque.class.getSimpleName() + "[", "]")
                .add("first=" + first)
                .add("last=" + last)
                .add("size=" + size)
                .toString();
    }
}