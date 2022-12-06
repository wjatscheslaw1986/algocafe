/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.WeightedArc;
import algos.graph.objects.WeightedGraph;

import java.util.*;

public class WeightedAdjacencyMatrixDirectedGraph<N, A extends WeightedArc> extends AdjacencyMatrixDirectedGraph<N, A> implements WeightedGraph<N, A> {

    private double[][] adjacencyMatrix;

    public WeightedAdjacencyMatrixDirectedGraph(N[] nodes, double[][] adjacency) throws GraphInstantiationException {
        super(nodes);
        if (adjacency.length != adjacency[0].length)
            throw new GraphInstantiationException("An adjacency matrix graph must have a square matrix because both dimensions are node indices");
        adjacencyMatrix = adjacency;
    }

    public double[][] getAdjacencyMatrixWeighted() {
        return adjacencyMatrix;
    }

    @Override
    public boolean[][] getAdjacencyMatrix() {
        throw new RuntimeException("You shouldn't be using this method");
    }

    @Override
    public void connectNodes(N one, N two) {
        connectNodes(indexOf(one), indexOf(two));
    }

    @Override
    public void connectNodes(int one, int two) {
        throw new IllegalArgumentException("You cannon connect nodes of a weighted graph with a weightless rib/arc. Don't use this method, then.");
    }

    @Override
    public void connectNodes(N from, N to, double weight) {
        connectNodes(indexOf(from), indexOf(to), weight);
    }

    @Override
    public void connectNodes(int from, int to, double weight) {
        if (weight == .0d) throw new IllegalArgumentException("Zero weight means no connection.");
        this.adjacencyMatrix[from][to] = weight;
    }

    @Override
    public void disconnectNodes(N from, N to) {
        disconnectNodes(indexOf(from), indexOf(to));
    }

    @Override
    public void disconnectNodes(int from, int to) {
        this.adjacencyMatrix[from][to] = .0d;
    }

    @Override
    public Set<N> successorsOf(int index) {
        Set<N> successors = new HashSet<>();
        for (int i = 0; i < getAdjacencyMatrixWeighted().length; i++) if (getAdjacencyMatrixWeighted()[index][i] != .0d) successors.add(getNodes()[i]);
        return successors;
    }

    @Override
    public Set<N> successorsOf(N vertex) {
        return successorsOf(indexOf(vertex));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeightedAdjacencyMatrixDirectedGraph{");
        sb.append("graph=").append(Arrays.toString(adjacencyMatrix));
        sb.append('}');
        return sb.toString();
    }
}