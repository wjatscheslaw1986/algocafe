/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.dynamic;

import java.util.*;

public final class Backpack {
    private int backpackSize;
    private BackpackState[][] states;
    private String[] items;
    private HashMap<String, Double> itemsCosts;
    private HashMap<String, Integer> itemsSizes;

    public void addElement(String el) {
        BackpackState[][] updatedStates = new BackpackState[states.length + 1][states[0].length];
        String[] updatedItems = new String[items.length + 1];
        updatedItems[items.length] = el;
        System.arraycopy(items, 0, updatedItems, 0, items.length);
        items = updatedItems;
        for (int t = 0; t < states.length; t++)
            System.arraycopy(states[t], 0, updatedStates[t], 0, states[t].length);
        states = updatedStates;
    }

    public static class BackpackState implements Comparable<BackpackState> {
        long id = -1;
        private double totalCost;
        private final HashSet<String> itemsInBackpack;

        public BackpackState(long id, double totalCost, HashSet<String> itemsInBackpack) {
            this.id = id;
            this.totalCost = totalCost;
            if (id < 0 || totalCost < .0d) throw new IllegalArgumentException("No negative cost and/or ID allowed");
            this.itemsInBackpack = itemsInBackpack;
        }

        public BackpackState copy() {
            return new BackpackState(this.id, this.totalCost, new HashSet<>(itemsInBackpack));
        }

        public double getTotalCost() {
            return totalCost;
        }

        public void setTotalCost(double totalCost) {
            this.totalCost = totalCost;
        }

        public HashSet<String> getItemsInBackpack() {
            return itemsInBackpack;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BackpackState that = (BackpackState) o;

            return id == that.id;
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }

        @Override
        public int compareTo(BackpackState o) {
            return Double.compare(this.totalCost, o.totalCost);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", BackpackState.class.getSimpleName() + "[", "]")
                    .add("totalCost=" + totalCost)
                    .add("itemsInBackpack=" + itemsInBackpack)
                    .toString();
        }
    }

    public Backpack(int backpackSize, HashMap<String, Double> itemsCosts, HashMap<String, Integer> itemsSizes) {
        this.states = new BackpackState[itemsCosts.size()][backpackSize];
        this.itemsCosts = itemsCosts;
        this.itemsSizes = itemsSizes;
        this.items = itemsCosts.keySet().toArray(String[]::new);
        this.backpackSize = backpackSize;
    }

    public int getBackpackSize() {
        return backpackSize;
    }

    public BackpackState[][] getStates() {
        return states;
    }

    public String[] getItems() {
        return items;
    }

    public HashMap<String, Double> getItemsCosts() {
        return itemsCosts;
    }

    public HashMap<String, Integer> getItemsSizes() {
        return itemsSizes;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public void setStates(BackpackState[][] states) {
        this.states = states;
    }

    public void recalculate() {
        recalculate(0, items.length);
    }

    public void recalculate(int from) {
        recalculate(from, items.length);
    }

    //TODO this if-else hell could be resolved by applying "State" OOP pattern
    public void recalculate(int from, int to) {
        for (int item = from; item < to; item++) {
            for (int size = 1; size <= backpackSize; size++) {
                if (item == 0)
                    if (itemsSizes.get(items[item]) <= size)
                        states[item][size - 1] = new BackpackState(item + size - 1, itemsCosts.get(items[item]), new HashSet<>(Set.of(items[item])));
                    else
                        states[item][size - 1] = new BackpackState(item + size - 1, .0d, new HashSet<>());
                else if (size == 1) {
                    if (itemsSizes.get(items[item]) <= size)
                        states[item][size - 1] = Collections.max(List.of(states[item - 1][size - 1].copy(), new BackpackState(item + size - 1, itemsCosts.get(items[item]), new HashSet<>(Set.of(items[item])))), BackpackState::compareTo);
                    else
                        states[item][size - 1] = states[item - 1][size - 1].copy();
                } else {
                    if (itemsSizes.get(items[item]) <= size) {
                        int prevSize = size - itemsSizes.get(items[item]);
                        double prevCost = .0d;
                        HashSet<String> chosenItems = new HashSet<>();
                        if (prevSize > 0) {
                            if (states[item][prevSize - 1].getItemsInBackpack().contains(items[item])) chosenItems.addAll(states[item - 1][prevSize - 1].getItemsInBackpack());
                                else chosenItems.addAll(states[item][prevSize - 1].getItemsInBackpack());
                            for (String itemName : chosenItems) prevCost += itemsCosts.get(itemName);
                        }
                        chosenItems.add(items[item]);
                        states[item][size - 1] = Collections.max(List.of(states[item - 1][size - 1].copy(), new BackpackState(item + size - 1, itemsCosts.get(items[item]) + prevCost, chosenItems)), BackpackState::compareTo);
                    } else
                        states[item][size - 1] = states[item - 1][size - 1].copy();
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < states.length; i++) {
            Arrays.stream(states[i]).filter(Objects::nonNull).map(el -> " " + el.getTotalCost() + " ").forEach(sb::append);
        }
        return sb.toString();
    }
}