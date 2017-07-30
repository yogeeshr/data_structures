package com.yogeesh.datastructures.common;

/**
 * Created by yogeesh.srkvs@gmail.com on 7/29/17.
 */
public class Node {

    public Node previousPointer = null, nextPointer = null;
    Data data = null;

    // Constructor
    public Node() {
        data = new Data();
    }

    // Overlaoded Constructor
    public Node(Data data) {
        this.data = data;
    }

    public Node getPreviousPointer() {
        return previousPointer;
    }

    public void setPreviousPointer(Node previousPointer) {
        this.previousPointer = previousPointer;
    }

    public Node getNextPointer() {
        return nextPointer;
    }

    public void setNextPointer(Node nextPointer) {
        this.nextPointer = nextPointer;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "previousPointer=" + previousPointer +
                ", nextPointer=" + nextPointer +
                ", data=" + data +
                '}';
    }

}
