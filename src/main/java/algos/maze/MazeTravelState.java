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

import java.util.Objects;

public class MazeTravelState {
	public final int row;
	public final int column;
	private final MazeTravelState goal;
	
	public MazeTravelState(int row, int column, MazeTravelState goal) {
		this.row = row;
		this.column = column;
		this.goal = goal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof MazeTravelState that))
			return false;
		return row == that.row && column == that.column;
	}

	@Override
	public int hashCode() {
		return Objects.hash(row, column);
	}

	public boolean isGoalAcheived() {
		return goal != null && goal.equals(this);
	}

}