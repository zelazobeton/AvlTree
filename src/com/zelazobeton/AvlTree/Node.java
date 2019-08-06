package com.zelazobeton.AvlTree;

public class Node extends INode {
    private INode leftChild;
    private INode rightChild;
    private INode parent;

    public Node(INode parent, IPayload payload, INode leftChild, INode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        super.payload = payload;
    }

    public Node(INode parent, IPayload payload) {
        this(parent, payload, null, null);
    }

    @Override
    public IPayload getPayload() {
        return payload;
    }

    @Override
    public int compareTo(INode o) {
        return o.payload.compareTo(o.payload);
    }

    @Override
    public void addLeftChild(INode newLeftChild) {
        this.leftChild = newLeftChild;
    }

    @Override
    public void addRightChild(INode newRightChild) {
        this.rightChild = newRightChild;
    }

    @Override
    public void removeLeftChild() {
        this.leftChild = null;
    }

    @Override
    public void removeRightChild() {
        this.rightChild = null;
    }

    @Override
    public INode getLeftChild() {
        return this.leftChild;
    }

    @Override
    public INode getRightChild() {
        return this.rightChild;
    }

    @Override
    public INode getParent() {
        return this.parent;
    }

    @Override
    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    @Override
    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    @Override
    public boolean isLeftChild() {
        return this.parent.getLeftChild() == this;
    }

    @Override
    public boolean isRightChild() {
        return this.parent.getRightChild() == this;
    }

    @Override
    public boolean isRoot() {
        return parent == null;
    }

    @Override
    public boolean isLeaf() {
        return (!hasLeftChild() && !hasRightChild());
    }
}
