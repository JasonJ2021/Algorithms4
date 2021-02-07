package Week3;

import Week2.ElementarySorts.exp;
import edu.princeton.cs.algs4.StdRandom;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MergeSort {
    private static int[] aux;
    public static void merge(int[] a , int lo , int mid , int hi){
        int i = lo;
        int j = mid + 1;
        for(int k = lo;k<=hi;k++) {
            aux[k] = a[k];
        }
        for(int k = lo;k<=hi;k++){
            if (i > mid){
                a[k] =aux[j++];
            }else if(j >hi){
                a[k] =aux[i++];
            }else if(aux[j] < aux[i]){
                a[k] = aux[j++];
            }else{
                a[k] = aux[i++];
            }
        }
    }
    public static void sort(int[] a ){
        aux = new int[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if(hi<=lo)return;
        int mid = (lo + hi)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

    public static void main(String[] args) {
        int[] a = new int[100000000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform(1000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date1 = new Date();
        String format1 = simpleDateFormat.format(date1);
        System.out.println(format1);
        sort(a);
        Date date2 = new Date();
        String format2 = simpleDateFormat.format(date2);
        System.out.println(format2);
    }
}
