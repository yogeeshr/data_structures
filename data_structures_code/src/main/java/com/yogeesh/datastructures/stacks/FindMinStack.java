package com.yogeesh.datastructures.stacks;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

/**
 * Created by yogeesh.srkvs@gmail.com on 8/15/17.
 */
public class FindMinStack extends Stack {

    Integer minEle = new Integer(-1);

    /**
     * Push
     * @param ele
     */
    public void push(int ele) {

        System.out.println("My Incoming element to push : "+ele);

        int pushEle=0;

        if(stack.size()==0) {
            pushEle = ele;
            minEle = ele;
        } else if ( null==minEle || minEle < ele ) {
            pushEle=ele;
        } else if ( minEle > ele ) {
            pushEle = ( 2*(ele) - minEle );
            minEle=ele;
        }

        stack.add(new Node(new Data(pushEle)));
        ++top;

        System.out.println("My Pushed element : "+pushEle);
        System.out.println("My Size of stack : "+(top+1));
        System.out.println("Minimum element now id : "+minEle);
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

        if (ele >= minEle) {
            originalPoppedEle = ele;
        } else {
            originalPoppedEle = minEle;
            minEle = (2*minEle)-ele;
        }

        System.out.println("My Popped element : "+originalPoppedEle);
        System.out.println("My Size of stack : "+(top+1));
        System.out.println("Minimum element now id : "+minEle);
        System.out.println("- - - - ");
        return poppedEle;
    }

    public static void main(String[] args) {
        Stack stack = new FindMaxStack();

        stack.push(11);
        stack.push(12);
        stack.push(1);

        System.out.println("- - -");
        stack.displayStack();
        System.out.println("- - -");

        stack.pop();
        stack.pop();
        stack.pop();

    }

}
