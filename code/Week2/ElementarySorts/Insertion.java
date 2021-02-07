package Week2.ElementarySorts;

import edu.princeton.cs.algs4.StdRandom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Insertion {
    public static void sort(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j >= 1 && exp.less(a[j], a[j - 1]); j--) {
                exp.exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(100);
        }
        exp.time();
        sort(arr);
        exp.time();
    }
}
