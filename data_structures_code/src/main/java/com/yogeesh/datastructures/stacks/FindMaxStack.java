package com.yogeesh.datastructures.stacks;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

/**
 * @author : yogeesh.srkvs@gmail.com
 */
public class FindMaxStack extends Stack {

    public Integer maxEle = new Integer(-1);

    /**
     * Push
     * @param ele
     */
    public void push(int ele) {

        System.out.println("My Incoming element to push : "+ele);

        int pushEle=0;

        if(stack.size()==0) {
            pushEle = ele;
            maxEle = ele;
        } else if ( null== maxEle || maxEle > ele ) {
            pushEle=ele;
        } else if ( maxEle < ele ) {
            pushEle = ( 2*(ele) + maxEle);
            maxEle = ele;
        }

        stack.add(new Node(new Data(pushEle)));
        ++top;

        System.out.println("My Pushed element : "+pushEle);
        System.out.println("My Size of stack : "+(top+1));
        System.out.println("Maximum element now id : "+ maxEle);
        System.out.println("- - - - ");
    }

    /**
     * Pop
     */
    public Node pop() {

        Node poppedEle = stack.get(top--);
        int originalPoppedEle = -1;
        int ele = poppedEle.getData().getInfo();

        System.out.println("Actual Popped element : "+poppedEle.getData().getInfo());

        if (ele <= maxEle) {
            originalPoppedEle = ele;
        } else {
            originalPoppedEle = maxEle;
            maxEle = (ele-maxEle)/2;
        }

        System.out.println("My Popped element : "+originalPoppedEle);
        System.out.println("My Size of stack : "+(top+1));
        System.out.println("Maximum element now id : "+ maxEle);
        System.out.println("- - - - ");
        return poppedEle;
    }

    public static void main(String[] args) {
        Stack stack = new FindMaxStack();

        stack.push(11);
        stack.push(12);
        stack.push(1);
        stack.push(13);

        System.out.println("- - -");
        stack.displayStack();
        System.out.println("- - -");

        System.out.println("Max ele is :"+((FindMaxStack)stack).maxEle);
        System.out.println("- - -");
        stack.pop();
        stack.pop();
        stack.pop();

    }

}
