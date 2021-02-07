package Week2.ElementarySorts;


import edu.princeton.cs.algs4.StdRandom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShellSort {
    public static void sort(int[] a ){
        int length = a.length;
        int h = 1;
        while(h < length/3){
            h = h*3 + 1;
        }
        while(h >=1){
            for (int i = h ; i < length;i++){
                for (int j = i ; j >=h &&exp.less(a[j],a[j-h]);j -=h){
                    exp.exch(a,j,j-h);
                }
            }
            h = h/3;
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(100);
        }
        exp.time();
        sort(arr);
        exp.time();
    }
}
