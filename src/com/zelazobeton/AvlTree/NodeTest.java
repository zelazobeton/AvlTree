package com.zelazobeton.AvlTree;

import static org.junit.Assert.*;

public class NodeTest {
    private static INode sut;

    @org.junit.Before
    public void setUp(){
        sut = new Node(null, new Payload());
    }

    @org.junit.Test
    public void getLeftChildShouldReturnNullIfThereIsNoLeftChild() {
        assertNull(sut.getLeftChild());
    }

    @org.junit.Test
    public void afterAddingLeftChildGetLeftChildShouldReturnIt() {
        INode newLeftChild = new Node(sut, new Payload());
        sut.addLeftChild(newLeftChild);
        assertEquals(newLeftChild, sut.getLeftChild());
    }

    @org.junit.Test
    public void getRightChildShouldReturnNullIfThereIsNoRightChild() {
        assertNull(sut.getRightChild());
    }

    @org.junit.Test
    public void afterAddingRightChildGetRightChildShouldReturnIt() {
        INode newRightChild = new Node(sut, new Payload());
        sut.addRightChild(newRightChild);
        assertEquals(newRightChild, sut.getRightChild());
    }

    @org.junit.Test
    public void isLeftChildShouldReturnTrue() {
        INode leftChild = new Node(sut, new Payload());
        sut.addLeftChild(leftChild);
        assertTrue(leftChild.isLeftChild());
    }

    @org.junit.Test
    public void isLeftChildShouldReturnFalse() {
        INode leftChild = new Node(sut, new Payload());
        sut.addRightChild(leftChild);
        assertFalse(leftChild.isLeftChild());
    }

    @org.junit.Test
    public void isRightChildShouldReturnTrue() {
        INode RightChild = new Node(sut, new Payload());
        sut.addRightChild(RightChild);
        assertTrue(RightChild.isRightChild());
    }

    @org.junit.Test
    public void isRightChildShouldReturnFalse() {
        INode RightChild = new Node(sut, new Payload());
        sut.addLeftChild(RightChild);
        assertFalse(RightChild.isRightChild());
    }

    @org.junit.Test
    public void isLeafReturnTrueIfNodeIsLeaf() {
        assertTrue(sut.isLeaf());
    }

    @org.junit.Test
    public void isLeafReturnFalseIfNodeIsNotLeaf() {
        sut.addRightChild(new Node(sut, new Payload()));
        assertFalse(sut.isLeaf());
    }

    @org.junit.Test
    public void isRootReturnTrue() {
        assertTrue(sut.isRoot());
    }

    @org.junit.Test
    public void isRootReturnFalse() {
        sut.addRightChild(new Node(sut, new Payload()));
        assertFalse(sut.getRightChild().isRoot());
    }
}