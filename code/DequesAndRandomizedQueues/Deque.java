package DequesAndRandomizedQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private int size;
    private Node last;
    // construct an empty deque
    public Deque(){
        size = 0;
        first = null;
        last = null;
    }
    private class Node{
        private Node prenode;
        private Node nextnode;
        private Item item;
    }
    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        validate(item);
        Node node = new Node();
        node.item = item;
        if(size == 0){
            node.prenode = null;
            node.nextnode = null;
            first = node;
            last = node;
        }else{
            node.prenode = null;
            node.nextnode = first;
            first.prenode = node;
            first = node;
        }
        size ++;
    }

    // add the item to the back
    public void addLast(Item item){
        validate(item);
        Node node = new Node();
        node.item = item;
        if(size == 0){
            node.prenode = null;
            node.nextnode = null;
            first = node;
            last = node;
        }else{
            node.prenode = last;
            node.nextnode = null;
            last.nextnode = node;
            last = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(size == 0){
            throw new NoSuchElementException("Deque is empty");
        }
        Node result = null;
        if(size == 1){
            result = first;
            first = null;
            last = null;
        }else{
            Node oldnode = first;
            result = oldnode;
            first = oldnode.nextnode;
            first.prenode = null;
            oldnode.nextnode = null;
            oldnode.item =null; //垃圾回收
        }
        size--;
        return result.item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size == 0)throw new NoSuchElementException("Deque is Empty");
        Node result = null;
        if(size == 1 ){
            result = last ;
            last = null;
            first = null;
        }else{
            Node oldnode = last;
            result = oldnode;
            last = oldnode.prenode;
            last.nextnode = null;
            oldnode.prenode = null;
            oldnode.item = null;
        }
        size--;
        return result.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator{
        Node cur = first;
        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (cur == null)throw new NoSuchElementException(" there are no more items to return");
            Item item = cur.item;
            cur = cur.nextnode;
            return item;
        }
    }
    // unit testing (required)
    public static void main(String[] args){
        Deque deque = new Deque();
        deque.addFirst(1);
        deque.addFirst(3);
        deque.addLast(5);
        deque.addLast(6);
        System.out.println("deque's size is " + deque.size);
        Iterator iterator = deque.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next());
        }
    }

    public void validate(Item item){
        if(item == null){
            throw new IllegalArgumentException("this item is null");
        }
    }   //judge whether item is valid;

}