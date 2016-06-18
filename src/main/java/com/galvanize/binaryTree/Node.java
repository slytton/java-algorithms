package com.galvanize.binaryTree;

/**
 * Created by gschool on 6/17/16.
 */
public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public Boolean setRight(Node right) {
        this.right = right;
        return true;
    }

    public Node getLeft() {
        return left;
    }

    public Boolean setLeft(Node left) {
        this.left = left;
        return true;
    }
}


