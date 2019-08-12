package com.zelazobeton.AvlTree;

public class Payload extends IPayload {
    private int key;
    public Payload(int key) {
        this.key = key;
    }

    public Payload() {
        this(0);
    }

    public int getKey(){
        return key;
    }

    @Override
    public int compareTo(IPayload o) {
        try
        {
            Payload payloadObj = (Payload) o;
            if (key < payloadObj.key) {
                return -1;
            }
            else if (key > payloadObj.key) {
                return 1;
            }
            else {
                return 0;
            }
        }
        catch (ClassCastException e){
            System.out.println(e.toString());
            return 0;
        }
    }
}
