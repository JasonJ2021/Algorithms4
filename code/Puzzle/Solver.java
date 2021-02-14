package Puzzle;

import Week4.BST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Stack;

public class Solver {
    private int moves;
    private boolean solvable;
    private Stack<Board> solution;
    // find a solution to the initial board (using the A* algorithm)
    private class Node{
        Board board;
        int moves;
        int priority;
        Node pre;
    }
    public Solver(Board initial){
        if (initial == null) {
            throw new IllegalArgumentException("initial == null\n");
        }
        moves = -1;
        solvable = true;
        solution = new Stack<>();
        MinPQ<Node> pq = init(initial);
        MinPQ<Node> twinpq = init(initial.twin());
        while(true){
            Node searchNode = pq.delMin();
            Node preNode = searchNode.pre;
            Board board = searchNode.board;
            if (searchNode.board.isGoal()){
                moves = searchNode.moves;
                solvable = true;
                solution = findRoot(searchNode);
                break;
            }
            for (Board neighbor : board.neighbors()) {
                if (preNode ==null){
                    Node temp = new Node();
                    temp.board = neighbor;
                    temp.pre = searchNode;
                    temp.moves = 1+ searchNode.moves;
                    temp.priority = neighbor.manhattan()+ temp.moves;
                    pq.insert(temp);
                }else if (!preNode.equals(neighbor)){
                    Node temp = new Node();
                    temp.board = neighbor;
                    temp.pre = searchNode;
                    temp.moves = 1+ searchNode.moves;
                    temp.priority = neighbor.manhattan()+ temp.moves;
                    pq.insert(temp);
                }
            }
            Node twinsearchNode = twinpq.delMin();
            Node twinpreNode = twinsearchNode.pre;
            Board twinboard = searchNode.board;
            if (twinsearchNode.board.isGoal()){
                solvable = false;
                break;
            }
            for (Board neighbor : twinboard.neighbors()) {
                if (twinpreNode ==null){
                    Node temp = new Node();
                    temp.board = neighbor;
                    temp.pre = twinsearchNode;
                    temp.moves = 1+ twinsearchNode.moves;
                    temp.priority = neighbor.manhattan()+ temp.moves;
                    twinpq.insert(temp);
                }else if (!twinpreNode.equals(neighbor)){
                    Node temp = new Node();
                    temp.board = neighbor;
                    temp.pre = twinsearchNode;
                    temp.moves = 1+ twinsearchNode.moves;
                    temp.priority = neighbor.manhattan()+ temp.moves;
                    twinpq.insert(temp);
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return solvable;
    }
    private MinPQ<Node> init(Board root){
        Node rootNode  = new Node();
        rootNode.board = root;
        rootNode.moves = 0;
        rootNode.priority = root.manhattan();
        rootNode.pre = null;
        MinPQ<Node> pq = new MinPQ<Node>(priority());
        pq.insert(rootNode);
        return pq;
    }
    private Comparator<Node> priority(){
        return new ByPriority();
    }
    private class ByPriority implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.priority < o2.priority){
                return -1;
            }else if (o1.priority >o2.priority){
                return 1;
            }else{
                return 0;
            }
        }
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        return moves;
    }
    private Stack<Board> findRoot(Node leaf){
        Stack<Board> path = new Stack<>();
        Node temp = leaf;
        path.push(temp.board);
        temp = temp.pre;
        while(temp !=null){
            path.push(temp.board);
            temp = temp.pre;
        }
        return path;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if (!solvable)return null;
        return solution;
    }

    // test client (see below)
    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}