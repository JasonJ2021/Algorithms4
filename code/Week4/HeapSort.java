package Week4;

import Week2.ElementarySorts.exp;
import edu.princeton.cs.algs4.StdRandom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeapSort {
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void sink(int[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j + 1 <= N && a[j] - a[j + 1] < 0){
                j++;
            }
            if (a[k] > a[j]) break;
            exch(a, j, k);
            k = j;
        }
    }

    public static void sort(int[] a) {
        int N = a.length-1;
        for(int k = N/2;k>=1;k--){
            sink(a,k,N);
        }
        while(N>1){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[100000000];
        a[0] = 0;
        for (int i = 1; i < a.length; i++) {
            a[i] = StdRandom.uniform(1000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String time1 = simpleDateFormat.format(date1);
        System.out.println(time1);
        HeapSort.sort(a);
        Date date2 = new Date();
        String time2 = simpleDateFormat.format(date2);
        System.out.println(time2);
    }
}
