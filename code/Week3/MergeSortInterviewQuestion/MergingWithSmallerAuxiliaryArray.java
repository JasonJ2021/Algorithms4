package Week3.MergeSortInterviewQuestion;

import Week2.ElementarySorts.exp;

public class MergingWithSmallerAuxiliaryArray {
    public static void sort(int[] a){
        //0 - a[n-1] is sorted and a[n] ~ a[2n-1]is sorted
        int N = a.length;
        int n = N/2;
        int start = 0;
        int end = n;
        int[] helper = new int[n];
        for (int i = 0; i < helper.length; i++) {
            helper[i] = a[i];
        }
        for(int i = 0 ;i< n ;i++){
            if(start == n){
                a[i] = a[end];
                end++;
                continue;
            }
            if(end == N){
                a[i] = helper[start];
                start++;
                continue;
            }
            if(helper[start] < a[end]){
                a[i] = helper[start];
                start++;
            }else{
                a[i] = a[end];
                end++;
            }
        }
        int k = 0;
        while(end != N){
            helper[k] = a[end];
            end++;
            k++;
        }
        end = start;
        start = 0;
        int ending = end;
        k = n;
        while(start != ending || end !=n){
            if(start == ending){
                a[k++] = helper[end++];
                continue;
            }
            if (end ==n){
                a[k++] = helper[start++];
                continue;
            }
            if(helper[start] < helper[end]){
                a[k++] = helper[start++];
            }else{
                a[k++] = helper[end++];
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {2,4,6,7,1,3,5,9};
        sort(a);
        exp.show(a);
    }
}
