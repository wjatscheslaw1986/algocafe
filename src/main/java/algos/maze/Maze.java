// MODIFIED BY VIACHESLAV MIKHAILOV
// From Classic Computer Science Problems in Java
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
package algos.maze;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {

    @Getter
    private final int rows, columns;
    @Getter
    private final MazeTravelState start, goal;
    private Cell[][] grid;
    private final float sparseness;

    public Maze() {
        this(10, 10, new MazeTravelState(0, 0, null), new MazeTravelState(9, 9, null), .1f);
    }

    public Maze(int rows, int columns, MazeTravelState start, MazeTravelState goal, float sparseness) {
        this.columns = columns;
        this.rows = rows;
        this.grid = new Cell[rows][columns];
        this.goal = goal;
        this.start = start;
        if (rows < 1 || columns < 1) throw new IllegalArgumentException();
        this.sparseness = sparseness;
        grid[start.row][start.column] = Cell.START;
        grid[goal.row-1][goal.column-1] = Cell.GOAL;
        for (int i = 0; i < rows; i++)
            for (int y = 0; y < columns; y++)
                grid[i][y] = Cell.EMPTY;
        randomlyFill();
    }

    public enum Cell {

        EMPTY(" "), BLOCKED("X"), START("S"), GOAL("G"), PATH("*");

        private final String code;

        Cell(String s) {
            this.code = s;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    private void randomlyFill() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        for (int i = 0; i < this.rows; i++)
            for (int y = 0; y < this.columns; y++)
                if (r.nextFloat(.0f, 1.0f) < this.sparseness)
                    this.grid[i][y] = Cell.BLOCKED;
    }

    /**
     * This function finds all possible advancements from that current maze location
     *
     * @param ml - current maze location
     * @return list of all possible advancements
     */
    public List<MazeTravelState> successors(MazeTravelState ml) {
        List<MazeTravelState> mls = new ArrayList<>();
        if (ml.row + 1 < rows && grid[ml.row + 1][ml.column] != Cell.BLOCKED) {
            mls.add(new MazeTravelState(ml.row + 1, ml.column, goal));
        }
        if (ml.row - 1 >= 0 && grid[ml.row - 1][ml.column] != Cell.BLOCKED) {
            mls.add(new MazeTravelState(ml.row - 1, ml.column, goal));
        }
        if (ml.column + 1 < columns && grid[ml.row][ml.column + 1] != Cell.BLOCKED) {
            mls.add(new MazeTravelState(ml.row, ml.column + 1, goal));
        }
        if (ml.column - 1 >= 0 && grid[ml.row][ml.column - 1] != Cell.BLOCKED) {
            mls.add(new MazeTravelState(ml.row, ml.column - 1, goal));
        }
        return mls;
    }

    public void mark(List<MazeTravelState> path, Cell type) {
        for (MazeTravelState ml : path) grid[ml.row][ml.column] = type;
        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    public double euclideanDistanceToGoal(MazeTravelState ml) {
        int xdist = Math.abs(ml.column - goal.column);
        int ydist = Math.abs(ml.row - goal.row);
        return Math.sqrt(Math.pow(xdist, 2) + Math.pow(ydist, 2));
    }

    public double manhattanDistanceToGoal(MazeTravelState ml) {
        int xdist = Math.abs(ml.column - goal.column);
        int ydist = Math.abs(ml.row - goal.row);
        return xdist + ydist;
    }

  
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : grid) {
            for (Cell c : row)
                sb.append(c.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}