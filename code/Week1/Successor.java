package Week1;

public class Successor {
    int num;
    int[] id;
    boolean[] isRemoved;
    public Successor(int num){
        this.num = num;
        id = new int[num];
        isRemoved = new boolean[num];
        for(int i = 0 ; i < num ; i++){
            isRemoved[i] = false;
            id[i] = i;
        }
    }
    public int find(int p ){
        while(p != id[p]){
            p = id[p];
        }
        return p;
    }
    public boolean isConnected(int p , int q){
        return find(p) == find(q);
    }
    public void union(int p , int q){
        int proot = find(p);
        int qroot = find(q);
        if(isConnected(p,q)){
            return;
        }else{
            if(proot < qroot){
                id[proot] = qroot;
            }else{
                id[qroot] = proot;
            }
        }
    }
    public void remove(int i ){
        isRemoved[i] = true;
        if(i > 0 && isRemoved[i-1] == true){
            union(i-1,i);
        }
        if(i < num - 1 && isRemoved[i + 1] == true){
            union(i,i+1);
        }
    }
    public int getsuccessor(int x){
        if(x< 0 || x >= num){
            throw new IllegalArgumentException("下标越界");
        }else if(isRemoved[x]){
            if(find(x) + 1 >= num){
                return -1;
            }else{
                return find(x) + 1;
            }
        }else{
            return x;
        }
    }

    public static void main(String[] args) {
        Successor successor = new Successor(9);
        successor.remove(1);
        successor.remove(2);
        System.out.println(successor.getsuccessor(1));
    }
}
