package algos.hanoi;

public class HanoiDisc implements Comparable<HanoiDisc> {
	private final Integer diameter;
	
	public HanoiDisc(int diameter) {
		this.diameter = Integer.valueOf(diameter);
	}

	public Integer getDiameter() {
		return diameter;
	}

	@Override
	public int compareTo(HanoiDisc o) {
		return o.getDiameter() == this.diameter ? 0 : o.getDiameter() < this.diameter ? 1 : -1;
	}	
}