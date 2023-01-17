/*
 * Copyright © 2023. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.collinear;

import java.util.Arrays;

public class FastCollinearPoints {

    private Point[] original;
    private Point[] sorting;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] pArray) {
        if (pArray == null) throw new IllegalArgumentException();
        for (Point p : pArray) if (p == null) throw new IllegalArgumentException();

        sorting = new Point[pArray.length];
        System.arraycopy(pArray, 0, sorting, 0, pArray.length);
        original = new Point[pArray.length];
        System.arraycopy(pArray, 0, original, 0, pArray.length);

        Arrays.sort(sorting, Point::compareTo);
        for (int i = 0; i < sorting.length - 1; i++)
            if (sorting[i].compareTo(sorting[i + 1]) == 0) throw new IllegalArgumentException();

        if (pArray.length < 4) {
            lineSegments = new LineSegment[0];
            return;
        }

        LineSegment[] ls = new LineSegment[sorting.length * sorting.length];
        int lsFill = 0;
        boolean[][] usedIndices = new boolean[original.length][original.length];

        Base:
        for (int p = 0; p < original.length; p++) {
            Arrays.sort(sorting, original[p].slopeOrder());
            int cursor = 0;
            int offset = 0;
            boolean hasBegun = false;
            O1:
            for (; cursor < sorting.length - 3;) {
                for (; cursor < sorting.length - 3;) {
                    Point p1 = sorting[0];
                    Point p2 = sorting[cursor + 1];
                    Point p3 = sorting[cursor + 2];
                    Point p4 = sorting[cursor + 3];
                    if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p3) == p1.slopeTo(p4)) {
                        cursor++;
                        hasBegun = true;
                    } else if (hasBegun)
                        break;
                    else
                        cursor = ++offset;
                }
                Point[] ps = new Point[cursor - offset + 3];
                if (ps.length < 4) {
                    hasBegun = false;
                    offset = ++cursor;
                    continue O1;
                }
                for (int y = 1; y < ps.length; y++)
                    ps[y] = sorting[offset + y];
                ps[0] = sorting[0];
                Arrays.sort(ps, Point::compareTo);
//                int[] origIndices = new int[ps.length];
//                Out:
//                for (int q1 = 0; q1 < ps.length; q1++) {
//                    for (int q = 0; q < original.length; q++) {
//                        if (ps[q1].compareTo(original[q]) == 0) {
//                            origIndices[q1] = q;
//                            continue Out;
//                        }
//                    }
//                }
                for (int lsy = 0; lsy < ls.length; lsy++) {
                    if (ls[lsy] == null) break;
                    if (ls[lsy].toString().equals(new LineSegment(ps[0], ps[ps.length - 1]).toString()) ||
                            ls[lsy].toString().equals(new LineSegment(ps[ps.length - 1], ps[0]).toString())) {
                        hasBegun = false;
                        offset = ++cursor;
                        continue O1;
                    }
                }
//                if (usedIndices[origIndices[0]][origIndices[origIndices.length - 1]] || usedIndices[origIndices[origIndices.length - 1]][origIndices[0]]) {
//                    hasBegun = false;
//                    offset = ++cursor;
//                    continue O1;
//                }
//                usedIndices[origIndices[0]][origIndices[origIndices.length - 1]] = true;
//                usedIndices[origIndices[origIndices.length - 1]][origIndices[0]] = true;
                ls[lsFill++] = new LineSegment(ps[0], ps[ps.length - 1]);
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