/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.sort;

import java.util.concurrent.LinkedBlockingDeque;

public class StackQuickSort<E extends Comparable<E>> {
    private E[] array;
    private int executionBranchCode;
    private final boolean reverse;
    private LinkedBlockingDeque<QuickSortStepState> stack;

    public StackQuickSort(E[] arrayToSort, boolean reverse) {
        this.executionBranchCode = 1; // next step execution branch
        this.reverse = reverse;
        this.stack = new LinkedBlockingDeque<>();
        this.array = arrayToSort;
        while (!sort());
    }

    private boolean sort() {
        switch (executionBranchCode) {
            case 1: // put first element on top of the stack
                stack.push(new QuickSortStepState(0, array.length - 1, 0)); // return address 0 is a termination code. But we won't even need it to terminate in case of quicksort
                executionBranchCode = 2; // check up to which triangle number in order our caller has requested to calculate
                break;
            case 2: // Check base condition and fill the stack with next execution steps
                if (stack.peek().right() - stack.peek().left() < 1) {
                    executionBranchCode = 5; // this execution branch has reached the base case. What to do next?
                    break;
                } else if (stack.peek().right() - stack.peek().left() == 1) {
                    if (reverse) {
                        if (array[stack.peek().left()].compareTo(array[stack.peek().right()]) < 0) QuickSort.<E>swap(array, stack.peek().left(), stack.peek().right());
                    } else {
                        if (array[stack.peek().left()].compareTo(array[stack.peek().right()]) > 0) QuickSort.<E>swap(array, stack.peek().left(), stack.peek().right());
                    }
                    executionBranchCode = 5; // this execution branch has reached the base case. What to do next?
                    break;
                } else if (stack.peek().right() - stack.peek().left() == 2) {
                    QuickSort.<E>manualSort3Elements(array, stack.peek().left(), reverse);
                    executionBranchCode = 5; // this execution branch has reached the base case. What to do next?
                    break;
                } else
                    executionBranchCode = 3; // further calculation (an imitation of a recursive call)
                break;
            case 3: // proceed calculation (the 'recursive' case)
                int pivot = QuickSort.<E>partition(array, stack.peek().left(), stack.peek().right(), reverse);
                int currentLeft = stack.peek().left();
                int currentRight = stack.peek().right();
                stack.pop(); // previous execution step is obsolete now since we don't accumulate anything in quicksort
                stack.push(new QuickSortStepState(currentLeft, pivot - 1, 2));
                stack.push(new QuickSortStepState(pivot + 1, currentRight, 2)); // the fresh top of the stack
                executionBranchCode = 2; // every time we need to check if we have reached the base case with the fresh top of the stack
                break;
            case 5: // a leaf in execution tree. We need to make a step back, to return on track of another branch of execution
                executionBranchCode = stack.pop().nextExecutionBranchCode(); // get code (what to do next?) and pull the element out
                if (stack.isEmpty()) return true;
                break;
            default: // any other code - exit the execution
                return true; // set termination value
        }
        return false; // set non-termination value
    }

    /**
     * This record is a state
     *
     * @param left left cursor (index, inclusive) for sorting algorithm
     * @param right right cursor (index, inclusive) for sorting algorithm
     * @param nextExecutionBranchCode a 'what to do next' code (program manual branching for overcoming recursion)
     */
    public record QuickSortStepState(int left, int right, int nextExecutionBranchCode) {}
}