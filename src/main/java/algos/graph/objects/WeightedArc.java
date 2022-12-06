/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

/**
 * WeightedArc is a class representing a weighty connection between two vertices of a graph
 */
public class WeightedArc extends Arc implements Comparable<WeightedArc> {

    public final double weight;

    public WeightedArc(int from, int to, double weight) {
        super(from, to);
        this.weight = weight;
    }

    @Override
    public WeightedArc reversed() {
        return new WeightedArc(to, from, this.weight);
    }

    public int compareTo(WeightedArc other) {
        Double mine = this.weight;
        Double their = other.weight;
        return mine.compareTo(their);
    }

    @Override
    public String toString() {
        return from + " " + weight + "> " + to;
    }

}