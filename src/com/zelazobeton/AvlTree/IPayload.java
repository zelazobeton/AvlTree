package com.zelazobeton.AvlTree;

public abstract class IPayload implements Comparable<IPayload> {
    public int key;
    public abstract int getKey(); //only for tests in main to remove
}
