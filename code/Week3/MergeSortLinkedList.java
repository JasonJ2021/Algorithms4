package Week3;

import java.util.Iterator;

public class MergeSortLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private class Node {
        T element;
        Node next;
    }

    private Node first = null;
    private Node last = null;
    private int n = 0;

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public class ListIterator implements Iterator<T> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T element = current.element;
            current = current.next;
            return element;
        }
    }

    public void add(T t) {
        Node node = new Node();
        node.element = t;
        node.next = null;
        if (first == null && last == null) {
            first = node;
            last = node;
        } else if (first == last && first != null) {
            first.next = node;
            last = node;

        } else {
            last.next = node;
            last = node;

        }
        n++;
    }

    @Override
    public String toString() {
        Iterator<T> iterator = iterator();
        String result = iterator.next().toString();
        while (iterator.hasNext()) {
            result += "," + iterator.next().toString();
        }
        return result;
    }
    public void mergeSort(){
        first = sort(first);
    }
    private Node sort(Node head) {
        if(head == null || head.next == null)return head;
        Node slow = head;
        Node fast = head;
        while(fast.next !=null && fast.next.next !=null){
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
    private Node merge(Node left , Node right){
        Node aux = new Node();
        Node l = left;
        Node r = right;
        Node cur = aux;
        while(l!=null && r!=null){
            if(less(l.element,r.element)){
                cur.next = l;
                cur = cur.next;
                l = l.next;
            }else{
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if(l!=null){
            cur.next = l;
        }else if(r!=null){
            cur.next = r;
        }
        return aux.next;
    }

    public static void main(String[] args) {
        MergeSortLinkedList<Integer> mergeSortLinkedList = new MergeSortLinkedList<>();
        mergeSortLinkedList.add(1);
        mergeSortLinkedList.add(2);
        mergeSortLinkedList.add(4);
        mergeSortLinkedList.add(3);
        mergeSortLinkedList.add(7);
        mergeSortLinkedList.add(6);
        mergeSortLinkedList.add(5);
        mergeSortLinkedList.add(8);
        System.out.println(mergeSortLinkedList);
        mergeSortLinkedList.mergeSort();
        System.out.println(mergeSortLinkedList);


    }
}
