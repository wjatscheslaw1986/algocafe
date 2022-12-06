/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.objects.Arc;
import algos.graph.objects.DirectedGraph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Generic implementation of an oriented graph which is based on an incidentality list.
 * Incidentality list is an array of vertices (nodes) whose indices match
 * sets of their arcs, contained in an arcs sets array.
 * Each of the ribs must have strictly two index references of two connected nodes.
 *
 * @param <V> generic node (vertex) type
 * @param <E> generified arc type
 */
public class IncidentalityListDirectedGraph<V, E extends Arc> implements DirectedGraph<V, E> {
    private ArrayList<V> nodes = new ArrayList<>();
    protected ArrayList<Set<E>> incidentality = new ArrayList<>();
    private Set<E> ribs = new HashSet<>();

    public IncidentalityListDirectedGraph(ArrayList<V> nodes) {
        this.nodes = nodes;
        for (V vertex : nodes) this.incidentality.add(new HashSet<>());
    }

    @Override
    public int getNodeCount() {
        return this.nodes.size();
    }

    public int getArcCount() {
        return this.incidentality.stream().mapToInt(Set::size).sum();
    }

    public int addVertex(V vertex) {
        this.nodes.add(vertex);
        this.incidentality.add(new HashSet<>());
        return getNodeCount() - 1;
    }

    //TODO make an annotation
    private void updateRibs() {
        this.ribs = this.incidentality.stream().flatMap(Set::stream).collect(Collectors.toSet());
    }

    @Override
    public V nodeAt(int index) {
        return this.nodes.get(index);
    }

    @Override
    public int getRibCount() {
        return getArcCount();
    }

    public int indexOf(V vertex) {
        return this.nodes.indexOf(vertex);
    }

    @Override
    public void connectNodes(V from, V to) {
        connectNodes(indexOf(from), indexOf(to));
    }

    @Override
    public void connectNodes(int from, int to) {
        this.incidentality.get(from).add((E) new Arc(from, to));
    }

    @Override
    public void disconnectNodes(V from, V to) {
        disconnectNodes(indexOf(from), indexOf(to));
    }

    @Override
    public void disconnectNodes(int from, int to) {

    }

    public Set<V> successorsOf(int index) {
        return this.incidentality.get(index).stream()
                .map(edge -> nodeAt(edge.to))
                .collect(Collectors.toSet());
    }

    public Set<V> successorsOf(V vertex) {
        return successorsOf(indexOf(vertex));
    }

    public Set<E> arcsOf(int index) {
        return this.incidentality.get(index);
    }

    public Set<E> arcsOf(V vertex) {
        return arcsOf(indexOf(vertex));
    }

    public ArrayList<V> getNodes() {
        return nodes;
    }

    public ArrayList<Set<E>> getIncidentality() {
        return incidentality;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getNodeCount(); i++)
            sb.append(nodeAt(i)).append(" -> ").append(Arrays.toString(successorsOf(i).toArray())).append(System.lineSeparator());
        return sb.toString();
    }
}