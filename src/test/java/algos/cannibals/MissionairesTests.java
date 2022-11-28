package algos.cannibals;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import algos.maze.PathNode;
import algos.maze.PathNodeUtil;


public class MissionairesTests {

    @Test
    public void mcTest() {
    	MissionariesAndCannibals mc = new MissionariesAndCannibals(2);
    	AtomicInteger stepCounter = new AtomicInteger(0);
        MissionariesAndCannibalsState start = new MissionariesAndCannibalsState(2, 2, true, 2);
        PathNode<MissionariesAndCannibalsState> solution = PathNodeUtil.dfs(start, MissionariesAndCannibalsState::isGoalAcheived, mc::successors, stepCounter);
        if (solution == null) {
            System.out.println("No solution found!");
        } else {
            List<MissionariesAndCannibalsState> path = PathNodeUtil.nodeToPath(solution);
            mc.displaySolution(path);
            System.out.println("Solution acheived in " + stepCounter + " steps");
        }
    }
    
    @Test
    public  void qTest() {
    	List<String> listOfWords = List.of("One", "Two", "Three", "Four", "Five");
		
    	Deque<String> deque = new ArrayDeque<>(listOfWords);
		Assertions.assertEquals(5, deque.size());
		Assertions.assertEquals("Five", deque.pollLast());
		Assertions.assertEquals("Four", deque.pollLast());
		Assertions.assertEquals(3, deque.size());
		Assertions.assertEquals("One", deque.poll());
		Assertions.assertEquals("Two", deque.pop());
		Assertions.assertEquals(1, deque.size());
		
		
		Queue<String> queue = new ConcurrentLinkedQueue<>(listOfWords);
		Assertions.assertEquals(5, queue.size());
		Assertions.assertEquals("One", queue.poll());
		Assertions.assertEquals("Two", queue.poll());
		Assertions.assertEquals(3, queue.size());
		Assertions.assertEquals("Three", queue.poll());
		Assertions.assertEquals("Four", queue.peek());
		Assertions.assertEquals(2, queue.size());
		
	}
}