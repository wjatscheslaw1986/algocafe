/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph;

import algos.graph.exception.DijkstraValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class DijkstraGraphHashtableTests {

    @Test
    public void test() {
        Map<String, Double> arcs = new HashMap<>();
        arcs.put("START->A", 6.0d);
        arcs.put("B->A", 3.0d);
        arcs.put("START->B", 2.0d);
        arcs.put("B->FINISH", 5.0d);
        arcs.put("A->FINISH", 1.0d);

        DijkstraGraphHashtable d = new DijkstraGraphHashtable(arcs);
        System.out.println(d.findShortestPathUsingDijkstra());

        Map<String, Double> arcs1 = new HashMap<>();
        arcs1.put("START->A", 5.0d);
        arcs1.put("START->B", 2.0d);
        arcs1.put("B->A", 8.0d);
        arcs1.put("B->D", 7.0d);
        arcs1.put("A->D", 2.0d);
        arcs1.put("A->C", 4.0d);
        arcs1.put("C->D", 6.0d);
        arcs1.put("D->FINISH", 1.0d);
        arcs1.put("C->FINISH", 3.0d);

        DijkstraGraphHashtable d1 = new DijkstraGraphHashtable(arcs1);
        System.out.println(d1.findShortestPathUsingDijkstra());

        Map<String, Double> arcs2 = new HashMap<>();
        arcs2.put("START->A", 10.0d);
        arcs2.put("B->A", 1.0d);
        arcs2.put("C->B", 1.0d);
        arcs2.put("C->FINISH", 30.0d);
        arcs2.put("A->C", 20.0d);

        DijkstraGraphHashtable d2 = new DijkstraGraphHashtable(arcs2);
        System.out.println(d2.findShortestPathUsingDijkstra());

        Map<String, Double> arcs3 = new HashMap<>();
        arcs3.put("START->A", 2.0d);
        arcs3.put("START->B", 2.0d);
        arcs3.put("B->A", 2.0d);
        arcs3.put("A->C", 2.0d);
        arcs3.put("C->B", -1.0d);
        arcs3.put("A->FINISH", 2.0d);
        arcs3.put("C->FINISH", 2.0d);

        Assertions.assertThrows(DijkstraValidationException.class, () -> new DijkstraGraphHashtable(arcs3));

        Map<String, Double> arcs4 = new HashMap<>();
        arcs4.put("START->A", 20.0d);
        arcs4.put("A->AA", 4.0d);
        arcs4.put("AA->B", 4.0d);
        arcs4.put("C->AA", 4.0d);
        arcs4.put("B->C", 4.0d);
        arcs4.put("B->BB", 4.0d);
        arcs4.put("BB->FINISH", 20.0d);

        DijkstraGraphHashtable d3 = new DijkstraGraphHashtable(arcs4);
        System.out.println(d3.findShortestPathUsingDijkstra());
    }
}
