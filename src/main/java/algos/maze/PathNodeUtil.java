package algos.maze;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class PathNodeUtil {

	public static <T> PathNode<T> dfs(T initial, Predicate<T> goalTest, Function<T, List<T>> successors, AtomicInteger stepsCounter) {
		Stack<PathNode<T>> frontier = new Stack<PathNode<T>>();
		frontier.push(new PathNode<>(initial, null));
		Set<T> explored = new HashSet<>();
		explored.add(initial);

		stepsCounter.set(0);

		while (!frontier.isEmpty()) {
			PathNode<T> currentNode = frontier.pop();
			T currentState = currentNode.getState();
			if (goalTest.test(currentState)) {
				return currentNode;
			}
			for (T child : successors.apply(currentState)) {
				if (explored.contains(child))
					continue;
				explored.add(child);
				frontier.push(new PathNode<>(child, currentNode));
				stepsCounter.incrementAndGet();
			}
		}
		return null;
	}

	public static <T> PathNode<T> bfs(T initial, Predicate<T> goalTest, Function<T, Collection<T>> successors, AtomicInteger stepsCounter) {
		Queue<PathNode<T>> frontier = new LinkedList<>();
		frontier.offer(new PathNode<>(initial, null));
		Set<T> explored = new HashSet<>();
		explored.add(initial);

		stepsCounter.set(0);

		while (!frontier.isEmpty()) {
			PathNode<T> currentNode = frontier.poll();
			T currentState = currentNode.getState();
			if (goalTest.test(currentState)) {
				return currentNode;
			}
			for (T child : successors.apply(currentState)) {
				if (explored.contains(child))
					continue;
				explored.add(child);
				frontier.offer(new PathNode<>(child, currentNode));
				stepsCounter.incrementAndGet();
			}
		}
		return null;
	}

	public static <T> PathNode<T> astar(T initial, Predicate<T> goalTest, Function<T, Collection<T>> successors, ToDoubleFunction<T> heuristic, AtomicInteger stepsCounter) {
		PriorityQueue<PathNode<T>> frontier = new PriorityQueue<>();
		frontier.offer(new PathNode<>(initial, null, 0.0d, heuristic.applyAsDouble(initial)));
		Map<T, Double> explored = new HashMap<>(); // here we store cost of reach for every cell
		explored.put(initial, .0d);

		stepsCounter.set(0);

		while (!frontier.isEmpty()) {
			PathNode<T> currentNode = frontier.poll();
			T currentState = currentNode.getState();
			if (goalTest.test(currentState)) {
				return currentNode;
			}
			for (T child : successors.apply(currentState)) {
				double newCost = currentNode.getCost() + 1.0d; // let's assume equal cost = 1.0d for all tiles
				if (!explored.containsKey(child) || explored.get(child) > newCost) {
					explored.put(child, newCost); // change successor's cost of reach to a lesser one newly discovered value
					frontier.offer(new PathNode<>(child, currentNode, newCost, heuristic.applyAsDouble(child)));
					stepsCounter.incrementAndGet();
				}
			}
		}
		return null;
	}

	public static <T> List<T> nodeToPath(PathNode<T> node) {
		Deque<T> path = new LinkedBlockingDeque<T>();
		path.add(node.getState());
		while (node.getParent() != null) {
			node = node.getParent();
			path.offer(node.getState());
		}
		List<T> l = new LinkedList<>();
		while (!path.isEmpty()) {
			l.add(path.pollLast());
		}
		return l;
	}

}
