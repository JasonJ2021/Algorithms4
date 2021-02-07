package Week3;

import Week2.ElementarySorts.exp;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {

    public static void sort(int[] a ){
        sort(a,0,a.length-1);
    }
    private static void sort(int[] a , int lo , int hi ){
        if(hi<=lo)return;
        int lt = lo , i = lo + 1,gt = hi;
        int v = a[lo];
        while(i<=gt){
            int cmp = a[i] - v;
            if(cmp < 0){
                exp.exch(a,lt++,i++);
            }else if(cmp > 0){
                exp.exch(a,gt--,i);
            }else{
                i++;
            }
        }
        sort(a,lo,lt-1);
        sort(a,gt+1,hi);
    }

    public static void main(String[] args) {
        int[] a = new int[20];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(20);
        }
        sort(a);
        boolean sorted = exp.isSorted(a);
        System.out.println(sorted);
    }
}
