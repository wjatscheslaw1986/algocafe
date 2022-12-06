/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

import java.util.Set;

public interface Graph<N, R extends Rib> {
    public abstract int getNodeCount();
    public abstract int getRibCount();
    public abstract N nodeAt(int i);
    public abstract int indexOf(N node);
    public abstract Set<N> successorsOf(N node);
    public abstract Set<N> successorsOf(int i);
    public abstract Set<R> ribsOf(N node);
    public abstract Set<R> ribsOf(int i);
    public abstract void connectNodes(N from, N to);
    public abstract void connectNodes(int from, int to);
    public abstract void disconnectNodes(N from, N to);
    public abstract void disconnectNodes(int from, int to);
}
