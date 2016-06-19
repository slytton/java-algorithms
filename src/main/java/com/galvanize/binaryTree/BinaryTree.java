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


//    **** #insertRecursively ****
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


//    **** #insertIteratively ****
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


//    **** #containsIteratively ****
    public boolean containsIteratively(int value) {
        Node current = this.root;
        while(true){
            if(current == null) return false;
            if(value == current.getValue()) return true;
            if(value < current.getValue()) current = current.getLeft();
            else if(value > current.getValue()) current = current.getRight();
        }
    }

//    **** #containsRecursively ****
    public boolean containsRecursively(int value) {
        return containsRecursively(value, this.root);
    }

    private boolean containsRecursively(int value, Node current){
        if(current == null) return false;
        return (value == current.getValue()) || ((value > current.getValue())
                                                    ? containsRecursively(value, current.getRight())
                                                    : containsRecursively(value, current.getLeft()));

    }


//    **** #breadthFirstSearch ****
    public ArrayList<Integer> breadthFirstSearch(){
        ArrayList<Integer> list = new ArrayList<>();
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


//    **** #DFSPreOrder ****
    public ArrayList<Integer> DFSPreOrder() {
        ArrayList<Integer> list = new ArrayList<>();
        DFSPreOrder(this.root, list);
        return list;
    }

    private void DFSPreOrder(Node current, ArrayList<Integer> list){
        if(current == null) return ;

        list.add(current.getValue());
        DFSPreOrder(current.getLeft(), list);
        DFSPreOrder(current.getRight(), list);
    }


//    **** DFSInOrder() ****
    public ArrayList<Integer> DFSInOrder(){
        return DFSInOrder(this.root);
    }

    private ArrayList<Integer> DFSInOrder(Node current){
        if(current == null) return new ArrayList<>();

        ArrayList<Integer> list = DFSInOrder(current.getLeft());
        list.add(current.getValue());
        list.addAll(DFSInOrder(current.getRight()));
        return list;
    }

//    **** DFSPostOrder() ****
    public ArrayList<Integer> DFSPostOrder(){
        return DFSPostOrder(this.root);
    }

    private ArrayList<Integer> DFSPostOrder(Node current){
        if(current == null) return new ArrayList<>();

        ArrayList<Integer> list = DFSPostOrder(current.getLeft());
        list.addAll(DFSPostOrder(current.getRight()));
        list.add(current.getValue());

        return list;
    }

//    **** #findLowest ****
    public int findLowest(){
        return findLowest(this.root);
    }

    private int findLowest(Node current){
        if(current.getLeft() == null) return current.getValue();
        return findLowest(current.getLeft());
    }

//    **** #findHighest ****
    public int findHighest(){
        return findHighest(this.root);
    }

    private int findHighest(Node current){
        if(current.getRight() == null) return current.getValue();
        return findHighest(current.getRight());
    }

//    **** #size ****
    public int size(){
        return size(this.root);
    }

    private int size(Node current){
        return (current == null) ? 0 : 1 + size(current.getLeft()) + size(current.getRight());
    }

//    **** #remove ****
    public boolean remove(int toRemove){
        return remove(toRemove, this.root, null);
    }

    public boolean remove(int toRemove, Node current, Node prev){
        if(current == null) return false;

        if(toRemove < current.getValue()) return remove(toRemove, current.getLeft(), current);
        if(toRemove > current.getValue()) return remove(toRemove, current.getRight(), current);

        if(current.getLeft() == null && current.getRight() == null){ // The node is a leaf
            if(current == this.root) this.root = null;  // The node is the last node in the tree.
            else setPrev(prev, current, null);
        } else if(current.getRight() == null) { // The node to remove only has a child on left
            setPrev(prev, current, current.getLeft());
        } else if(current.getLeft() == null) { // The node to remove only has a child on the right
            setPrev(prev, current, current.getRight());
        } else { // The node to remove has children on both left and right.
            // Get the smallest value from the large side relative to current.
            int smallest = findLowest(current.getRight());
            // Remove that value from tree.
            remove(smallest);
            // Replace removed node with the smallest value.
            Node newCurrent = new Node(smallest);
            newCurrent.setLeft(current.getLeft());
            newCurrent.setRight(current.getRight());
            setPrev(prev, current, newCurrent);
        }
        return true;
    }

    private void setPrev(Node prev, Node toReplace, Node setTo){
        if(prev == null){
            this.root = setTo;
        }else if(prev.getLeft().getValue() == toReplace.getValue()) prev.setLeft(setTo);
        else prev.setRight(setTo);
    }

}
