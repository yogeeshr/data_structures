package com.yogeesh.datastructures.trees;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

import java.util.ArrayList;

/**
 * Created by yogeesh.srkvs@gmail.com on 7/29/17.
 */
public class BinarySearchTree {

    public Node root = null;
    Node head;

    // Initialize previously visited node as NULL. This is
    // static so that the same value is accessible in all recursive
    // calls
    static Node prev = null;

    /**
     * Method to add data to BST - Non recursive code / Iterative
     *
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
            if (null == temp && null == tempTrailing) {
                System.out.println(" Added : " + newNode.getData().getInfo());
                root = newNode;
                return root;
            }

            //If temp is null then we should add newNode to tempTrailing node
            if (null == temp && info < tempTrailing.getData().getInfo()) {
                tempTrailing.setPreviousPointer(newNode);
                System.out.println(" Added : " + newNode.getData().getInfo());
                return root;
            } else if (null == temp && info >= tempTrailing.getData().getInfo()) {
                tempTrailing.setNextPointer(newNode);
                System.out.println(" Added : " + newNode.getData().getInfo());
                return root;
            }

            //Keep trailing pointer
            tempTrailing = temp;

            //Based on BST condition move ahead the pointer
            temp = (temp.getData().getInfo() > info) ? temp.getPreviousPointer() : temp.getNextPointer();

        }

    }

    /**
     * Method to delete data from BST
     *
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
            if (null == temp && null == tempTrailing) {
                System.out.println(" Delete : There is no tree to delete anything ");
                root = newNode;
                return root;
            }

            if (null == temp) {
                System.out.println(" Delete : There is no node specified info to delete in tree ");
                return root;
            }

            // We got node to delete
            if (temp.getData().getInfo() == info) {

                // Case 1 : Where node to be removed has no siblings at all
                if (null == temp.getNextPointer() && null == temp.getPreviousPointer()) {

                    if (tempTrailing.getPreviousPointer() == temp) {
                        tempTrailing.setPreviousPointer(null);
                        return root;
                    } else if (tempTrailing.getNextPointer() == temp) {
                        tempTrailing.setNextPointer(null);
                        return root;
                    } else if (null == tempTrailing) {
                        //Case of only root node and that is info and tree has only root node
                        this.root = null;
                        return root;
                    }

                }

                // Case 2 : Where node to be removed has 1 sibling to previous / left
                if (null == temp.getNextPointer()) {

                    // Case where element to be removed is root and right subtree is not at all there
                    if (null == tempTrailing) {
                        this.root = temp.getPreviousPointer();
                        return root;
                    }

                    //Case where element to be removed is not root node
                    if (temp == tempTrailing.getPreviousPointer()) {
                        tempTrailing.setPreviousPointer(temp.getPreviousPointer());
                    } else if (temp == tempTrailing.getNextPointer()) {
                        tempTrailing.setNextPointer(temp.getPreviousPointer());
                    }

                    return root;

                }

                // Case 3 : Where node to be removed has 1 sibling to next / right
                if (null == temp.getPreviousPointer()) {

                    // Case where element to be removed is root and left subtree is not at all there
                    if (null == tempTrailing) {
                        this.root = temp.getNextPointer();
                        return root;
                    }

                    //Case where element to be removed is not root node
                    if (temp == tempTrailing.getPreviousPointer()) {
                        tempTrailing.setPreviousPointer(temp.getNextPointer());
                    } else if (temp == tempTrailing.getNextPointer()) {
                        tempTrailing.setNextPointer(temp.getNextPointer());
                    }

                    return root;

                }

                // Case 4 : Where temp or node to be removed has both left and right siblings
                if (null != temp.getNextPointer() && null != temp.getPreviousPointer()) {

                    // Case : Where root node is the element to be removed
                    if (null == tempTrailing) {
                        temp.getNextPointer().setPreviousPointer(temp.getPreviousPointer());
                        this.root = temp.getNextPointer();
                        return root;
                    }

                    // Case : Where node is somewhere in between

                    //Set next's previous pointer as (node to be removed)'s previous pointer
                    temp.getNextPointer().setPreviousPointer(temp.getPreviousPointer());

                    if (tempTrailing.getNextPointer() == temp) {
                        tempTrailing.setNextPointer(temp.getNextPointer());
                    } else if (tempTrailing.getPreviousPointer() == temp) {
                        tempTrailing.setPreviousPointer(temp.getNextPointer());
                    }

                    return root;
                }

            }

            //Keep trailing pointer
            tempTrailing = temp;

            //Based on BST condition move ahead the pointer
            temp = (temp.getData().getInfo() > info) ? temp.getPreviousPointer() : temp.getNextPointer();

        }

    }

    /**
     * Method to insert data recursively
     *
     * @param node
     * @param info
     * @return
     */
    public Node insert(Node node, int info) {

        if (null == node) {
            node = new Node(new Data(info));
            System.out.println(" Added : " + node.getData().getInfo());
            return (node);
        }

        if (info < node.getData().getInfo()) {
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
     *
     * @param node
     * @return
     */
    private Node showPreOrder(Node node) {

        if (null == node) {
            return node;
        }

        System.out.println("| [ " + node.getData().getInfo() + " ] | Next : "+
                ((node.getNextPointer()!=null)? node.getNextPointer().getData().getInfo(): "null") +"| Prev : "
                + ((node.getPreviousPointer()!=null)? node.getPreviousPointer().getData().getInfo(): "null") );

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
     *
     * @param node
     * @return
     */
    private Node showPostOrder(Node node) {

        if (null == node) {
            return node;
        }

        showPostOrder(node.getPreviousPointer());
        showPostOrder(node.getNextPointer());
        System.out.println("| [ " + node.getData().getInfo() + " ] | ");

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
     *
     * @param node
     * @return
     */
    private Node showInOrder(Node node) {

        if (null == node) {
            return node;
        }

        showInOrder(node.getPreviousPointer());
        System.out.println("| [ " + node.getData().getInfo() + " ] | ");
        showInOrder(node.getNextPointer());

        return null;
    }

    /**
     * Method to find max of tree
     * @param a
     * @param b
     * @return
     */
    private int max(int a, int b) {
        if (a>b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Method to find height of tree
     * @param node
     * @return
     */
    public int heightOfTree(Node node){

        if (null==node) {
            return 0;
        }

        int height = max(heightOfTree(node.getPreviousPointer()), heightOfTree(node.getNextPointer()));

        return height+1;
    }

    /**
     * Level Order Traversal
     * @param node
     * @param level
     */
    public void traverseLevel(Node node, int level){

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

        traverseLevel(node.getPreviousPointer(), level-1);
        traverseLevel(node.getNextPointer(), level-1);
    }

    /**
     * Least common ancestor
     * @param data1
     * @param data2
     */
    public void leastCommonAncestor(int data1, int data2) {
        ArrayList <Node>node1Traversal = new ArrayList();
        ArrayList <Node>node2Traversal = new ArrayList();

        Node node = root;
        while (node!=null) {
            node1Traversal.add(node);

            if (node.getData().getInfo()==data1) {
                break;
            }

            if (node.getData().getInfo()>data1) {
                node = node.getPreviousPointer();
            } else {
                node = node.getNextPointer();
            }
        }

        Node node1 = root;
        while (node1!=null) {
            node2Traversal.add(node1);

            if (node1.getData().getInfo()==data2) {
                break;
            }

            if (node1.getData().getInfo()>data2) {
                node1 = node1.getPreviousPointer();
            } else {
                node1 = node1.getNextPointer();
            }
        }

        if (node1==null || node==null) {
            System.out.println("One of nodes not found");
            return;
        }

        int i=0, flag=0;

        Node common = null;

        while ( (node1Traversal.size()!=0 && node2Traversal.size()!=0) &&
                (i<node1Traversal.size() && i<node2Traversal.size()) &&
                node1Traversal.get(i).getData().getInfo() == node2Traversal.get(i).getData().getInfo()) {
            flag=1;
            common=node1Traversal.get(i);
            ++i;
        }

        if (0==flag) {
            System.out.println("No Common node at all | elements not in tree");
        }

        for (int j=0; j<node1Traversal.size(); j++) {
            System.out.print(node1Traversal.get(j).getData().getInfo()+"\t");
        }

        System.out.println();

        for (int j=0; j<node2Traversal.size(); j++) {
            System.out.print(node2Traversal.get(j).getData().getInfo()+"\t");
        }

        System.out.println("\n\nLeast common ancestor : for ("+data1+","+data2+") : "+common.getData().getInfo());
    }

    /**
     * Method to check isomorpishim of tree
     * Isomorphic : One tree can be got by rotating other trees node left to right swaps
     * @param node1
     * @param node2
     * @return
     */
    public static boolean isIsomorphic(Node node1, Node node2) {

        if (null==node1 && null==node2) {
            return true;
        }

        if (null==node1 || null==node2) {
            return false;
        }

        if (node1.getData().getInfo()!=node2.getData().getInfo()) {
            return false;
        }

        return ( ( (isIsomorphic(node1.getPreviousPointer(), node2.getPreviousPointer())) && (isIsomorphic(node1.getNextPointer(), node2.getNextPointer())) ) ||
                 ( (isIsomorphic(node1.getPreviousPointer(), node2.getNextPointer())) && (isIsomorphic(node1.getNextPointer(), node2.getPreviousPointer())) )
                );

    }

    /**
     *
     * @param node
     * @param data1
     * @param data2
     */
    public Node lowsetCommonAncestorGeneric(Node node, int data1, int data2) {

        if (null==node || node.getData().getInfo()==data1 || node.getData().getInfo()==data2) {
            return node;
        }

        if (null!=node) {
            Node n1 = lowsetCommonAncestorGeneric(node.getPreviousPointer(), data1, data2);
            Node n2 = lowsetCommonAncestorGeneric(node.getNextPointer(), data1, data2);

            if (n1==null && n2!=null) {
                return n2;
            } else if (n1!=null && n2==null) {
                return n1;
            } else if (n1!=null && n2!=null) {
                return node;
            } else if (null==n1 && n2 == null) {
                return null;
            }

//            Optimization
//            if (n1.getData().getInfo()!=data1 || n1.getData().getInfo()!=data2) {
//                System.out.println("LCA is : "+n1.getData().getInfo());
//            } else {
//                Node n2 = lowsetCommonAncestorGeneric(node.getNextPointer(), data1, data2);
//            }

        }

        return null;

    }

    /**
     * Method to convert tree to doubly linked list
     * @param node
     */
    public void convertToDll(Node node) {

        if (null==node) {
            return;
        }

        convertToDll(node.getNextPointer());
        convertToDll(node.getPreviousPointer());

        // -- Crux of the logic start --

        Node right = node.getNextPointer();
        Node left = node.getPreviousPointer();

        while (left!=null && left.getNextPointer()!=null) {
            left=left.getNextPointer();
        }

        while (right!=null && right.getPreviousPointer()!=null) {
            right=right.getPreviousPointer();
        }

        try {
            if (null != left) {
                left.setNextPointer(node);
                node.setPreviousPointer(left);
            }

            if (null != right) {
                right.setPreviousPointer(node);
                node.setNextPointer(right);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return;

        // -- Crux of the logic end --

    }

    /**
     * Entry point
     * @param args
     */
    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();

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

        System.out.println(" Height of the tree is [ "+bst.heightOfTree(bst.root)+" ]");

        // Level Order Traversal
        for (int i=1; i<=bst.heightOfTree(bst.root); i++) {
            System.out.println(" Traversing Level : "+i);
            bst.traverseLevel(bst.root, i);
        }

        bst.add(20);
        bst.add(19);
        bst.add(21);

        System.out.println("\n[ Least common ancestor Quest ]");
        bst.leastCommonAncestor(3, 4);

        Node n = bst.lowsetCommonAncestorGeneric(bst.root, 13,14);

        System.out.println("LCA Generic : "+((null!=n)? n.getData().getInfo(): " No Common ancestor"));

        bst.displayInOrderTree();

        BinarySearchTree bst1 = new BinarySearchTree();

        bst1.root = bst1.insert(bst1.root, 6);
        bst1.root = bst1.insert(bst1.root, 1);
        bst1.root = bst1.insert(bst1.root, 5);
        bst1.add(2);
        bst1.root = bst1.insert(bst1.root, 3);
        bst1.add(4);
        bst1.add(7);
        //bst1.delete(2);
        bst1.add(20);
        bst1.add(19);
        bst1.add(21);

        bst.displayInOrderTree();
        bst1.displayInOrderTree();

        System.out.println("Both trees isomorphism is : "+isIsomorphic(bst.root, bst1.root));

        System.out.println("ConvertedDll : ");

        Node temp = bst1.root;

        while (null!=temp.getPreviousPointer()) {
            temp = temp.getPreviousPointer();
        }

        try {
            bst1.convertToDll(bst1.root);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bst1.root=temp;

        System.out.println(" Doubly linked list : ");

        Node temp1 = bst1.root;

        System.out.println("");
        while (temp1!=null) {
            System.out.print(temp1.getData().getInfo()+"  ");
            temp1=temp1.getNextPointer();
        }
        System.out.println("");

    }

}
