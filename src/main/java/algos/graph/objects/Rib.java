/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

/**
 * Rib is a representation of a connection between two nodes of an undirected graph.
 */
public class Rib {

    public final int from, to;

    /**
     * The words 'from' and 'to' don't actually mean direction here, so that you may assign them vice versa, when instantiating an object of this class.
     * The naming 'to' and 'from' has been preserved for inheritance purpose only
     *
     * @param from - any of the two nodes index
     * @param to - any other of the two nodes index
     */
    public Rib(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rib rib = (Rib) o;

        if (((this.from == rib.from)&&(this.to == rib.to)) || ((this.to == rib.from)&&(this.from == rib.to))) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        return result;
    }

    @Override
    public String toString() {
        return from + " --- " + to ;
    }
}