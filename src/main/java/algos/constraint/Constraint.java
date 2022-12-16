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

import java.util.List;
import java.util.Map;

// V is the variable type, and D is the domain type
public abstract class Constraint<V, D> {

	// A unidirectional one-to-many reference. Same one constraint may be applicable to multiple variables
	protected List<V> variables;

	public Constraint(List<V> variables) {
		this.variables = variables;
	}

	// must be overridden by subclasses
	public abstract boolean satisfied(Map<V, D> assignment);
}