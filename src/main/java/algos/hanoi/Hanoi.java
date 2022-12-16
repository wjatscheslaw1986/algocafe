// MODIFIED BY VIACHESLAV MIKHAILOV
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
package algos.hanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Hanoi {
	private final int numDiscs;
	private final List<HanoiTower> towers;
	
	public Hanoi(int towerNum, int discs, int maxDiameter, int minDiameter) {
		if (towerNum < 3) 
			throw new IllegalArgumentException("Let more towers in");
		numDiscs = discs;
		towers = new ArrayList<HanoiTower>(towerNum);
		for (int i = 1; i <= towerNum; i++) {
			towers.add(new HanoiTower("Tower #" + Integer.valueOf(i).toString()));
		}
		for (int i = 1; i <= discs; i++) {
			towers.get(0).push(new HanoiDisc((maxDiameter - minDiameter)/i));
		}
	}
	
	public void move(HanoiTower begin, HanoiTower end, HanoiTower temp, int n) {
		if (n == 1) {
			end.push(begin.pop());
		}
		else {
			move(begin, temp, end, n-1);
			move(begin, end, null, 1);
			move(temp, end, begin, n-1);
		}
	}
	
	public void solve() {
		if (towers.size() < 3) System.out.println("С количеством башен меньше 3х эту задачу не решить");
		move(towers.get(0), towers.get(1), towers.get(towers.size()-1), numDiscs);
	}
	
	
}