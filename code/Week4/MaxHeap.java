package Week4;

public class MaxHeap {
    public int[] pq;
    public int N =0;
    private void resize(int capacity){
        int[] temp = new int[capacity];
        for (int i = 0; i < N+1; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
    public MaxHeap(){
        pq = new int[1];
    }
    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public void insert(int v){
        if(N + 1 ==pq.length)resize(2*(N+1));
        pq[++N] = v;
        swim(N);
    }
    public int delMax(){
        int max = pq[1];
        exch(1,N--);
        pq[N+1] = 0;
        if((N+1)  == pq.length/4)resize(pq.length/4);
        sink(1);
        return max;
    }
    public int Max(){
        return pq[1];
    }
    private boolean less(int i , int j ){
        return (pq[i] - pq[j]) <0;
    }
    private void exch(int i , int j ){
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
    public void swim(int k ){
        while(k>1 && less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }
    public void sink(int k ){
        while(2*k <=N){
            int j = 2*k;
            if (j<N && less(j,j+1))j++;
            if(!(less(k,j)))break;
            exch(k,j);
            k = j;
        }
    }

    public static void main(String[] args) {

    }
}
