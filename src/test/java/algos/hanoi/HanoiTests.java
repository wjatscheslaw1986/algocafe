package algos.hanoi;

import org.junit.jupiter.api.Test;

public class HanoiTests {
	
	@Test
	public void testHanoi() {
		Hanoi ht = new Hanoi(3, 13, 2225, 5);
		ht.solve();
	}

}
