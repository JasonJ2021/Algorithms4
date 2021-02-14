package Puzzle;

import java.util.LinkedList;

public class Board {
    private int[][] blocks;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                blocks[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(dimension() + "\n");
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                str.append(String.format("%2d",blocks[row][col]));
            }
            str.append("\n");
        }
        return str.toString();
    }

    // board dimension n
    public int dimension(){
        return blocks.length;
    }

    // number of tiles out of place
    public int hamming(){
        int hamming = 0;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (!blockIsInplace(row,col)){
                    hamming++;
                }
            }
        }
        return hamming;
    }
    private boolean blockIsInplace(int row , int col){
        int temp = blocks[row][col];
        if (temp == 0)return true;
        if(temp == (row)*dimension() + col+1){
            return true;
        }else{
            return false;
        }
    }
    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int manhattan = 0;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (blockIsInplace(row,col)){
                    continue;
                }else{
                    manhattan += Math.abs((blocks[row][col]-1)/dimension() - row) + Math.abs(col - Math.floorMod(blocks[row][col]-1,dimension()));
                }
            }
        }
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal(){
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (!blockIsInplace(row,col)){
                    return false;
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if (y == this)return true;
        if (y == null || !(y instanceof Board)||((Board) y).blocks.length != this.blocks.length)return false;
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if(((Board) y).blocks[row][col] !=blocks[row][col] )return false;
            }
        }
        return true;
    }
    private int[] spaceLocation(){
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                if (blocks[row][col] == 0){
                    int[] location = new int[2];
                    location[0] = row;
                    location[1] = col;
                    return location;
                }
            }
        }
        throw new RuntimeException();
    }
    private int[][] swap(int row1,int col1,int row2,int col2){
        int[][] copy = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                copy[row][col] = blocks[row][col];
            }
        }
        int temp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = temp;
        return copy;
    }
    // all neighboring boards
    public Iterable<Board> neighbors(){
        LinkedList<Board> neighbors = new LinkedList<Board>();

        int[] location = spaceLocation();
        int spaceRow = location[0];
        int spaceCol = location[1];

        if (spaceRow > 0)               neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
        if (spaceRow < dimension() - 1) neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
        if (spaceCol > 0)               neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
        if (spaceCol < dimension() - 1) neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol + 1)));

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        for (int row = 0; row < blocks.length; row++)
            for (int col = 0; col < blocks.length - 1; col++)
                if (blocks[row][col] != 0 && blocks[row][col + 1] != 0) {
                    return new Board(swap(row, col, row, col + 1));
                }
        throw new RuntimeException();
    }


}