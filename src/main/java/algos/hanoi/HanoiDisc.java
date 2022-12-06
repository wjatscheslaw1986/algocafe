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
		return this.diameter.compareTo(o.getDiameter());
	}	
}