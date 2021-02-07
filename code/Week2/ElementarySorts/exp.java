package Week2.ElementarySorts;

import edu.princeton.cs.algs4.StdOut;

import java.text.SimpleDateFormat;
import java.util.Date;

public class exp {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    public static boolean less(int v , int w){
        return v-w < 0;
    }
    public static void exch(int[] a , int i , int j ){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    public static void show(int[] a){
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    public static boolean isSorted(int[] a ){
        for (int i = 1; i < a.length; i++) {
            if (less(a[i] ,a[i-1]))return false;
        }
        return true;
    }
    public static void time(){
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
}
