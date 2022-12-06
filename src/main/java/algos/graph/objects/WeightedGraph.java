/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

public interface WeightedGraph<N, A extends Rib> extends Graph<N, A> {
    public abstract void connectNodes(N from, N to, double weight);
    public abstract void connectNodes(int from, int to, double weight);
}
