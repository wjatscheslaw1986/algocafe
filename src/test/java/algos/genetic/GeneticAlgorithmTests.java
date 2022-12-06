/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.genetic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithmTests {

    @Test
    public void gaTest() {
        ArrayList<SampleEquation> initialPopulaton = new ArrayList<>();
        final int POPULATION_SIZE = 20;
        final int GENERATIONS_AMOUNT = 100;
        final double THRESHOLD = 13.0d;
        for (int i = 0; i < POPULATION_SIZE; i++)
            initialPopulaton.add(SampleEquation.randomInstance());
        GeneticAlgorithm<SampleEquation> geneticAlgorithm = new GeneticAlgorithm<>(
                initialPopulaton,
                .1d,
                .7d,
                GeneticAlgorithm.SelectionType.TOURNAMENT,
                ThreadLocalRandom.current()
                );
        SampleEquation result  = geneticAlgorithm.run(GENERATIONS_AMOUNT, THRESHOLD);
        System.out.println(result.toString());
    }
}
