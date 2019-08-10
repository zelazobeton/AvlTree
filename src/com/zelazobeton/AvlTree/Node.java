package com.zelazobeton.AvlTree;

public class Node extends INode {
    private INode leftChild;
    private INode rightChild;
    private INode parent;
    private byte balanceFactor;

    public Node(INode parent, IPayload payload, INode leftChild, INode rightChild, byte balanceFactor) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        this.balanceFactor = balanceFactor;
        super.payload = payload;
    }

    public Node(INode parent, IPayload payload) {
        this(parent, payload, null, null, (byte) 0);
    }

    @Override
    public void addToBalanceFactor(int toAdd){
        this.balanceFactor += toAdd;
    }

    @Override //To delete?
    public void setBalanceFactor(byte newBalanceFactor){
        this.balanceFactor = newBalanceFactor;
    }

    @Override
    public byte getBalanceFactor() {
        return this.balanceFactor;
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
    public void setLeftChild(INode newLeftChild) {
        this.leftChild = newLeftChild;
        if(newLeftChild != null){
            newLeftChild.setParent(this);
        }
    }

    @Override
    public void setRightChild(INode newRightChild) {
        this.rightChild = newRightChild;
        if(newRightChild != null){
            newRightChild.setParent(this);
        }
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
    public void setParent(INode newParent) {
        this.parent = newParent;
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
