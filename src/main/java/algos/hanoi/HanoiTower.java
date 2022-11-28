package algos.hanoi;

import java.util.concurrent.ConcurrentLinkedDeque;

public class HanoiTower {
	private final String name;
	private final ConcurrentLinkedDeque<HanoiDisc> stack;
	
	public HanoiTower(String name) {
		this.name = name;
		this.stack = new ConcurrentLinkedDeque<HanoiDisc>();
	}
	
	public HanoiDisc pop() {
		var result = stack.pop();
		System.out.println("Сняли кольцо диаметра " + result.getDiameter() + " с башни " + name);
		return result;
	}
	
	public void push(HanoiDisc ring) {
		System.out.println("Надеваем кольцо диаметра " + ring.getDiameter() + " на башню " + name);
		this.stack.push(ring);
	}	
}