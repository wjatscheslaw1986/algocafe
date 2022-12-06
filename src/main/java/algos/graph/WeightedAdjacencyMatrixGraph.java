/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.WeightedGraph;
import algos.graph.objects.WeightedRib;

import java.util.HashSet;
import java.util.Set;

public class WeightedAdjacencyMatrixGraph<N, A extends WeightedRib> extends AdjacencyMatrixGraph<N, A> implements WeightedGraph<N, A> {

    private double[][] adjacencyMatrix;

    public WeightedAdjacencyMatrixGraph(N[] nodes, double[][] adjacency) throws GraphInstantiationException {
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
        this.adjacencyMatrix[to][from] = weight;
    }

    @Override
    public void disconnectNodes(N from, N to) {
        disconnectNodes(indexOf(from), indexOf(to));
    }

    @Override
    public void disconnectNodes(int from, int to) {
        this.adjacencyMatrix[from][to] = .0d;
        this.adjacencyMatrix[to][from] = .0d;
    }

    @Override
    public Set<N> successorsOf(int index) {
        Set<N> successors = new HashSet<>();
        for (int i = 0; i < getAdjacencyMatrixWeighted().length; i++)
            if (getAdjacencyMatrixWeighted()[index][i] != .0d || getAdjacencyMatrixWeighted()[i][index] != .0d) {
                successors.add(getNodes()[i]);
            }
        return successors;
    }

    @Override
    public Set<N> successorsOf(N vertex) {
        return successorsOf(indexOf(vertex));
    }

    @Override
    public Set<A> ribsOf(int index) {
        Set<A> a = new HashSet<>();
        for (int i = 0; i < getAdjacencyMatrixWeighted().length; i++) {
            if (getAdjacencyMatrixWeighted()[index][i] != .0d)
                a.add((A) new WeightedRib(index, i, getAdjacencyMatrixWeighted()[index][i]));
            if (getAdjacencyMatrixWeighted()[i][index] != .0d)
                a.add((A) new WeightedRib(i, index, getAdjacencyMatrixWeighted()[i][index]));
        }
        return a;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            sb.append(nodeAt(i).toString()).append(" >>> ");
            for (int y = 0; y < adjacencyMatrix.length; y++) {
                var w = adjacencyMatrix[i][y];
                if (w != .0d)
                    sb.append(nodeAt(y).toString()).append("(").append(w).append("); ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}