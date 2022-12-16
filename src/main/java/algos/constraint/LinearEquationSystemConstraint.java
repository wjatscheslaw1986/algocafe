/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.constraint;

import java.util.List;
import java.util.Map;

public class LinearEquationSystemConstraint extends Constraint<LinearEquationCoefficient, Long> {

    public LinearEquationSystemConstraint(List<LinearEquationCoefficient> coefficients) {
        super(coefficients);
    }

    @Override
    public boolean satisfied(Map<LinearEquationCoefficient, Long> assignment) {

        // if all variables have been assigned, check if it adds correctly
        if (assignment.size() == variables.size()) {
            long result = 0L;
            for (LinearEquationCoefficient coefficient : assignment.keySet()) {
                result += assignment.get(coefficient) * coefficient.c;
            }
            return result == 0L;
        }
        return true; // simply continue if not all variables have been assigned
    }
}