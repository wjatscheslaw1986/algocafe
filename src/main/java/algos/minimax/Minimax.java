// Modified by Viacheslav Mikhailov
// Minimax.java
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

public class Minimax {

    /**
     * This method returns a numeric evaluation of this choice of move for originalPlayer.
     * The original player is not the one, whose move is in the turn of the 'board'.
     * Instead, this method call is how the original player tries to foresee his opponent's move,
     * and his own further response to the opponent's move, and so on, recursively.
     *
     * @param board the state of board, when the original player has made his imaginary move (prediction)
     * @param maximizing is the original's player opponent a maximizing one?
     * @param originalPlayer the player who is trying to recursively foresee and quantitatively estimate the chosen move direction
     * @param maxDepth maximal depth of player's thought. Maximal amount of turn the original player will be foreseeing
     * @return the quantitative estimation of the chosen move option
     * @param <Move> position indicator type (index of the cell, etc.)
     */
    public static <Move> double minimax(Board<Move> board, boolean maximizing, Piece originalPlayer, int maxDepth) {
        // Base case - terminal position or maximum depth reached
        if (board.isWin() || board.isDraw() || maxDepth == 0) {
            return board.evaluate(originalPlayer);
        }
        // Recursive case - maximize your gains or minimize the opponent's gains
        if (maximizing) {
            double bestEval = Double.NEGATIVE_INFINITY; // result must be higher
            for (Move move : board.getLegalMoves()) {
                double result = minimax(board.move(move), false, originalPlayer, maxDepth - 1);
                bestEval = Math.max(result, bestEval);
            }
            return bestEval;
        } else { // minimizing
            double worstEval = Double.POSITIVE_INFINITY; // result must be lower
            for (Move move : board.getLegalMoves()) {
                double result = minimax(board.move(move), true, originalPlayer, maxDepth - 1);
                worstEval = Math.min(result, worstEval);
            }
            return worstEval;
        }
    }

    // Find the best possible move in the current position
    // looking up to maxDepth ahead
    public static <Move> Move findBestMove(Board<Move> board, int maxDepth, boolean alphabeta) {
        double bestEval = Double.NEGATIVE_INFINITY;
        Move bestMove = null; // won't stay null for sure
        for (Move move : board.getLegalMoves()) {
            double result;
            if (alphabeta)
                result = alphabeta(board.move(move), false, board.getTurn(), maxDepth);
            else
                result = minimax(board.move(move), false, board.getTurn(), maxDepth);
            if (result > bestEval) {
                bestEval = result;
                bestMove = move;
            }
        }
        return bestMove;
    }

    // Helper that sets alpha and beta for the first call
    public static <Move> double alphabeta(Board<Move> board, boolean maximizing, Piece originalPlayer, int maxDepth) {
        return alphabeta(board, maximizing, originalPlayer, maxDepth, Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY);
    }

    /**
     * This method is an optimized version of a recursive minimax algorithm, known as alphabeta algorithm.
     * Its improvement over a classic minimax is that it does search through all the solution tree available from
     * current position, but
     *
     * @param board a state object
     * @param maximizing 'true' if current is human player's turn, otherwise 'false'
     * @param originalPlayer player of the previous turn, whose move is the last put on the board
     * @param maxDepth how many turns ahead current player evaluates in terms of their subjective value
     * @param alpha a lower value border. The maximizing player seeks to increase it above the upper border 'beta'.
     * @param beta an upper value border. The minimizing player seeks to decrease it below the lower border 'alpha'.
     * @return an estimated subjective value of current turn for current player, which becomes alpha in case of maximizing player, otherwise beta. The higher the value the better, if the player is a maximizer. Otherwise, the lower value the better.
     * @param <Move> a type of turn identifier. In TicTacToe, it is a cell index, for example.
     */
    private static <Move> double alphabeta(Board<Move> board, boolean maximizing, Piece originalPlayer, int maxDepth,
                                           double alpha,
                                           double beta) {
        // Base case - terminal position or maximum depth reached
        if (board.isWin() || board.isDraw() || maxDepth == 0) {
            return board.evaluate(originalPlayer);
        }

        // Recursive case - maximize your gains or minimize the opponent's gains
        if (maximizing) {
            for (Move m : board.getLegalMoves()) {
                alpha = Math.max(alpha, alphabeta(board.move(m), false, originalPlayer, maxDepth - 1, alpha, beta));
                if (beta <= alpha) { // check cutoff. BEWARE: if your evaluation function returns more diverse winning and losing markers than simply 1 and -1, the <= operator will prevent from searching for an optimal victory scenario. You'd need to use < operator, then.
                    break;
                }
            }
            return alpha;
        } else { // minimizing
            for (Move m : board.getLegalMoves()) {
                beta = Math.min(beta, alphabeta(board.move(m), true, originalPlayer, maxDepth - 1, alpha, beta));
                if (beta <= alpha) { // check cutoff. BEWARE: if your evaluation function returns more diverse winning and losing markers than simply 1 and -1, the <= operator will prevent from searching for an optimal victory scenario. You'd need to use < operator, then.
                    break;
                }
            }
            return beta;
        }
    }
}