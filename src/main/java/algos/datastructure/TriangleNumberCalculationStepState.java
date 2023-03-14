/*
 * Copyright © 2023. Viacheslav Mikhailov https://github.com/vjatscheslaw
 */
package algos.datastructure;

/**
 * This record is a state of a calculation step
 *
 * @param currentStepValue the triangle calculated number value at current step
 * @param nextExecutionBranchCode a 'what to do next' code (program manual branching instead of naive recursion)
 */
public record TriangleNumberCalculationStepState(int currentStepValue, int nextExecutionBranchCode) {}