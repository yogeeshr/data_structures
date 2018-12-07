package com.yogeesh.datastructures.trees;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

import java.util.Objects;

/**
 * @author : yogeesh.srkvs@gmail.com
 * Date : 6 Dec 2018
 * Question : https://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 */

class MaxSubTreeBST {
    int noOfNodes = 0;
    Node root = null;
    int min=Integer.MIN_VALUE, max=Integer.MIN_VALUE;

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public void setNoOfNodes(int noOfNodes) {
        this.noOfNodes = noOfNodes;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}

public class GetMaxBSTinBinaryTree {

    /**
     * Create data or tree
     * @return
     */
    public static Node createData() {
        Node root = TreeUtil.addLevelOrder(null, new Data(11));
        TreeUtil.addLevelOrder(null, new Data(11));
        TreeUtil.addLevelOrder(root, new Data(9));
        TreeUtil.addLevelOrder(root, new Data(5));
        TreeUtil.addLevelOrder(root, new Data(7));
        TreeUtil.addLevelOrder(root, new Data(10));
        TreeUtil.addLevelOrder(root, new Data(4));
        TreeUtil.addLevelOrder(root, new Data(3));
        TreeUtil.addLevelOrder(root, new Data(6));
        TreeUtil.addLevelOrder(root, new Data(8));
        TreeUtil.addLevelOrder(root, new Data(11));
        TreeUtil.addLevelOrder(root, new Data(12));
        TreeUtil.addLevelOrder(root, new Data(2));
        TreeUtil.addLevelOrder(root, new Data(1));
        return root;
    }

    // get min of 3 numbers
    public static int min(int x, int y, int z) {
        int temp = (x>y)? y: x;
        temp = (temp>z)? z: temp;
        return temp;
    }

    // get max of 3 numbers
    public static int max(int x, int y, int z) {
        int temp = (x>y)? x: y;
        temp = (temp>z)? temp: z;
        return temp;
    }

    // Get max bst : main magical function
    public static MaxSubTreeBST getMaxBst(Node node) {

        MaxSubTreeBST maxSubTreeBST = new MaxSubTreeBST();

        if (Objects.isNull(node)) {
            return null;
        }

        MaxSubTreeBST maxSubTreeBSTLeft = GetMaxBSTinBinaryTree.getMaxBst(node.getPreviousPointer());
        MaxSubTreeBST maxSubTreeBSTRight = GetMaxBSTinBinaryTree.getMaxBst(node.getNextPointer());

        maxSubTreeBST.setRoot(node);

        // Leaf node condition
        if (maxSubTreeBSTLeft==null && maxSubTreeBSTRight==null) {
            maxSubTreeBST.setNoOfNodes(1);
            maxSubTreeBST.setMin(node.getData().getInfo());
            maxSubTreeBST.setMax(node.getData().getInfo());
            return maxSubTreeBST;
        }

        // condition : if root returned from left and right are my child
        if (maxSubTreeBSTLeft!=null && maxSubTreeBSTRight!=null && maxSubTreeBSTLeft.getRoot().equals(node.getPreviousPointer())
                && maxSubTreeBSTRight.getRoot().equals(node.getNextPointer())) {

            // If max and min from left and right matches BST property
            if (node.getData().getInfo() > maxSubTreeBSTLeft.getMax() &&
                    node.getData().getInfo() < maxSubTreeBSTRight.getMin()) {

                maxSubTreeBST.setNoOfNodes(maxSubTreeBSTLeft.getNoOfNodes()+maxSubTreeBSTRight.getNoOfNodes()+1);
                maxSubTreeBST.setMin(min(maxSubTreeBSTLeft.getMin(), maxSubTreeBSTRight.getMin(), node.getData().getInfo()));
                maxSubTreeBST.setMax(max(maxSubTreeBSTLeft.getMin(), maxSubTreeBSTRight.getMin(), node.getData().getInfo()));
                return maxSubTreeBST;
            } else if (maxSubTreeBSTLeft.getNoOfNodes() > maxSubTreeBSTRight.getNoOfNodes()) {
                return maxSubTreeBSTLeft;
            } else {
                return maxSubTreeBSTRight;
            }

        }

        // Left node is null case
        if (maxSubTreeBSTLeft==null && maxSubTreeBSTRight.equals(node.getNextPointer()) &&
                node.getData().getInfo() < maxSubTreeBSTRight.getMin()) {

            maxSubTreeBST.setNoOfNodes(maxSubTreeBSTLeft.getNoOfNodes()+1);
            maxSubTreeBST.setMax(max(node.getData().getInfo(), maxSubTreeBSTRight.getMax(), Integer.MIN_VALUE));
            maxSubTreeBST.setMin(node.getData().getInfo());
            return maxSubTreeBST;

        }

        // Right node is null case
        if (maxSubTreeBSTRight==null && maxSubTreeBSTLeft.equals(node.getPreviousPointer())
                && node.getData().getInfo() > maxSubTreeBSTLeft.getMax()) {

            maxSubTreeBST.setNoOfNodes(maxSubTreeBSTLeft.getNoOfNodes()+1);
            maxSubTreeBST.setMax(node.getData().getInfo());
            maxSubTreeBST.setMin(min(node.getData().getInfo(), maxSubTreeBSTLeft.min, Integer.MAX_VALUE));
            return maxSubTreeBST;

        }

        if (maxSubTreeBSTLeft==null) {
            return maxSubTreeBSTRight;
        } else {
            return maxSubTreeBSTLeft;
        }

    }

    public static void main(String[] args) {
        Node node = GetMaxBSTinBinaryTree.createData();
        System.out.println(GetMaxBSTinBinaryTree.getMaxBst(node).getRoot().getData().getInfo());
    }
}
