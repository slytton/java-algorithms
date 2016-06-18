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
        [5,3,1,2,10,9,11].forEach({value -> largeBinTree.insertRecursively(value)});


        expect:
        binTree.breadthFirstSearch().equals([7,3,9,1,99,44,66])
        largeBinTree.breadthFirstSearch().equals([5,3,10,1,2,9,11])
    }

    def "#depthFirstSearch - preorder"() {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        Arrays.equals(binTree.DFSPreOrder(), [7, 3, 1, 9, 99, 44, 66]);

    }

    def "#depthFirstSearch - inOrder"() {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        Arrays.equals(binTree.DFSInOrder(), [1, 3, 7, 9, 44, 66, 99]);

    }

    def "#depthFirstSearch - postOrder"() {
        binTree = new BinaryTree();
        [7,3,9,1,99,44,66].forEach({value -> binTree.insertRecursively(value)});

        expect:
        Arrays.equals(binTree.DFSPostOrder(), [1, 3, 66, 44, 99, 9, 7]);

    }

}


//        describe("postorder", function(){
//        it("searches from left - right - root", function(){
//        expect(binTree.DFSPostOrder()).to.deep.eq([1, 3, 66, 44, 99, 9, 7]);
//        });
//        });
//        });
//        describe("#findLowest", function(){
//        it("It should", function(){
//        binTree = new data.BinTree();
//        [7,3,9,1,99,44,66].forEach(function(v) {
//        binTree.insertRecursively(v);
//        });
//        expect(binTree.findLowest()).to.equal(1);
//        });
//        });
//        describe("#findHighest", function(){
//        it("It should", function(){
//        binTree = new data.BinTree();
//        [7,3,9,1,99,44,66].forEach(function(v) {
//        binTree.insertRecursively(v);
//        });
//        expect(binTree.findHighest()).to.equal(99);
//        });
//        });
//        describe("#size", function(){
//        it("It should return the size of the binary tree", function(){
//        binTree = new data.BinTree();
//        [7,3,9,1,99,44,66].forEach(function(v) {
//        binTree.insertRecursively(v);
//        });
//        expect(binTree.size()).to.equal(7);
//        });
//        });
//        describe("#remove", function(){
//        beforeEach(function(){
//        binTree = new data.BinTree();
//        [7,3,9,8,5,1,99,44,33,66].forEach(function(v) {
//        binTree.insertRecursively(v);
//        })
//        });
//        it("does not remove values not in the tree", function(){
//        expect(binTree.remove(100)).to.equal("Value not in the tree!");
//        });
//        it("removes leaf nodes correctly", function(){
//        binTree.remove(1);
//        expect(binTree.DFSInOrder()).to.deep.eq([3,5,7,8,9,33,44,66,99]);
//        });
//        it("removes a node with 1 child on the left", function() {
//        binTree = new data.BinTree();
//        binTree.insertRecursively(50);
//        binTree.insertRecursively(20);
//        binTree.insertRecursively(55);
//        binTree.insertRecursively(54);
//        binTree.remove(55);
//        expect(binTree.root.value).to.equal(50);
//        expect(binTree.root.right.value).to.equal(54);
//        expect(binTree.root.left.value).to.equal(20);
//        expect(binTree.root.left.left).to.equal(null);
//        expect(binTree.root.right.right).to.equal(null);
//        });
//        it("removes a node with 1 child on the right", function() {
//        binTree = new data.BinTree();
//        binTree.insertRecursively(50);
//        binTree.insertRecursively(20);
//        binTree.insertRecursively(21);
//        binTree.insertRecursively(54);
//        binTree.remove(20);
//        expect(binTree.root.value).to.equal(50);
//        expect(binTree.root.right.value).to.equal(54);
//        expect(binTree.root.left.value).to.equal(21);
//        expect(binTree.root.left.left).to.equal(null);
//        expect(binTree.root.right.right).to.equal(null);
//        });
//        it("removes nodes with two children correctly", function(){
//        binTree.remove(3);
//        expect(binTree.DFSInOrder()).to.deep.eq([1,5,7,8,9,33,44,66,99]);
//        });
//        it("removes the root node correctly when the root is a leaf", function(){
//        binTree = new data.BinTree();
//        binTree.insertRecursively(7);
//        binTree.remove(7)
//        expect(binTree.DFSInOrder()).to.deep.eq([]);
//        });
//        it("removes the root node correctly when the root has a child", function(){
//        binTree = new data.BinTree();
//        binTree.insertRecursively(7);
//        binTree.insertRecursively(10);
//        binTree.remove(7);
//        expect(binTree.DFSInOrder()).to.deep.eq([10]);
//        });
//        it("removes the root node correctly when the root has two children", function(){
//        binTree.remove(7);
//        expect(binTree.DFSInOrder()).to.deep.equal([1,3,5,8,9,33,44,66,99]);
//        });
//        it("removes correctly with 2 children and the right child doesn't have any left children", function() {
//        binTree = new data.BinTree();
//        binTree.insertRecursively(25);
//        binTree.insertRecursively(10);
//        binTree.insertRecursively(27);
//        binTree.insertRecursively(28);
//        binTree.remove(25);
//        expect(binTree.root.value).to.equal(27);
//        expect(binTree.root.left.value).to.equal(10);
//        expect(binTree.root.right.value).to.equal(28);
//        })
//        });
//        });

