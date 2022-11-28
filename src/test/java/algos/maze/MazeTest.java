package algos.maze;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MazeTest {

    private static Maze m;

    @BeforeAll
    static void initAll() {
        m = new Maze(117, 122, new MazeTravelState(0, 5, null), new MazeTravelState(116, 77, null), .2f);
        Assertions.assertEquals(5, m.getStart().column);
        Assertions.assertEquals(0, m.getStart().row);
        Assertions.assertEquals(77, m.getGoal().column);
        Assertions.assertEquals(116, m.getGoal().row);
    }

    @Test
    public void goalTest() {

        int[] stepsTookEachTime = new int[10000];
        LocalDateTime startDFS = LocalDateTime.now();
        long dfssTookMillis = 0, bfssTookMillis = 0, astarsTookMillis = 0;
        int avgDFSSteps = 0, avgBFSSteps = 0, avgAstarSteps = 0;
        for (int i = 0; i < 10000; i++) {
        	AtomicInteger count = new AtomicInteger(0);
            PathNode<MazeTravelState> solution1 = PathNodeUtil.<MazeTravelState>dfs(m.getStart(), MazeTravelState::isGoalAcheived, m::successors, count);

            if (solution1 == null) {
//                System.out.println("No solution was found using depth-first search");
            } else {
                List<MazeTravelState> path1 = PathNodeUtil.nodeToPath(solution1);
                dfssTookMillis = Duration.between(startDFS, LocalDateTime.now()).toMillis();
                stepsTookEachTime[i] = count.get();
                m.mark(path1, Maze.Cell.PATH);
//                System.out.println(m);
                m.mark(path1, Maze.Cell.EMPTY);
            }
        }

        System.out.println("10000 DFS took " + dfssTookMillis + " millis");
        System.out.println("Average DFS took " + Arrays.stream(stepsTookEachTime).asLongStream().average().getAsDouble() + " steps");

        stepsTookEachTime = new int[10000];
        LocalDateTime startBFS = LocalDateTime.now();

        for (int i = 0; i < 10000; i++) {
        	AtomicInteger count = new AtomicInteger(0);
            PathNode<MazeTravelState> solution2 = PathNodeUtil.<MazeTravelState>bfs(m.getStart(), MazeTravelState::isGoalAcheived, m::successors, count);

            if (solution2 == null) {
//                System.out.println("No solution was found using breit-first search");
            } else {
                List<MazeTravelState> path2 = PathNodeUtil.nodeToPath(solution2);
                bfssTookMillis = Duration.between(startBFS, LocalDateTime.now()).toMillis();
                stepsTookEachTime[i] = count.get();
                m.mark(path2, Maze.Cell.PATH);
//                System.out.println(m);
                m.mark(path2, Maze.Cell.EMPTY);
            }
        }

        System.out.println("10000 BFS took " + bfssTookMillis + " millis");
        System.out.println("Average BFS took " + Arrays.stream(stepsTookEachTime).asLongStream().average().getAsDouble() + " steps");

        stepsTookEachTime = new int[10000];
        LocalDateTime startAStar = LocalDateTime.now();

        for (int i = 0; i < 10000; i++) {
        	AtomicInteger count = new AtomicInteger(0);
            PathNode<MazeTravelState> solution2 = PathNodeUtil.<MazeTravelState>astar(m.getStart(), MazeTravelState::isGoalAcheived, m::successors, m::manhattanDistanceToGoal, count);

            if (solution2 == null) {
//                System.out.println("No solution was found using breit-first search");
            } else {
                List<MazeTravelState> path2 = PathNodeUtil.nodeToPath(solution2);
                astarsTookMillis = Duration.between(startAStar, LocalDateTime.now()).toMillis();
                stepsTookEachTime[i] = count.get();
                m.mark(path2, Maze.Cell.PATH);
//                System.out.println(m);
                m.mark(path2, Maze.Cell.EMPTY);
            }
        }

        System.out.println("10000 A* took " + astarsTookMillis + " millis");
        System.out.println("Average A* took " + Arrays.stream(stepsTookEachTime).asLongStream().average().getAsDouble() + " steps");

//        Assertions.assertTrue(bfssTookMillis * 1.5d < dfssTookMillis);
//        Assertions.assertFalse(bfssTookMillis * 3 < dfssTookMillis);
    }

}