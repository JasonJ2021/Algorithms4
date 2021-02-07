package Week2.StacksAndQueues;

public class LinkedStackOfStrings {
    private class Node{
        String item;
        Node Next;
    }
    private Node first;
    public boolean isEmpty(){
        return first == null;
    }
    public void push(String s){
        Node oldNode = first;
        first = new Node();
        first.item = s;
        first.Next = oldNode;
    }
    public String pop(){
        String temp = first.item;
        first = first.Next;
        return temp;
    }
}
