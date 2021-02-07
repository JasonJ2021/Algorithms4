package Week4.HeapInteviewQuestions;

import Week2.ElementarySorts.exp;
import Week4.MaxHeap;
import Week4.MinHeap;
import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;

public class DynamicMedian {
    MaxHeap maxHeap;
    MinHeap minHeap;
    int[] pq;
    public DynamicMedian(int[] pq){
        this.pq = pq;
        maxHeap = new MaxHeap();
        minHeap = new MinHeap();
    }
    public int find(){
        maxHeap.insert(pq[0]);
        for (int i = 1; i < pq.length; i++) {
            int temp = pq[i];
            if (temp < maxHeap.Max()){
                maxHeap.insert(temp);
            }else{
                minHeap.insert(temp);
            }
            if(minHeap.size() - maxHeap.size() >=2){
                int min = minHeap.delMin();
                maxHeap.insert(min);
            }
            if(maxHeap.size() - minHeap.size() >=2){
                int max = maxHeap.delMax();
                minHeap.insert(max);
            }
        }
        if(maxHeap.size()  == minHeap.size()){
            return (maxHeap.Max() + minHeap.Min())/2;
        }
        if (maxHeap.size() - minHeap.size() ==1){
            return maxHeap.Max();
        }
        if(minHeap.size() - maxHeap.size() == 1){
            return minHeap.Min();
        }
        return 0;
    }
    public static void main(String[] args) {
        int[] a = {1,2,5,7,8,9,11,13,1313};
        StdRandom.shuffle(a);
        exp.show(a);
        DynamicMedian dynamicMedian = new DynamicMedian(a);
        int i = dynamicMedian.find();
        System.out.println(i);
    }
}
