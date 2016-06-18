package com.galvanize.binaryTree;

import java.util.ArrayList;

/**
 * Created by gschool on 6/17/16.
 */
public class BinaryTree {
    private Node root;

    public BinaryTree(){
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }



    public boolean insertRecursively(int value) {
        if(this.root == null) this.root = new Node(value);

        return insertRecursively(value, this.root);
    }

    private boolean insertRecursively(int value, Node location){

        if(value > location.getValue()){
            return (location.getRight() == null) ? location.setRight(new Node(value)) : insertRecursively(value, location.getRight());
        }

        if(value < location.getValue()){
            return (location.getLeft() == null) ? location.setLeft(new Node(value)) : insertRecursively(value, location.getLeft());
        }

        return false;
    }

    public boolean insertIteratively(int value){
        if(this.root == null){
            this.root = new Node(value);
            return true;
        }

        Node current = this.root;
        while(true){
            if(current.getValue() == value) return false;
            else if(value > current.getValue()){
                if(current.getRight() == null){
                    current.setRight(new Node(value));
                    return true;
                }else{
                    current = current.getRight();
                }
            }else{
                if(current.getLeft() == null){
                    current.setLeft(new Node(value));
                    return true;
                }else{
                    current = current.getLeft();
                }
            }
        }
    }

    public boolean containsIteratively(int value) {
        Node current = this.root;
        while(true){
            if(current == null) return false;
            if(value == current.getValue()) return true;
            if(value < current.getValue()) current = current.getLeft();
            else if(value > current.getValue()) current = current.getRight();
        }
    }

    public boolean containsRecursively(int value) {
        return containsRecursively(value, this.root);
    }

    private boolean containsRecursively(int value, Node current){
        if(current == null) return false;
        return (value == current.getValue()) || ((value > current.getValue())
                                                    ? containsRecursively(value, current.getRight())
                                                    : containsRecursively(value, current.getLeft()));

    }


    public ArrayList<Integer> breadthFirstSearch(){
        ArrayList<Integer> list = new ArrayList<>();
        if(this.root == null) return list;
        list.add(this.root.getValue());
        list.addAll(breadthFirstSearch(this.root));
        return list;
    }

    private ArrayList<Integer> breadthFirstSearch(Node current){
        ArrayList<Integer> list = new ArrayList<>();

        if(current == null) return list;

        Node left = current.getLeft();
        Node right = current.getRight();

        if(left != null) list.add(left.getValue());
        if(right != null) list.add(right.getValue());

        list.addAll(breadthFirstSearch(left));
        list.addAll(breadthFirstSearch(right));

        return list;
    }




}
