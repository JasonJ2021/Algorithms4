package Week1;

public class QuickUnionUF {
    private int[] id;
    public QuickUnionUF(int num){
        id = new int[num];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }
    public int findroot(int i){
        while(id[i] != i){
            i = id[i];
        }
        return i;
    }
    public boolean isConnected(int p , int q){
      return findroot(p) == findroot(q);
    }
    public void union(int p , int q ){
        int proot = findroot(p);
        int qroot = findroot(q);
        id[proot] = qroot;
    }

}
