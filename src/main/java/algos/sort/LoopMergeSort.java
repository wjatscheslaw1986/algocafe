/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 * This code is tribute to Rama Hoetzlein https://stackoverflow.com/users/2434404/rama-hoetzlein
 */
package algos.sort;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class LoopMergeSort<E extends Comparable<E>> {

	private E[] array;
	private E[] workspace;
	private final boolean reverse;

	public LoopMergeSort(E[] array, boolean reverse) {
		this.array = array;
		this.workspace = (E[]) new Comparable[array.length];
		this.reverse = reverse;
		sort();
	}

	public LoopMergeSort(E[] array, boolean reverse, ThreadPoolExecutor pool) {
		this.array = array;
		this.workspace = (E[]) new Comparable[array.length];
		this.reverse = reverse;
		parallelSort(pool);
		while (pool.getActiveCount() > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}

	private void sort() {
		for (int k = 1; k < array.length; k *= 2)
			for (int left = 0; left + k < array.length; left += k * 2)
				merge(workspace, left, left + k, left + k + k - 1, reverse);
	}

	private void parallelSort(ThreadPoolExecutor pool) {
		int parallelism = Integer.MAX_VALUE;
		Semaphore smphr = new Semaphore(parallelism);
//		AtomicInteger n = new AtomicInteger(4);
		Outer:
		for (int k = 1; k < array.length; k *= 2) {
			for (int left = 0; left + k < array.length; left += k * 2) {
				final int finLeft = left;
				final int finK = k;
				
				try {
					smphr.acquire();
					//System.out.println("Semaphore slots remain: " + n.decrementAndGet());
				} catch (InterruptedException e) {
					e.printStackTrace();
					break Outer;
				}
				pool.submit(() -> {
					merge(workspace, finLeft, finLeft + finK, finLeft + finK + finK - 1, reverse);
					//System.out.println("Semaphore slots available: " + n.incrementAndGet());
					smphr.release();
				});
			}
//			long processorBurnCount = 0L; 
			while (smphr.availablePermits() < parallelism) {
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				processorBurnCount++;
			}
//			System.out.println("ProcBurnCount " + processorBurnCount);
		}
	}

	private void merge(E[] workspace, int offset, int firstRightIndex, int lastRightIndex, boolean reverse) {
		if (lastRightIndex > array.length - 1)
			lastRightIndex = array.length - 1;

		int workspaceLeftCursor = offset;
		int arrayLeftCursor = offset;
		int arrayMiddleCursor = firstRightIndex;

		while (arrayLeftCursor < firstRightIndex && arrayMiddleCursor <= lastRightIndex)
			if (reverse) {
				if (array[arrayLeftCursor].compareTo(array[arrayMiddleCursor]) > 0)
					workspace[workspaceLeftCursor++] = array[arrayLeftCursor++];
				else
					workspace[workspaceLeftCursor++] = array[arrayMiddleCursor++];
			} else {
				if (array[arrayLeftCursor].compareTo(array[arrayMiddleCursor]) < 0)
					workspace[workspaceLeftCursor++] = array[arrayLeftCursor++];
				else
					workspace[workspaceLeftCursor++] = array[arrayMiddleCursor++];
			}

		while (arrayLeftCursor < firstRightIndex)
			workspace[workspaceLeftCursor++] = array[arrayLeftCursor++];

		while (arrayMiddleCursor <= lastRightIndex)
			workspace[workspaceLeftCursor++] = array[arrayMiddleCursor++];

		for (int i = offset; i <= lastRightIndex; i++)
			array[i] = workspace[i];
	}
}