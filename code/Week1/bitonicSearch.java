package Week1;

import java.util.Arrays;

public class bitonicSearch {
    public static boolean WorstSituation(int[] a, int target) {
        //先找到最大值
        int length = a.length;
        int maxlocation = findMax(a);
        if (target == a[maxlocation])return true;
        if(target > a[maxlocation])return false;
        if (a[0] <= target) {
            int min = Arrays.binarySearch(a, 0, maxlocation, target);
            if(min >=0)return true;
        }
        if(a[length-1] <=target){
            int max = Arrays.binarySearch(a, maxlocation, length, target);
            if(max >=0)return true;
        }
        return false;
    }
    public static int findMax(int[] a){
        int i = 0,j = a.length-1;
        while(i <=j){
            int temp = i + (j-i)/2;
            if(a[temp] < a[temp + 1]){
                i=temp+1;
            }else{
                j = temp - 1;
            }
        }
        return i;
    }
    public static boolean bitonicsearch(int[] a ,int target,int start , int end){
        return false;
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,6,7,12,14,8,4,3,2,1};
        for(int i = 1;i <= 14;i++){
            System.out.println(bitonicsearch(a,i,0,a.length-1));
        }
    }
}
