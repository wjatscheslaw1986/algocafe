/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.puzzle8;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

public class Puzzle8Tests {

    /******************************************************************************
     *  Compilation:  javac-algs4 PuzzleChecker.java
     *  Execution:    java-algs4 PuzzleChecker filename1.txt filename2.txt ...
     *  Dependencies: Board.java Solver.java
     *
     *  This program creates an initial board from each filename specified
     *  on the command line and finds the minimum number of moves to
     *  reach the goal state.
     *
     *  % java-algs4 PuzzleChecker puzzle*.txt
     *  puzzle00.txt: 0
     *  puzzle01.txt: 1
     *  puzzle02.txt: 2
     *  puzzle03.txt: 3
     *  puzzle04.txt: 4
     *  puzzle05.txt: 5
     *  puzzle06.txt: 6
     *  ...
     *  puzzle3x3-impossible: -1
     *  ...
     *  puzzle42.txt: 42
     *  puzzle43.txt: 43
     *  puzzle44.txt: 44
     *  puzzle45.txt: 45
     *
     ******************************************************************************/


    @Test
    public static void test() {
        String[] args = {"file1", "file2"};
        // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
//            Board initial = new Board(tiles);
//            Solver solver = new Solver(initial);
//            StdOut.println(filename + ": " + solver.moves());
        }
    }
}

