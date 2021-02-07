package CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BruteCollinearPoints {
    LineSegment[] lines;
    int numberofSegments;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("points is null");
        }
        Point[] temp = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("point is null");
            }
            temp[i] = points[i];
        }
        Arrays.sort(temp);
        if (Duplicate(temp)) {
            throw new IllegalArgumentException("point is duplicate");
        }
        LineSegment[] tempSegment = new LineSegment[points.length];
        for (int a = 0; a < temp.length; a++) {
            for (int b = a + 1; b < temp.length; b++) {
                for (int c = b + 1; c < temp.length; c++) {
                    for (int d = c + 1; d < temp.length; d++) {
                        if (Double.compare(temp[a].slopeTo(temp[b]), temp[b].slopeTo(temp[c])) == 0.0 && Double.compare(temp[b].slopeTo(temp[c]), temp[c].slopeTo(temp[d])) == 0.0) {
                            LineSegment lineSegment = new LineSegment(temp[a], temp[d]);
                            tempSegment[numberofSegments++] = lineSegment;
                        }
                    }
                }
            }
        }
        lines = Arrays.copyOf(tempSegment, numberofSegments);
        tempSegment = null;
    }    // finds all line segments containing 4 points

    public int numberOfSegments() {
        return numberofSegments;
    }       // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[numberofSegments];
        for (int i = 0; i < lines.length; i++) {
            result[i] = lines[i];
        }
        return result;
    }                // the line segments

    public boolean Duplicate(Point[] points) {
        boolean flag = false;
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        collinear.numberOfSegments();
        collinear.numberOfSegments();
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            //segment.draw();
        }
        collinear.numberOfSegments();
        collinear.numberOfSegments();
        collinear.numberOfSegments();
        collinear.numberOfSegments();
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            //segment.draw();
        }
    }
}