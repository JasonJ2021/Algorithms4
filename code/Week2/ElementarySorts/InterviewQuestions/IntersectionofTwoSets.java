package Week2.ElementarySorts.InterviewQuestions;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class IntersectionofTwoSets {
    //可以用hashSet easy accomplish
    private Set<Point> s = new HashSet<Point>();
    private int Samesizes;
    public IntersectionofTwoSets(int n , Point[] a , Point[] b){
        for(int i = 0 ; i < n ;i++){
            s.add(a[i]);
            s.add(b[i]);
        }
        Samesizes = 2*n - s.size();
    }
    public int getSamesizes(){
        return Samesizes;
    }

}
