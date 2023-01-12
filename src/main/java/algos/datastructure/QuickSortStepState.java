/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

/**
 * This record is a state
 *
 * @param left left cursor (index, inclusive) for sorting algorithm
 * @param right right cursor (index, inclusive) for sorting algorithm
 * @param nextExecutionBranchCode a 'what to do next' code (program manual branching for overcoming recursion)
 */
public record QuickSortStepState(int left, int right, int nextExecutionBranchCode) {}