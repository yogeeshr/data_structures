package com.yogeesh.datastructures.trees;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author : yogeesh.srkvs@gmail.com
 * Date : 6 Dec 2018
 */

public class TreeUtil {

    /**
     * Method to show tree In Order
     *
     * @param node
     * @return
     */
    public static Node showPreOrder(Node node) {

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
     * Method to show tree Post Order
     *
     * @param node
     * @return
     */
    public static Node showPostOrder(Node node) {

        if (null == node) {
            return node;
        }

        showPostOrder(node.getPreviousPointer());
        showPostOrder(node.getNextPointer());

        System.out.println("| [ " + node.getData().getInfo() + " ] | Next : "+
                ((node.getNextPointer()!=null)? node.getNextPointer().getData().getInfo(): "null") +"| Prev : "
                + ((node.getPreviousPointer()!=null)? node.getPreviousPointer().getData().getInfo(): "null") );

        return null;
    }

    /**
     * Method to show tree In Order
     *
     * @param node
     * @return
     */
    public static Node showInOrder(Node node) {

        if (null == node) {
            return node;
        }

        showInOrder(node.getPreviousPointer());

        System.out.println("| [ " + node.getData().getInfo() + " ] | Next : "+
                ((node.getNextPointer()!=null)? node.getNextPointer().getData().getInfo(): "null") +" | Prev : "
                + ((node.getPreviousPointer()!=null)? node.getPreviousPointer().getData().getInfo(): "null") );

        showInOrder(node.getNextPointer());

        return null;
    }

    /**
     * Method to find height of tree
     * @param node
     * @return
     */
    public static int heightOfTree(Node node){

        if (null==node) {
            return 0;
        }

        int height = max(heightOfTree(node.getPreviousPointer()), heightOfTree(node.getNextPointer()));

        return height+1;
    }

    /**
     * Method to find max of tree
     * @param a
     * @param b
     * @return
     */
    private static int max(int a, int b) {
        if (a>b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Method to add nodes level order
     */
    public static Node addLevelOrder(Node root,Data data) {

        if (Objects.isNull(data)) {
            return root;
        }

        Node node = new Node(data);
        node.setPreviousPointer(null);
        node.setNextPointer(null);

        if (Objects.isNull(root)) {
            return node;
        }

        Queue<Node> queue = new LinkedList<>();
        ((LinkedList<Node>) queue).addFirst(root);

        while (true) {
            Node nodeRemoved = ((LinkedList<Node>) queue).removeFirst();

            if (nodeRemoved.getPreviousPointer()==null) {
                nodeRemoved.setPreviousPointer(node);
                return root;
            }

            if (nodeRemoved.getNextPointer()==null) {
                nodeRemoved.setNextPointer(node);
                return root;
            }

            ((LinkedList<Node>) queue).addLast(nodeRemoved.getPreviousPointer());
            ((LinkedList<Node>) queue).addLast(nodeRemoved.getNextPointer());

        }

    }

}
