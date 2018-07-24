package com.bonaparte.common;

/**
 * Created by yangmingquan on 2018/7/24.
 */
public class Node {
    int value;
    Node leftChild;
    Node rightChild;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node(int value) {
        this.value = value;
    }

    public String toString(){
        return String.valueOf(value);
    }
}
