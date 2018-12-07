package com.yogeesh.datastructures.trees;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

/**
 * @author : yogeesh.srkvs@gmail.com
 * Date : 6 Dec 2018
 */

// Unival Tree : Single valued tree

// Question : https://www.geeksforgeeks.org/find-count-of-singly-subtrees/

class Result {
    int count;
    boolean isUnival;

    Result(int count, boolean isUnival) {
        this.count=count;
        this.isUnival=isUnival;
    }

    public void setCount(int i) {
        this.count=i;
    }

    public void setUnival(boolean b) {
        this.isUnival=b;
    }
}

public class GetCountOfUnivalTrees {

    public static Result countOfUnivalTree(Node node) {

        if (node==null) {
            Result result = new Result(0, true);
            return result;
        }

        if (node.getPreviousPointer()==null && node.getNextPointer()==null) {
            Result result = new Result(1, true);
            return result;
        }

        Result resultLeft = countOfUnivalTree(node.getPreviousPointer());
        Result resultRight = countOfUnivalTree(node.getNextPointer());

        Result resultantResult = new Result(resultLeft.count+resultRight.count, false);

        if (resultLeft.isUnival && resultRight.isUnival) {
            boolean leftMatches = (node.getPreviousPointer()!=null && node.getData().getInfo() == node.getPreviousPointer().getData().getInfo());
            boolean rightMatches = (node.getNextPointer()!=null && node.getData().getInfo() == node.getNextPointer().getData().getInfo());

            if (leftMatches && rightMatches) {
                resultantResult.setCount(resultLeft.count+resultRight.count+1);
                resultantResult.setUnival(true);
            }

        }



        return resultantResult;

    }

    public static void main(String[] args) {
        Node node = new Node(new Data(11));
        node.setPreviousPointer(new Node(new Data(11)));
        node.setNextPointer(new Node(new Data(11)));

        System.out.println(GetCountOfUnivalTrees.countOfUnivalTree(node).count);
    }

}
