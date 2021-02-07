package Week3;

import Week2.ElementarySorts.exp;
import Week4.HeapSort;
import edu.princeton.cs.algs4.StdRandom;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {
    private static int partition(int[] a , int lo , int hi){
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while(true){
            while(exp.less(a[++i] , v)){
                if (i == hi)break;
            }
            while(exp.less(v,a[--j])){
                if (j == lo)break;
            }
            if(i>=j)break;
            exp.exch(a,i,j);
        }
        exp.exch(a,lo,j);
        return j ;
    }
    private static void sort(int[] a , int lo , int hi){
        if (lo>=hi)return;
        int partition = partition(a, lo, hi);
        sort(a,lo,partition-1);
        sort(a,partition+1,hi);
    }
    public static void sort(int[] a ){
        sort(a,0,a.length-1);
    }

    public static void main(String[] args) {
        int[] a = new int[10000000];
        for (int i = 1; i < a.length; i++) {
            a[i] = StdRandom.uniform(1000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String time1 = simpleDateFormat.format(date1);
        System.out.println(time1);
        sort(a);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);
        System.out.println(time2);
    }
}
