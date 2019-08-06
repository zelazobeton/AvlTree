package com.example.AVL;

public interface IBinaryTree {
    public boolean put(IPayload payload);
    public IPayload get(int key);
    public boolean contains(int key);
    public boolean delete(int key);
//    public int size();

}
