/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

/**
 * This record is an OOP's 'State', effectively
 *
 * @param currentStepValue the triangle calculated number value at current step
 * @param nextExecutionBranchCode a 'what to do next' code (program manual branching instead of naive recursion)
 */
public record TriangleNumberCalculationStepState(int currentStepValue, int nextExecutionBranchCode) {}