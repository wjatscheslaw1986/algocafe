///*
// * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
// */
//package algos.puzzle8;
//
//import edu.princeton.cs.algs4.In;
//import edu.princeton.cs.algs4.StdOut;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class Puzzle8Tests {
//
//    @Test
//    public void test() {
//        String[] args = {"src/test/java/algos/puzzle8/puzzle3x3.txt"};
//        // for each command-line argument
//        for (String filename : args) {
//
//            // read in the board specified in the filename
//            In in = new In(filename);
//            int n = in.readInt();
//            int[][] tiles = new int[n][n];
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    tiles[i][j] = in.readInt();
//                }
//            }
//
////          solve the slider puzzle
//            Board initial = new Board(tiles);
//
//            StdOut.println("Twin:\n");
//            StdOut.println(initial.twin());
//
//            for (Board b : initial.neighbors()) StdOut.println(b);
//
//            Solver solver = new Solver(initial);
//
//            // print solution to standard output
//            if (!solver.isSolvable())
//                StdOut.println(filename + ": No solution possible");
//            else {
//                StdOut.println(filename + ": Minimum number of moves = " + solver.moves());
//                for (Board board : solver.solution())
//                    StdOut.println(board);
//            }
//
//        }
//    }
//
//    @Test
//    public void equalsTest() {
//        int[][] tiles = new int[3][3];
//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles[0].length; j++) {
//                if (i == tiles.length - 1 && j == tiles[0].length - 1) {
//                    tiles[j][i] = 0;
//                    continue;
//                }
//                tiles[j][i] = i + (j * tiles.length) + 1;
//            }
//        }
//        Board board = new Board(tiles);
//        System.out.println(board.toString());
//        Board board2 = new Board(tiles);
//        System.out.println(board2.toString());
//        Assertions.assertEquals(board, board2);
//        board2 = board2.twin();
//        System.out.println(board2.toString());
//        Assertions.assertNotEquals(board, board2);
//
//    }
//
//    @Test
//    public void hammingTest() {
//        int[][] tiles = new int[3][3];
//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles[0].length; j++) {
//                if (i == tiles.length - 1 && j == tiles[0].length - 1) {
//                    tiles[j][i] = 0;
//                    continue;
//                }
//                tiles[j][i] = i + (j * tiles.length) + 1;
//            }
//        }
//        Board board = new Board(tiles);
//        System.out.println(board.toString());
//        Assertions.assertEquals(0, board.hamming());
//        board = board.twin();
//        System.out.println(board.toString());
//        Assertions.assertNotEquals(0, board.hamming());
//        Assertions.assertEquals(2, board.hamming());
//    }
//
//    @Test
//    public void manhattanTests() {
//        int[][] tiles = new int[3][3];
//        for (int i = 0; i < tiles.length; i++) {
//            for (int j = 0; j < tiles[0].length; j++) {
//                if (i == tiles.length - 1 && j == tiles[0].length - 1) {
//                    tiles[j][i] = 0;
//                    continue;
//                }
//                tiles[j][i] = i + (j * tiles.length) + 1;
//            }
//        }
//        Board board = new Board(tiles);
//        System.out.println(board.toString());
//        Assertions.assertEquals(0, board.manhattan());
//        board = board.twin();
//        System.out.println(board.toString());
//        Assertions.assertEquals(2, board.manhattan());
//        board = swapCopy(tiles, 1, 2, 2, 2);
//        System.out.println(board.toString());
//        Assertions.assertEquals(1, board.manhattan());
//        board = swapCopy(tiles, 2, 2, 1, 2);
//        System.out.println(board.toString());
//        Assertions.assertEquals(1, board.manhattan());
//        board = swapCopy(tiles, 2, 1, 2, 2);
//        System.out.println(board.toString());
//        Assertions.assertEquals(1, board.manhattan());
//        board = swapCopy(tiles, 2, 2, 2, 1);
//        System.out.println(board.toString());
//        Assertions.assertEquals(1, board.manhattan());
//
//        board = swapCopy(tiles, 1, 2, 2, 2).twin();
//        System.out.println(board.toString());
//        Assertions.assertEquals(3, board.manhattan());
//        board = swapCopy(tiles, 2, 2, 1, 2).twin();
//        System.out.println(board.toString());
//        Assertions.assertEquals(3, board.manhattan());
//        board = swapCopy(tiles, 2, 1, 2, 2).twin();
//        System.out.println(board.toString());
//        Assertions.assertEquals(3, board.manhattan());
//        board = swapCopy(tiles, 2, 2, 2, 1).twin();
//        System.out.println(board.toString());
//        Assertions.assertEquals(3, board.manhattan());
//    }
//
//    private Board swapCopy(int[][] brd, int x1, int y1, int x2, int y2) {
//        int[][] brdCopy = new int[brd.length][brd[0].length];
//        for (int y = 0; y < brd.length; y++) {
//            int[] tmp = new int[brd.length];
//            System.arraycopy(brd[y], 0, tmp, 0, brd.length);
//            brdCopy[y] = tmp;
//        }
//        int temp = brdCopy[y2][x2];
//        brdCopy[y2][x2] = brdCopy[y1][x1];
//        brdCopy[y1][x1] = temp;
//        return new Board(brdCopy);
//    }
//}