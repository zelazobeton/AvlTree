package com.zelazobeton.AvlTree;

public class AvlTree {
    INode root;

    public AvlTree() {
        this(null);
    }

    public AvlTree(INode root) {
        this.root = root;
    }

    private enum EWhichChild
    {
        IS_LEFT_CHILD,
        IS_RIGHT_CHILD
    }

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
            return false;
        }
        else if (ptrNode.payload.compareTo(payload) < 0) {
            if (ptrNode.hasRightChild()) {
                return put(payload, ptrNode.getRightChild());
            }
            else {
                ptrNode.setRightChild(new Node(ptrNode, payload));
                updateBalanceIfNodeAdded(ptrNode.getRightChild());
            }
        }
        else if (ptrNode.payload.compareTo(payload) > 0) {
            if (ptrNode.hasLeftChild()) {
                return put(payload, ptrNode.getLeftChild());
            } else {
                ptrNode.setLeftChild(new Node(ptrNode, payload));
                updateBalanceIfNodeAdded(ptrNode.getLeftChild());
            }
        }
        return true;
    }

    public IPayload get(IPayload payloadToGet) {
        if (root == null){
            return null;
        }
        else {
            INode ptrNode = get(payloadToGet, root);
            if (ptrNode == null) {
                return null;
            }
            else {
                return ptrNode.getPayload();
            }
        }
    }

    INode get(IPayload keyPayload, INode ptrNode) {
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

    public boolean contains(IPayload payload) {
        IPayload keyPayload = get(payload);
        if (keyPayload == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean delete(IPayload payloadToDelete) {
        if (root == null){
            return false;
        }
        else {
            INode toDelete = get(payloadToDelete, root);
            if (toDelete == null) {
                return false;
            }
            if (toDelete.hasLeftChild() && toDelete.hasRightChild()){
                INode successor = getSuccessor(toDelete);
                INode originalSuccessorParent = successor.getParent();
                replaceParentOfNodeWithParentOfAnother(successor, successor.getRightChild());
                fullyReplaceOneNodeWithAnother(toDelete, successor);
                updateBalanceAfterSuccessorReplacement(originalSuccessorParent, successor, toDelete);
            }
            else if (toDelete.hasLeftChild()) {
                replaceParentOfNodeWithParentOfAnother(toDelete, toDelete.getLeftChild());
                updateBalanceNodeDeleted(toDelete.getLeftChild());
            }
            else if (toDelete.hasRightChild()) {
                replaceParentOfNodeWithParentOfAnother(toDelete, toDelete.getRightChild());
                updateBalanceNodeDeleted(toDelete.getRightChild());
            }
            else {
                if (toDelete.isLeftChild()) {
                    toDelete.getParent().setLeftChild(null);
                    updateBalanceNodeDeleted(toDelete, EWhichChild.IS_LEFT_CHILD);
                }
                else {
                    toDelete.getParent().setRightChild(null);
                    updateBalanceNodeDeleted(toDelete, EWhichChild.IS_RIGHT_CHILD);
                }
            }
            return true;
        }
    }

    private INode getSuccessor(INode toDelete){
        INode successor = toDelete.getRightChild();
        while (successor.hasLeftChild()) {
            successor = successor.getLeftChild();
        }
        return successor;
    }

    private void fullyReplaceOneNodeWithAnother(INode toReplace, INode newNode){
        newNode.setLeftChild(toReplace.getLeftChild());
        newNode.setRightChild(toReplace.getRightChild());
        newNode.setBalanceFactor(toReplace.getBalanceFactor());
        replaceParentOfNodeWithParentOfAnother(toReplace, newNode);
    }

    private void updateBalanceAfterSuccessorReplacement(INode originalSuccessorParent,
                                                        INode successor,
                                                        INode newSuccessorPlace) {
        if (originalSuccessorParent != newSuccessorPlace) {
            originalSuccessorParent.addToBalanceFactor(-1);
            if (originalSuccessorParent.getBalanceFactor() == 0) {
                updateBalanceNodeDeleted(originalSuccessorParent);
            }
        } else {
            successor.addToBalanceFactor(1);
            if (successor.getBalanceFactor() == 0) {
                updateBalanceNodeDeleted(successor);
            }
        }
    }

    private void replaceParentOfNodeWithParentOfAnother(INode toReplace, INode newNode) {
        if (toReplace == root){
            newNode.setParent(null);
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

    private void updateBalanceIfNodeAdded(INode node){
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
            updateBalanceIfNodeAdded(node.getParent());
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
    }

    private void updateBalanceNodeDeleted(INode node, EWhichChild whichChild){
        if (whichChild == EWhichChild.IS_LEFT_CHILD) {
            node.getParent().addToBalanceFactor(-1);
            if (node.getParent().getBalanceFactor() >= 0){
                updateBalanceNodeDeleted(node.getParent());
            }
        }
        else if (whichChild == EWhichChild.IS_RIGHT_CHILD){
            node.getParent().addToBalanceFactor(1);
            if (node.getParent().getBalanceFactor() <= 0){
                updateBalanceNodeDeleted(node.getParent());
            }
        }
    }

    private void rotateLeft(INode pivotNode){
        INode newPivot = pivotNode.getRightChild();
        pivotNode.setRightChild(newPivot.getLeftChild());
        replaceParentOfNodeWithParentOfAnother(pivotNode, newPivot);
        newPivot.setLeftChild(pivotNode);

        pivotNode.addToBalanceFactor(1 - Math.min(newPivot.getBalanceFactor(), 0));
        newPivot.addToBalanceFactor(1 + Math.max(pivotNode.getBalanceFactor(), 0));
    }

    private void rotateRight(INode pivotNode){
        INode newPivot = pivotNode.getLeftChild();
        pivotNode.setLeftChild(newPivot.getRightChild());
        replaceParentOfNodeWithParentOfAnother(pivotNode, newPivot);
        newPivot.setRightChild(pivotNode);

        pivotNode.addToBalanceFactor(- 1 - Math.max(newPivot.getBalanceFactor(), 0));
        newPivot.addToBalanceFactor(- 1 + Math.min(pivotNode.getBalanceFactor(), 0));
    }

    private void rebalance(INode node){
        if(node.getBalanceFactor() > 1){
            if(node.getLeftChild().getBalanceFactor() < 0){
                rotateLeft(node.getLeftChild());
            }
            rotateRight(node);
        }
        else if(node.getBalanceFactor() < -1){
            if(node.getRightChild().getBalanceFactor() > 0){
                rotateRight(node.getRightChild());
            }
            rotateLeft(node);
        }
    }
}
