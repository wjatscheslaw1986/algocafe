/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

import java.util.Set;

public interface DirectedGraph<N, A extends Arc> extends Graph<N, A> {
    @Override
    default Set<A> ribsOf(int i) {
        return arcsOf(i);
    }

    @Override
    default Set<A> ribsOf(N node) {
        return arcsOf(node);
    }

    @Override
    default int getRibCount() {
        return getArcCount();
    }

    abstract Set<A> arcsOf(int i);

    abstract Set<A> arcsOf(N node);

    abstract int getArcCount();
}