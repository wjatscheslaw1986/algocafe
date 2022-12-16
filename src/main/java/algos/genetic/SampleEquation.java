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
package algos.genetic;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SampleEquation extends Chromosome<SampleEquation> {
    private int x, y;
    private static final int MAX_START = 100;

    public SampleEquation(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public double fitness() {
        return 6 * x - x * x + 4 * y - y * y;
    }

    @Override
    public void mutate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (random.nextDouble() < .5d) {
            if (random.nextDouble() < .5d) {
                x += 1;
            } else {
                x -= 1;
            }
        } else {
            if (random.nextDouble() > .5d) {
                y += 1;
            } else {
                y -= 1;
            }
        }
    }

    public static SampleEquation randomInstance() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new SampleEquation(random.nextInt(MAX_START), random.nextInt(MAX_START));
    }

    @Override
    public List<SampleEquation> crossover(SampleEquation other) {
        SampleEquation se1 = new SampleEquation(x, other.y);
        SampleEquation se2 = new SampleEquation(other.x, y);
        return List.of(se1, se2);
    }

    @Override
    public SampleEquation copy() {
        return new SampleEquation(x, y);
    }

    public String toString() {
        return "X: " + x + " Y: " + y + " Fitness: " + fitness();
    }
}
