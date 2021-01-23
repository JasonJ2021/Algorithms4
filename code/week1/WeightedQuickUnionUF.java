package week1;

public class WeightedQuickUnionUF {
    private int[] parents;
    private int count;
    private int[] size;
    public WeightedQuickUnionUF(int num){
        parents = new int[num];
        size = new int[num];
        count = num;
        for (int i = 0; i < num; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }
    public int find(int p ){
        while(parents[p] != p){
            p = parents[p];
        }
        return p;
    }
    public boolean isConnected(int p , int q ){
        return (parents[p] == parents[q]);
    }
    public void union(int p , int q ){
        if(isConnected(p,q)){
            return;
        }else{
            int pid = find(p);
            int qid = find(q);
            if(size[p] <= size[q]){
                parents[p] = qid;
                size[qid] += size[pid];
            }else{
                parents[q] = pid;
                size[pid] += size[qid];
            }
            count--;
        }
    }
}
