package com.zelazobeton.AvlTree;

public abstract class INode implements Comparable<INode> {
    protected IPayload payload;
    public abstract IPayload getPayload();
    public abstract void setLeftChild(INode newLeftChild);
    public abstract void setRightChild(INode newRightChild);
    public abstract boolean hasLeftChild();
    public abstract boolean hasRightChild();
    public abstract INode getLeftChild();
    public abstract INode getRightChild();
    public abstract INode getParent();
    public abstract void setParent(INode newParent);
    public abstract boolean isLeftChild();
    public abstract boolean isRightChild();
    public abstract boolean isRoot();
    public abstract boolean isLeaf();
    public abstract void addToBalanceFactor(int toAdd);
    public abstract byte getBalanceFactor();
    public abstract void setBalanceFactor(byte newBalanceFactor);
}
