package DequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] array;
    // construct an empty randomized queue
    public RandomizedQueue(){
        size = 0;
        array = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void valivate(Item item){
        if(item == null)throw new
                IllegalArgumentException("this item is null");
    }
    public void enqueue(Item item){
        valivate(item);
        if(size == array.length){
            resize(size * 2);
        }
        array[size] = item;
        size++;
    }
    public void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }
    // remove and return a random item
    public Item dequeue(){
        if(size == 0){
            throw new NoSuchElementException("this deque is empty ");
        }
        int location = StdRandom.uniform(size);
        Item item = array[location];
        array[location] = array[size - 1];
        array[size -1 ] = null;
        size--;
        if(size > 0 && size <= array.length/4){
            resize(array.length/4);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        int random = StdRandom.uniform(size);
        return array[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new Arrayiterator();
    }

    public class Arrayiterator implements Iterator<Item>{
        Item[] copyArray = (Item[]) new Object[size];
        int copyN = size;
        public Arrayiterator(){
            for (int i = 0; i < copyArray.length; i++) {
                copyArray[i] = array[i];
            }
        }
        @Override
        public boolean hasNext() {
            return copyN != 0;
        }

        @Override
        public Item next() {
            int uniform = StdRandom.uniform(copyN);
            Item item = copyArray[uniform];
            copyArray[uniform] = copyArray[copyN];
            copyArray[copyN] = null;
            copyN--;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        //client
        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                queue.enqueue(item);
            }else{
                if(!queue.isEmpty()){
                    StdOut.print(queue.dequeue() + "");
                }
            }
        }
        System.out.println("还有" + queue.size + "剩余在队列");
    }

}