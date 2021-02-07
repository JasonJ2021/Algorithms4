package Week2.StacksAndQueues;

import java.util.Stack;

public class QueuewithtwoStacks {
    Stack<String> stack1 = new Stack<>();
    Stack<String> stack2 = new Stack<>();

    public void add(String s) {
        stack1.push(s);
    }

    public String remove() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        } else {
            return stack2.pop();
        }
    }

    public static void main(String[] args) {
        QueuewithtwoStacks queue = new QueuewithtwoStacks();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());


    }
}
