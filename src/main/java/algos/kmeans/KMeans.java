// Modified by Viacheslav Mikhailov
// KMeans.java
// From Classic Computer Science Problems in Java Chapter 6
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package algos.kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class KMeans<Point extends ClusterDataPoint> {

	public class Cluster {
		public List<Point> points;
		public ClusterDataPoint centroid;

		public Cluster(List<Point> points, ClusterDataPoint randPoint) {
			this.points = points;
			this.centroid = randPoint;
		}
	}

	private List<Point> points;
	private List<Cluster> clusters;

	public KMeans(int k, List<Point> points) {
		if (k < 1) { // can't have negative or zero clusters
			throw new IllegalArgumentException("k must be >= 1");
		}
		this.points = points;
		zScoreNormalize();
		// initialize empty clusters with random centroids
		clusters = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			ClusterDataPoint randPoint = randomPoint();
			Cluster cluster = new Cluster(new ArrayList<Point>(), randPoint);
			clusters.add(cluster);
		}
	}

	private List<ClusterDataPoint> centroids() {
		return clusters.stream().map(cluster -> cluster.centroid)
				.collect(Collectors.toList());
	}

	private List<Double> dimensionSlice(int dimension) {
		return points.stream().map(x -> x.dimensions.get(dimension))
				.collect(Collectors.toList());
	}

	/**
	 * This method substitutes real dimensional values of each point for their Z-score values
	 */
	private void zScoreNormalize() {
		List<List<Double>> zscored = new ArrayList<>();
		for (Point point : points) {
			zscored.add(new ArrayList<Double>());
		}
		for (int dimension = 0; dimension < points.get(0).numDimensions; dimension++) {
			List<Double> dimensionSlice = dimensionSlice(dimension); // values of all points for a single dimension slice
			SingleDimentionSliceStatistics dimentionSliceStat = new SingleDimentionSliceStatistics(dimensionSlice);
			List<Double> zscores = dimentionSliceStat.getZScores(); // Z-scores of all points for a single dimension slice
			for (int index = 0; index < zscores.size(); index++) {
				zscored.get(index).add(zscores.get(index));
			}
		}
		for (int i = 0; i < points.size(); i++) {
			points.get(i).dimensions = zscored.get(i);
		}
	}

	private ClusterDataPoint randomPoint() {
		List<Double> randDimensions = new ArrayList<>();
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int dimension = 0; dimension < points.get(0).numDimensions; dimension++) { // we may choose any point to iterate through its dimensional values since all points must be of same dimensions number
			List<Double> values = dimensionSlice(dimension);
			SingleDimentionSliceStatistics stats = new SingleDimentionSliceStatistics(values);
			Double randValue = random.doubles(stats.min(), stats.max()).findFirst().getAsDouble();
			randDimensions.add(randValue);
		}
		return new ClusterDataPoint(randDimensions);
	}

	// Find the closest cluster centroid to each point and assign the point
	// to that cluster
	private void assignClusters() {
		for (Point point : points) {
			double lowestDistance = Double.MAX_VALUE;
			Cluster closestCluster = clusters.get(0);
			for (Cluster cluster : clusters) {
				double centroidDistance = point.distance(cluster.centroid);
				if (centroidDistance < lowestDistance) {
					lowestDistance = centroidDistance;
					closestCluster = cluster;
				}
			}
			closestCluster.points.add(point);
		}
	}

	// Find the center of each cluster and move the centroid to there
	private void updateCentroids() {
		for (Cluster cluster : clusters) {
			// Ignore if the cluster is empty
			if (cluster.points.isEmpty()) {
				continue;
			}
			List<Double> means = new ArrayList<>();
			for (int i = 0; i < cluster.points.get(0).numDimensions; i++) {
				int dimension = i; // needed to use in scope of closure
				Double dimensionMean = cluster.points.stream()
						.mapToDouble(x -> x.dimensions.get(dimension)).average().getAsDouble();
				means.add(dimensionMean);
			}
			cluster.centroid = new ClusterDataPoint(means);
		}
	}

	// Check if two Lists of DataPoints are of equivalent DataPoints
	private boolean listsEqual(List<ClusterDataPoint> first, List<ClusterDataPoint> second) {
		if (first.size() != second.size()) {
			return false;
		}
		for (int i = 0; i < first.size(); i++) {
			for (int j = 0; j < first.get(0).numDimensions; j++) {
				if (first.get(i).dimensions.get(j).doubleValue() != second.get(i).dimensions.get(j).doubleValue()) {
					return false;
				}
			}
		}
		return true;
	}

	public List<Cluster> run(int maxIterations) {
		for (int iteration = 0; iteration < maxIterations; iteration++) {
			for (Cluster cluster : clusters) // clear all clusters
				cluster.points.clear(); // this cleares the list of real points of the cluster, but leaves the centroid virtual point of the cluster untouched
			assignClusters(); // since cluster's centroids have been left untouched, and since points' positions are pre-defined, we may assign each point to some cluster by cartesian proximity principle
			List<ClusterDataPoint> oldCentroids = new ArrayList<>(centroids());
			updateCentroids(); // find new centroids for all clusters
			if (listsEqual(oldCentroids, centroids())) {
				System.out.println("Converged after " + iteration + " iterations.");
				return clusters; // no reason for further improving perfect clusterization
			}
		}
		return clusters;
	}
}