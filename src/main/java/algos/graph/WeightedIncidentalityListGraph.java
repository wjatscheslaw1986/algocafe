/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.objects.WeightedGraph;
import algos.graph.objects.WeightedRib;

import java.util.ArrayList;

public class WeightedIncidentalityListGraph<V, E extends WeightedRib> extends IncidentalityListGraph<V, E> implements WeightedGraph<V, E> {

    public WeightedIncidentalityListGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    @Override
    public void connectNodes(int one, int two) {
        throw new IllegalArgumentException("You cannon connect nodes of a weighted graph with a weightless rib/arc. Don't use this method, then.");
    }

    @Override
    public void connectNodes(V from, V to, double weight) {
        connectNodes(indexOf(from), indexOf(to), weight);
    }

    @Override
    public void connectNodes(int from, int to, double weight) {
        this.getIncidentality().get(from).add((E) new WeightedRib(from, to, weight));
        this.getIncidentality().get(to).add((E) new WeightedRib(to, from, weight));
    }
}