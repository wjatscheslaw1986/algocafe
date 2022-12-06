/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.DijkstraValidationException;

import java.util.*;

/**
 * Dijkstra algorithm can be used to calculate the cheapest path from START to any OTHER vertex on a graph.
 * Dijkstra algorithm is applicable only to DIRECTED ACYCLIC (no cycles are allowed) WEIGHTED graphs.
 * All weights must be positive, otherwise you need to use Bellman-Ford algorithm.
 * For an unweighted graph you may want to look at Breit First Search algorithm.
 * For an undirected weighted graph you may want to look at A* algorithm.
 */
public class DijkstraGraphHashtable {
    private final Map<String, Double> arcs;
    private final Map<String, HashMap<String, Double>> parentsOnTheirChildren = new HashMap<>(); // mapping of vertex on mapping of its neighbours on their costs of reach
    private final Map<String, String> childrenOnTheirParents = new HashMap<>(); // parents are needed for the cheapest path reconstruction
    private final Map<String, Double> weight = new HashMap<>();
    private final Map<String, String> visited = new HashMap<>();
    private final Set<String> vertices = new HashSet<>();

    public DijkstraGraphHashtable(Map<String, Double> arcs) {
        this.arcs = arcs;
        this.validate();
    }

    private void init() {
        vertices.clear();
        visited.clear();
        weight.clear();
        parentsOnTheirChildren.clear();
        childrenOnTheirParents.clear();

        for (var key : arcs.keySet()) {
            String[] points = key.split("->");
            if (points.length != 2)
                throw new RuntimeException("Bad ARCS argument");
            vertices.add(points[0]);
            vertices.add(points[1]);
        }

        for (var key : vertices) {
            parentsOnTheirChildren.put(key, new HashMap<String, Double>());
            weight.put(key, Double.POSITIVE_INFINITY);
            childrenOnTheirParents.put(key, null);
        }

        var a = arcs.keySet().stream().sorted(Comparator.naturalOrder()).toList();

        for (var key : a) {
            String[] points = key.split("->");
            parentsOnTheirChildren.get(points[0]).put(points[1], arcs.get(key)); // init neighbours
            childrenOnTheirParents.put(points[1], points[0]); // init parents
        }

        weight.put("START", 0.0d);
    }

    private void validate() {
        for (Double weight : this.arcs.values())
            if (weight < .0d)
                throw new DijkstraValidationException("Dijkstra algorithm isn't applicable to graphs with negative weights");
    }

    public String findShortestPathUsingDijkstra() {
        init();

        String currentNode = null;
        while (true) {
            currentNode = successor(weight, visited);
            if (currentNode == null) break;
            var currentNodeWeight = weight.get(currentNode);
            var childrenOnTheirWeights = parentsOnTheirChildren.get(currentNode);

            for (var child : childrenOnTheirWeights.keySet()) {
                var newWeight = currentNodeWeight + childrenOnTheirWeights.get(child);
                if (weight.get(child) > newWeight) {
                    weight.put(child, newWeight); // updating child's weight
                    childrenOnTheirParents.put(child, currentNode); // updating child's parent
                }
            }
            visited.put(currentNode, currentNode);
        }

        List<String> resultPath = new LinkedList<>();
        String parent = "FINISH";
        while (parent != null) {
            resultPath.add(parent);
            if (parent.equals("START")) break;
            parent = childrenOnTheirParents.get(parent);
        }

        Collections.reverse(resultPath);

        return "Cost is: " + weight.get("FINISH") + " PATH: " + resultPath.stream().reduce((String a1, String a2) -> a1.concat(" => ".concat(a2))).orElse("");
    }

    /**
     * This method returns a node which is cheapest to reach and which hasn't been visited yet.
     * Node counts as visited when the algorithm had entered it and has iterated over all of its children,
     * registering their costs of reach.
     * In a priority queue based implementations, same effect is achieved by polling an element.
     *
     * @param costs - costs of reach
     * @param visited - those who were visited shouldn't have their children's weights to be updated once again
     * @return the node cheapest to reach at the current step of the algorithm
     */
     String successor(Map<String, Double> costs, Map<String, String> visited) {
        String cheapestId = null;
        Double cheapestCost = Double.POSITIVE_INFINITY;
        for (var vertex : costs.keySet())
            if (!visited.containsKey(vertex) && costs.get(vertex) < cheapestCost) {
                cheapestId = vertex;
                cheapestCost = costs.get(vertex);
            }

        return cheapestId;
    }

}
