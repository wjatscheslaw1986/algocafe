/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.*;
import algos.graph.specialized.CityWeightedAdjacencyMatrixGraph;
import algos.graph.specialized.CrossroadsWeightedAdjacencyMatrixGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static algos.graph.objects.City.*;

public class JarnikTests {

    @Test
    public void jarnickWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {

        WeightedAdjacencyMatrixGraph graph =
                new WeightedAdjacencyMatrixGraph<String, WeightedRib>(
                        new String[]{"A", "B", "C", "D", "E", "F", "J", "K", "Y"},
                        new double[9][9]
                );

        graph.connectNodes(graph.indexOf("A"), graph.indexOf("B"), 1.0d);
        graph.connectNodes(graph.indexOf("A"), graph.indexOf("D"), 10.0d);
        graph.connectNodes(graph.indexOf("B"), graph.indexOf("C"), 1.0d);
        graph.connectNodes(graph.indexOf("C"), graph.indexOf("F"), 1.0d);
        graph.connectNodes(graph.indexOf("B"), graph.indexOf("E"), 1.0d);
        graph.connectNodes(graph.indexOf("F"), graph.indexOf("E"), 1.0d);
        graph.connectNodes(graph.indexOf("E"), graph.indexOf("D"), 1.0d);
        graph.connectNodes(graph.indexOf("D"), graph.indexOf("J"), 1.0d);
        graph.connectNodes(graph.indexOf("J"), graph.indexOf("K"), 5.0d);
        graph.connectNodes(graph.indexOf("K"), graph.indexOf("Y"), 10.0d);
        graph.connectNodes(graph.indexOf("F"), graph.indexOf("Y"), 15.0d);
        graph.connectNodes(graph.indexOf("E"), graph.indexOf("K"), 1.0d);

        System.out.println(graph.toString());

        List<WeightedRib> minimalSpanningTree = null;
        for (int i = 0; i < 9; i++) {
            minimalSpanningTree = GraphUtil.jarnik(graph, i);
            Assertions.assertEquals(17.0d, minimalSpanningTree.stream().mapToDouble(WeightedRib::getWeight).sum());
        }
        GraphUtil.printWeighted(minimalSpanningTree, graph);
    }

