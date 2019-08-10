package com.zelazobeton.AvlTree;

import static org.junit.Assert.*;

public class AvlTreeBasicTestSuite {
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

    @org.junit.Before
    public void setUp(){
        sut = new AvlTree();
        putFiveCorrectPayloads();
    }

    @org.junit.Test
    public void putFiveCorrectPayloadsShouldInsertFivePayloads() {
        refToFirstPayload = sut.root.getLeftChild().getPayload();
        refToSecondPayload = sut.root.getRightChild().getLeftChild().getPayload();
        refToThirdPayload = sut.root.getPayload();
        IPayload refToFourthPayload = sut.root.getRightChild().getPayload();
        IPayload refToFifthPayload = sut.root.getRightChild().getRightChild().getPayload();

        assertSame(refToFirstPayload, firstPayload);
        assertSame(refToSecondPayload, secondPayload);
        assertSame(refToThirdPayload, thirdPayload);
        assertSame(refToFourthPayload, fourthPayload);
        assertSame(refToFifthPayload, fifthPayload);
    }

    @org.junit.Test
    public void checkBalanceFactorForFirstFiveNodes() {
        assertEquals(0, sut.get(firstPayload, sut.root).getBalanceFactor());
        assertEquals(0, sut.get(secondPayload, sut.root).getBalanceFactor());
        assertEquals(-1, sut.get(thirdPayload, sut.root).getBalanceFactor());
        assertEquals(0, sut.get(fourthPayload, sut.root).getBalanceFactor());
        assertEquals(0, sut.get(fifthPayload, sut.root).getBalanceFactor());
    }

    @org.junit.Test
    public void deleteNodeWithBothChildren() {
        sut.delete(SECOND_KEY);
        refToFirstPayload = sut.root.getLeftChild().getPayload();
        refToThirdPayload = sut.root.getPayload();
        IPayload refToFourthPayload = sut.root.getRightChild().getPayload();
        IPayload refToFifthPayload = sut.root.getRightChild().getRightChild().getPayload();
        assertSame(refToFirstPayload, firstPayload);
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
        IPayload refToThirdPayload = sut.get(INEXISTING_KEY);
        assertNull(refToThirdPayload);
    }
}