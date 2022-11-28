package algos.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {
	
	
	Map<String, Double> arcs = null;
	
	public Dijkstra(Map<String, Double> arcs) {
		this.arcs = arcs;
	}
	
	Map<String, HashMap<String, Double>> theGraph = new HashMap<>();
	Map<String, Double> costs = new HashMap<>();
	Map<String, String> parents = new HashMap<>();
	Map<String, String> processed = new HashMap<>();

	public String findShortestPath() {
		
		Set<String> uniqueKeys = new HashSet<>();
		for (var key : arcs.keySet()) {
			String[] points = key.split("->");
			if (points.length != 2)
				throw new RuntimeException("Bad ARCS argument");
			uniqueKeys.add(points[0]);
			uniqueKeys.add(points[1]);
		}

		for (var key : uniqueKeys) {
			theGraph.put(key, new HashMap<>());
			costs.put(key, Double.POSITIVE_INFINITY);
			parents.put(key, null);
		}

		for (var key : arcs.keySet()) {
			String[] points = key.split("->");
			theGraph.get(points[0]).put(points[1], arcs.get(key));
		}

		for (var key : arcs.keySet()) {
			String[] points = key.split("->");
			parents.put(points[1], points[0]);
		}

		costs.put("START", 0.0d);

		String node = null;
		do {
			node = getCheapestDestination(costs, processed);
			if (node == null) break; 
			var cost = costs.get(node);
			var neighbours = theGraph.get(node);
			try {
			for (var neigh : neighbours.keySet()) {
				var newCost = cost + neighbours.get(neigh);
				if (costs.get(neigh) > newCost) {
					costs.put(neigh, newCost);
					parents.put(neigh, node);
				}
			}
			processed.put(node, node);
			} catch (Exception e) {
				System.out.println("HERE");
			}
		} while (node != null);

		List<String> pathe = new LinkedList<>();
		String parent = "FINISH";
		while (parent != null) {
			if (parent.equals("START")) {
				pathe.add(parent);
				break;
			}
			var p = parents.get(parent);
			pathe.add(parent);
			parent = p;
		}
		List<String> revPathe = new LinkedList<>();
		for (int i = pathe.size()-1; i >= 0; i--) {
			revPathe.add(pathe.get(i));
		}
		String drawing = "Cost is: " + costs.get("FINISH") + " PATH: " + revPathe.stream().reduce((String a1, String a2) -> a1.concat(" => ".concat(a2))).orElse("");
				
		return drawing;
		
	}

	private String getCheapestDestination(Map<String, Double> costs, Map<String, String> processed) {
		String cheapestId = null;
		Double cheapestCost = Double.POSITIVE_INFINITY;
		for (var key : costs.keySet())
			if (!processed.containsKey(key) && costs.get(key) < cheapestCost) {
				cheapestId = key;
				cheapestCost = costs.get(key);
			}

		return cheapestId;
	}

}
