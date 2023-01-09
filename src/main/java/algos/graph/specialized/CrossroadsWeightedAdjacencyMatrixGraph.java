/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.specialized;

import algos.graph.UnionFind;
import algos.graph.WeightedAdjacencyMatrixGraph;
import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.Crossroad;
import algos.graph.objects.CrossroadsNode;
import algos.graph.objects.WeightedRib;

public class CrossroadsWeightedAdjacencyMatrixGraph<N extends CrossroadsNode, A extends WeightedRib> extends WeightedAdjacencyMatrixGraph<N, A> implements UnionFind<N> {

    public CrossroadsWeightedAdjacencyMatrixGraph(N[] vertices, double[][] adjacency) throws GraphInstantiationException {
        super(vertices, adjacency);
    }

    @Override
    public int indexOf(CrossroadsNode node) {
        return this.indexOf(node.getCrossroad());
    }

    public int indexOf(Crossroad crossroad) {
        for (int i = 0; i < this.getNodes().length; i++)
            if (this.getNodes()[i].getCrossroad() == crossroad) return i;
        return -1;
    }

    @Override
    public void union(N node1, N node2) {

    }

    @Override
    public boolean connected(N node1, N node2) {
        return false;
    }
}