package Week3.MergeSortInterviewQuestion;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class ShufflingALinkedList<T extends Comparable> implements Iterable<T> {
    private class Node {
        Node next;
        T element;
    }
    Node first = null;
    Node last = null;
    int n = 0;
    private boolean less(Comparable a , Comparable b){
        return a.compareTo(b) < 0;
    }
    private void add(T element){
        Node node = new Node();
        node.element = element;
        node.next = null;
        if(first == null && last == null){
            first = node;
            last = node;
        }else if( first !=null && first == last){
            first.next = node;
            last = node;
        }else{
            last.next = node;
            last = node;
        }
        n++;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }
    public class ListIterator implements Iterator<T>{
        Node cur = first;
        @Override
        public boolean hasNext() {
            return cur !=null;
        }

        @Override
        public T next() {
            T element = cur.element;
            cur = cur.next;
            return element;
        }
    }
    public Node sort(Node head){
        if(head == null || head.next == null)return head;
        Node slow = head;
        Node fast = head;
        while(fast.next!=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node left = head;
        Node right = slow.next;
        slow.next = null;
        left = sort(left);
        right = sort(right);
        return merge(left,right);
    }
    public Node merge(Node left , Node right){
        Node aux = new Node();
        Node l = left;
        Node r = right;
        Node cur = aux;
        while(l != null && r !=null){
            int uniform = StdRandom.uniform(2);
            if(uniform == 0){
                cur.next = l;
                cur = cur.next;
                l = l.next;
            }else{
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if(l !=null){
            cur.next = l;
        }else if(r !=null){
            cur.next = r;
        }
        return aux.next;
    }
    public void randomMergeSort(){
        first = sort(first);
    }

    @Override
    public String toString() {
        Iterator<T> iterator = iterator();
        String result = iterator.next().toString();
        while(iterator.hasNext()){
            result = result + ',' + iterator.next().toString();
        }
        return result;
    }

    public static void main(String[] args) {
        ShufflingALinkedList<Integer> shufflingALinkedList = new ShufflingALinkedList();
        shufflingALinkedList.add(1);
        shufflingALinkedList.add(5);
        shufflingALinkedList.add(3);
        shufflingALinkedList.add(7);
        shufflingALinkedList.add(4);
        shufflingALinkedList.add(9);
        shufflingALinkedList.add(13);
        System.out.println(shufflingALinkedList);
        shufflingALinkedList.randomMergeSort();
        System.out.println(shufflingALinkedList);
    }
}
