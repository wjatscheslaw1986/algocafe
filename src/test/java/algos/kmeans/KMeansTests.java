/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.kmeans;

import algos.graph.objects.Crossroad;
import algos.graph.objects.CrossroadsNode;
import org.junit.jupiter.api.Test;

import java.util.List;

public class KMeansTests {

    @Test
    public void datapointKMeansTest() {
        ClusterDataPoint point1 = new ClusterDataPoint(List.of(22.0, 1.0, 1.0));
        ClusterDataPoint point2 = new ClusterDataPoint(List.of(2.0, 22.7, 5.0));
        ClusterDataPoint point3 = new ClusterDataPoint(List.of(3.0, 1.5, 22.5));
        KMeans<ClusterDataPoint> kmeansTest = new KMeans<>(3, List.of(point1, point2, point3));
        List<KMeans<ClusterDataPoint>.Cluster> testClusters = kmeansTest.run(23);
        for (int clusterIndex = 0; clusterIndex < testClusters.size(); clusterIndex++)
            System.out.println("Cluster " + clusterIndex + ": " + testClusters.get(clusterIndex).points);
    }

    @Test
    public void crossroadsKMeansTest() {
        ClusterDataPoint point0 = new CrossroadsNode(Crossroad.KAMSKAYA_SMOLENKA_EMB_12_13_LINES);
        ClusterDataPoint point1 = new CrossroadsNode(Crossroad.KAMSKAYA_16_17_LINES);
        ClusterDataPoint point2 = new CrossroadsNode(Crossroad.KAMSKAYA_14_15_LINES);
        ClusterDataPoint point3 = new CrossroadsNode(Crossroad.BOLSHOY_PROSPECT_18_19_LINES);
        ClusterDataPoint point4 = new CrossroadsNode(Crossroad.BOLSHOY_PROSPECT_16_17_LINES);
        ClusterDataPoint point5 = new CrossroadsNode(Crossroad.SREDNIY_PROSPECT_18_19_LINES);
        ClusterDataPoint point6 = new CrossroadsNode(Crossroad.MALIY_PROSPECT_8_9_LINES);
        ClusterDataPoint point7 = new CrossroadsNode(Crossroad.BOLSHOY_PROSPECT_8_9_LINES);
        KMeans<ClusterDataPoint> kmeansTest = new KMeans<>(3, List.of(point0, point1, point2, point3, point4, point5, point6, point7));
        List<KMeans<ClusterDataPoint>.Cluster> testClusters = kmeansTest.run(99);
        for (int clusterIndex = 0; clusterIndex < testClusters.size(); clusterIndex++)
            System.out.println("Cluster " + clusterIndex + ": " + testClusters.get(clusterIndex).points);
    }
}
