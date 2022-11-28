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