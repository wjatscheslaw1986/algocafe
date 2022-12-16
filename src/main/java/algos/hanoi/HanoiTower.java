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

import java.util.concurrent.ConcurrentLinkedDeque;

public class HanoiTower {
	private final String name;
	private final ConcurrentLinkedDeque<HanoiDisc> stack;
	
	public HanoiTower(String name) {
		this.name = name;
		this.stack = new ConcurrentLinkedDeque<HanoiDisc>();
	}
	
	public HanoiDisc pop() {
		var result = stack.pop();
		System.out.println("Сняли кольцо диаметра " + result.getDiameter() + " с башни " + name);
		return result;
	}
	
	public void push(HanoiDisc ring) {
		System.out.println("Надеваем кольцо диаметра " + ring.getDiameter() + " на башню " + name);
		this.stack.push(ring);
	}	
}