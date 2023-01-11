/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.datastructure;

/**
 * This class is an approach for universally overcoming recursion.
 * Since stack is how JVM internally handles recursive method calls,
 * here we imitate it using same data structure;
 *
 * @param <P> each recursive method call carries its state, which is recursive method argument values. The generic type P is the recursive call state type.
 */
public class ExecutionStack<P> {
    private int maxSize;
    private P[] stack;
    private int top;
    public ExecutionStack(int s) {
        maxSize = s;
        stack = (P[]) new Object[maxSize];
        top = -1;
    }

    public P peek() {
        return stack[top];
    }

    public P pop() {
        return stack[top--];
    }

    public void push(P p) {
        stack[++top] = p;
    }
}