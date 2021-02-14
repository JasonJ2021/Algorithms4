package Week5;

public class RedBlackBST<Key extends Comparable , Value>{
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Key key;
        Value val;
        Node left,right;// 左右子树
        int N;//节点数量
        boolean color;
        public Node(Key key,Value val,int N , boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    private boolean isRed(Node node){
        if (node == null)return false;
        return node.color == RED;
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.N = h.N;
        h.color = RED;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.N = h.N;
        h.color = RED;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private int size(Node x){
        return x.N;
    }
    private void flipColors(Node h ){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = RED;
    }
    public void put(Key key , Value value){
        root = put(root ,key,value);
        root.color = BLACK;
    }
    private Node put(Node h,Key key,Value val){
        if (h == null)return new Node(key,val,1,RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0)h.left = put(h.left,key,val);
        else if (cmp > 0)h.right = put(h.right,key,val);
        else h.val = val;
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))h = rotateRight(h);
        if (isRed(h.left)&&isRed(h.right))flipColors(h);
        h.N = size(h.left) + 1 + size(h.right);
        return h;
    }
}
