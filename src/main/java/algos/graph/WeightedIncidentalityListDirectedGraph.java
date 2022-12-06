/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.objects.WeightedArc;

import java.util.ArrayList;
import java.util.List;

public class WeightedIncidentalityListDirectedGraph<V, E extends WeightedArc> extends IncidentalityListDirectedGraph<V, E> {

    public WeightedIncidentalityListDirectedGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public void addArc(E edge) {
        incidentality.get(edge.from).add(edge);
        incidentality.get(edge.to).add((E) edge.reversed());
    }

    public void addArc(int from, int to, double weight) {
        addArc((E) new WeightedArc(from, to, weight));
    }

    public void addArc(V from, V to, double weight) {
        addArc(indexOf(from), indexOf(to), weight);
    }

    public static double totalWeight(List<WeightedArc> path) {
        return path.parallelStream().mapToDouble(we -> we.weight).sum();
    }

}