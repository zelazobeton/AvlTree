package com.zelazobeton.AvlTree;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running empty program.");
        final int FIRST_KEY = 9;
        final int SECOND_KEY = 12;
        final int THIRD_KEY = 11;
        final int FOURTH_KEY = 14;
        final int FIFTH_KEY = 17;
        IPayload firstPayload = new Payload(FIRST_KEY);
        IPayload secondPayload = new Payload(SECOND_KEY);
        IPayload thirdPayload = new Payload(THIRD_KEY);
        IPayload fourthPayload = new Payload(FOURTH_KEY);
        IPayload fifthPayload = new Payload(FIFTH_KEY);

        IPayload refToFirstPayload;
        IPayload refToSecondPayload;
        IPayload refToThirdPayload;

        AvlTree sut = new AvlTree();

        sut.put(firstPayload);
        sut.put(secondPayload);
        sut.put(thirdPayload);
        sut.put(fourthPayload);
        sut.put(fifthPayload);

        sut.delete(SECOND_KEY);
        refToThirdPayload = sut.root.getRightChild().getLeftChild().getPayload();
        IPayload refToFourthPayload = sut.root.getRightChild().getPayload();
        IPayload refToFifthPayload = sut.root.getRightChild().getRightChild().getPayload();
    }
}
