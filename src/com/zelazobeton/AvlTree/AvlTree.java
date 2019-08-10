package com.zelazobeton.AvlTree;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

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
                ptrNode.setRightChild(new Node(ptrNode, payload));
                System.out.println("Key: " + payload.getKey() + " added");
                updateBalanceNodeAdded(ptrNode.getRightChild());
                return true;
            }
        }
        else {
            if (ptrNode.hasLeftChild()) {
                return put(payload, ptrNode.getLeftChild());
            } else {
                ptrNode.setLeftChild(new Node(ptrNode, payload));
                updateBalanceNodeAdded(ptrNode.getLeftChild());
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
                //To refactor?
                INode successorParentIfItIsNotToDelete = null;
                if (successor.getParent() != toDelete){
                    successorParentIfItIsNotToDelete = successor.getParent();
                }

                replaceOneNodeWithAnother(successor, successor.getRightChild());
                successor.setLeftChild(toDelete.getLeftChild());
                successor.setRightChild(toDelete.getRightChild());
                successor.setBalanceFactor(toDelete.getBalanceFactor());
                replaceOneNodeWithAnother(toDelete, successor);

                //To refactor
                if(successorParentIfItIsNotToDelete != null){
                    successorParentIfItIsNotToDelete.addToBalanceFactor(-1);
                    if (successorParentIfItIsNotToDelete.getBalanceFactor() == 0) {
                        updateBalanceNodeDeleted(successorParentIfItIsNotToDelete);
                    }
                }
                else {
                    successor.addToBalanceFactor(1);
                    if (successor.getBalanceFactor() == 0) {
                        updateBalanceNodeDeleted(successor);
                    }
                }
            }
            else if (toDelete.hasLeftChild()) {
                updateBalanceNodeDeleted(toDelete);
                replaceOneNodeWithAnother(toDelete, toDelete.getLeftChild());
            }
            else if (toDelete.hasRightChild()) {
                updateBalanceNodeDeleted(toDelete);
                replaceOneNodeWithAnother(toDelete, toDelete.getRightChild());
            }
            else {
                updateBalanceNodeDeleted(toDelete);
                if (toDelete.isLeftChild()) {
                    toDelete.getParent().setLeftChild(null);
                }
                else {
                    toDelete.getParent().setRightChild(null);
                }
            }
            return true;
        }
    }

    private void replaceOneNodeWithAnother(INode toReplace, INode newNode) {
        if (toReplace == root){
            root = newNode;
            return;
        }
        if (toReplace.isLeftChild()) {
            toReplace.getParent().setLeftChild(newNode);
        }
        else {
            toReplace.getParent().setRightChild(newNode);
        }
    }

    private void updateBalanceNodeAdded(INode node){
        if (node.getBalanceFactor() < -1 || node.getBalanceFactor() > 1){
            rebalance(node);
            return;
        }
        if (node.isRoot()) {
            return;
        }
        if (node.isLeftChild()) {
            node.getParent().addToBalanceFactor(1);
        }
        else if (node.isRightChild()) {
            node.getParent().addToBalanceFactor(-1);
        }
        if (node.getParent().getBalanceFactor() != 0) {
            updateBalanceNodeAdded(node.getParent());
        }
    }

    private void updateBalanceNodeDeleted(INode node){
        if (node.getBalanceFactor() < -1 || node.getBalanceFactor() > 1){
            rebalance(node);
            return;
        }
        if (node.isRoot()) {
            return;
        }
        if (node.isLeftChild()) {
            node.getParent().addToBalanceFactor(-1);
            if (node.getParent().getBalanceFactor() >= 0){
                updateBalanceNodeDeleted(node.getParent());
            }
        }
        else if (node.isRightChild()){
            node.getParent().addToBalanceFactor(1);
            if (node.getParent().getBalanceFactor() <= 0){
                updateBalanceNodeDeleted(node.getParent());
            }
        }
        if (node.getParent().getBalanceFactor() < -1 &&  node.getParent().getBalanceFactor() < -1) {
            updateBalanceNodeDeleted(node.getParent());
        }
    }

    private void rotateLeft(INode pivotNode){
        INode newPivot = pivotNode.getRightChild();
//        if (newPivot.hasLeftChild()){
//            pivotNode.setRightChild(newPivot.getLeftChild());
//        }
        pivotNode.setRightChild(newPivot.getLeftChild());
        replaceOneNodeWithAnother(pivotNode, newPivot);
        newPivot.setLeftChild(pivotNode);
//        pivotNode.setRightChild(null);
        pivotNode.addToBalanceFactor(1 - Math.min(newPivot.getBalanceFactor(), 0));
        newPivot.addToBalanceFactor(1 + Math.max(pivotNode.getBalanceFactor(), 0));
    }

    private void rotateRight(INode pivotNode){
        INode newPivot = pivotNode.getLeftChild();
//        if (newPivot.hasRightChild()){
//            pivotNode.setLeftChild(newPivot.getRightChild());
//        }
        pivotNode.setLeftChild(newPivot.getRightChild());

        replaceOneNodeWithAnother(pivotNode, newPivot);
        newPivot.setRightChild(pivotNode);
//        pivotNode.setLeftChild(null);
        pivotNode.addToBalanceFactor(- 1 - Math.max(newPivot.getBalanceFactor(), 0));
        newPivot.addToBalanceFactor(- 1 + Math.max(pivotNode.getBalanceFactor(), 0));
    }

    private void rebalance(INode node){
        if(node.getBalanceFactor() > 1){
            System.out.println("Rebalance > 1, Key: " + node.getPayload().getKey());
            if(node.getLeftChild().getBalanceFactor() < 0){
                rotateLeft(node.getLeftChild());
            }
            rotateRight(node);
        }
        else if(node.getBalanceFactor() < -1){
            System.out.println("Rebalance < -1, Key: " + node.getPayload().getKey());
            if(node.getRightChild().getBalanceFactor() > 0){
                rotateRight(node.getRightChild());
            }
            rotateLeft(node);
        }
    }

    public void inorder(INode node, String address){
        if(node != null){
            String addressLeft = address + 'L';
            inorder(node.getLeftChild(), addressLeft);
            System.out.println("Key: " + node.getPayload().getKey() + " Address: " + address + " BF: " + node.getBalanceFactor());
            String addressRight = address + 'R';
            inorder(node.getRightChild(), addressRight);
        }
    }
}
