package com.yogeesh.datastructures.trees;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

/**
 * Created by yogeesh.rajendra on 7/29/17.
 */
public class BinarySearchTree {

    Node root = null;

    /**
     * Method to add data to BST - Non recursive code / Iterative
     * @param info
     * @return
     */
    public Node add(int info) {

        // Create new node to add
        Node newNode = new Node();
        newNode.setData(new Data(info));

        //Already tree exists
        Node temp = root;
        Node tempTrailing = null;

        //Actual adding logic
        while (true) {

            // Very first node addition
            if (null==temp && null==tempTrailing) {
                System.out.println(" Added : " + newNode.getData().getInfo());
                root = newNode;
                return root;
            }

            //If temp is null then we should add newNode to tempTrailing node
            if (null==temp && info<tempTrailing.getData().getInfo()) {
                tempTrailing.setPreviousPointer(newNode);
                System.out.println(" Added : " + newNode.getData().getInfo());
                return root;
            } else if (null==temp && info>=tempTrailing.getData().getInfo()) {
                tempTrailing.setNextPointer(newNode);
                System.out.println(" Added : " + newNode.getData().getInfo());
                return root;
            }

            //Keep trailing pointer
            tempTrailing = temp;

            //Based on BST condition move ahead the pointer
            temp = (temp.getData().getInfo() > info)? temp.getPreviousPointer(): temp.getNextPointer();

        }

    }

    /**
     * Method to delete data from BST
     * @param info
     * @return
     */
    public Node delete(int info) {

        // Create new node to add
        Node newNode = new Node();
        newNode.setData(new Data(info));

        //Already tree exists
        Node temp = root;
        Node tempTrailing = null;

        //Actual adding logic
        while (true) {

            // Very first node addition
            if (null==temp && null==tempTrailing) {
                System.out.println(" Delete : There is no tree to delete anything ");
                root = newNode;
                return root;
            }

            if (null==temp) {
                System.out.println(" Delete : There is no node specified info to delete in tree ");
                return root;
            }

            // We got node to delete
            if (temp.getData().getInfo() == info) {

                // Case 1 : Where node to be removed has no siblings at all
                if (null==temp.getNextPointer() && null==temp.getPreviousPointer()) {

                    if (tempTrailing.getPreviousPointer()==temp) {
                        tempTrailing.setPreviousPointer(null);
                        return root;
                    } else if (tempTrailing.getNextPointer()==temp) {
                        tempTrailing.setNextPointer(null);
                        return root;
                    } else if (null==tempTrailing) {
                        //Case of only root node and that is info and tree has only root node
                        this.root = null;
                        return root;
                    }

                }

                // Case 2 : Where node to be removed has 1 sibling to previous / left
                if (null==temp.getNextPointer()) {

                    // Case where element to be removed is root and right subtree is not at all there
                    if (null==tempTrailing) {
                        this.root=temp.getPreviousPointer();
                        return root;
                    }

                    //Case where element to be removed is not root node
                    if (temp==tempTrailing.getPreviousPointer()) {
                        tempTrailing.setPreviousPointer(temp.getPreviousPointer());
                    } else if (temp==tempTrailing.getNextPointer()) {
                        tempTrailing.setNextPointer(temp.getPreviousPointer());
                    }

                    return root;

                }

                // Case 3 : Where node to be removed has 1 sibling to next / right
                if (null==temp.getPreviousPointer()) {

                    // Case where element to be removed is root and left subtree is not at all there
                    if (null==tempTrailing) {
                        this.root=temp.getNextPointer();
                        return root;
                    }

                    //Case where element to be removed is not root node
                    if (temp==tempTrailing.getPreviousPointer()) {
                        tempTrailing.setPreviousPointer(temp.getNextPointer());
                    } else if (temp==tempTrailing.getNextPointer()) {
                        tempTrailing.setNextPointer(temp.getNextPointer());
                    }

                    return root;

                }

                // Case 4 : Where temp or node to be removed has both left and right siblings
                if (null!=temp.getNextPointer() && null!=temp.getPreviousPointer()) {

                    // Case : Where root node is the element to be removed
                    if (null==tempTrailing) {
                        temp.getNextPointer().setPreviousPointer(temp.getPreviousPointer());
                        this.root=temp.getNextPointer();
                        return root;
                    }

                    // Case : Where node is somewhere in between

                    //Set next's previous pointer as (node to be removed)'s previous pointer
                    temp.getNextPointer().setPreviousPointer(temp.getPreviousPointer());

                    if (tempTrailing.getNextPointer()==temp) {
                        tempTrailing.setNextPointer(temp.getNextPointer());
                    } else if (tempTrailing.getPreviousPointer()==temp) {
                        tempTrailing.setPreviousPointer(temp.getNextPointer());
                    }

                    return root;
                }

            }

            //Keep trailing pointer
            tempTrailing = temp;

            //Based on BST condition move ahead the pointer
            temp = (temp.getData().getInfo() > info)? temp.getPreviousPointer(): temp.getNextPointer();

        }

    }

    /**
     * Method to insert data recursively
     * @param node
     * @param info
     * @return
     */
    public Node insert(Node node, int info){

        if (null==node) {
            node = new Node(new Data(info));
            System.out.println(" Added : " + node.getData().getInfo());
            return (node);
        }

        if (info<node.getData().getInfo()) {
            node.previousPointer = insert(node.getPreviousPointer(), info);
        } else {
            node.nextPointer = insert(node.getNextPointer(), info);
        }

        return node;

    }

    /**
     * Method to print tree Pre Order
     */
    public void displayPreOrderTree() {
        showPreOrder(this.root);
    }

    /**
     * Method to show tree In Order
     * @param node
     * @return
     */
    private Node showPreOrder(Node node) {

        if (null==node) {
            return node;
        }

        System.out.println("| [ "+node.getData().getInfo()+" ] | ");
        showPreOrder(node.getPreviousPointer());
        showPreOrder(node.getNextPointer());

        return null;
    }


    /**
     * Method to print tree Post Order
     */
    public void displayPostOrderTree() {
        showPostOrder(this.root);
    }

    /**
     * Method to show tree Post Order
     * @param node
     * @return
     */
    private Node showPostOrder(Node node) {

        if (null==node) {
            return node;
        }

        showPostOrder(node.getPreviousPointer());
        showPostOrder(node.getNextPointer());
        System.out.println("| [ "+node.getData().getInfo()+" ] | ");

        return null;
    }

    /**
     * Method to print tree In Order
     */
    public void displayInOrderTree() {
        showInOrder(this.root);
    }

    /**
     * Method to show tree In Order
     * @param node
     * @return
     */
    private Node showInOrder(Node node) {

        if (null==node) {
            return node;
        }

        showInOrder(node.getPreviousPointer());
        System.out.println("| [ "+ node.getData().getInfo() +" ] | ");
        showInOrder(node.getNextPointer());

        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();


        System.out.println(" Inserting element . . .");
        bst.root = bst.insert(bst.root, 6);
        bst.root = bst.insert(bst.root, 1);
        bst.root = bst.insert(bst.root, 5);
        bst.add(2);
        bst.root = bst.insert(bst.root, 3);
        bst.add(4);
        bst.add(7);

        System.out.println(" In Order Traversal ");
        bst.displayInOrderTree();

        System.out.println(" Post Order Traversal ");
        bst.displayPostOrderTree();

        System.out.println(" Pre Order Traversal ");
        bst.displayPreOrderTree();

        bst.delete(2);

        System.out.println(" Pre Order Traversal After removing [2] root element ");
        bst.displayInOrderTree();

    }
}
