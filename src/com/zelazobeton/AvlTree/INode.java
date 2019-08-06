package com.zelazobeton.AvlTree;

public abstract class INode implements Comparable<INode> {
    protected IPayload payload;
    public abstract IPayload getPayload();
    public abstract void addLeftChild(INode newLeftChild);
    public abstract void addRightChild(INode newRightChild);
    public abstract boolean hasLeftChild();
    public abstract boolean hasRightChild();
    public abstract INode getLeftChild();
    public abstract INode getRightChild();
    public abstract INode getParent();
    public abstract void removeLeftChild();
    public abstract void removeRightChild();
    public abstract boolean isLeftChild();
    public abstract boolean isRightChild();
    public abstract boolean isRoot();
    public abstract boolean isLeaf();
}
