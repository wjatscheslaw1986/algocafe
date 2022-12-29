/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */

package algos.minimax;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimaxTests {

    public static final int NUM_COLUMNS = 7;
    public static final int NUM_ROWS = 6;

    @Test
    public void c4AlphabetaTest() {
        C4Piece[][] positions = new C4Piece[NUM_COLUMNS][NUM_ROWS];
        for (int i = 0; i < NUM_COLUMNS; i++)
            for (int j = 0; j < NUM_ROWS; j++) {
                positions[i][j] = C4Piece.E;
            }
        C4Board testBoard1 = new C4Board(positions, C4Piece.R);
        Integer answer1 = Minimax.findBestMove(testBoard1, 8, true);
        Assertions.assertEquals(3, answer1);
    }

    @Test
    public void c4MinimaxTest() {
        C4Piece[][] positions = new C4Piece[NUM_COLUMNS][NUM_ROWS];
        for (int i = 0; i < NUM_COLUMNS; i++)
            for (int j = 0; j < NUM_ROWS; j++) {
                positions[i][j] = C4Piece.E;
            }
        C4Board testBoard1 = new C4Board(positions, C4Piece.R);
        Integer answer1 = Minimax.findBestMove(testBoard1, 2, false);
        Assertions.assertEquals(0, answer1);
    }

    @Test
    public void easyPositionAlphabeta() {
        TTTPiece[] toWinEasyPosition = new TTTPiece[]{
                TTTPiece.X, TTTPiece.O, TTTPiece.X,
                TTTPiece.X, TTTPiece.E, TTTPiece.O,
                TTTPiece.E, TTTPiece.E, TTTPiece.O};
        TTTBoard testBoard1 = new TTTBoard(toWinEasyPosition, TTTPiece.X);
        Integer answer1 = Minimax.findBestMove(testBoard1, 8, true);
        Assertions.assertEquals(answer1, 6);
    }

    @Test
    public void blockPositionAlphabeta() {
        TTTPiece[] toBlockPosition = new TTTPiece[]{
                TTTPiece.X, TTTPiece.E, TTTPiece.E,
                TTTPiece.E, TTTPiece.E, TTTPiece.O,
                TTTPiece.E, TTTPiece.X, TTTPiece.O};
        TTTBoard testBoard2 = new TTTBoard(toBlockPosition, TTTPiece.X);
        Integer answer2 = Minimax.findBestMove(testBoard2, 8, true);
        Assertions.assertEquals(answer2, 2);
    }

    @Test
    public void hardPositionAlphabeta() {
        TTTPiece[] toWinHardPosition = new TTTPiece[]{
                TTTPiece.X, TTTPiece.E, TTTPiece.E,
                TTTPiece.E, TTTPiece.E, TTTPiece.O,
                TTTPiece.O, TTTPiece.X, TTTPiece.E};
        TTTBoard testBoard3 = new TTTBoard(toWinHardPosition, TTTPiece.X);
        Integer answer3 = Minimax.findBestMove(testBoard3, 8, true);
        Assertions.assertEquals(answer3, 1);
    }

    @Test
    public void easyPositionMinimax() {
        TTTPiece[] toWinEasyPosition = new TTTPiece[]{
                TTTPiece.X, TTTPiece.O, TTTPiece.X,
                TTTPiece.X, TTTPiece.E, TTTPiece.O,
                TTTPiece.E, TTTPiece.E, TTTPiece.O};
        TTTBoard testBoard1 = new TTTBoard(toWinEasyPosition, TTTPiece.X);
        Integer answer1 = Minimax.findBestMove(testBoard1, 8, false);
        Assertions.assertEquals(answer1, 6);
    }

    @Test
    public void blockPositionMinimax() {
        TTTPiece[] toBlockPosition = new TTTPiece[]{
                TTTPiece.X, TTTPiece.E, TTTPiece.E,
                TTTPiece.E, TTTPiece.E, TTTPiece.O,
                TTTPiece.E, TTTPiece.X, TTTPiece.O};
        TTTBoard testBoard2 = new TTTBoard(toBlockPosition, TTTPiece.X);
        Integer answer2 = Minimax.findBestMove(testBoard2, 8, false);
        Assertions.assertEquals(answer2, 2);
    }

    @Test
    public void hardPositionMinimax() {
        TTTPiece[] toWinHardPosition = new TTTPiece[]{
                TTTPiece.X, TTTPiece.E, TTTPiece.E,
                TTTPiece.E, TTTPiece.E, TTTPiece.O,
                TTTPiece.O, TTTPiece.X, TTTPiece.E};
        TTTBoard testBoard3 = new TTTBoard(toWinHardPosition, TTTPiece.X);
        Integer answer3 = Minimax.findBestMove(testBoard3, 8, false);
        Assertions.assertEquals(answer3, 1);
    }
}