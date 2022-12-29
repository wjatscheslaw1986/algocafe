// Modified by Viacheslav Mikhailov
// TTTBoard.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a state ('state' as in OOP patterns) of a TicTacToe game party
 */
public class TTTBoard implements Board<Integer> {
	private static final int NUM_SQUARES = 9;
	private TTTPiece[] position;
	private TTTPiece turn; // whose turn is now (X or O)

	public TTTBoard(TTTPiece[] position, TTTPiece turn) {
		this.position = position;
		this.turn = turn;
	}

	public TTTBoard() {
		// by default start with blank board
		position = new TTTPiece[NUM_SQUARES];
		Arrays.fill(position, TTTPiece.E);
		// X goes first
		turn = TTTPiece.X;
	}

	@Override
	public Piece getTurn() {
		return turn;
	}

	@Override
	public TTTBoard move(Integer location) {
		TTTPiece[] tempPosition = Arrays.copyOf(position, position.length);
		tempPosition[location] = turn;
		return new TTTBoard(tempPosition, turn.opposite());
	}

	/**
	 * In case of TicTacToe, any free space on the board is legal to move in
	 * @return a list of available board cells to move in
	 */
	@Override
	public List<Integer> getLegalMoves() {
		ArrayList<Integer> legalMoves = new ArrayList<>();
		for (int i = 0; i < NUM_SQUARES; i++) {
			// empty squares are legal moves
			if (position[i] == TTTPiece.E) {
				legalMoves.add(i);
			}
		}
		return legalMoves;
	}

	/**
	 * This method checks if current player (determined in 'turn' field) has just won
	 * @return 'true' if current player has won, else 'false'
	 */
	@Override
	public boolean isWin() {
		// three row, three column, and then two diagonal checks
		return checkPos(0, 1, 2) || checkPos(3, 4, 5) || checkPos(6, 7, 8)
				|| checkPos(0, 3, 6) || checkPos(1, 4, 7) || checkPos(2, 5, 8)
				|| checkPos(0, 4, 8) || checkPos(2, 4, 6);
	}

	private boolean checkPos(int p0, int p1, int p2) {
		return position[p0] == position[p1] && position[p0] == position[p2]
				&& position[p0] != TTTPiece.E;
	}

	/**
	 * This method evaluates positive value of that particular minimax recursive branch of execution.
	 * If there has already been a victory case on the board (isWin()), and the turn was passed to player 'player' then,
	 * the 'player' has lost (negative value). Otherwise, 'player' has won (positive value).
	 * Draw or pre-victory outcome has no value at all.
	 *
	 * @param player is the player whose turn is now
	 * @return an estimated value of this branch of execution for the player 'player'
	 */
	@Override
	public double evaluate(Piece player) {
		if (isWin() && turn == player) {
			return -1;
		} else if (isWin() && turn != player) {
			return 1;
		} else {
			return 0.0;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				sb.append(position[row * 3 + col].toString());
				if (col != 2) {
					sb.append("|");
				}
			}
			sb.append(System.lineSeparator());
			if (row != 2) {
				sb.append("-----");
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
}