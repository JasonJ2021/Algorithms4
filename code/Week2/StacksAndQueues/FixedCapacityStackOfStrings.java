package Week2.StacksAndQueues;

public class FixedCapacityStackOfStrings {
    private int N = 0;
    private String[] s;
    public FixedCapacityStackOfStrings(int N){
        s = new String[N];
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public void push(String item){
        s[N++] = item;
    }
    public String pop(){
        /*String temp = s[--N];
        return temp;*/ //loitering case
        String temp = s[--N];
        s[N] = null;
        return temp;
    }
}
