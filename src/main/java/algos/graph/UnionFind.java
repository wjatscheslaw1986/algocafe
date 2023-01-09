/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

public interface UnionFind<T> {
    public void union(T node1, T node2);
    public boolean connected(T node1, T node2);
}