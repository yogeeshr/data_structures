package com.yogeesh.datastructures.heaps;

import com.yogeesh.datastructures.common.Data;
import com.yogeesh.datastructures.common.Node;
import com.yogeesh.datastructures.trees.BinaryTree;
import com.yogeesh.datastructures.trees.TreeUtil;

/**
 * @author : yogeesh.srkvs@gmail.com
 */
public class Heap extends BinaryTree{

    int size=0, maxSize=50, level = (int)Math.floor(Math.log(25) / Math.log(2)), left=0, right=0;

    /**
     * Adding element to node
     * @param node
     * @return
     */
    public Node insert (Node node, int ele) {

        if (null==node) {
            ++size;
            return (new Node(new Data(ele)));
        }

        if (size==maxSize) {

            if (root.getData().getInfo()<ele) {
                System.out.println(". . . Incoming element is greater + heap is full . . . | No insert ");
                return root;
            }

            extractMax();
            --size;
        }

        if (ele<node.getData().getInfo()) {

        }

        return null;

    }

    /**
     * heapification . . .
     * @param node
     */
    public static Node heapify(Node node) {

        if (null==node) {
            return null;
        }

        Node left = heapify(node.getPreviousPointer());
        Node right = heapify(node.getNextPointer());

        Node temp = getNodeToSwap(node, left, right);

        if (null!=temp) {
            swap(node, temp);
        }

        return node;

    }

    /**
     * get correct node to swap with
     * @param node
     * @return
     */
    private static Node getNodeToSwap(Node node, Node left, Node right) {

        if (null==right && null==left) {
            return null;
        }

        if (left==null && right.getData().getInfo()>node.getData().getInfo()) {
            return  right;
        }

        if (right==null && left.getData().getInfo()>node.getData().getInfo()) {
            return  left;
        }

        if (left==null || right==null) {
            return null;
        }

        Node temp = (left.getData().getInfo() > right.getData().getInfo())? left: right;


        if (temp.getData().getInfo() > node.getData().getInfo()) {
            return temp;
        }

        return null;

    }

    /**
     * Decrease key from heap !
     */
    public void extractMax() {

        if (root==null) {
            System.out.println("Nothing to remove");
            root=null;
            return;
        }

        --size;
        System.out.println("Removed max : "+ root.getData().getInfo());

        if (root.getPreviousPointer()==null && root.getNextPointer()==null) {
            root=null;
            return;
        }

        if (root.getNextPointer()==null && root.getPreviousPointer()!=null) {
            root=root.getPreviousPointer();
        } else if (root.getNextPointer()!=null && root.getPreviousPointer()==null) {
            root=root.getNextPointer();
        } else {
            Node trial = root, node1=root.getNextPointer();

            while (node1.getNextPointer()!=null) {
                trial=node1;
                node1=node1.getNextPointer();
            }

            int x = root.getData().getInfo();

            //Moving root element to in order predecessor of root
            swap(root,node1);

            if (trial.getNextPointer().getData().getInfo()==x) {
                trial.setNextPointer(null);
            } else {
                trial.setPreviousPointer(null);
            }

            TreeUtil.showPreOrder(root);

            heapify(root);

        }

    }

    /**
     * Swap method
     * @param n1
     * @param n2
     */
    public static boolean swap(Node n1, Node n2) {

        if (n1==null && n2==null) {
            return false;
        }

        int temp1 = n1.getData().getInfo();
        int temp2 = n2.getData().getInfo();

        n1.getData().setInfo(temp2);
        n2.getData().setInfo(temp1);

        return true;

    }

    public static void main(String[] args) {

        Heap heap = new Heap();

        heap.add(19);
        heap.add(10);
        heap.add(21);
        heap.add(9);
        heap.add(11);
        heap.add(20);
        heap.add(22);

        TreeUtil.showPreOrder(heap.root);

        heapify(heap.root);

        System.out.println(" - - - ");

        TreeUtil.showPreOrder(heap.root);

        System.out.println(" - - - ");

        heap.extractMax();

        System.out.println(" - - - ");

        TreeUtil.showPreOrder(heap.root);

    }

}
