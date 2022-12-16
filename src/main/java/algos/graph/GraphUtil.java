// MODIFIED BY VIACHESLAV MIKHAILOV
// From Classic Computer Science Problems in Java
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package algos.graph;

import algos.graph.objects.*;

import java.util.*;
import java.util.function.IntConsumer;

public class GraphUtil {

    /**
     * This algorithm is also known as Robert C. Prim's algorithm (1957), though firstly invented by Vojtech Jarnik in 1930
     * The algorithm returns a minimum spanning tree of the graph as a list of arcs
     *
     * @param graph the graph object
     * @param start an index of a vertex where we want to start our journey from
     * @param <V>   generic vertex (node) type
     * @param <E>   generic edge (rib, arc) type
     * @param <T>   generic graph implementation type
     * @return a minimum spanning tree of the graph
     */
    public static <V, E extends WeightedRib, T extends Graph<V, E>> List<E> jarnik(T graph, int start) {
        LinkedList<E> result = new LinkedList<>();

        if (start < 0 || start > (graph.getNodeCount() - 1))
            throw new IllegalArgumentException("No node of the graph may have negative index. Bad index was " + start);

        final PriorityQueue<E> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.getNodeCount()];

        IntConsumer visit = index -> {
            visited[index] = true;
            for (E edge : graph.ribsOf(index))
                if (!visited[edge.to] || !visited[edge.from]) pq.offer(edge);
        };

        visit.accept(start);
        while (!pq.isEmpty()) {
            E edge = pq.poll();
            if (visited[edge.to]) {
                if (visited[edge.from]) continue;
                else {
                    result.add(edge);
                    visit.accept(edge.from);
                    continue;
                }
            }
            result.add(edge);
            visit.accept(edge.to);
        }

        return result;
    }

    /**
     * The method returns a String representation of the path on the given graph
     *
     * @param path  list of interconnected edges of the graph
     * @param graph the graph
     * @param <T>   generic vertex type
     * @param <V>   generic graph implementation type
     * @param <E>   generic edge (rib, arc) type
     * @return String representation of the path
     */
    public static <T, V extends WeightedIncidentalityListDirectedGraph<T, E>, E extends WeightedArc> String printWeightedPath(List<E> path, V graph) {
        StringBuilder sb = new StringBuilder();
        for (E edge : path)
            sb.append(graph.nodeAt(edge.from)).append(" =").append(edge.weight).append("=> ").append(graph.nodeAt(edge.to)).append("\n");
        return sb.toString();
    }

    public static final class DijkstraNode implements Comparable<DijkstraNode> {
        public final int vertex;
        public final double distance;

        public DijkstraNode(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(DijkstraNode o) {
            Double mine = this.distance;
            Double their = o.distance;
            return mine.compareTo(their);
        }
    }

    public static final class DijkstraResult<E extends WeightedRib> {
        public final double[] distances;
        public final Map<Integer, E> pathMap;

        public DijkstraResult(double[] distances, Map<Integer, E> pathMap) {
            this.distances = distances;
            this.pathMap = pathMap;
        }
    }

    /**
     * The method calculates the shortest distance from the root node to any other node of the given graph
     *
     * @param root - the root node
     * @param <N>  - graph node generic type
     * @param <T>  - graph edge (arc, rib) generic type
     * @param <G>  - generic graph implementation type
     * @return an encapsulated result of the calculation
     */
    public static <N, T extends WeightedRib, G extends WeightedGraph<N, T>> DijkstraResult<T> dijkstra(N root, G graph) {
        int start = graph.indexOf(root);
        double[] distances = new double[graph.getNodeCount()];
        distances[start] = .0d;
        boolean[] visited = new boolean[graph.getNodeCount()];
        visited[start] = true;
        HashMap<Integer, T> pathMap = new HashMap<>();
        PriorityQueue<DijkstraNode> pQueue = new PriorityQueue<>();
        pQueue.offer(new DijkstraNode(start, distances[start]));

        while (!pQueue.isEmpty()) {
            int nodeIndex = pQueue.poll().vertex;
            double nodeDistance = distances[nodeIndex];
            for (T wEdge : graph.ribsOf(nodeIndex)) {
                double oldDistance = distances[wEdge.to];
                double pathWeight = wEdge.weight + nodeDistance;
                if (!visited[wEdge.to] || (oldDistance > pathWeight)) {
                    visited[wEdge.to] = true;
                    distances[wEdge.to] = pathWeight;
                    pathMap.put(wEdge.to, wEdge);
                    pQueue.offer(new DijkstraNode(wEdge.to, distances[wEdge.to]));
                }
            }
        }
        return new DijkstraResult<>(distances, pathMap);
    }

    public static <N, T extends WeightedArc, G extends WeightedIncidentalityListDirectedGraph<N, T>> Map<N, Double> distanceArrayToDistanceMap(double[] distances, G graph) {
        HashMap<N, Double> distanceMap = new HashMap<>();
        for (int i = 0; i < distances.length; i++) distanceMap.put(graph.nodeAt(i), distances[i]);
        return distanceMap;
    }

    public static <E extends Rib> List<E> pathMapToPathList(int start, int end, Map<Integer, E> pathMap) {
        if (pathMap.size() == 0) return List.of();
        LinkedList<E> path = new LinkedList<>();
        E wRib = pathMap.get(end);
        if (Objects.isNull(wRib)) {
            System.out.println("df");
        }
        path.add(wRib);
        while (wRib.from != start) {
            wRib = pathMap.get(wRib.from);
            path.add(wRib);
        }
        Collections.reverse(path);
        return path;
    }

    public static <T, G extends Rib> void print(Collection<G> connections, Graph<T, G> graph) {
        connections.stream()
                .map(wRib -> graph.nodeAt(wRib.from) + " ==== " + graph.nodeAt(wRib.to))
                .forEach(System.out::println);
    }

    public static <T, R extends WeightedRib> void printWeighted(Collection<R> connections, Graph<T, R> graph) {
        connections.stream()
                .map(wRib -> graph.nodeAt(wRib.from) + " ==" + wRib.weight + "== " + graph.nodeAt(wRib.to))
                .forEach(System.out::println);
    }

}