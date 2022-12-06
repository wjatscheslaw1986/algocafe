/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.objects;

public class CrossroadsNode {
    private Crossroad crossroad;

    public CrossroadsNode(Crossroad c) {
        this.crossroad = c;
    }
    public Crossroad getCrossroad() {
        return crossroad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CrossroadsNode cityNode = (CrossroadsNode) o;

        return crossroad == cityNode.getCrossroad();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + crossroad.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return crossroad.toString();
    }
}