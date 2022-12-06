/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm<C extends Chromosome<C>> {

    private ArrayList<C> population;
    private double mutationChance;
    private double crossoverChance;
    private SelectionType selectionType;
    private ThreadLocalRandom random;

    public GeneticAlgorithm(List<C> population, double mutationChance, double crossoverChance, SelectionType selectionType, ThreadLocalRandom random) {
        this.population = new ArrayList<>(population);
        this.mutationChance = mutationChance;
        if (crossoverChance < .0d || crossoverChance > 1.0d)
            throw new IllegalArgumentException("Warning: crossover chance must be a floating point value between 0 and 1.");
        this.crossoverChance = crossoverChance;
        this.selectionType = selectionType;
        this.random = random;
    }

    public enum SelectionType {
        ROULETTE, TOURNAMENT;
    }

    /**
     * The algorithm picks the given amount of individuals out of the population collection based on their individual fitness.
     * This algorithm is based on a roulette principle.
     *
     * @param wheel - an array of all individuals (chomosomes) of the given population presented as ratios of
     *              each of their individual fitnesses to a total fitness of the population. Sum of all the array elements gives 1.0
     * @param numPicks - number of most fit individuals we want to pick
     * @return a collection of individuals (chromosomes) chosen on the basis of their individual fitness
     */
    private List<C> pickRoulette(double[] wheel, int numPicks) {
        List<C> picks = new ArrayList<>();
        for (int i = 0; i < numPicks; i++) {
            double pick = this.random.nextDouble();
            for (int j = 0; j < wheel.length; j++) {
                pick -= wheel[j];
                if (pick <= .0d) {
                    picks.add(this.population.get(j));
                    break;
                }
            }
        }
        return picks;
    }

    /**
     * The algorithm picks the given amount of individuals out of the population collection based on their individual fitness.
     * This algorithm is based on a tournament principle.
     *
     * @param numParticipants - we may want to limit the number of participants of the whole population
     * @param numPicks - number of most fit individuals we want to pick
     * @return a collection of individuals (chromosomes) chosen on the basis of their individual fitness
     */
    private List<C> pickTournament(int numParticipants, int numPicks) {
        Collections.shuffle(this.population);
        List<C> tournament = this.population.subList(0, numParticipants);
        tournament.sort(Collections.reverseOrder());
        return tournament.subList(0, numPicks);
    }

    /**
     * The method steps a generation forth by reproduction
     */
     private void reproduceAndReplace() {
        ArrayList<C> nextPopulation = new ArrayList<>();
        while(nextPopulation.size() < this.population.size()) {
            List<C> parents;
            if (this.selectionType == SelectionType.ROULETTE) {
                final double totalFitnessLocal = this.population.parallelStream().mapToDouble(C::fitness).sum();
                double[] wheel = this.population.stream().mapToDouble((C chromo) -> chromo.fitness() / totalFitnessLocal).toArray();
                parents = pickRoulette(wheel, 2);
            } else {
                parents = pickTournament(this.population.size() / 2, 2);
            }
            if (random.nextDouble() < crossoverChance) {
                C parent1 = parents.get(0);
                C parent2 = parents.get(1);
                nextPopulation.addAll(parent1.crossover(parent2));
            } else {
                nextPopulation.addAll(parents);
            }
        }
        if (nextPopulation.size() > this.population.size()) {
            nextPopulation.remove(0);
        }
        this.population = nextPopulation;
    }

    /**
     * Gives every individual a chance to mutate
     */
    private void mutate() {
         for (C individual : population) if (random.nextDouble() < mutationChance) individual.mutate();
    }

    /**
     * The method applies genetic algorithm on a given population using provided parameters
     *
     * @param maxGenerations - how many generations should we try to wait at max
     * @param threshold - fitness of a solution which we are happy with
     * @return - the solution (individual) which fits our demands
     */
    public C run(int maxGenerations, double threshold) {
        C best = Collections.max(population).copy();
        for (int generation = 0; generation < maxGenerations; generation++) {
            if (best.fitness() >= threshold) return best;
            System.out.println("Generation " + generation + " Best " + best.fitness() + " Avg " + this.population.parallelStream().mapToDouble(C::fitness).average().orElse(.0d));
            reproduceAndReplace();
            mutate();
            C highest = Collections.max(this.population);
            if (highest.fitness() > best.fitness()) best = highest.copy();
        }
        return best;
    }

}