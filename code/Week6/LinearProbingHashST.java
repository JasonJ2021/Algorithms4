package Week6;

public class LinearProbingHashST<Key,Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] vals;
    public LinearProbingHashST(){
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    private void resize(int capacity){
        Key[] tempkeys = (Key[]) new Object[capacity];
        Value[] tempvals = (Value[]) new Object[capacity];
        for (int i = 0; i < M; i++) {
            tempkeys[i] = keys[i];
            tempvals[i] = vals[i];
        }
        keys = tempkeys;
        vals = tempvals;
        M = capacity;
    }
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) %M;
    }
    public void put(Key key,Value val){
        if (N >=M/2)resize(2*M);
        int i ;
        for (i = hash(key);keys[i] !=null;i = (i+1)%M){
            if (keys[i].equals(key)){
                vals[i] = val; // 若有重复的key覆盖
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    public Value get(Key key){
        for (int i = hash(key) ; keys[i] !=null; i = (i+1)%M){
            if (keys[i].equals(key)){
                return vals[i];
            }
        }
        return null;
    }
}
