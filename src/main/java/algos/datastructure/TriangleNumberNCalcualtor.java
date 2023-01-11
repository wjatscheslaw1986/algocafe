/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

public class TriangleNumberNCalcualtor extends ExecutionStack<TriangleNumberCalculationStepState>{
    private int answer;
    private int executionBranchCode;
    private final int triangleNumberNo;

    public TriangleNumberNCalcualtor(int n) {
        super(n);
        executionBranchCode = 1; // next step execution branch
        triangleNumberNo = n;
        while (!step());
    }

    public int getAnswer() {
        return answer;
    }

    private boolean step() {
        switch (executionBranchCode) {
            case 1: // put first element on top of the stack
                push(new TriangleNumberCalculationStepState(triangleNumberNo, 6)); // return address 6 is a termination code. The bottom stack element must have it to terminate execution once all elements are pulled out
                executionBranchCode = 2; // check up to which triangle number in order our caller has requested to calculate
                break;
            case 2: // Check base condition and fill the stack with execution steps
                if (peek().currentStepValue() == 1) { // if user has requested a triangle number by order number 1, then it is a good time to simply return the known triangle number value (which is 1) to the user
                    answer = 1; // However, this must be also the very first yield to result when calculating any triangle number. This is the base case,
                    executionBranchCode = 5; // the stack is filled. What to do next?
                } else
                    executionBranchCode = 3; // further calculation (an imitation of a recursive call)
                break;
            case 3: // proceed calculation (the recursive case)
                push(new TriangleNumberCalculationStepState(peek().currentStepValue() - 1, 4)); // each step it brings us closer to the base case (Case 2)
                executionBranchCode = 2; // every time we check fi we have reached the base case
                break;
            case 4: // case 4 is a leaf on the execution tree. It yields to the result
                answer = answer + peek().currentStepValue(); // update the result (second and all further yields to result)
                executionBranchCode = 5; // get to know what to do next
                break;
            case 5: // this step tells us what to do next. It gets execution branch code from the recursive call on the top of the call stack.
                executionBranchCode = pop().nextExecutionBranchCode(); // get code (what to do next?) and pull the element out
                break;
            case 6: // exit execution
                return true; // set termination value
        }
        return false; // set non-termination value
    }
}