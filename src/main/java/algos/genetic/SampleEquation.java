/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
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
