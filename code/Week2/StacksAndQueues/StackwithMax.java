package Week2.StacksAndQueues;

import java.util.Stack;

public class StackwithMax {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> temp = new Stack<>();
    Integer max;
    public void push(Integer item){
        stack.push(item);
    }
    public Integer pop(){
        return stack.pop();
    }
    public Integer findMax(){
        while(!stack.isEmpty()){
            Integer tep = stack.pop();
            if(max == null){
                max = tep;
            }else{
                max = Math.max(max,tep);
            }
            temp.push(tep);
        }
        while(!temp.isEmpty()){
            stack.push(temp.pop());
        }
        return max;
    }

    public static void main(String[] args) {
        StackwithMax stack = new StackwithMax();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(9);
        stack.push(13);
        System.out.println(stack.findMax());

    }
}
