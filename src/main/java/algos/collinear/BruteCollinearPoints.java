/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.collinear;

import java.util.Arrays;

public class BruteCollinearPoints {

    private Point[] points;
    private LineSegment[] lineSegments;
    private boolean[][] usedIndices;
    public BruteCollinearPoints(Point[] pss) {
        if (pss == null) throw new IllegalArgumentException();
        for (Point p : pss) if (p == null) throw new IllegalArgumentException();

        Point[] pnts = new Point[pss.length];
        System.arraycopy(pss, 0, pnts, 0, pss.length);
        points = pnts;
        Arrays.sort(points, Point::compareTo);
        for (int i = 0; i < points.length - 1; i++) if (points[i].compareTo(points[i + 1]) == 0) throw new IllegalArgumentException();

        if (pss.length < 4) {
            lineSegments = new LineSegment[0];
            return;
        }

        LineSegment[] ls = new LineSegment[points.length];
        int lsFill = 0;
        usedIndices = new boolean[points.length][points.length];

        L1:
        for (int i = 0; i < points.length; i++) {
            if (usedIndices[i][i]) continue;
            L2:
            for (int j = 0; j < points.length; j++) {
                if (j == i || usedIndices[i][j]) continue;
                L3:
                for (int k = 0; k < points.length; k++) {
                    if (k == j || k == i || usedIndices[j][k]) continue;
                    L4:
                    for (int v = 0; v < points.length; v++) {
                        if (v == i || v == j || v == k || usedIndices[k][v]) continue;
                        Point[] ps = { points[i], points[j], points[k], points[v] };
                        if (ps[0].slopeTo(ps[1]) == ps[0].slopeTo(ps[2]) && ps[0].slopeTo(ps[1]) == ps[0].slopeTo(ps[3])) {
                            Arrays.sort(ps);
                            ls[lsFill++] = new LineSegment(ps[0], ps[3]);
                            usedIndices[i][v] = true;
                            usedIndices[v][i] = true;
                            usedIndices[i][k] = true;
                            usedIndices[k][i] = true;
                            usedIndices[v][v] = true;
                            usedIndices[i][j] = true;
                            usedIndices[j][k] = true;
                            usedIndices[k][v] = true;
                            usedIndices[j][i] = true;
                            usedIndices[k][j] = true;
                            usedIndices[v][k] = true;
                            continue L1;
                        }
                    }
                }
            }
        }

        lineSegments = new LineSegment[lsFill];
        System.arraycopy(ls, 0, lineSegments, 0, lsFill);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        LineSegment[] lses = new LineSegment[numberOfSegments()];
        System.arraycopy(lineSegments, 0, lses, 0, numberOfSegments());
        return lses;
    }
}