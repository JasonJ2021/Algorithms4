package CollinearPoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    LineSegment[] lines;
    int numberofSegments;
    public FastCollinearPoints(Point[] points){
        int n = points.length;
        LineSegment[] temp = new LineSegment[n*n];
        Point[] copy1 = new Point[n];
        Point[] copy2 = new Point[n];
        copy(points,copy1);
        Arrays.sort(copy1);
        copy(copy1,copy2);
        for (int i = 0; i < copy1.length; i++) {
            int count = 1;
            Arrays.sort(copy2,copy1[i].slopeOrder());
            Point min = copy2[0];
            Point max = copy2[0];
            for(int j = 1 ; j < n ; j++){
                if (Double.compare(copy1[i].slopeTo(copy2[j]),copy1[i].slopeTo(copy2[j-1]))==0){
                    count ++;
                    if(copy2[j].compareTo(min) <0){
                        min = copy2[j];
                    }
                    if (copy2[j].compareTo(max) >0){
                        max = copy2[j];
                    }
                    if(j == n-1 && copy1[i].compareTo(min) <0 && count >=3){
                        temp[numberofSegments++] = new LineSegment(copy1[i],max);
                    }
                }else{
                    if(copy1[i].compareTo(min) <0 && count >=3){
                        temp[numberofSegments++] = new LineSegment(copy1[i],max);
                    }
                    min = copy2[j];
                    max = copy2[j];
                    count = 1;
                }
            }
        }
        lines = Arrays.copyOf(temp , numberofSegments);
    }     // finds all line segments containing 4 or more points

    public int numberOfSegments(){
        return numberofSegments;
    }       // the number of line segments
    public boolean Duplicate(Point[] points) {
        boolean flag = false;
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }//检测是否有重复的point
    public LineSegment[] segments(){
        LineSegment[] result = new LineSegment[numberofSegments];
        for (int i = 0; i < result.length; i++) {
            result[i] = lines[i];
        }
        return result;
    }               // the line segments
    public static void main(String[] args) {

        // read the n points from a file
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
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
    public void copy(Point[] origin,Point[] target){
        for (int i = 0; i < origin.length; i++) {
            if (origin[i] == null)throw new IllegalArgumentException();
            target[i] = origin[i];
        }
    }
}