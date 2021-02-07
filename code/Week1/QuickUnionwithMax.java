package Week1;

public class QuickUnionwithMax {
    private int[] parents;
    private int count;
    private int[] size;
    private int[] max;
    public QuickUnionwithMax(int num){
        parents = new int[num];
        size = new int[num];
        count = num;
        for (int i = 0; i < num; i++) {
            parents[i] = i;
            size[i] = 1;
            max[i] = i;
        }
    }
    public int find(int p ){
        while(parents[p] != p){
            parents[p] = parents[parents[p]];   //路径压缩!!!!
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
                if(max[pid] > max[qid])max[qid] = max[pid];
            }else{
                parents[q] = pid;
                size[pid] += size[qid];
                if(max[qid] > max[pid])max[pid] = max[qid];
            }
            count--;
        }
    }
    public int findmax(int p ){
        return max[p];
    }
}
