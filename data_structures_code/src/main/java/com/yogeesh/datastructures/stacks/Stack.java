package com.yogeesh.datastructures.stacks;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

import java.util.ArrayList;

/**
 * Created by yogeesh.srkvs@gmail.com on 8/15/17.
 */
public class Stack {
    int top=-1;
    ArrayList<Node> stack = new ArrayList<>();

    /**
     * Push
     * @param ele
     */
    public void push(int ele) {
        stack.add(new Node(new Data(ele)));
        ++top;

        System.out.println("Pushed element : "+ele);
        System.out.println("Size of stack : "+(top+1));
    }

    /**
     * Pop
     */
    public Node pop() {
        Node poppedEle = stack.get(top);
        --top;
        System.out.println("Popped element : "+poppedEle.getData().getInfo());
        System.out.println("Size of stack : "+(top+1));
        return poppedEle;
    }

    /**
     * Peek and see the top of stack
     */
    public void peek() {
        System.out.println("Top of stack is : "+stack.get(top).getData().getInfo());
        System.out.println("Size of stack is : "+ (top+1));
    }

    /**
     * Display elements of stack top down
     * This violates rule of stack : this is just used to have an understanding and see how stack looks
     */
    public void displayStack() {
        System.out.print("Stack elements are : ");
        // Elements print
        for(int i=stack.size()-1; i>=0; i--) {
            System.out.print(stack.get(i).getData().getInfo()+"\t");
        }
        System.out.println("\nSize of stack is : "+ (top+1));
    }

    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push(0);
        stack.push(0);
        stack.push(0);
        stack.push(0);
        stack.push(1);

        System.out.println("------");
        stack.displayStack();

        System.out.println("\n------");
        stack.peek();

    }

}
