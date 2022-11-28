// WordSearchConstraint.java
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

import algos.constraint.WordGrid.GridLocation;

import java.util.*;

public class WordSearchConstraint extends Constraint<String, List<GridLocation>> {

	public WordSearchConstraint(List<String> words) {
		super(words);
	}

	@Override
	public boolean satisfied(Map<String, List<GridLocation>> assignment) {
		// combine all GridLocations into one giant List
		List<GridLocation> allLocations = assignment.values().stream()
				.flatMap(Collection::stream).toList();
		// a set will eliminate duplicates using equals()
		Set<GridLocation> allLocationsSet = new HashSet<>(allLocations);
		// if there are any duplicate grid locations then there is an overlap
		return allLocations.size() == allLocationsSet.size();
	}
}