// Modified by Viacheslav Mikhailov
// From Classic Computer Science Problems in Java
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

import algos.constraint.exception.ConstraintSatisfactionProblemException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Constraint Satisfaction Problem code representation
 *
 * @param <V> - variables' collection type
 * @param <D> - mapping variables on domains of their possible values type
 */
public class CSP<V, D> {
	private final List<V> variables; // given variables to work with
	private final Map<V, List<D>> domains; // each variable has its domain, as if it was a math function with discrete options for values of its variables. Keys of this map are taken from variables list
	private final Map<V, List<Constraint<V, D>>> constraints = new HashMap<>(); // each variable must submit to its constraints

	/**
	 * The constructor for the Constraint Satisfaction Problem code representation
	 * size of variables collection must be equal to size of variables on domains mapping
	 *
	 * @param variables - variables to process
	 * @param domains - domains for each variable
	 */
	public CSP(List<V> variables, Map<V, List<D>> domains) {
		this.variables = variables;
		this.domains = domains;
		for (V variable : variables) {
			constraints.put(variable, new ArrayList<Constraint<V, D>>());
			if (!domains.containsKey(variable))
				throw new ConstraintSatisfactionProblemException("Every variable should have a domain assigned to it.");
		}
		if (variables.size() != domains.size() || variables.size() != constraints.size()) throw new ConstraintSatisfactionProblemException("Failed to create a Constraint Satisfaction Problem object. Unequal collections size");
	}

	/**
	 * The method adds a new Constraint object to its list-value of the variables-on-their-constraints mapping, found by the Constraint key.
	 * This is done for every variable aggregated in the Constraint object in a Collection
	 *
	 * @param constraint - the added Constraint object
	 */
	public void addConstraint(Constraint<V, D> constraint) {
		for (V variable : constraint.variables) {
			if (!variables.contains(variable)) throw new ConstraintSatisfactionProblemException("Variable in constraint not in CSP variables list");
			constraints.get(variable).add(constraint);
		}
	}

	// Check if the value assignment is consistent by checking all constraints
	// for the given variable against it
	public boolean consistent(V variable, Map<V, D> assignment) {
		for (Constraint<V, D> constraint : constraints.get(variable))
			if (!constraint.satisfied(assignment)) return false;
		return true;
	}

	public Map<V, D> backtrackingSearch(Map<V, D> assignment) {
		// assignment is complete if every variable is assigned (our base case)
		if (assignment.size() == variables.size()) return assignment;
		// get any first variable in the CSP which isn't in the assignment
		V unassigned = variables.stream().filter(v -> !assignment.containsKey(v)).findFirst().orElseThrow(() -> new ConstraintSatisfactionProblemException("At least one duplicate in variables list."));
		// get the every possible domain value for the unassigned variable
		for (D domain : domains.get(unassigned)) {
			// shallow copy of assignment that we can change
			Map<V, D> localAssignment = new HashMap<>(assignment);
			localAssignment.put(unassigned, domain);
			// if we're still consistent, we recurse (continue)
			if (consistent(unassigned, localAssignment)) {
				Map<V, D> result = backtrackingSearch(localAssignment);
				// if we didn't find the result, we will end up backtracking
				if (result != null) return result;
			}
		}
		return null;
	}
}
