package KdTrees;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;


public class PointSET {
    private SET<Point2D> points;

    public PointSET() {
        points = new SET<Point2D>();
    }                               // construct an empty set of points

    public boolean isEmpty() {
        return points.isEmpty();
    }                      // is the set empty?

    public int size() {
        return points.size();
    }                         // number of points in the set

    public void insert(Point2D p){
        if (p == null){
            throw new IllegalArgumentException("the element inserted is null\n");
        }
        if (!points.contains(p)){
            points.add(p);
        }
    }              // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p){
        if (p == null)throw new IllegalArgumentException("the element is null\n");
        return points.contains(p);
    }            // does the set contain point p?

    public void draw(){
        for (Point2D p : points) {
            p.draw();
        }
    }                         // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect){
        if (rect == null)throw new IllegalArgumentException("null\n");
        double xmin = rect.xmin();
        double xmax = rect.xmax();
        double ymin = rect.ymin();
        double ymax = rect.ymax();
        Stack<Point2D> list = new Stack<>();
        for (Point2D point : points) {
            double x = point.x();
            double y = point.y();
            if (x>=xmin && x<=xmax && y>=ymin && y<=ymax){
                list.push(point);
            }
        }
        return list;
    }             // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p){
        if (p == null)throw new IllegalArgumentException("null\n");
        if (points.isEmpty())return null;
        double dist = 0xffff; //十进制的65535
        Point2D nearest = null;
        for (Point2D point : points) {
            if (point.distanceSquaredTo(p) < dist){
                dist = point.distanceSquaredTo(p);
                nearest = point;
            }
        }
        return nearest;
    }             // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {}                 // unit testing of the methods (optional)
}