package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    public static double Fractile = 1.96;
    int size;
    int times;
    double mean;
    double stddev;
    double[] data;
    // perform independent trials on an n-by-n grid
    private int Random(){
        return StdRandom.uniform(0,size) + 1;
    }
    public PercolationStats(int n, int trials){
        if(n<=0 || trials <=0){
            throw new IllegalArgumentException("Error");
        }
        size = n;
        times = trials;
        data = new double[trials];
        for(int i = 0 ; i <trials ; i++){
            Percolation percolation = new Percolation(size);
            while(!percolation.percolates()){
                int row = Random();
                int col = Random();
                percolation.open(row,col);
            }
            data[i] = ((double) percolation.numberOfOpenSites()) / (size*size);
        }
        mean = StdStats.mean(data);
        stddev = StdStats.stddev(data);
    }
    // sample mean of percolation threshold
    public double mean(){
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        double temp = Fractile * stddev /Math.sqrt(times);
        return mean - temp;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        double temp = Fractile* stddev / Math.sqrt(times);
        return mean + temp;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 100;
        int trials = 100;
        PercolationStats percStats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + percStats.mean());
        StdOut.println("stddev                  = " + percStats.stddev());
        StdOut.println("95% confidence interval = [" + percStats.confidenceLo()
                + ", " + percStats.confidenceHi() + "]");
    }

}
