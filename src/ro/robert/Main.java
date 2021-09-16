package ro.robert;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Node x = new Node(5);
        Stack<Node> stack = new Stack<>();
        stack.push(x);
        x.setKey(10);
        System.out.println(stack.peek());

    }
}
