/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.specialized;

import algos.graph.WeightedIncidentalityListGraph;
import algos.graph.objects.Crossroad;
import algos.graph.objects.CrossroadsNode;
import algos.graph.objects.WeightedGraph;
import algos.graph.objects.WeightedRib;

import java.util.ArrayList;

public class CrossroadsWeightedIncidentalityListGraph<V extends CrossroadsNode, E extends WeightedRib> extends WeightedIncidentalityListGraph<V, E> implements WeightedGraph<V, E> {

    public CrossroadsWeightedIncidentalityListGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public int indexOf(Crossroad crossroad) {
        for (int i = 0; i < this.getNodes().size(); i++) if (this.getNodes().get(i).getCrossroad() == crossroad) return i;
        return -1;
    }
}