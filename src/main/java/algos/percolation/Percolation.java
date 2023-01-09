/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] substance;
    private int opened = 0;
    private final int n;
    private final int nSquare2;
    private final WeightedQuickUnionUF uf;

    public Percolation(int nLength) {
        if (nLength <= 0) throw new IllegalArgumentException("");
        n = nLength;
        substance = new boolean[n][n]; // square time for constructor
        nSquare2 = n * n;
        uf = new WeightedQuickUnionUF(nSquare2 + 2); // we create two virtual points: one for the top (index N_square_2) and one for the bottom (index N_square_2 + 1)
    }

    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row >= n || col >= n) throw new IllegalArgumentException("");
        if (isOpen(row, col)) return;
        ++opened;
        substance[row - 1][col - 1] = true;
        if (row == 1) {
            uf.union(translate2DTo1D(row, col, n), uf.find(nSquare2));                                                                          // ^
            if (n >= 2 && isOpen(row + 1, col)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row + 1, col, n));             // v
            if (col - 2 >= 0 && isOpen(row, col - 1)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row, col - 1, n));        // <
            if (col < n && isOpen(row, col + 1)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row, col + 1, n));             // >
        } else if (row == n) {
            uf.union(translate2DTo1D(row, col, n), uf.find(nSquare2 + 1));                                                                       // v
            if (n >= 2 && isOpen(row - 1, col)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row - 1, col, n));              // ^
            if (col - 2 >= 0 && isOpen(row, col - 1)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row, col - 1, n));         // <
            if (col < n && isOpen(row, col + 1)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row, col + 1, n));              // >
        } else {
            if (row - 2 >= 0 && isOpen(row - 1, col)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row - 1, col, n));        // ^
            if (col - 2 >= 0 && isOpen(row, col - 1)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row, col - 1, n));         // <
            if (row < n && isOpen(row + 1, col)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row + 1, col, n));             // v
            if (col < n && isOpen(row, col + 1)) uf.union(translate2DTo1D(row, col, n), translate2DTo1D(row, col + 1, n));              // >
        }
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row >= n || col >= n) throw new IllegalArgumentException("");
        return substance[row - 1][col - 1];
    }

    private int translate2DTo1D(int row, int col, int n) {
        return (row - 1) * n + (col - 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row >= n || col >= n) throw new IllegalArgumentException("");
        return uf.find(translate2DTo1D(row, col, n)) == uf.find(nSquare2);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(nSquare2) == uf.find(nSquare2 + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(10000);
        while (!percolation.percolates())
            percolation.open(StdRandom.uniformInt(1, 10001), StdRandom.uniformInt(1, 10001));
        System.out.println("Percentage : " + Double.valueOf(percolation.numberOfOpenSites()/100000000.0d).toString());
    }
}