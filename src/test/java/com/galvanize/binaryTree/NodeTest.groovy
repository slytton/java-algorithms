package com.galvanize.binaryTree

import spock.lang.Specification

/**
 * Created by gschool on 6/17/16.
 */
class NodeTest extends Specification {
    def "Node has properties that can be initialized with a constructor" () {
        def node = new Node(12)

        expect:
        node.getValue() == 12;
        node.getLeft() == null;
        node.getRight() == null;
    }
}
