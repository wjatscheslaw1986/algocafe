/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.objects.WeightedArc;
import algos.graph.objects.WeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WeightedIncidentalityListDirectedGraph<V, E extends WeightedArc> extends IncidentalityListDirectedGraph<V, E> implements WeightedGraph<V, E> {

    public WeightedIncidentalityListDirectedGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public static double totalWeight(List<WeightedArc> path) {
        return path.parallelStream().mapToDouble(we -> we.weight).sum();
    }

    @Override
    public void connectNodes(V from, V to, double weight) {
        connectNodes(indexOf(from), indexOf(to), weight);
    }

    @Override
    public void connectNodes(int from, int to, double weight) {
        this.incidentality.get(from).add((E) new WeightedArc(from, to, weight));
    }

    @Override
    public void disconnectNodes(V from, V to) {
        disconnectNodes(indexOf(from), indexOf(to));
    }

    @Override
    public void disconnectNodes(int from, int to) {
        this.incidentality.get(from).remove(ribsOf(from).stream().filter(rib -> rib.to == to).findAny().get());
    }

    @Override
    public Set<E> ribsOf(int i) {
        return arcsOf(i);
    }

    @Override
    public Set<E> ribsOf(V node) {
        return arcsOf(node);
    }
}