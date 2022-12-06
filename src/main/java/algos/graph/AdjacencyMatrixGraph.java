/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.*;

import java.util.*;

/**
 * Generic implementation of an adjacency matrix based graph.
 * Adjacency matrix is a square matrix of boolean values. Both matrix dimensions are node ids
 * which are matrix indices in their own turn.
 * The matrix must have zeroes (false values) on its main diagonal, which means no self-looping
 *
 * @param <N> generic node type
 * @param <A> generic rib type
 */
public class AdjacencyMatrixGraph<N, A extends Rib> implements Graph<N, A> {
    private boolean[][] adjacencyMatrix;
    private N[] nodes;
    private HashSet<String> ribs;

    public AdjacencyMatrixGraph(N[] nodes) {
        this.nodes = nodes;
    }
    public AdjacencyMatrixGraph(N[] nodes, boolean[][] adjacency) throws GraphInstantiationException {
        this.nodes = nodes;
        this.adjacencyMatrix = adjacency;
    }

    public void setAdjacencyMatrix(boolean[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public N[] getNodes() {
        return nodes;
    }

    public int getNodeCount() {
        return this.nodes.length;
    }

    @Override
    public int getRibCount() {
        if (Objects.isNull(this.ribs)) {
            this.ribs = new HashSet<>();
            for (int i = 0; i < nodes.length; i++) {
                for (int j = 0; j < nodes.length; j++) {
                    if (i == j) continue;
                    if (getAdjacencyMatrix()[i][j]) ribs.add(i + " --- " + j);
                }
            }
        }
        return this.ribs.size();
    }

    public int addNode(N vertex, boolean[][] adjacency) {
        N[] updatedNodes = (N[]) new Object[this.nodes.length + 1];
        updatedNodes[updatedNodes.length-1] = vertex;
        int oldLength = this.nodes.length;
        System.arraycopy(this.nodes, 0, updatedNodes, 0, oldLength);
        this.nodes = updatedNodes;
        this.adjacencyMatrix = adjacency;
        return oldLength;
    }

    public N nodeAt(int index) {
        return this.nodes[index];
    }

    //TODO maybe it would be cheaper to have a hashtable for an O(1) search?
    public int indexOf(N node) {
        for (int i = 0; i < this.nodes.length; i++) if (nodes[i] == node) return i;
        return -1;
    }

    @Override
    public Set<A> ribsOf(N node) {
        return ribsOf(indexOf(node));
    }

    @Override
    public Set<A> ribsOf(int index) {
        Set<A> a = new HashSet<>();
        for (int i = 0; i < getAdjacencyMatrix().length; i++) {
            if (getAdjacencyMatrix()[index][i]) a.add((A) new Rib(index, i));
            if (getAdjacencyMatrix()[i][index]) a.add((A) new Rib(i, index));
        }
        return a;
    }

    @Override
    public Set<N> successorsOf(int index) {
        Set<N> successors = new HashSet<>();
        for (int i = 0; i < getAdjacencyMatrix().length; i++)
            if (getAdjacencyMatrix()[index][i] || getAdjacencyMatrix()[i][index]) {
                successors.add(nodes[i]);
            }
        return successors;
    }

    @Override
    public Set<N> successorsOf(N vertex) {
        return successorsOf(indexOf(vertex));
    }
    @Override
    public void connectNodes(N one, N two) {
        connectNodes(this.indexOf(one), this.indexOf(two));
    }

    @Override
    public void connectNodes(int one, int two) {
        getAdjacencyMatrix()[one][two] = true;
        getAdjacencyMatrix()[two][one] = true;
    }

    @Override
    public void disconnectNodes(N one, N two) {
        disconnectNodes(this.indexOf(one), this.indexOf(two));
    }

    @Override
    public void disconnectNodes(int one, int two) {
        getAdjacencyMatrix()[one][two] = false;
        getAdjacencyMatrix()[two][one] = false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getNodeCount(); i++)
            sb.append(nodeAt(i)).append(" -> ").append(Arrays.toString(successorsOf(i).toArray())).append(System.lineSeparator());
        return sb.toString();
    }
}