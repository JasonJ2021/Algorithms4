package Week3.QuickSortInterviewQuestions;

public class SelectionInTwoSortedArrays {
    //hard!!
    private static int find(int[] a , int alow , int ahi , int[] b , int blow,int bhi,int k ){
        if(alow>ahi)return b[bhi-k+1];
        if(blow>bhi)return a[ahi-k+1];
        if(k==1)return a[ahi]>b[bhi] ? a[ahi]: b[bhi];
        int at = ahi - (k-1)/2 > alow ? ahi - (k-1)/2 : alow;
        int bt = bhi - (k-1)/2 > blow ? bhi - (k-1)/2 : blow;
        if (a[at] >= b[bt]){
            return find(a,alow,at-1,b,blow,bhi,k-(ahi-at+1));
        }else{
            return find(a,alow,ahi,b,blow,bt-1,k-(bhi-bt+1));
        }
    }

    public static void main(String[] args) {
        int[] a = {1,3,5,7,9};
        int[] b = {2,4,6};
        int i = find(a, 0, a.length - 1, b, 0, b.length - 1, 8);
        System.out.println(i);
    }
}
