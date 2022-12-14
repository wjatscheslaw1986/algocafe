/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.objects.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generic implementation of a graph which is based on an incidentality list.
 * Incidentality list is an array of vertices (nodes) whose indices match
 * sets of their ribs, contained in a ribs sets array.
 * Each of the ribs must have strictly two index references of two connected nodes.
 *
 * @param <V> generic vertex type
 * @param <E> generified rib type
 */
public class IncidentalityListGraph<V, E extends Rib> implements Graph<V, E> {
    private ArrayList<V> nodes = new ArrayList<>();
    private ArrayList<Set<E>> incidentality = new ArrayList<>();
    private Set<E> ribs = new HashSet<>();

    public IncidentalityListGraph(ArrayList<V> nodes) {
        this.nodes = nodes;
        for (V node : nodes) this.incidentality.add(new HashSet<>());
    }

    @Override
    public int getNodeCount() {
        return this.nodes.size();
    }

    public int addVertex(V vertex) {
        this.nodes.add(vertex);
        this.incidentality.add(new HashSet<>());
        return getNodeCount() - 1;
    }

    @Override
    public V nodeAt(int index) {
        return this.nodes.get(index);
    }

    @Override
    public int getRibCount() {
        return this.ribs.size() / 2;
    }

    @Override
    public int indexOf(V vertex) {
        return this.nodes.indexOf(vertex);
    }

    private void updateRibs() {
        this.ribs = this.incidentality.stream().flatMap(Set::stream).collect(Collectors.toSet());
    }

    @Override
    public void connectNodes(V from, V to) {
        connectNodes(indexOf(from), indexOf(to));
    }

    @Override
    public void connectNodes(int from, int to) {
        this.incidentality.get(from).add((E) new Rib(from, to));
        this.incidentality.get(to).add((E) new Rib(to, from));
        updateRibs();
    }

    @Override
    public void disconnectNodes(V from, V to) {
        disconnectNodes(indexOf(from), indexOf(to));
    }

    @Override
    public void disconnectNodes(int from, int to) {
        this.incidentality.get(from).remove(ribsOf(from).stream().filter(rib -> rib.to == to).findAny().get());
        this.incidentality.get(to).remove(ribsOf(to).stream().filter(rib -> rib.from == to).findAny().get());
        updateRibs();
    }

    public Set<V> successorsOf(int index) {
        return this.incidentality.get(index).stream()
                .flatMap(edge -> Stream.of(nodeAt(edge.to), nodeAt(edge.from)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<E> ribsOf(V node) {
        return ribsOf(indexOf(node));
    }

    @Override
    public Set<E> ribsOf(int index) {
        return this.incidentality.get(index);
    }

    public Set<V> successorsOf(V vertex) {
        return successorsOf(indexOf(vertex));
    }

    public Set<E> arcsOf(int index) {
        return ribsOf(index);
    }

    public Set<E> arcsOf(V vertex) {
        return ribsOf(indexOf(vertex));
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