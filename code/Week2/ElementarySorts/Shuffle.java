package Week2.ElementarySorts;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {

    private void shuffle(int[] a){
        for (int i = 0; i < a.length; i++) {
            int random = StdRandom.uniform(i + 1);
            exp.exch(a,i,random);
        }
    }

    public static void main(String[] args) {
        Shuffle a = new Shuffle();
        int[] array = {1,2,3,4,5,6,7,8};
        a.shuffle(array);
        exp.show(array);
    }
}
