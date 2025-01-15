/**
 * Wjatscheslaw Michailov (taleskeeper@yandex.com) All rights reserved © 2025.
 */
package algos.sort;

import java.util.function.Supplier;

/**
 * Shell sort algorithm implementation.
 * 
 * @author Wjatscheslaw Michailov 12.01.2025
 */
public class ShellSort {

	/**
	 * Complexity cannot be theoretically explained. Experimental estimations vary
	 * from O(N^(3/2)) to O(N^(7/6)) - Robert W. Lafore "Data Structures and
	 * Algorithms in Java", Chapter 7. 2003
	 *
	 * @param array     an array to sort
	 * @param reverse   should order be reversed? 'true' if reverse, else 'false'
	 * @param generator next step index supplier function
	 * @return a sorted array
	 */
	public static <T extends Comparable<T>> T[] sort(final T[] array, final boolean reverse,
			final StepGenerator generator) {
		final var stepGen = generator.create(array.length);
		int step = stepGen.get();
		while (step > 0) {
			for (int i = step; i < array.length; i++) {
				T tmp = array[i];
				int j = i;
					while (j > step - 1 && (array[j - step].compareTo(tmp) > 0 ^ reverse)) {
						T tmp1 = array[j];
						array[j] = array[j - step];
						array[j - step] = tmp1;
						j -= step;
					}
				array[j] = tmp;
			}
			step = stepGen.get();
		}
		return array;
	}

	/**
	 * Shell sort efficiency depends much on how we choose steps over the sequence of elements to swap them.
	 * This is why there are several possible algorithms for calculating the initial step index.
	 * This factory enum is used to create step generators (i.e. initial step index suppliers), 
	 * each for its own use case.<p>
	 * Each step generator contains the last used initial step index as a closure, 
	 * which means you are not supposed to reuse the same step generator for another time sorting. 
	 */
	public enum StepGenerator {
		SHELL(new ShellStepGeneratorFactory()), 
		HIBBARD(new HibbardStepGeneratorFactory()),
		SEDGEWICK(new SedgewickStepGeneratorFactory()), 
		KNUTH(new KnuthStepGeneratorFactory());

		private final StepGeneratorFactory factory;

		private StepGenerator(final StepGeneratorFactory f) {
			this.factory = f;
		}

		public Supplier<Integer> create(final int arrayLength) {
			return this.factory.createGenerator(arrayLength);
		}
	}

	private static sealed abstract class StepGeneratorFactory
			permits ShellStepGeneratorFactory, HibbardStepGeneratorFactory, 
			SedgewickStepGeneratorFactory, KnuthStepGeneratorFactory {

		/**
		 * Get index step generator.
		 */
		abstract Supplier<Integer> createGenerator(final int arrayLength);
	}

	private static final class ShellStepGeneratorFactory extends StepGeneratorFactory {
		@Override
		Supplier<Integer> createGenerator(int arrayLength) {
			return new Supplier<Integer>() {
				private int currentStepIndex = arrayLength / 2;

				@Override
				public Integer get() {
					int tmp = this.currentStepIndex;
					this.currentStepIndex = this.currentStepIndex / 2;
					return tmp;
				}
			};
		}
	}

	private static final class HibbardStepGeneratorFactory extends StepGeneratorFactory {
		@Override
		Supplier<Integer> createGenerator(int arrayLength) {
			return new Supplier<Integer>() {
				private int currentStepIndex = 0;

				{
					while ((int) (Math.pow(2, this.currentStepIndex) - 1) < arrayLength)
						this.currentStepIndex++;
				}

				@Override
				public Integer get() {
					return (int) (Math.pow(2, --currentStepIndex) - 1);
				}
			};
		}
	}

	private static final class SedgewickStepGeneratorFactory extends StepGeneratorFactory {
		@Override
		Supplier<Integer> createGenerator(int arrayLength) {
			return new Supplier<Integer>() {
				private int i = 0;

				{
					long number = (long) (9 * (Math.pow(2, i) - Math.pow(2, ((int) i / 2))) + 1);
					while (number < arrayLength) {
						i++;
						if (i % 2 == 0)
							number = (long) (9 * (Math.pow(2, i) - Math.pow(2, ((int) i / 2))) + 1);
						else
							number = (long) (8 * Math.pow(2, i) - 6 * Math.pow(2, (int) ((i + 1) / 2)) + 1);
					}
				}

				@Override
				public Integer get() {
					i--;
					if (i < 0) {
						return 0;
					}
					if (i % 2 == 0)
						return (int) (9 * (Math.pow(2, i) - Math.pow(2, i / 2)) + 1);
					else
						return (int) (8 * Math.pow(2, i) - 6 * Math.pow(2, (i + 1) / 2) + 1);
				}
			};
		}
	}

	private static final class KnuthStepGeneratorFactory extends StepGeneratorFactory {
		@Override
		Supplier<Integer> createGenerator(int arrayLength) {
			return new Supplier<Integer>() {
				private int i = 0;

				{
					while ((Math.pow(3, i) - 1) / 2 < (int) (arrayLength / 3)) i++;
				}

				@Override
				public Integer get() {
					return (int) ((Math.pow(3, i--) - 1) / 2);
				}
			};
		}
	}
}