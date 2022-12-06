/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

/**
 * WeightedRib is a class representing a weighty connection between two vertices of a graph
 */
public class WeightedRib extends Rib implements Comparable<WeightedRib> {

    public final double weight;

    public double getWeight() {
        return weight;
    }

    public WeightedRib(int from, int to, double weight) {
        super(from, to);
        this.weight = weight;
    }

    public int compareTo(WeightedRib other) {
        Double mine = this.weight;
        Double their = other.weight;
        return mine.compareTo(their);
    }

    @Override
    public String toString() {
        return from + " <" + weight + "> " + to;
    }

}