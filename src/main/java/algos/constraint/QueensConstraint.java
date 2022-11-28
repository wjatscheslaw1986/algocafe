// QueensConstraint.java
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
import java.util.Map.Entry;

public class QueensConstraint extends Constraint<Integer, Integer> {
	private List<Integer> columns;

	public QueensConstraint(List<Integer> columns) {
		super(columns);
		this.columns = columns;
	}

	@Override
	public boolean satisfied(Map<Integer, Integer> assignment) {
		for (Entry<Integer, Integer> item : assignment.entrySet()) {
			// q1c = queen 1 column, q1r = queen 1 row
			int q1c = item.getKey();
			int q1r = item.getValue();
			// q2c = queen 2 column
			for (int q2c = q1c + 1; q2c <= columns.size(); q2c++) {
				if (assignment.containsKey(q2c)) {
					// q2r = queen 2 row
					int q2r = assignment.get(q2c);
					// same row?
					if (q1r == q2r) {
						return false;
					}
					// same diagonal?
					if (Math.abs(q1r - q2r) == Math.abs(q1c - q2c)) {
						return false;
					}
				}
			}
		}
		return true; // no conflict
	}


}
