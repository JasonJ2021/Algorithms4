package Week2.StacksAndQueues;

public class ResizingStackOfStrings {
    private int N = 0;
    private String[] s;
    public ResizingStackOfStrings(int N){
        s = new String[N];
    }
    public void push(String item){
        if(N == s.length)resize(2*N);
        s[N++] = item;
    }

    private void resize(int n) {
        String[] temp = new String[n];
        for(int i = 0; i < N ; i++){
            temp[i] = s[i];
        }
        s = temp;
    }
    public String pop(){
        String temp = s[--N];
        s[N] = null; //
        if(N > 0 && N == s.length/4){
            resize(s.length/4);
        }
        return temp;
    }
}
