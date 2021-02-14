package Week4;

import edu.princeton.cs.algs4.In;

import java.util.Comparator;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.N;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.N = x.left.N + x.right.N;
        return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null)return null;
        int t = size(x.left);
        if (t > k){
            return select(x.left,k);
        }else if (t<k){
            return select(x.right,k-t-1);
        }else {
            return x;
        }
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    public Node deleteMin(Node x){
        if (x.left == null)return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left)+size(x.right) +1;
        return x;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if (x.left == null)return x;
        return min(x.left);
    }
    public void delete(Key key){
        root = delete(root,key);
    }
    private Node delete(Node x , Key key){
        if (x == null)return null;
        int cmp  = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left,key);
        }else if (cmp > 0){
            x.right = delete(x.right , key);
        }else{
            if (x.left == null){
                return x.right;
            }
            if (x.right == null){
                return x.left;
            }
            Node t = x;
            x = min(t.right);
            x.left = t.left;
            x.right = deleteMin(t.right);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("S", 0);
        bst.put("E", 1);
        bst.put("A", 2);
        bst.put("R", 3);
        bst.put("C", 4);
        bst.put("H", 5);
        bst.put("E", 6);
        bst.put("X", 7);
        bst.put("A", 8);
        bst.put("M", 9);
        bst.put("P", 10);
        bst.put("L", 11);
        bst.put("E", 12);

    }

}


