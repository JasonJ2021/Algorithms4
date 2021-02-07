package Week1;

import java.util.Arrays;
import java.util.HashMap;

public class ThreeSum {
    public static int[] TwoSum(int[] a, int target){
        HashMap<Integer , Integer> hs = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if(hs.containsKey(a[i])){
                return new int[]{hs.get(a[i]),a[i]};
            }
            hs.put(target - a[i] , a[i]);
        }
        return null;
    }
    public static int[] ThreeSum(int[] a , int target){
        for (int i = 0; i < a.length; i++) {
            int[] temp = TwoSum(a,target - a[i]);
            if(temp !=null){
                if(a[i] != temp[0]&&a[i] !=temp[1])return new int[]{a[i],temp[0],temp[1]};
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        int[] result = ThreeSum(a,6);
        System.out.println(Arrays.toString(result));
    }
}

