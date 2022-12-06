/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph;

import algos.Vincenty;
import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.Crossroad;
import algos.graph.objects.CrossroadsNode;
import algos.graph.objects.WeightedRib;
import algos.graph.specialized.CrossroadsWeightedAdjacencyMatrixGraph;
import algos.maze.PathNode;
import algos.maze.PathNodeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class AStarTests {
    static CrossroadsNode[] allNodes = new CrossroadsNode[Crossroad.values().length];

    @BeforeAll
    static void initCrossroads() {
        for (int i = 0; i < Crossroad.values().length; i++) allNodes[i] = new CrossroadsNode(Crossroad.values()[i]);
    }

    @BeforeEach
    void init() {

    }

    @Test
    public void aStarCrossroadsWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {
        AtomicInteger stepsCounter = new AtomicInteger(0);

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
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.KAMSKAYA_16_17_LINES), 500.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_SMOLENKA_EMB_12_13_LINES), graph.indexOf(Crossroad.KAMSKAYA_14_15_LINES), 225.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_14_15_LINES), graph.indexOf(Crossroad.KAMSKAYA_16_17_LINES), 209.0d);
        graph.connectNodes(graph.indexOf(Crossroad.KAMSKAYA_SMOLENKA_EMB_12_13_LINES), graph.indexOf(Crossroad.SMOLENKA_EMB_10_11_LINES), 180.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES), graph.indexOf(Crossroad.SMOLENKA_EMB_10_11_LINES), 185.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.SMOLENKA_EMB_10_11_LINES), 297.0d);
        graph.connectNodes(graph.indexOf(Crossroad.MALIY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.MALIY_PROSPECT_12_13_LINES), 182.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_16_17_LINES), graph.indexOf(Crossroad.NEMANSKY_PER_16_17_LINES), 81.0d);
        graph.connectNodes(graph.indexOf(Crossroad.SREDNIY_PROSPECT_14_15_LINES), graph.indexOf(Crossroad.NEMANSKY_PER_14_15_LINES), 78.0d);
        graph.connectNodes(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_10_11_LINES), graph.indexOf(Crossroad.BOLSHOY_PROSPECT_8_9_LINES), 181.0d);

        Assertions.assertEquals(Crossroad.values().length, graph.getNodeCount());

        PathNode<CrossroadsNode> forth;
        PathNode<CrossroadsNode> back;

        forth = PathNodeUtil.astar(graph.nodeAt(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES)), c -> graph.indexOf(c) == graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES), graph::successorsOf, node -> Vincenty.getDistance(node.getCrossroad().getLat(), node.getCrossroad().getLon(), graph.nodeAt(graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES)).getCrossroad().getLat(), graph.nodeAt(graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES)).getCrossroad().getLon()), stepsCounter);
        back = PathNodeUtil.astar(graph.nodeAt(graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES)), c -> graph.indexOf(c) == graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES), graph::successorsOf, node -> Vincenty.getDistance(node.getCrossroad().getLat(), node.getCrossroad().getLon(), graph.nodeAt(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES)).getCrossroad().getLat(), graph.nodeAt(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES)).getCrossroad().getLon()), stepsCounter);

        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        System.out.println("FORTH: " + PathNodeUtil.nodeToPath(forth));
        System.out.println("BACK: " + PathNodeUtil.nodeToPath(back));
        System.out.println("The A* algorithm took " + stepsCounter.get() + " steps to complete on " + graph.getNodeCount() + " nodes.");
    }

}