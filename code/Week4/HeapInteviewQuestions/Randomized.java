package Week4.HeapInteviewQuestions;

import Week4.MaxHeap;
import edu.princeton.cs.algs4.StdRandom;

import java.security.PublicKey;

public class Randomized {
    MaxHeap maxHeap;
    public Randomized(int[] pq){
        maxHeap = new MaxHeap();
        for (int i = 0; i < pq.length; i++) {
            maxHeap.insert(pq[i]);
        }
    }
    public int sample(){
        int random = 1+ StdRandom.uniform(maxHeap.size());
        return maxHeap.pq[random];
    }
    public int delRandom(){
        int random = 1 + StdRandom.uniform(maxHeap.size());
        int temp = maxHeap.pq[random];
        maxHeap.pq[random] = maxHeap.pq[maxHeap.N];
        maxHeap.N--;
        maxHeap.sink(random);
        return temp;
    }
}
