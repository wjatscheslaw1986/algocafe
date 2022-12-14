/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.*;
import algos.graph.specialized.*;
import algos.maze.PathNode;
import algos.maze.PathNodeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static algos.graph.objects.City.*;

public class BFSTests {

    static CityNode[] someNodes;
    boolean[][] adjacency;
    double[][] weightedAdjacency;
    AtomicInteger stepsCounter;
    static CityNode[] allNodes = new CityNode[City.values().length];
    WeightedAdjacencyMatrixGraph<String, WeightedRib> weightedGraph;
    static CrossroadsNode[] allCrossroadNodes = new CrossroadsNode[Crossroad.values().length];

    @BeforeAll
    static void initCities() {
        for (int i = 0; i < City.values().length; i++) allNodes[i] = new CityNode(City.values()[i]);
        for (int i = 0; i < Crossroad.values().length; i++) allCrossroadNodes[i] = new CrossroadsNode(Crossroad.values()[i]);
        someNodes = new CityNode[]{
                new CityNode(SAINT_PETERSBURG),
                new CityNode(VYBORG),
                new CityNode(HELSINKI),
                new CityNode(PETROZAVODSK),
                new CityNode(SHLISSELBURG),
                new CityNode(GREAT_NOVGOROD),
                new CityNode(PSKOV),
                new CityNode(VOLOGDA),
                new CityNode(YAROSLAVL),
                new CityNode(KOSTROMA),
                new CityNode(VLADIMIR),
                new CityNode(MOSCOW),
                new CityNode(RYAZAN),
                new CityNode(TULA),
                new CityNode(TVER)
        };
    }

    @BeforeEach
    void init() {
        adjacency = new boolean[someNodes.length][someNodes.length];
        weightedAdjacency = new double[City.values().length][City.values().length];
        stepsCounter = new AtomicInteger(0);

        weightedAdjacency[0][4] = 193.0d;
        weightedAdjacency[0][6] = 710.0d;
        weightedAdjacency[6][7] = 183.0d;
        weightedAdjacency[7][4] = 360.0d;
        weightedAdjacency[0][2] = 136.0d;
        weightedAdjacency[3][0] = 430.0d;
        weightedAdjacency[2][3] = 500.0d;
        weightedAdjacency[1][2] = 245.0d;
        weightedAdjacency[0][21] = 54.0d;
        weightedAdjacency[0][5] = 293.0d;
        weightedAdjacency[25][30] = 540.0d;
        weightedAdjacency[25][31] = 480.0d;
        weightedAdjacency[25][26] = 460.0d;
        weightedAdjacency[25][29] = 710.0d;
        weightedAdjacency[28][26] = 217.0d;
        weightedAdjacency[32][6] = 810.0d;
        weightedAdjacency[6][24] = 380.0d;
        weightedAdjacency[25][24] = 470.0d;
        weightedAdjacency[25][27] = 266.0d;
        weightedAdjacency[6][10] = 185.0d;
        weightedAdjacency[20][19] = 199.0d;
        weightedAdjacency[16][5] = 350.0d;
        weightedAdjacency[8][9] = 107.0d;
        weightedAdjacency[9][11] = 182.0d;
        weightedAdjacency[18][6] = 400.0d;
        weightedAdjacency[18][19] = 203.0d;
        weightedAdjacency[33][14] = 620.0d;
        weightedAdjacency[14][13] = 84.0d;
        weightedAdjacency[13][6] = 265.0d;
        weightedAdjacency[12][15] = 440.0d;
        weightedAdjacency[15][0] = 220.0d;
        weightedAdjacency[17][0] = 370.0d;
        weightedAdjacency[22][11] = 450.0d;
        weightedAdjacency[0][8] = 840.0d;
    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphAllToAllTest() throws GraphInstantiationException {

        AdjacencyMatrixDirectedGraph<CityNode, Arc> orientedGraph = new AdjacencyMatrixDirectedGraph<CityNode, Arc>(someNodes, adjacency);
        for (int i = 0; i < someNodes.length; i++)
            for (int j = 0; j < someNodes.length; j++) {
                if (i == j) continue;
                orientedGraph.connectNodes(orientedGraph.nodeAt(i), orientedGraph.nodeAt(j));
            }

        for (int i = 0; i < someNodes.length; i++) Assertions.assertEquals(i, orientedGraph.indexOf(someNodes[i]));

        PathNode<CityNode> bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[14]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(2, PathNodeUtil.nodeToPath(bfsResult).size());
    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphAllToOneTest() throws GraphInstantiationException {

        AdjacencyMatrixDirectedGraph<CityNode, Arc> orientedGraph = new AdjacencyMatrixDirectedGraph<CityNode, Arc>(someNodes, adjacency);
        for (int i = 0; i < someNodes.length; i++) {
            if (i == 3) continue;
            orientedGraph.connectNodes(orientedGraph.nodeAt(i), orientedGraph.nodeAt(3));
        }

        for (int i = 0; i < someNodes.length; i++) Assertions.assertEquals(i, orientedGraph.indexOf(someNodes[i]));

        PathNode<CityNode> bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[14]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNull(bfsResult);

        bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[3]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(2, PathNodeUtil.nodeToPath(bfsResult).size());

        orientedGraph.connectNodes(0, 5);
        orientedGraph.connectNodes(5, 6);
        bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[6]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());

    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphTest() throws GraphInstantiationException {

        AdjacencyMatrixDirectedGraph<CityNode, Arc> orientedGraph =
                new AdjacencyMatrixDirectedGraph<CityNode, Arc>(someNodes, adjacency);

        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(1, 2);
        orientedGraph.connectNodes(0, 3);
        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(0, 4);
        orientedGraph.connectNodes(4, 3);
        orientedGraph.connectNodes(0, 5);
        orientedGraph.connectNodes(5, 6);
        orientedGraph.connectNodes(4, 7);
        orientedGraph.connectNodes(5, 14);
        orientedGraph.connectNodes(14, 8);
        orientedGraph.connectNodes(8, 7);

        for (int i = 0; i < someNodes.length; i++)
            Assertions.assertEquals(i, orientedGraph.indexOf(someNodes[i]));

        PathNode<CityNode> bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());
        
        bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[2]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());
        
        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNull(bfsResult);
        orientedGraph.connectNodes(2, 1);
        orientedGraph.connectNodes(1, 0);
        
        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(5, PathNodeUtil.nodeToPath(bfsResult).size());
        orientedGraph.disconnectNodes(0, 4);
        
        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(7, PathNodeUtil.nodeToPath(bfsResult).size());
    }

