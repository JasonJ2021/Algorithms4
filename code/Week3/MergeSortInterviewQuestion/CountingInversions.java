package Week3.MergeSortInterviewQuestion;

public class CountingInversions {
    private static int[] aux;
    private static int nums;
    public static void merge(int[] a, int lo , int mid , int hi){
        int i = lo;
        int j = mid+1;
        for(int k = lo;k<=hi;k++){
            aux[k] = a[k];
        }
        for(int k = lo ; k <=hi; k++){
            if(i >mid){
                a[k] = aux[j++];
            }else if (j >hi){
                a[k] = aux[i++];
            }else if (aux[j] < aux[i]){
                a[k] = aux[j++];
                //此时aux[i]~aux[mid]之间所有数与aux[j]形成inversions
                nums += mid - i +1;
            }else{
                a[k] = aux[i++];
            }
        }
    }
    public static void sort(int[] a){
        aux = new int[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(int[] a, int lo, int hi) {
        if(hi<= lo)return;
        int mid = (lo+hi)/2;
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }
    public static int getNums(){
        return nums;
    }
    public static void main(String[] args) {
        int[] a = {1,3,7,4,5};
        sort(a);
        int nums = getNums();
        System.out.println(nums);
    }
}
