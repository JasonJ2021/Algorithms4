package Week1;

public class EggDrop {
    public static int T = 10;
    public static int NumofBrokenEggs;
    public static int trynums;
    public static boolean drop(int i ){
        trynums++;
        if(i <T){
            System.out.println("from" + i + "not broken");
            return false;
        }else{
            System.out.println("from" +  i + "broken");
            NumofBrokenEggs++;
            return true;
        }
    }
    public static void virsion0(int n){
        for(int i = 0 ; i < n ; i++){
            boolean drop = drop(i);
            if (drop == true){
                System.out.println("T is " + i );
                break;
            }
        }
    }
    public static void virsion1(int n ,int start,int end){
        boolean drop = drop((start + end) / 2);
        while(start < end){
            if(drop == false){
                start = (start + end) / 2 + 1;
                drop = drop((start + end) / 2);
            }else{
                end = (start + end) / 2 - 1;
                drop = drop((start + end) / 2);
            }
        }
        if (drop == false){
            System.out.println("T is " + ((start + end) / 2 +1));
        }else{
            System.out.println("T is " + (start + end) / 2);
        }
    }
    public static void virsion2(int n ){
        int start = 0;
        int end = n - 1;
        for(int i = 0 ; i < Math.sqrt(n) -1;i++){
            int t = (int)Math.pow(2,(double)i);
            boolean drop = drop(t);
            if(drop == true){
                start = (int)Math.pow(2,(double)i - 1);
                end = (int)Math.pow(2,(double)i);
                break;
            }
        }
        virsion1(n,start,end);
    }
    public static void virsion3(int n ){
        int k = -1;
        int l = (int)Math.sqrt(n);
        for(int i = 0 ; i*l <n ;i++){
            boolean drop = drop(i * l);
            if(drop == true){
                k = i;
                break;
            }
        }
        for(int i = (k-1)*l + 1 ; i<k*l;i++){
            boolean drop = drop(i);
            if (drop == true){
                System.out.println("T is " + i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        int n = 40;
        virsion3(n);

    }
}
