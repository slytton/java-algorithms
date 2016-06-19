package com.galvanize.binaryTree;

/**
 * Created by gschool on 6/17/16.
 */
public class Node {
    private int value;
    private Node left;
    private Node right;
    private Node parent;

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Node(int value, Node parent){
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = parent;
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

    public Boolean setParent(Node parent) {
        this.parent = parent;
        return true;
    }

    public Node getParent() {
        return this.parent;
    }
}


