/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

import java.util.concurrent.LinkedBlockingDeque;

public class QuickSortStack<E extends Comparable<E>> {
    private E[] array;
    private int executionBranchCode;
    private final boolean reverse;
    private LinkedBlockingDeque<QuickSortStepState> stack;

    public QuickSortStack(E[] arrayToSort, boolean reverse) {
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
                        if (array[stack.peek().left()].compareTo(array[stack.peek().right()]) < 0) swap(stack.peek().left(), stack.peek().right());
                    } else {
                        if (array[stack.peek().left()].compareTo(array[stack.peek().right()]) > 0) swap(stack.peek().left(), stack.peek().right());
                    }
                    executionBranchCode = 5; // this execution branch has reached the base case. What to do next?
                    break;
                } else if (stack.peek().right() - stack.peek().left() == 2) {
                    manualSort3Elements(stack.peek().left(), reverse);
                    executionBranchCode = 5; // this execution branch has reached the base case. What to do next?
                    break;
                } else
                    executionBranchCode = 3; // further calculation (an imitation of a recursive call)
                break;
            case 3: // proceed calculation (the 'recursive' case)
                int pivot = partition(stack.peek().left(), stack.peek().right(), reverse);
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
     * Partition algorithm has complexity of O(N). It moves all the lesser than the pivot elements of the given array to left,
     * and all the larger than the pivot elements of the given array to right.
     *
     * @param left  from index, inclusive
     * @param right to index, inclusive
     * @return amount of the elements in the left group of the 'array'. Also, it is an index of an already sorted element in the 'array' after partitioning
     */
    private int partition(int left, int right, boolean reverse) {
        if (left < 0 || right >= array.length)
            throw new IllegalArgumentException("the 'left' and 'right' arguments must be indices of an array");
        int leftCursor, rightCursor;
        if (reverse) {
            leftCursor = left; // for purposes of QuickSort, we exclude the very right element from partitioning and consider it to be a pivot of partitioning
            rightCursor = right + 1;
        } else {
            leftCursor = left - 1;
            rightCursor = right; // for purposes of QuickSort, we exclude the very right element from partitioning and consider it to be a pivot of partitioning
        }
        while (true) {
            if (reverse) {
                while (rightCursor > left && array[--rightCursor].compareTo(array[left]) < 0) ;
                while (leftCursor < right && array[++leftCursor].compareTo(array[left]) > 0) ;
                if (leftCursor >= rightCursor) break;
                else swap(leftCursor, rightCursor);
            } else {
                while (leftCursor < right && array[++leftCursor].compareTo(array[right]) < 0) ;
                while (rightCursor > left && array[--rightCursor].compareTo(array[right]) > 0) ;
                if (leftCursor >= rightCursor) break;
                else swap(leftCursor, rightCursor);
            }
        }
        if (reverse) {
            swap(rightCursor, left); // by the end of partitioning, 'leftCursor' is the index of the most left element of the right (larger) segment. We swap it with the pivot. Now, the pivot is the only sorted element by now (in the range between 'left' and 'right' indices, inclusive), and holds its final position (i.e. array index) till the end of QuickSort
            return rightCursor;
        } else {
            swap(leftCursor, right); // by the end of partitioning, 'leftCursor' is the index of the most left element of the right (larger) segment. We swap it with the pivot. Now, the pivot is the only sorted element by now (in the range between 'left' and 'right' indices, inclusive), and holds its final position (i.e. array index) till the end of QuickSort
            return leftCursor;
        }
    }


    private void manualSort3Elements(int from, boolean reverse) {
        if (array.length <= from)
            throw new IllegalArgumentException("Array index out of bounds: " + from + " from array length " + array.length);
        if (array.length - from == 1) return;
        if (array.length - from == 2) {
            if (reverse) {
                if (array[from].compareTo(array[from + 1]) > 0) swap(from, from + 1);
            } else {
                if (array[from].compareTo(array[from + 1]) < 0) swap(from, from + 1);
            }
        }
        if (array.length - from > 2) {
            if (reverse) {
                if (array[from].compareTo(array[from + 1]) < 0)
                    swap(from, from + 1);
                if (array[from].compareTo(array[from + 2]) < 0)
                    swap(from, from + 2);
                if (array[from + 1].compareTo(array[from + 2]) < 0)
                    swap(from + 1, from + 2);
            } else {
                if (array[from].compareTo(array[from + 1]) > 0)
                    swap(from, from + 1);
                if (array[from].compareTo(array[from + 2]) > 0)
                    swap(from, from + 2);
                if (array[from + 1].compareTo(array[from + 2]) > 0)
                    swap(from + 1, from + 2);
            }
        }
    }

    private void swap(int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}