package com.zelazobeton.AvlTree;

public class AvlTree implements IBinaryTree {
    public INode root;

    public AvlTree() {
        this(null);
    }

    public AvlTree(INode root) {
        this.root = root;
    }

    @Override
    public boolean put(IPayload payload) {
        if (root == null){
            this.root = new Node(null, payload);
            return true;
        }
        else {
            return put(payload, root);
        }
    }

    private boolean put(IPayload payload, INode ptrNode){
        if (ptrNode.payload.compareTo(payload) == 0) {
            System.out.println("Attempt to insert node with existing key.");
            return false;
        }
        else if (ptrNode.payload.compareTo(payload) < 0) {
            if (ptrNode.hasRightChild()) {
                return put(payload, ptrNode.getRightChild());
            }
            else {
                ptrNode.addRightChild(new Node(ptrNode, payload));
                return true;
            }
        }
        else {
            if (ptrNode.hasLeftChild()) {
                return put(payload, ptrNode.getLeftChild());
            } else {
                ptrNode.addLeftChild(new Node(ptrNode, payload));
                return true;
            }
        }
    }

    @Override
    public IPayload get(int key) {
        if (root == null){
            return null;
        }
        else {
            INode ptrNode = get(new Payload(key), root);
            if (ptrNode == null) {
                return null;
            }
            else {
                return ptrNode.getPayload();
            }
        }
    }

    public INode get(IPayload keyPayload, INode ptrNode) {
        if (ptrNode.payload.compareTo(keyPayload) == 0) {
            return ptrNode;
        }
        else if (ptrNode.payload.compareTo(keyPayload) < 0 && ptrNode.hasRightChild()) {
            return get(keyPayload, ptrNode.getRightChild());
        }
        else if (ptrNode.payload.compareTo(keyPayload) > 0 && ptrNode.hasLeftChild()) {
            return get(keyPayload, ptrNode.getLeftChild());
        }
        else {
            return null;
        }
    }

    @Override
    public boolean contains(int key) {
        IPayload keyPayload = get(key);
        if (keyPayload == null) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean delete(int key) {
        if (root == null){
            return false;
        }
        else {
            INode toDelete = get(new Payload(key), root);
            if (toDelete == null) {
                return false;
            }
            if (toDelete.hasLeftChild() && toDelete.hasRightChild()){
                INode successor = toDelete.getRightChild();
                while (successor.hasLeftChild()) {
                    successor = successor.getLeftChild();
                }
                INode successChild = successor.getRightChild();
                if (successChild != null){
                    replaceOneNodeWithAnother(successor, successChild);
                }
                successor.addLeftChild(toDelete.getLeftChild());
                successor.addRightChild(toDelete.getRightChild());
                replaceOneNodeWithAnother(toDelete, successor);
            }
            else if (toDelete.hasLeftChild()) {
                replaceOneNodeWithAnother(toDelete, toDelete.getLeftChild());
            }
            else if (toDelete.hasRightChild()) {
                replaceOneNodeWithAnother(toDelete, toDelete.getRightChild());
            }
            else {
                replaceOneNodeWithAnother(toDelete, null);
            }
            return true;
        }
    }
    private void replaceOneNodeWithAnother(INode toReplace, INode newNode) {
        if (toReplace.isLeftChild()) {
            toReplace.getParent().addLeftChild(newNode);
        }
        else {
            toReplace.getParent().addRightChild(newNode);
        }
    }
}
