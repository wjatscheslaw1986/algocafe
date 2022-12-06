/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.Arc;
import algos.graph.objects.DirectedGraph;

import java.util.*;

/**
 * Generic implementation of an adjacency matrix based graph.
 * Adjacency matrix is a square matrix of boolean values. Both matrix dimensions are node ids
 * which are nodes matrix indices in their own turn.
 * Each of the nodes must have two arrays of index references in it: parent nodes (from-nodes) and child nodes (to-nodes).
 * The matrix must have zeroes (false values) on its main diagonal, which means no self-looping
 *
 * @param <N> generic node type
 * @param <A> generic arc type
 */
public class AdjacencyMatrixDirectedGraph<N, A extends Arc> implements DirectedGraph<N, A> {
    private boolean[][] adjacencyMatrix;
    private N[] nodes;
    private HashSet<String> arcs;

    public AdjacencyMatrixDirectedGraph(N[] nodes) {
        this.nodes = nodes;
    }
    public AdjacencyMatrixDirectedGraph(N[] nodes, boolean[][] adjacency) throws GraphInstantiationException {
        reinit(nodes, adjacency);
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
        return getArcCount();
    }

    public int getArcCount() {
        if (Objects.isNull(this.arcs)) {
            this.arcs = new HashSet<>();
            for (int i = 0; i < nodes.length; i++) {
                for (int j = 0; j < nodes.length; j++) {
                    if (i == j) continue;
                    if (getAdjacencyMatrix()[i][j]) arcs.add(i + " --> " + j);
                }
            }
        }
        return this.arcs.size();
    }

    public int addNode(N vertex, boolean[][] adjacency) throws GraphInstantiationException {
        N[] updatedNodes = (N[]) new Object[this.nodes.length + 1];
        updatedNodes[updatedNodes.length-1] = vertex;
        int oldLength = this.nodes.length;
        System.arraycopy(this.nodes, 0, updatedNodes, 0, oldLength);
        reinit(updatedNodes, adjacency);
        return oldLength;
    }

    public N nodeAt(int index) {
        return this.nodes[index];
    }

    public int indexOf(N node) {
        for (int i = 0; i < nodes.length; i++) if (nodes[i] == node) return i;
        return -1;
    }

    @Override
    public Set<N> successorsOf(int index) {
        Set<N> successors = new HashSet<>();
        for (int i = 0; i < getAdjacencyMatrix().length; i++) if (getAdjacencyMatrix()[index][i]) successors.add(nodes[i]);
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
    }

    @Override
    public void disconnectNodes(N one, N two) {
        disconnectNodes(this.indexOf(one), this.indexOf(two));
    }

    @Override
    public void disconnectNodes(int one, int two) {
        getAdjacencyMatrix()[one][two] = false;
    }

    @Override
    public Set<A> arcsOf(int index) {
        Set<A> a = new HashSet<>();
        for (int i = 0; i < getAdjacencyMatrix().length; i++) {
            if (getAdjacencyMatrix()[index][i]) a.add((A) new Arc(index, i));
            if (getAdjacencyMatrix()[i][index]) a.add((A) new Arc(i, index));
        }
        return a;
    }

    @Override
    public Set<A> arcsOf(N node) {
        return arcsOf(indexOf(node));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getNodeCount(); i++)
            sb.append(nodeAt(i)).append(" -> ").append(Arrays.toString(successorsOf(i).toArray())).append(System.lineSeparator());
        return sb.toString();
    }

    private void reinit(N[] nodes, boolean[][] adjacency) throws GraphInstantiationException {
        this.nodes = nodes;
        setAdjacencyMatrix(adjacency);

//        this.graph = new boolean[nodes.length][nodes.length];
//        for (int i = 0; i < graph.length; i++) {
//            for (int fromIndex : nodes[i].getFromNodes()) {
//                if (fromIndex == i) throw new GraphInstantiationException("Self loops aren't allowed. Self-referencing nodes aren't acceptable.");
//                graph[i][fromIndex] = true;
//            }
//            for (int toIndex : nodes[i].getToNodes()) {
//                if (toIndex == i) throw new GraphInstantiationException("Self loops aren't allowed. Self-referencing nodes aren't acceptable.");
//                graph[i][toIndex] = true;
//            }
//        }
//
//        this.arcs = new HashSet<>();
//        for (int i = 0; i < nodes.length; i++) {
//            for (int fromIndex : nodes[i].getFromNodes()) arcs.add(i +"=>"+ fromIndex);
//            for (int toIndex : nodes[i].getToNodes()) arcs.add(i +"=>"+ toIndex);
//        }

//        validate(nodes, arcs);
    }

//    private void validate (DirectedNode[] nodes, HashSet<String> arcs) throws GraphInstantiationException {
//        validate(nodes, arcs.stream().map(s -> {
//            String[] txt = s.split("=>");
//            return new Arc(Integer.parseInt(txt[0]), Integer.parseInt(txt[1]));
//        }).toArray(Arc[]::new));
//    }
//
//    private void validate(DirectedNode[] nodes, Arc[] arcs) throws GraphInstantiationException {
//        HashMap<Integer, DirectedNode> mapNodeIndicesOnTheirObjects = Arrays.stream(nodes).collect(Collectors.toMap(
//                Node::getId,
//                dNode -> dNode,
//                (dNode1, dNode2) ->
//                        new DirectedNode(
//                                dNode1.getId(),
//                                IntStream.concat(Arrays.stream(dNode1.getFromNodes()), Arrays.stream(dNode2.getFromNodes())).toArray(),
//                                IntStream.concat(Arrays.stream(dNode1.getToNodes()), Arrays.stream(dNode2.getToNodes())).toArray()),
//                HashMap::new));
//        Set<Integer> nodeIndicesOfArcs = Arrays.stream(arcs).flatMap((Arc a) -> Arrays.stream(new Integer[]{a.from, a.to})).collect(Collectors.toSet());
//        if (nodeIndicesOfArcs.size() > mapNodeIndicesOnTheirObjects.keySet().size()) throw new GraphInstantiationException("Some nodes are missing from the instantiation array");
//    }
}