    @Test
    public void bfsIncidentalityListDirectedGraphTest() throws GraphInstantiationException {

        IncidentalityListDirectedGraph<CityNode, Arc> orientedGraph =
                new IncidentalityListDirectedGraph<CityNode, Arc>(new ArrayList<>(List.of(someNodes)));

        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(1, 2);
        orientedGraph.connectNodes(0, 3);
        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(0, 4);
        orientedGraph.connectNodes(4, 3);
        orientedGraph.connectNodes(0, 5);
        orientedGraph.connectNodes(5, 6);
        orientedGraph.connectNodes(4, 7);
        orientedGraph.connectNodes(5, 14);
        orientedGraph.connectNodes(14, 8);
        orientedGraph.connectNodes(8, 7);

        for (int i = 0; i < someNodes.length; i++)
            Assertions.assertEquals(i, orientedGraph.indexOf(someNodes[i]));

        PathNode<CityNode> bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());

        bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[2]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());

        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNull(bfsResult);
        orientedGraph.connectNodes(2, 1);
        orientedGraph.connectNodes(1, 0);

        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(5, PathNodeUtil.nodeToPath(bfsResult).size());
        orientedGraph.disconnectNodes(0, 4);

        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(7, PathNodeUtil.nodeToPath(bfsResult).size());
    }

    @Test
    public void bfsWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {

        CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib> weightedCityGraph = new CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib>(allNodes, new double[allNodes.length][allNodes.length]);

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(GREAT_NOVGOROD), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedCityGraph.connectNodes(weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph.indexOf(GREAT_NOVGOROD), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(GREAT_NOVGOROD), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedCityGraph.connectNodes(weightedCityGraph.indexOf(GREAT_NOVGOROD), weightedCityGraph.indexOf(SAINT_PETERSBURG), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(GREAT_NOVGOROD), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedCityGraph.connectNodes(weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph.indexOf(MOSCOW), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedCityGraph.connectNodes(weightedCityGraph.indexOf(MOSCOW), weightedCityGraph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedCityGraph.disconnectNodes(weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph.indexOf(MOSCOW));
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);
    }

    @Test
    public void bfsIncidentalityListMatrixGraphTest() {

        CityWeightedIncidentalityListGraph<CityNode, WeightedRib> graph = new CityWeightedIncidentalityListGraph<CityNode, WeightedRib>(new ArrayList<>(List.of(allNodes)));

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.nodeAt(graph.indexOf(GREAT_NOVGOROD)), graph.nodeAt(graph.indexOf(SAINT_PETERSBURG)), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        graph.connectNodes(graph.nodeAt(graph.indexOf(SAINT_PETERSBURG)), graph.nodeAt(graph.indexOf(GREAT_NOVGOROD)), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        graph.disconnectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW));
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);
    }

    @Test
    public void bfsWeightedAdjacencyMatrixDirectedGraphTest() throws GraphInstantiationException {

        CityWeightedAdjacencyMatrixDirectedGraph<CityNode, WeightedArc> graph =
                new CityWeightedAdjacencyMatrixDirectedGraph<CityNode, WeightedArc>(allNodes, new double[allNodes.length][allNodes.length]);

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(GREAT_NOVGOROD), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(GREAT_NOVGOROD), graph.indexOf(SAINT_PETERSBURG), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        graph.disconnectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW));
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNotNull(back);
    }

    @Test
    public void bfsWeightedIncidentalityListDirectedGraphTest() throws GraphInstantiationException {

        CityWeightedIncidentalityListDirectedGraph<CityNode, WeightedArc> graph =
                new CityWeightedIncidentalityListDirectedGraph<CityNode, WeightedArc>(new ArrayList<>(List.of(allNodes)));

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(GREAT_NOVGOROD), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(GREAT_NOVGOROD), graph.indexOf(SAINT_PETERSBURG), 193.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(GREAT_NOVGOROD), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        graph.disconnectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW));
        forth = PathNodeUtil.bfs(allNodes[0], c -> graph.indexOf(c) == graph.indexOf(MOSCOW), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> graph.indexOf(c) == graph.indexOf(SAINT_PETERSBURG), graph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNotNull(back);
    }

    @Test
    public void bfsCrossroadsWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {
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

        forth = PathNodeUtil.bfs(graph.nodeAt(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES)), c -> graph.indexOf(c) == graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(graph.nodeAt(graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES)), c -> graph.indexOf(c) == graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES), graph::successorsOf, stepsCounter);

        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        System.out.println("FORTH: " + PathNodeUtil.nodeToPath(forth));
        System.out.println("BACK: " + PathNodeUtil.nodeToPath(back));
        System.out.println("The BFS algorithm took " + stepsCounter.get() + " steps to complete on " + graph.getNodeCount() + " nodes.");
    }

    @Test
    public void bfsCrossroadsWeightedIncidentalityListGraphTest() throws GraphInstantiationException {
        Crossroad[] arr = Crossroad.values();

        CrossroadsWeightedIncidentalityListGraph<CrossroadsNode, WeightedRib> graph =
                new CrossroadsWeightedIncidentalityListGraph<>(Arrays.stream(arr).map(CrossroadsNode::new).collect(Collectors.toCollection(ArrayList::new)));

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

        forth = PathNodeUtil.bfs(graph.nodeAt(graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES)), c -> graph.indexOf(c) == graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES), graph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(graph.nodeAt(graph.indexOf(Crossroad.SMOLENKA_EMB_8_9_LINES)), c -> graph.indexOf(c) == graph.indexOf(Crossroad.BOLSHOY_PROSPECT_18_19_LINES), graph::successorsOf, stepsCounter);

        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        System.out.println("FORTH: " + PathNodeUtil.nodeToPath(forth));
        System.out.println("BACK: " + PathNodeUtil.nodeToPath(back));
        System.out.println("The BFS algorithm took " + stepsCounter.get() + " steps to complete on " + graph.getNodeCount() + " nodes.");
    }
}
