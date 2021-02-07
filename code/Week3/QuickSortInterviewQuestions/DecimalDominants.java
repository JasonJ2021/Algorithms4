package Week3.QuickSortInterviewQuestions;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;

public class DecimalDominants {
    //Given an array with nn keys, design an algorithm to find all values that occur more than n/10times.
    // The expected running time of your algorithm should be linear.
    private class element{
        private int item;
        private int count;
        public element(int item , int count){
            this.item = item;
            this.count = count;
        }
    }
    element[] aux = new element[9];
    public int findIndex(int target){
        for (int i = 0; i < aux.length; i++) {
            if(aux[i].item == target){
                return i;
            }else if(aux[i].count == 0){
                aux[i].item = target;
                return i;
            }
        }
        return -1;
    }
    public void addItem(int item){
        boolean flag = false;
        while(!flag){
            for (int i = 0; i < aux.length; i++) {
                aux[i].count--;
                if(aux[i].count <=0){
                    flag = true;
                    aux[i].item = item;
                    aux[i].count = 1;
                    break;
                }
            }
        }
    }
    public ArrayList<Integer> findDecimalDominants(int[] array){

        for (int i = 0; i < aux.length; i++) {
            aux[i] = new element(0,0);
        }
        for (int i = 0; i < array.length; i++) {
            int index = findIndex(array[i]);
            if(index>=0){
                aux[index].count +=1;
            }else{
                addItem(array[i]);
            }
        }
        return varify(array);
    }
    public ArrayList<Integer> varify(int[] array){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < aux.length; i++) {
            aux[i].count =0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == aux[i].item){
                    aux[i].count ++;
                }
            }
            if (aux[i].count >array.length/10){
                result.add(aux[i].item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,1,2,3,4,5,1,2,3,4};
        DecimalDominants decimalDominants = new DecimalDominants();
        ArrayList<Integer> decimalDominants1 = decimalDominants.findDecimalDominants(array);
        System.out.println(decimalDominants1.toString());
    }
}
