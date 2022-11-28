// SendMoreMoneyConstraint.java
// From Classic Computer Science Problems in Java Chapter 3
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package algos.constraint;

import java.util.List;
import java.util.Map;

public class LinearEquationSystemConstraint extends Constraint<LinearEquationCoefficient, Long> {

    public LinearEquationSystemConstraint(List<LinearEquationCoefficient> coefficients) {
        super(coefficients);
    }

    @Override
    public boolean satisfied(Map<LinearEquationCoefficient, Long> assignment) {
        // if there are duplicate values then it's not a solution
//		if ((new HashSet<>(assignment.values())).size() < assignment.size())
//			return false;

        // if all variables have been assigned, check if it adds correctly
        if (assignment.size() == variables.size()) {
            long result = 0L;
            for (LinearEquationCoefficient coefficient : assignment.keySet()) {
                result += (long) (assignment.get(coefficient) * coefficient.c);
            }
            return result == 0L;
        }
        return true; // no conflicts
    }
}