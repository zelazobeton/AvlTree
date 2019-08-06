package com.example.AVL;

import org.junit.Test;

import java.security.PublicKey;

import static org.junit.Assert.*;

public class AvlTreeTest {
    private static final int FIRST_KEY = 9;
    private static final int SECOND_KEY = 12;
    private static final int THIRD_KEY = 11;
    private static final int FOURTH_KEY = 14;
    private static final int FIFTH_KEY = 17;
    private static final int INEXISTING_KEY = 99;
    private static IPayload firstPayload = new Payload(FIRST_KEY);
    private static IPayload secondPayload = new Payload(SECOND_KEY);
    private static IPayload thirdPayload = new Payload(THIRD_KEY);
    private static IPayload fourthPayload = new Payload(FOURTH_KEY);
    private static IPayload fifthPayload = new Payload(FIFTH_KEY);

    private IPayload refToFirstPayload;
    private IPayload refToSecondPayload;
    private IPayload refToThirdPayload;

    private static AvlTree sut;

    private static void putFiveCorrectPayloads(){
        sut.put(firstPayload);
        sut.put(secondPayload);
        sut.put(thirdPayload);
        sut.put(fourthPayload);
        sut.put(fifthPayload);
    }

    @org.junit.BeforeClass
    public static void setUp(){
        sut = new AvlTree();
        putFiveCorrectPayloads();
    }

    @org.junit.Test
    public void putThreeCorrectPayloadsShouldInsertThreePayloads() {
        refToFirstPayload = sut.root.getPayload();
        refToSecondPayload = sut.root.getRightChild().getPayload();
        refToThirdPayload = sut.root.getRightChild().getLeftChild().getPayload();

        assertSame(refToFirstPayload, firstPayload);
        assertSame(refToSecondPayload, secondPayload);
        assertSame(refToThirdPayload, thirdPayload);
    }

    @org.junit.Test
    public void deleteNodeWithBothChildren() {
        sut.delete(SECOND_KEY);
        refToThirdPayload = sut.root.getRightChild().getLeftChild().getPayload();
        IPayload refToFourthPayload = sut.root.getRightChild().getPayload();
        IPayload refToFifthPayload = sut.root.getRightChild().getRightChild().getPayload();
        assertSame(refToThirdPayload, thirdPayload);
        assertSame(refToFourthPayload, fourthPayload);
        assertSame(refToFifthPayload, fifthPayload);
    }

    @org.junit.Test
    public void getShouldReturnPayloadIfItExistInTheTree() {
        refToThirdPayload = sut.get(THIRD_KEY);
        assertSame(refToThirdPayload, thirdPayload);
    }

    @org.junit.Test
    public void getShouldReturnNullIfPayloadNotInTheTree() {
//        putFiveCorrectPayloads();
        IPayload refToThirdPayload = sut.get(INEXISTING_KEY);
        assertNull(refToThirdPayload);
    }

}