
package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int count; //the number of the open sites
    WeightedQuickUnionUF uf;
    WeightedQuickUnionUF backwash;
    int size;
    boolean[][] judge;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if(n <=0)throw new IllegalArgumentException("Error : n <=0");
        size = n;
        judge = new boolean[n][n]; // boolean default false Boolean default == null
        uf = new WeightedQuickUnionUF(n*n + 2);
        backwash = new WeightedQuickUnionUF(n*n + 1);
        count = 0;
    }
    public void validate(int n ){
        if(n <=0 || n > size)throw new IllegalArgumentException("row or column is not in the valid spectrum");
    }
    public int locate(int row, int col){
        return (row - 1)*size + col;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(judge[row-1][col-1] == true)return;
        count++;
        judge[row-1][col-1] = true;
        int location = locate(row , col);
        if(row == 1){
            uf.union(location,0);
            backwash.union(location,0);
        }
        if(row == size){
            uf.union(location,size*size+1);
        }
        int temp = 0;
        //上搜查
        if(row >1){
            if(isOpen(row-1,col)){
                temp = locate(row -1 , col);
                uf.union(temp,location);
                backwash.union(temp,location);
            }
        }
        //下搜查
        if(row<size){
            if(isOpen(row+1,col)){
                temp = locate(row + 1 , col);
                uf.union(temp,location);
                backwash.union(temp,location);
            }
        }
        //左搜查
        if(col > 1 ){
            if(isOpen(row,col -1 )){
                temp = locate(row,col-1);
                uf.union(temp,location);
                backwash.union(temp,location);
            }
        }
        if(col < size){
            if(isOpen(row,col+1)){
                temp = locate(row , col + 1);
                uf.union(temp,location);
                backwash.union(temp,location);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validate(row);
        validate(col);
        return judge[row - 1 ][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int location = locate(row,col);
        if(judge[row-1][col-1] == false)return false;
        return uf.connected(0,location) && backwash.connected(0,location);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return count;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(0,size*size + 1);
    }

    // test client (optional)
    public static void main(String[] args){
    }
}