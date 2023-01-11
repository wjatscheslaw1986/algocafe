/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.recursion;

import java.util.*;

public class Rucksack {

    private int weightSum;
    private Map<String, Integer> weights;
    private int weightGoal;
    private List<String> result = null;


    public Rucksack(Map<String, Integer> weights, int wGoal) {
        this.weights = weights;
        this.weightSum = weights.values().stream().mapToInt(i -> i).sum();
        if (wGoal > weightSum) throw new IllegalArgumentException("No such weight");
        if (wGoal < 0) throw new IllegalArgumentException("Weight must be a positive value");
        this.weightGoal = wGoal;
    }

    public List<String> packToWeight() {
        pickNext(new LinkedList<>());
        return result;
    }

    public void pickNext(List<String> picked) {
        int currentW = picked.stream().mapToInt(item -> weights.get(item)).sum();
        if (currentW == weightGoal) {
            result = picked;
            return;
        }
        List<String> available = weights.keySet().stream()
                .filter(key -> !picked.contains(key))
                .toList();
        if (available.isEmpty()) return;
        for (String a : available) {
            if (currentW + weights.get(a) == weightGoal) {
                picked.add(a);
                result = picked;
                return;
            }
        }
        for (String a : available) {
            if (currentW + weights.get(a) < weightGoal) {
                List<String> l = new LinkedList<>(picked);
                l.add(a);
                pickNext(l);
            }
        }
    }
}