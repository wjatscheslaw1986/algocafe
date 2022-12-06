///*
// * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
// */
//package algos.graph;
//
//import algos.graph.objects.*;
//import algos.maze.PathNode;
//import algos.maze.PathNodeUtil;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Stream;
//
//import static algos.graph.objects.City.*;
//
//public class IncidentalityListDirectedGraphTests {
//    static ArrayList<String> listOfCities;
//    IncidentalityListDirectedGraph<CityNode, Arc> routeGraph1;
//    CityWeightedIncidentalityListDirectedGraph<CityNode, WeightedArc> routeGraph2, routeGraph3;
//
//    @BeforeAll
//    static void initCities() {
//        listOfCities = new ArrayList<>(Stream.of(City.values()).map(City::toString).toList());
//    }
//
//    @BeforeEach
//    void init() {
//        routeGraph1 = new IncidentalityListDirectedGraph(listOfCities);
//        routeGraph2 = new CityWeightedIncidentalityListDirectedGraph(listOfCities);
//        routeGraph3 = new CityWeightedIncidentalityListDirectedGraph(listOfCities);
//
//        routeGraph1.connectNodes(SAINT_PETERSBURG, GREAT_NOVGOROD);
//        routeGraph1.connectNodes(SAINT_PETERSBURG, MOSCOW);
//        routeGraph1.connectNodes(MOSCOW, TVER);
//        routeGraph1.connectNodes(TVER, GREAT_NOVGOROD);
//        routeGraph1.connectNodes(SAINT_PETERSBURG, VYBORG);
//        routeGraph1.connectNodes(SAINT_PETERSBURG, PETROZAVODSK);
//        routeGraph1.connectNodes(HELSINKI, VYBORG);
//        routeGraph1.connectNodes(SAINT_PETERSBURG, SHLISSELBURG);
//        routeGraph1.connectNodes(SAINT_PETERSBURG, PSKOV);
//        routeGraph1.connectNodes(KIEV, LVOV);
//        routeGraph1.connectNodes(KIEV, ODESSA);
//        routeGraph1.connectNodes(KIEV, KHARKIV);
//        routeGraph1.connectNodes(KIEV, VORONEZH);
//        routeGraph1.connectNodes(DNIPRO, KHARKIV);
//        routeGraph1.connectNodes(KAZAN, MOSCOW);
//        routeGraph1.connectNodes(MOSCOW, BRYANSK);
//        routeGraph1.connectNodes(KIEV, BRYANSK);
//        routeGraph1.connectNodes(KIEV, VINNITSA);
//        routeGraph1.connectNodes(MOSCOW, VLADIMIR);
//        routeGraph1.connectNodes(MINSK, MOGILEV);
//        routeGraph1.connectNodes(VITEBSK, PSKOV);
//        routeGraph1.connectNodes(KALUGA, TULA);
//        routeGraph1.connectNodes(TULA, RYAZAN);
//        routeGraph1.connectNodes(SMOLENSK, MOSCOW);
//        routeGraph1.connectNodes(SMOLENSK, MOGILEV);
//        routeGraph1.connectNodes(KIROV, KOSTROMA);
//        routeGraph1.connectNodes(KOSTROMA, YAROSLAVL);
//        routeGraph1.connectNodes(YAROSLAVL, MOSCOW);
//        routeGraph1.connectNodes(VOLOGDA, TIHVIN);
//        routeGraph1.connectNodes(TIHVIN, SAINT_PETERSBURG);
//        routeGraph1.connectNodes(TALLIN, SAINT_PETERSBURG);
//        routeGraph1.connectNodes(SARANSK, RYAZAN);
//        routeGraph1.connectNodes(MOSCOW, KALUGA);
//
//        routeGraph2.connectNodes(SAINT_PETERSBURG, GREAT_NOVGOROD, 193);
//        routeGraph2.connectNodes(SAINT_PETERSBURG, MOSCOW, 710);
//        routeGraph2.connectNodes(MOSCOW, TVER, 183);
//        routeGraph2.connectNodes(TVER, GREAT_NOVGOROD, 360);
//        routeGraph2.connectNodes(SAINT_PETERSBURG, VYBORG, 136);
//        routeGraph2.connectNodes(PETROZAVODSK, SAINT_PETERSBURG, 430);
//        routeGraph2.connectNodes(VYBORG, PETROZAVODSK, 500);
//        routeGraph2.connectNodes(HELSINKI, VYBORG, 245);
//        routeGraph2.connectNodes(SAINT_PETERSBURG, SHLISSELBURG, 54);
//        routeGraph2.connectNodes(SAINT_PETERSBURG, PSKOV, 293);
//        routeGraph2.connectNodes(KIEV, LVOV, 540);
//        routeGraph2.connectNodes(KIEV, ODESSA, 480);
//        routeGraph2.connectNodes(KIEV, KHARKIV, 460);
//        routeGraph2.connectNodes(KIEV, VORONEZH, 710);
//        routeGraph2.connectNodes(DNIPRO, KHARKIV, 217);
//        routeGraph2.connectNodes(KAZAN, MOSCOW, 810);
//        routeGraph2.connectNodes(MOSCOW, BRYANSK, 380);
//        routeGraph2.connectNodes(KIEV, BRYANSK, 470);
//        routeGraph2.connectNodes(KIEV, VINNITSA, 266);
//        routeGraph2.connectNodes(MOSCOW, VLADIMIR, 185);
//        routeGraph2.connectNodes(MINSK, MOGILEV, 199);
//        routeGraph2.connectNodes(VITEBSK, PSKOV, 350);
//        routeGraph2.connectNodes(KALUGA, TULA, 107);
//        routeGraph2.connectNodes(TULA, RYAZAN, 182);
//        routeGraph2.connectNodes(SMOLENSK, MOSCOW, 400);
//        routeGraph2.connectNodes(SMOLENSK, MOGILEV, 203);
//        routeGraph2.connectNodes(KIROV, KOSTROMA, 620);
//        routeGraph2.connectNodes(KOSTROMA, YAROSLAVL, 84);
//        routeGraph2.connectNodes(YAROSLAVL, MOSCOW, 265);
//        routeGraph2.connectNodes(VOLOGDA, TIHVIN, 440);
//        routeGraph2.connectNodes(TIHVIN, SAINT_PETERSBURG, 220);
//        routeGraph2.connectNodes(TALLIN, SAINT_PETERSBURG, 370);
//        routeGraph2.connectNodes(SARANSK, RYAZAN, 450);
//        routeGraph2.connectNodes(SAINT_PETERSBURG, KALUGA, 840);
//
//        routeGraph3.connectNodes(SAINT_PETERSBURG, GREAT_NOVGOROD, 193);
//        routeGraph3.connectNodes(MOSCOW, TVER, 183);
//        routeGraph3.connectNodes(SAINT_PETERSBURG, MOSCOW, 710);
//        routeGraph3.connectNodes(SAINT_PETERSBURG, VYBORG, 136);
//        routeGraph3.connectNodes(SAINT_PETERSBURG, PETROZAVODSK, 430);
//        routeGraph3.connectNodes(TVER, GREAT_NOVGOROD, 360);
//    }
//
//
//    @Test
//    public void unweightedGraphTest() {
//        AtomicInteger stepsCounter = new AtomicInteger(0);
//
//        PathNode<CityNode> bfsResult = GraphUtil.bfs(SAINT_PETERSBURG, v -> v.equals(MINSK), routeGraph1::successorsOf, stepsCounter);
//
//        if (bfsResult == null) {
//            System.out.println("Unreachable");
//        } else {
//            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
//            System.out.print("Path from Saint-Petersburg to Minsk\n");
//            System.out.println(path);
//            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
//        }
//
//        bfsResult = GraphUtil.bfs(SAINT_PETERSBURG, v -> v.equals(KIEV), routeGraph1::successorsOf, stepsCounter);
//
//        if (bfsResult == null) {
//            System.out.println("Unreachable");
//        } else {
//            List<City> path = PathNodeUtil.nodeToPath(bfsResult);
//            System.out.print("Path from Saint-Petersburg to Kiev\n");
//            System.out.println(path);
//            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
//        }
//    }
//
//    @Test
//    public void weightedGraphTest() {
//        AtomicInteger stepsCounter = new AtomicInteger(0);
//
//        PathNode<City> bfsResult = PathNodeUtil.bfs(SAINT_PETERSBURG, v -> v.equals(ODESSA), routeGraph2::successorsOf, stepsCounter);
//
//        if (bfsResult == null) {
//            System.out.println("Unreachable");
//        } else {
//            List<City> path = PathNodeUtil.nodeToPath(bfsResult);
//            System.out.print("Path from Saint-Petersburg to Odessa\n");
//            System.out.println(path);
//            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
//        }
//    }
//
//    @Test
//    public void jarnikTest() {
//        List<WeightedArc> jarnikPath = GraphUtil.<CityNode, WeightedArc, WeightedIncidentalityListDirectedGraph<CityNode, WeightedArc>>jarnik(routeGraph2, 0);
//        Assertions.assertEquals(66, routeGraph2.getArcCount());
//        Assertions.assertEquals(32, jarnikPath.size());
//        routeGraph2.connectNodes(SAINT_PETERSBURG, MINSK, 800);
//        List<WeightedArc> jarnikPath1 = GraphUtil.<CityNode, WeightedArc, WeightedIncidentalityListDirectedGraph<CityNode, WeightedArc>>jarnik(routeGraph2, 0);
//        Assertions.assertEquals(68, routeGraph2.getArcCount());
//        Assertions.assertEquals(32, jarnikPath1.size());
//        routeGraph2.connectNodes(SAINT_PETERSBURG, DNIPRO, 1640);
//        List<WeightedArc> jarnikPath2 = GraphUtil.<CityNode, WeightedArc, WeightedIncidentalityListDirectedGraph<CityNode, WeightedArc>>jarnik(routeGraph2, 14);
//        Assertions.assertEquals(70, routeGraph2.getArcCount());
//        Assertions.assertEquals(32, jarnikPath2.size());
//
//        System.out.println(GraphUtil.printWeightedPath(jarnikPath2, routeGraph2));
//    }
//
//    @Test
//    public void dijkstraTest() {
//        GraphUtil.DijkstraResult<WeightedArc> dijkstraPath = GraphUtil.<CityNode, WeightedArc, CityWeightedIncidentalityListDirectedGraph<CityNode, WeightedArc>>dijkstra(routeGraph2.nodeAt(routeGraph2.indexOf(SAINT_PETERSBURG)), routeGraph2);
//        Map<CityNode, Double> nameDistance = GraphUtil.distanceArrayToDistanceMap(dijkstraPath.distances, routeGraph2);
//        System.out.println("Distances from " + SAINT_PETERSBURG);
//        nameDistance.forEach((name, distance) -> System.out.println(name + " : " + distance));
//        System.out.println("Shortest path from " + SAINT_PETERSBURG + " to " + RYAZAN);
//        List<WeightedArc> path = GraphUtil.pathMapToPathList(routeGraph2.indexOf(SAINT_PETERSBURG), routeGraph2.indexOf(RYAZAN), dijkstraPath.pathMap);
//        System.out.println(GraphUtil.printWeightedPath(path, routeGraph2));
//
//        dijkstraPath = GraphUtil.<CityNode, WeightedArc, CityWeightedIncidentalityListDirectedGraph<CityNode, WeightedArc>>dijkstra(routeGraph2.nodeAt(routeGraph2.indexOf(KIEV)), routeGraph2);
//        System.out.println("Shortest path from " + KIEV + " to " + PETROZAVODSK);
//        path = GraphUtil.pathMapToPathList(routeGraph2.indexOf(KIEV), routeGraph2.indexOf(PETROZAVODSK), dijkstraPath.pathMap);
//        System.out.println(GraphUtil.printWeightedPath(path, routeGraph2));
//
//        dijkstraPath = GraphUtil.<CityNode, WeightedArc, WeightedIncidentalityListDirectedGraph<CityNode, WeightedArc>>dijkstra(routeGraph2.nodeAt(routeGraph2.indexOf(TVER)), routeGraph3);
//    }
//
//
//}