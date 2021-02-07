package Week3.QuickSortInterviewQuestions;

import edu.princeton.cs.algs4.StdRandom;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NutsAndBolts {
    //复习继承 map
    Map<Nut,Bolt> pairs = new HashMap<>();
    Nut[] nuts;
    Bolt[] bolts;
    int n;
    public NutsAndBolts(Nut[] nuts , Bolt[] bolts, int n){
        this.nuts = nuts;
        this.bolts = bolts;
        this.n = n;
    }
    private int less(NBParent v , NBParent w){
        int vsize = v.getSize();
        int wsize = w.getSize();
        if(vsize == wsize){return 0;
        }else if (vsize < wsize){
            return -1;
        }else{
            return 1;
        }
    }
    public void exch(NBParent[] nb , int i , int j ){
        NBParent t = nb[i];
        nb[i] = nb[j];
        nb[j] = t;
    }
    public int partition(NBParent[] nb , int lo , int hi ){
        int i = lo ;
        int j = hi + 1;
        NBParent v = nb[lo];
        while(true){
            while(less(nb[++i],v) <0){
                if(i == hi)break;
            }
            while(less(v,nb[--j]) <0){
                if (j == lo)break;
            }
            if (i>=j)break;
            exch(nb,i,j);
        }
        exch(nb,j,lo);
        return j;
    }
    private void sort(NBParent[] nb , int lo , int hi){
        if(hi<=lo)return;
        int partition = partition(nb, lo, hi);
        sort(nb,lo,partition-1);
        sort(nb,partition+1,hi);
    }
    public Bolt findBolt(Nut nut){
        int lo = 0;
        int hi = n-1;
        while(lo <=hi){
            int mid = (lo + hi )/2;
            int compare = less(bolts[mid],nut);
            if(compare <0){
                lo = mid + 1;
            }else if(compare > 0){
                hi = mid -1;
            }else {
                return bolts[mid];
            }
        }
        return null;
    }
    public Map<Nut , Bolt> findPairs(){
        sort(bolts,0,bolts.length);
        for (int i = 0; i < n; i++) {
            Nut nut = nuts[i];
            Bolt bolt = findBolt(nut);
            if(bolt  !=null){
                pairs.put(nut,bolt);
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        Nut[] nuts = new Nut[10];
        Bolt[] bolts = new Bolt[10];
        for (int i = 0; i < 9; i++) {
            Nut nut = new Nut(i+1);
            nuts[i] = nut;
            Bolt bolt = new Bolt(i+2);
            bolts[i] = bolt;
        }
        nuts[9] = new Nut(13);
        bolts[9] = new Bolt(1);
        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);
        NutsAndBolts nb = new NutsAndBolts(nuts,bolts,10);
        Map<Nut,Bolt> pairs = nb.findPairs();
        Set<Map.Entry<Nut, Bolt>> entries = pairs.entrySet();
        Iterator<Map.Entry<Nut, Bolt>> iterator = entries.iterator();
        while(iterator.hasNext()){
            Map.Entry<Nut, Bolt> next = iterator.next();
            Nut key = next.getKey();
            Bolt value = next.getValue();
            System.out.println("<"+key.getSize() + "," + value.getSize()+">");
        }
    }
}
class NBParent{
    public NBParent(int size){
        this.size = size;
    }
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
class Nut extends NBParent{
    public Nut(int size){
        super(size);
    }
}
class Bolt extends NBParent{
    public Bolt(int size){
        super(size);
    }
}