package Week2.ElementarySorts.InterviewQuestions;

import Week2.ElementarySorts.exp;

public class DutchNationalFlag {
    public static int Red = 0;
    public static int White = 1;
    public static int Blue = 2;
    private int[] buckets;
    public int colortimes = 0;
    public int swaptimes = 0;
    public DutchNationalFlag(int[] buckets){
        this.buckets = buckets;
    }
    public void sort(){
        int low = 0;
        int high = buckets.length - 1;
        int cur = low;
        while(cur <= high){
            if(color(cur) == Red){
                if(cur != low){
                    swap(cur,low);
                }
                cur++;
                low++;
            }else if (buckets[cur] == Blue){
                swap(cur,high);
                high--;
            }else{
                cur++;
            }
        }
    }
    public int color(int loc){
        colortimes++;
        return buckets[loc];
    }
    public void swap(int i , int j ){
        swaptimes++;
        int temp = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = temp;
    }

    public static void main(String[] args) {
        int[] bucket = {0,1,2,1,0,1,2,1,2,1,1,1,2,2,2,0,0,0};
        DutchNationalFlag dutchNationalFlag = new DutchNationalFlag(bucket);
        dutchNationalFlag.sort();
        exp.show(dutchNationalFlag.buckets);
        System.out.println(dutchNationalFlag.buckets.length);
        System.out.println(dutchNationalFlag.colortimes);
        System.out.println(dutchNationalFlag.swaptimes);
    }
}
