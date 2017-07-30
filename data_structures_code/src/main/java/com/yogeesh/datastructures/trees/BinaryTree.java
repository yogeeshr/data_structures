package com.yogeesh.datastructures.trees;

import com.yogeesh.datastructures.common.Node;

/**
 * Created by yogeesh.srkvs@gmail.com on 7/30/17.
 * For Simplicity preserved logic of binary tree creation same as BST
 */
public class BinaryTree extends BinarySearchTree {

    private boolean leftViewFlag = true;

    /**
     * Method to print tree Post Order
     */
    public void displayMirrorrTree() {
        makeMirror(this.root);
    }

    /**
     * Method to show tree Post Order
     *
     * @param node
     * @return
     */
    private Node makeMirror(Node node) {

        if (null == node) {
            return node;
        }

        makeMirror(node.getPreviousPointer());
        makeMirror(node.getNextPointer());

        Node temp = node.getNextPointer();
        node.setNextPointer(node.getPreviousPointer());
        node.setPreviousPointer(temp);

        return root;
    }

    /**
     * Level Order ZigZag Traversal
     * @param node
     * @param level
     */
    public void traverseLevelZigZag(Node node, int level, Boolean leftToRight){

        if (null==node) {
            return;
        }

        if (level<1) {
            System.out.println("Invalid level number");
        }

        if (1==level) {
            System.out.println("| [ " + node.getData().getInfo() + " ] | ");
            return;
        }

        traverseLevelZigZag((leftToRight) ? node.getPreviousPointer(): node.getNextPointer(), level - 1, leftToRight);
        traverseLevelZigZag((leftToRight) ? node.getNextPointer(): node.getPreviousPointer(), level - 1, leftToRight);

    }

    /**
     * Level view of tree
     * @param node
     * @param level
     */
    public void leftView(Node node, int level){

        if (null==node) {
            return;
        }

        if (level==1 && this.leftViewFlag) {
            System.out.println("| [ " + node.getData().getInfo() + " ] | ");
            this.leftViewFlag =false;
        }

        leftView(node.getPreviousPointer(), level-1);
        leftView(node.getNextPointer(), level-1);

    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        System.out.println(" Inserting element . . .");
        binaryTree.root = binaryTree.insert(binaryTree.root, 16);
        binaryTree.root = binaryTree.insert(binaryTree.root, 11);
        binaryTree.root = binaryTree.insert(binaryTree.root, 15);
        binaryTree.add(12);
        binaryTree.root = binaryTree.insert(binaryTree.root, 13);
        binaryTree.add(14);
        binaryTree.add(17);
        binaryTree.add(18);

        System.out.println(" In Order Traversal ");
        binaryTree.displayInOrderTree();

        System.out.println(" Post Order Traversal ");
        binaryTree.displayPostOrderTree();

        System.out.println(" Pre Order Traversal ");
        binaryTree.displayPreOrderTree();

        binaryTree.delete(12);

        System.out.println(" Mirrored Tree Traversal [ Ideally reverse in order traversal ] - Decreasing order . . .  ");
        //Mirror in place
        binaryTree.makeMirror(binaryTree.root);

        binaryTree.displayInOrderTree();

        System.out.println(" Un-Mirrored Tree Traversal [ Ideally reverse in order traversal ] - Decreasing order . . .  ");
        //Correcting mirror made
        binaryTree.makeMirror(binaryTree.root);

        binaryTree.displayInOrderTree();


        System.out.println(" Spiral Traversal of Tree . . . ");
        int height = binaryTree.heightOfTree(binaryTree.root);

        Boolean leftToRight = false;
        for (int i=1; i<=height; i++) {
            binaryTree.traverseLevelZigZag(binaryTree.root, i, leftToRight);
            leftToRight = !leftToRight;
        }

        System.out.println(" Left View of Tree . . . ");
        for (int i=1; i<=height; i++) {
            binaryTree.leftViewFlag = true;
            binaryTree.leftView(binaryTree.root, i);
        }

    }

}
