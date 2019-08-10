package com.zelazobeton.AvlTree;

import org.junit.Before;

import java.util.*;

import static org.junit.Assert.*;

public class AvlTreeBigInputTests {
    private AvlTree sut;
    private Set<Integer> fillTreeWithRandomNumbers(int sizeOfTree, int maxNumberInTree){
        Set<Integer> setRandom = new HashSet<Integer>();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < sizeOfTree; i++)
        {
            Integer r = rand.nextInt() % maxNumberInTree;
            setRandom.add(r);
            sut.put(new Payload(r));
        }
        return setRandom;
    }

    @Before
    public void setUp() throws Exception {
        sut = new AvlTree();
    }

    @org.junit.Test
    public void shouldInsertCorrectlyBigSet(){
        int sizeOfTree = 1000;
        int maxNumberInTree = 512;
        Set<Integer> inputSet = fillTreeWithRandomNumbers(sizeOfTree, maxNumberInTree);
        for(Integer elem : inputSet){
            assertTrue(sut.contains(elem));
        }
    }

    @org.junit.Test
    public void shouldDeleteCorrectlyFromBigSet(){
        int sizeOfTree = 1000;
        int maxNumberInTree = 512;
        int approxPartOfTreeToDelete = 4;
        Set<Integer> inputSet = fillTreeWithRandomNumbers(sizeOfTree, maxNumberInTree);
        Set<Integer> deletedSet = new HashSet<>();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for (Iterator<Integer> itr = inputSet.iterator(); itr.hasNext();) {
            Integer elem = itr.next();
            Integer r = rand.nextInt() % approxPartOfTreeToDelete;
            if (r == 0){
                deletedSet.add(elem);
                sut.delete(elem);
                itr.remove();
            }
        }

        for(Integer elem : inputSet){
            assertTrue(sut.contains(elem));
        }
        for(Integer elem : deletedSet){
            assertFalse(sut.contains(elem));
        }
    }
}