/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

/**
 * Arc is a representation of a one way connection between two nodes of an oriented (directed) graph.
 */
public class Arc extends Rib {

    public Arc(int from, int to) {
        super(from, to);
    }

    public Arc reversed() {
        return new Arc(to, from);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arc arc = (Arc) o;

        if (this.from != arc.from) return false;
        return this.to == arc.to;
    }

    @Override
    public String toString() {
        return from + " --> " + to ;
    }

}