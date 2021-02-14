package KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;


public class KdTree {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private Node root;
    private int size;

    private class Node {
        private Point2D p;
        private Node left;
        private Node right;
        private boolean divide; //vertical or horizontal;

        public Node(Point2D p) {
            this.p = p;
        }
    }

    public KdTree(){
        size = 0;
        root = null;
    }                            // construct an empty set of points

    public boolean isEmpty(){
        return root == null;
    }                    // is the set empty?

    public int size(){
        return size;
    }                     // number of points in the set

    public void insert(Point2D p){
        if (p == null)throw new IllegalArgumentException("null\n");
        root = insert(root,p,VERTICAL);
    }              // add the point to the set (if it is not already in the set)
    private Node insert(Node h , Point2D p ,boolean divide){
        if (h == null){
            Node temp = new Node(p);
            size ++;
            temp.divide = divide;
            return temp;
        }
        double x = p.x();
        double y = p.y();
        double hx = h.p.x();
        double hy = h.p.y();
        if (h.divide == VERTICAL){
            if (x > hx){
                h.right = insert(h.right,p,!divide);
            }else if (x < hx){
                h.left = insert(h.left,p,!divide);
            }else if (y != hy){
                h.right = insert(h.right,p,!divide);
            }
        }
        if (h.divide == HORIZONTAL){
            if (y > hy){
                h.right = insert(h.right,p,!divide);
            }else if (y < hy){
                h.left = insert(h.left,p,!divide);
            }else if (x != hx){
                h.right = insert(h.right,p,!divide);
            }
        }
        return h;
    }
    public boolean contains(Point2D p){
        if (p == null)throw new IllegalArgumentException("null\n");
        return contains(root,p);
    }            // does the set contain point p?
    private boolean contains(Node h , Point2D p ){
        while(h !=null){
            if (h.divide == VERTICAL){
                if (p.x() > h.p.x()){
                    h = h.right;
                }else if (p.x() < h.p.x()){
                    h = h.left;
                }else if (p.y() != h.p.y()){
                    h = h.right;
                }else{
                    return true;
                }
            }else if (h.divide == HORIZONTAL){
                if (p.y() > h.p.y()){
                    h = h.right;
                }else if (p.y() < h.p.y()){
                    h = h.left;
                }else if (p.x() != h.p.x()){
                    h = h.right;
                }else{
                    return true;
                }
            }
        }
        return false;
    }
    public void draw(){
        draw(root,0.0,0.0,1.0,1.0);
    }                         // draw all points to standard draw
    private void draw(Node h , double xmin,double ymin,double xmax,double ymax){
        if (h == null)return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        h.p.draw();
        if (h.divide == VERTICAL){
            //画垂直线 红线
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            RectHV rect = new RectHV(h.p.x(),ymin,h.p.x(),ymax);
            rect.draw();
            draw(h.left,xmin,ymin,h.p.x(),ymax);
            draw(h.right,h.p.x(),ymin,xmax,ymax);
        }
        if (h.divide == HORIZONTAL){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            RectHV rect = new RectHV(xmin,h.p.y(),xmax,h.p.y());
            rect.draw();
            draw(h.left,xmin,ymin,xmax,h.p.y());
            draw(h.right,xmin,h.p.y(),xmax,ymax);
        }
    }
    public Iterable<Point2D> range(RectHV rect){
        if (rect == null){
            throw new IllegalArgumentException("null\n");
        }
        Stack<Point2D> stack = new Stack<Point2D>();
        RectHV rootrect = new RectHV(0.0,0.0,1.0,1.0);
        range(root,rootrect,rect,stack);
        return stack;
    }             // all points that are inside the rectangle (or on the boundary)
    private void range(Node h ,RectHV rootrect,RectHV rect,Stack stack){
        if (h == null)return;
        if (!rootrect.intersects(rect))return;
        if (rect.contains(h.p))stack.push(h);
        if (h.divide == VERTICAL){
            double ymin = rootrect.ymin();
            double ymax = rootrect.ymax();
            //先向右查找
            double xmin = h.p.x();
            double xmax = rootrect.xmax();
            range(h.right,new RectHV(xmin,ymin,xmax,ymax),rect,stack);
            //向左查找
            xmin = rootrect.xmin();
            xmax = h.p.x();
            range(h.left,new RectHV(xmin,ymin,xmax,ymax),rect,stack);
        }else if (h.divide == HORIZONTAL){
            double xmin = rootrect.xmin();
            double xmax = rootrect.xmax();
            //先向上查找right
            double ymin = h.p.y();
            double ymax = rootrect.ymax();
            range(h.right,new RectHV(xmin,ymin,xmax,ymax),rect,stack);
            //向下查找 left
            ymin = rootrect.ymin();
            ymax = h.p.y();
            range(h.left,new RectHV(xmin,ymin,xmax,ymax),rect,stack);
        }
    }
    public Point2D nearest(Point2D p){
        if (p == null)throw new IllegalArgumentException("null\n");
        if (isEmpty())return null;
        Node nearestNode = new Node(root.p);
        nearestNode.left = root.left;
        nearestNode.right = root.right;
        nearestNode.divide = root.divide;
        RectHV rootrect = new RectHV(0.0,0.0,1.0,1.0);
        nearest(root,nearestNode,rootrect,p);
        return nearestNode.p;
    }             // a nearest neighbor in the set to point p; null if the set is empty
    private void nearest(Node h , Node nearestNode , RectHV rootrect , Point2D p){
        if (h == null)return;
        if (p.distanceSquaredTo(h.p) < p.distanceSquaredTo(nearestNode.p)){
            nearestNode.p = h.p;
        }
        double hx = h.p.x();
        double hy = h.p.y();
        double x = p.x();
        double y = p.y();
        double xmin,ymin,xmax,ymax;
        if (h.divide == VERTICAL){
            //制造两个矩阵一个检索左边一个检索右边
            //先制造左边的矩阵
            xmin = rootrect.xmin();
            xmax = h.p.x();
            ymin = rootrect.ymin();
            ymax = rootrect.ymax();
            RectHV rightRect = new RectHV(xmin,xmax,ymin,ymax);
            xmin = h.p.x();
            xmax = rootrect.xmax();
            RectHV leftRect = new RectHV(xmin,xmax,ymin,ymax);
            if (x >= hx){
                //在右边检索
                nearest(h.right,nearestNode,rightRect,p);
                if (leftRect.distanceSquaredTo(p) < nearestNode.p.distanceSquaredTo(p)){
                    nearest(h.left,nearestNode,leftRect,p);
                }
            }else{
                nearest(h.left,nearestNode,leftRect,p);
                if (rightRect.distanceSquaredTo(p) < nearestNode.p.distanceSquaredTo(p)){
                    nearest(h.right,nearestNode,rightRect,p);
                }
            }
        }else if (h.divide == HORIZONTAL){
            //制造两个矩阵一个检索上面一个检索下面
            xmin = rootrect.xmin();
            xmax = rootrect.xmax();
            ymin = rootrect.ymin();
            ymax = h.p.y();
            RectHV rightRect = new RectHV(xmin,xmax,ymin,ymax);
            ymin = h.p.y();
            ymax = rootrect.ymax();
            RectHV leftRect = new RectHV(xmin,xmax,ymin,ymax);
            if (y >= hy){
                //在右边检索
                nearest(h.right,nearestNode,rightRect,p);
                if (leftRect.distanceSquaredTo(p) < nearestNode.p.distanceSquaredTo(p)){
                    nearest(h.left,nearestNode,leftRect,p);
                }
            }else{
                nearest(h.left,nearestNode,leftRect,p);
                if (rightRect.distanceSquaredTo(p) < nearestNode.p.distanceSquaredTo(p)){
                    nearest(h.right,nearestNode,rightRect,p);
                }
            }
        }
    }
    public static void main(String[] args){}                // unit testing of the methods (optional)
}