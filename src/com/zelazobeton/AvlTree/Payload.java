package com.zelazobeton.AvlTree;

public class Payload extends IPayload {

    public Payload(int key) {
        this.key = key;
    }

    public Payload() {
        this(0);
    }

    @Override
    public int compareTo(IPayload o) {
        if (key < o.key) {
            return -1;
        }
        else if (key > o.key) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