    @Test
    public void jarnickCityWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {
        City[] arr = City.values();

        CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib> graph =
                new CityWeightedAdjacencyMatrixGraph<>(Arrays.stream(arr).map(CityNode::new).toArray(CityNode[]::new), new double[arr.length][arr.length]);

        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(GREAT_NOVGOROD), 193.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW), 710.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(TVER), 183.0d);
        graph.connectNodes(graph.indexOf(TVER), graph.indexOf(GREAT_NOVGOROD), 360.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(VYBORG), 136.0d);
        graph.connectNodes(graph.indexOf(PETROZAVODSK), graph.indexOf(SAINT_PETERSBURG), 430.0d);
        graph.connectNodes(graph.indexOf(VYBORG), graph.indexOf(PETROZAVODSK), 500.0d);
        graph.connectNodes(graph.indexOf(HELSINKI), graph.indexOf(VYBORG), 245.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(SHLISSELBURG), 54.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(PSKOV), 293.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(LVOV), 540.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(ODESSA), 480.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(KHARKIV), 460.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(VORONEZH), 710.0d);
        graph.connectNodes(graph.indexOf(DNIPRO), graph.indexOf(KHARKIV), 217.0d);
        graph.connectNodes(graph.indexOf(KAZAN), graph.indexOf(MOSCOW), 810.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(BRYANSK), 380.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(BRYANSK), 470.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(VINNITSA), 266.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(VLADIMIR), 185.0d);
        graph.connectNodes(graph.indexOf(MINSK), graph.indexOf(MOGILEV), 199.0d);
        graph.connectNodes(graph.indexOf(VITEBSK), graph.indexOf(PSKOV), 350.0d);
        graph.connectNodes(graph.indexOf(KALUGA), graph.indexOf(TULA), 107.0d);
        graph.connectNodes(graph.indexOf(TULA), graph.indexOf(RYAZAN), 182.0d);
        graph.connectNodes(graph.indexOf(SMOLENSK), graph.indexOf(MOSCOW), 400.0d);
        graph.connectNodes(graph.indexOf(SMOLENSK), graph.indexOf(MOGILEV), 203.0d);
        graph.connectNodes(graph.indexOf(KOSTROMA), graph.indexOf(KIROV), 620.0d);
        graph.connectNodes(graph.indexOf(YAROSLAVL), graph.indexOf(KOSTROMA), 84.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(YAROSLAVL), 265.0d);
        graph.connectNodes(graph.indexOf(VOLOGDA), graph.indexOf(TIHVIN), 440.0d);
        graph.connectNodes(graph.indexOf(TIHVIN), graph.indexOf(SAINT_PETERSBURG), 220.0d);
        graph.connectNodes(graph.indexOf(TALLIN), graph.indexOf(VORONEZH), 370.0d);
        graph.connectNodes(graph.indexOf(SARANSK), graph.indexOf(RYAZAN), 450.0d);

        List<WeightedRib> minimalSpanningTree = null;
        for (int i = 0; i < 6; i++) { // whatever node you start from, the result must be the same
            minimalSpanningTree = GraphUtil.jarnik(graph, i);
            Assertions.assertEquals(9563.0d, minimalSpanningTree.stream().mapToDouble(WeightedRib::getWeight).sum());
        }
        GraphUtil.printWeighted(minimalSpanningTree, graph);
    }

    @Test
    public void jarnickCrossroadsWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {
        Crossroad[] arr = Crossroad.values();

        CrossroadsWeightedAdjacencyMatrixGraph<CrossroadsNode, WeightedRib> graph =
                new CrossroadsWeightedAdjacencyMatrixGraph<>(Arrays.stream(arr).map(CrossroadsNode::new).toArray(CrossroadsNode[]::new), new double[arr.length][arr.length]);

        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_18_19_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_18_19_LINES), 510.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_18_19_LINES), graph.indexOf(Crossroad.MALIY_PROSPECT_16_17_LINES), 170.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_16_17_LINES), 530.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.MALIY_PROSPECT_DONSKAYA), 129.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_14_15_LINES), graph.indexOf(Crossroad.MALIY_PROSPECT_DONSKAYA), 92.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_14_15_LINES), graph.indexOf(Crossroad.MALIY_PROSPECT_12_13_LINES), 203.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_10_11_LINES), 500.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.MALIY_PROSPECT_8_9_LINES), 168.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_8_9_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_8_9_LINES), 500.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_12_13_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_12_13_LINES), 510.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_8_9_LINES), 173.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_12_13_LINES), 178.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_14_15_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_12_13_LINES), 181.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_14_15_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_16_17_LINES), 178.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_18_19_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_16_17_LINES), 174.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_18_19_LINES), graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES), 520.0d);
        graph.connectNodes(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_8_9_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_8_9_LINES), 520.0d);
        graph.connectNodes(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES), 194.0d);
        graph.connectNodes(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.BOLSHOY_PROSPECT_14_15_LINES), 196.0d);
        graph.connectNodes(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_12_13_LINES), graph.indexOf(Crossroad.BOLSHOY_PROSPECT_14_15_LINES), 179.0d);
        graph.connectNodes(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_12_13_LINES), graph.indexOf(Crossroad.SREDNIY_PROSPECT_12_13_LINES), 520.0d);
        graph.connectNodes(graph.indexOf(Crossroad.DONSKAYA_NEMANSKY), graph.indexOf(Crossroad.MALIY_PROSPECT_DONSKAYA), 420.0d);
        graph.connectNodes(graph.indexOf(Crossroad.DONSKAYA_NEMANSKY), graph.indexOf(Crossroad.NEMANSKY_PER_16_17_LINES), 227.0d);
        graph.connectNodes(graph.indexOf(Crossroad.DONSKAYA_NEMANSKY), graph.indexOf(Crossroad.NEMANSKY_PER_14_15_LINES), 202.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.KAMSKAYA_16_17_LINES), 500.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_14_15_LINES), graph.indexOf(Crossroad.KAMSKAYA_16_17_LINES), 214.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_SMOLENKA_EMB_12_13_LINES), graph.indexOf(Crossroad.KAMSKAYA_16_17_LINES), 217.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.KAMSKAYA_16_17_LINES), 500.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_14_15_LINES), graph.indexOf(Crossroad.KAMSKAYA_16_17_LINES), 214.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_SMOLENKA_EMB_12_13_LINES), graph.indexOf(Crossroad.KAMSKAYA_14_15_LINES), 209.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_SMOLENKA_EMB_12_13_LINES), graph.indexOf(Crossroad.SMOLENKA_EMB_10_11_LINES), 180.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES), graph.indexOf(Crossroad.SMOLENKA_EMB_10_11_LINES), 185.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.SMOLENKA_EMB_10_11_LINES), 297.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.MALIY_PROSPECT_12_13_LINES), 182.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.NEMANSKY_PER_16_17_LINES), 81.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_14_15_LINES), graph.indexOf(Crossroad.NEMANSKY_PER_14_15_LINES), 78.0d);
        graph.connectNodes(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.BOLSHOY_PROSPECT_8_9_LINES), 181.0d);

        Assertions.assertEquals(Crossroad.values().length, graph.getNodeCount());

        List<WeightedRib> minimalSpanningTree = null;
        for (int i = 0; i < Crossroad.values().length; i++) { // whatever node you start from, the result must be the same
            minimalSpanningTree = GraphUtil.jarnik(graph, i);
            double result = minimalSpanningTree.stream().mapToDouble(WeightedRib::getWeight).sum();
            Assertions.assertEquals(5484.0d, result);
        }
        GraphUtil.printWeighted(minimalSpanningTree, graph);
    }

}