/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.constraint;

import java.util.Objects;

public class LinearEquationCoefficient {

    String l;
    long c = 0L;

    public LinearEquationCoefficient(String letter, long coefficient) {
        l = letter;
        c = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinearEquationCoefficient coefficient = (LinearEquationCoefficient) o;
        return l.equals(coefficient.l);
    }

    @Override
    public int hashCode() {
        return Objects.hash(l);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(c).append("*").append(l);
        return sb.toString();
    }

}
