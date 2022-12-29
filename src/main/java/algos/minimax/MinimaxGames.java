// TicTacToe.java
// From Classic Computer Science Problems in Java Chapter 8
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

package algos.minimax;

import java.util.Scanner;

public class MinimaxGames {

	private TTTBoard tttBoard = new TTTBoard();
	private C4Board c4Board = new C4Board();
	private Scanner scanner = new Scanner(System.in);

	private Integer getPlayerMove() {
		int playerMove = -1;
		while (!tttBoard.getLegalMoves().contains(playerMove)) {
			System.out.println("Enter a legal square (0-8):");
			playerMove = scanner.nextInt();
		}
		return playerMove;
	}

	private void runC4() {
		// main game loop
		while (true) {
			//Integer humanMove = getPlayerMove();
			Integer humanMove = Minimax.findBestMove(c4Board, 7, true);;
			c4Board = c4Board.move(humanMove);
			if (c4Board.isWin()) {
				System.out.println("Human wins!");
				break;
			} else if (c4Board.isDraw()) {
				System.out.println("Draw!");
				break;
			}
			Integer computerMove = Minimax.findBestMove(c4Board, 7, true);
			System.out.println("Computer move is " + computerMove);
			c4Board = c4Board.move(computerMove);
			System.out.println(c4Board);
			if (c4Board.isWin()) {
				System.out.println("Computer wins!");
				break;
			} else if (c4Board.isDraw()) {
				System.out.println("Draw!");
				break;
			}
		}
	}

	private void runTicTacToe() {
		// main game loop
		while (true) {
//			Integer humanMove = getPlayerMove();
			Integer humanMove = Minimax.findBestMove(tttBoard, 9, true);
			tttBoard = tttBoard.move(humanMove);
			if (tttBoard.isWin()) {
				System.out.println(tttBoard);
				System.out.println("Human wins!");
				break;
			} else if (tttBoard.isDraw()) {
				System.out.println(tttBoard);
				System.out.println("Draw!");
				break;
			}
			Integer computerMove = Minimax.findBestMove(tttBoard, 9, true);
			System.out.println("Computer move is " + computerMove);
			tttBoard = tttBoard.move(computerMove);
			System.out.println(tttBoard);
			if (tttBoard.isWin()) {
				System.out.println(tttBoard);
				System.out.println("Computer wins!");
				break;
			} else if (tttBoard.isDraw()) {
				System.out.println(tttBoard);
				System.out.println("Draw!");
				break;
			}
		}
	}

	public static void main(String[] args) {
		new MinimaxGames().runC4();
	}

}
