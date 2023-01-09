/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.percolation;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] percentages;
    private double mean, stddev, confidenceHi, confidenceLo;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("");
        double nSquared = n * n;
        percentages = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates())
                percolation.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
            percentages[i] = percolation.numberOfOpenSites() / nSquared;
        }
        mean = StdStats.mean(percentages);
        stddev = StdStats.stddev(percentages);
        double halfInterval = (1.96d * stddev) / Math.sqrt(trials);
        confidenceLo = mean - halfInterval;
        confidenceHi = mean + halfInterval;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {
        if (args.length < 2) throw new IllegalArgumentException();
        int n = Integer.parseInt(args[0]), trials = Integer.parseInt(args[1]);
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}