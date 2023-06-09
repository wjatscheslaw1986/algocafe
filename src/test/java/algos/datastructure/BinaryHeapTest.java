package algos.datastructure;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryHeapTest {

	@Test
	void testBinaryHeap() {
		int countOfElements = 900_000;
		BinaryHeap<Long> bh = new BinaryHeap<>();
		for (int i = 0; i < countOfElements; i++) {
			bh.insert(ThreadLocalRandom.current().nextLong(100L, 9_999_999L));
		}
		for (int i = 0; i < countOfElements; i += 2) {
			Assertions.assertTrue(bh.delMax() >= bh.delMax());
		}
		for (int i = 0; i < 100; i++)
			Assertions.assertDoesNotThrow(() -> bh.delMax(), "impossible!");
	}

}
