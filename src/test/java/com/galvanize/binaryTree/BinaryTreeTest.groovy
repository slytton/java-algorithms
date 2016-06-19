package com.galvanize.binaryTree

import spock.lang.Specification

/**
 * Created by gschool on 6/17/16.
 */
class BinaryTreeTest extends Specification {
    BinaryTree binTree;

    def setup() {
        binTree = new BinaryTree();
        binTree.insertIteratively(5);
        binTree.insertIteratively(10);
        binTree.insertIteratively(3);
    }

    def "BinaryTree starts with an empty root." (){
        expect:
        new BinaryTree().getRoot() == null;
    }

    def "#insertIteratively adds successfully" () {
        expect:
        binTree.root.value == 5
        binTree.root.right.value == 10
        binTree.root.left.value == 3
    }

    def "#insertIteratively does not add duplicates" () {
        binTree.insertIteratively(3);
        binTree.insertIteratively(3);
        binTree.insertIteratively(3);
        binTree.insertIteratively(3);

        expect:
        binTree.root.left.value == 3;
        binTree.root.left.left == null;
        binTree.root.left.right == null;
    }

    def "#insertIteratively adds multiple numbers in the correct position" () {
        binTree.insertIteratively(4);
        binTree.insertIteratively(7);
        binTree.insertIteratively(6);

        expect:
        binTree.root.left.right.value == 4;
        binTree.root.right.left.value == 7;
        binTree.root.right.left.left.value == 6;
    }

    def "#insertRecursively adds successfully" () {
        binTree = new BinaryTree();
        binTree.insertRecursively(5)
        binTree.insertRecursively(10)
        binTree.insertRecursively(3)

        expect:
        binTree.root.value == 5
        binTree.root.right.value == 10
        binTree.root.left.value == 3
    }

    def "#insertRecursively does not add duplicates" () {
        binTree.insertRecursively(3);
        binTree.insertRecursively(3);
        binTree.insertRecursively(3);
        binTree.insertRecursively(3);

        expect:
        binTree.root.left.value == 3;
        binTree.root.left.left == null;
        binTree.root.left.right == null;
    }

    def "#insertRecursively adds multiple numbers in the correct position" () {
        binTree.insertRecursively(4);
        binTree.insertRecursively(7);
        binTree.insertRecursively(6);

        expect:
        binTree.root.left.right.value == 4;
        binTree.root.right.left.value == 7;
        binTree.root.right.left.left.value == 6;
    }

    def "#containsIteratively should return true when value is found" (int input, boolean result){
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.containsIteratively(input) == result

        where:
        input  ||  result
        7      ||  true
        3      ||  true
        9      ||  true
        1      ||  true
        99     ||  true
        44     ||  true
        66     ||  true
    }

    def "#containsIteratively should return false when value is not found" (int input, boolean result){
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.containsIteratively(input) == result

        where:
        input  ||  result
        -20    ||  false
        20     ||  false

    }

    def "#containsRecursively should return true when value is found" (int input, boolean result){
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.containsRecursively(input) == result

        where:
        input  ||  result
        7      ||  true
        3      ||  true
        9      ||  true
        1      ||  true
        99     ||  true
        44     ||  true
        66     ||  true
    }

    def "#containsRecursively should return false when value is not found" (int input, boolean result){
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.containsIteratively(input) == result

        where:
        input  ||  result
        -20    ||  false
        20     ||  false

    }

    def "#breadthFirstSearch should search left to right" () {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});
        def largeBinTree = new BinaryTree();
        [5,3,1,2,10,9,11,15,14].forEach({value -> largeBinTree.insertRecursively(value)});


        expect:
        binTree.breadthFirstSearch().equals([7,3,9,1,99,44,66])
        largeBinTree.breadthFirstSearch().equals([5,3,10,1,2,9,11,15,14])
    }

    def "#depthFirstSearch - preorder"() {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.DFSPreOrder().equals([7, 3, 1, 9, 99, 44, 66]);
    }

    def "#depthFirstSearch - inOrder"() {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.DFSInOrder().equals([1, 3, 7, 9, 44, 66, 99]);
    }

    def "#depthFirstSearch - postOrder"() {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.DFSPostOrder().equals([1, 3, 66, 44, 99, 9, 7]);
    }

    def "#findLowest should find the smallest value in the tree" () {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.findLowest() == 1;
    }

    def "#findHighest should find the smallest value in the tree" () {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.findHighest() == 99;
    }

    def "#size should return the number of nodes in the tree." () {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.size() == 7;
    }

    def "#remove does not remove values not in the tree" (){
        binTree = new BinaryTree();
        [7,3,9,8,5,1,99,44,33,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        binTree.remove(100) == false;
    }

    def "#remove removes leaf nodes correctly" (){
        binTree = new BinaryTree();
        [7,3,9,8,5,1,99,44,33,66].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(1)

        expect:
        binTree.DFSInOrder().equals([3,5,7,8,9,33,44,66,99]);
    }

    def "#remove removes a node with 1 child on the left" () {
        binTree = new BinaryTree();
        [50,20,55,54].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(55);

        expect:
        binTree.getRoot().value                  ==  50;
        binTree.getRoot().getRight().getValue()  ==  54;
        binTree.getRoot().getLeft().getValue()   ==  20;
        binTree.getRoot().getLeft().getLeft()    ==  null;
        binTree.getRoot().getRight().getRight()  ==  null;

    }

    def "#remove removes a node with 1 child on the right" () {
        binTree = new BinaryTree();
        [50,20,21,54].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(20);

        expect:
        binTree.getRoot().value                  ==  50;
        binTree.getRoot().getRight().getValue()  ==  54;
        binTree.getRoot().getLeft().getValue()   ==  21;
        binTree.getRoot().getLeft().getLeft()    ==  null;
        binTree.getRoot().getRight().getRight()  ==  null;

    }

    def "#remove removes a node with 2 children" () {
        binTree = new BinaryTree();
        [7,3,9,8,5,1,99,44,33,66].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(3);

        expect:
        binTree.DFSInOrder().equals([1,5,7,8,9,33,44,66,99])

    }

    def "#remove removes the root when it's a leaf." () {
        binTree = new BinaryTree();
        [7].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(7);

        expect:
        binTree.DFSInOrder().equals([]);

    }

    def "#remove removes the root correctly when it has a child." () {
        binTree = new BinaryTree();
        [7, 10].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(7);

        expect:
        binTree.DFSInOrder().equals([10]);

    }

    def "#remove removes the root node correctly when it has two children." () {
        binTree = new BinaryTree();
        [7,3,9,8,5,1,99,44,33,66].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(7);

        expect:
        binTree.DFSInOrder().equals([1,3,5,8,9,33,44,66,99]);
    }

    def "#remove removes a node with two children where the right child doesn't have any left children" (){
        binTree = new BinaryTree();
        [25, 10, 27, 28].forEach({value -> binTree.insertRecursively(value)});
        binTree.remove(25);

        expect:
        binTree.getRoot().getValue() == 27;
        binTree.getRoot().getLeft().getValue() == 10;
        binTree.getRoot().getRight().getValue() == 28;
    }
}