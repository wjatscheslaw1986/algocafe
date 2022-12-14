/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.specialized;

import algos.graph.WeightedIncidentalityListGraph;
import algos.graph.objects.City;
import algos.graph.objects.CityNode;
import algos.graph.objects.WeightedGraph;
import algos.graph.objects.WeightedRib;

import java.util.ArrayList;

public class CityWeightedIncidentalityListGraph<V extends CityNode, E extends WeightedRib> extends WeightedIncidentalityListGraph<V, E> implements WeightedGraph<V, E> {

    public CityWeightedIncidentalityListGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public int indexOf(City city) {
        for (int i = 0; i < this.getNodes().size(); i++) if (this.getNodes().get(i).getCity() == city) return i;
        return -1;
    }
}