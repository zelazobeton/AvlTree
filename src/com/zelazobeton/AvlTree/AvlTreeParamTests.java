package com.zelazobeton.AvlTree;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AvlTreeParamTests {
    private AvlTree sut;
    public int sizeOfTree;
    public int maxNumberInTree;

    private class IntWrapper{
        public int value;
        public IntWrapper(int value) {
            this.value = value;
        }
    }

    public int getMinPossibleHeight(){
        return (int) (Math.log(sizeOfTree + 1) / Math.log(2));
    }

    public int getMaxPossibleHeight(){
        return (int)(1.44 * (Math.log(sizeOfTree) / Math.log(2)) - 0.328);
    }

    public AvlTreeParamTests(int sizeOfTree, int maxNumberInTree) {
        this.sizeOfTree = sizeOfTree;
        this.maxNumberInTree = maxNumberInTree;
    }

    private Set<Integer> fillTreeWithRandomNumbers(int sizeOfTree, int maxNumberInTree){
        Set<Integer> setRandom = new HashSet<Integer>();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for(int idx = 0; idx < sizeOfTree; idx++)
        {
            Integer r = rand.nextInt() % maxNumberInTree;
            sut.put(new Payload(r));
            setRandom.add(r);
        }
        this.sizeOfTree = setRandom.size();
        return setRandom;
    }

    private Set randomDeleteFromTree(Set<Integer> inputSet, int approxPartOfTreeToDelete){
        Set<Integer> deletedSet = new HashSet<>();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (Iterator<Integer> itr = inputSet.iterator(); itr.hasNext();) {
            Integer elem = itr.next();
            Integer r = rand.nextInt() % approxPartOfTreeToDelete;
            if (r == 0){
                deletedSet.add(elem);
                sut.delete(new Payload(elem));
                itr.remove();
            }
        }
        this.sizeOfTree -= deletedSet.size();
        return deletedSet;
    }

    private int inorderGetHeight(INode node, Integer level){
        IntWrapper maxLevel = new IntWrapper(0);
        inorderGetHeight(node, level, maxLevel);
        return maxLevel.value;
    }

    private void inorderGetHeight(INode node, Integer level, IntWrapper maxLevel){
        if(node != null) {
            inorderGetHeight(node.getLeftChild(), level + 1, maxLevel);
            if (level > maxLevel.value){
                maxLevel.value = level;
            }
            inorderGetHeight(node.getRightChild(), level + 1, maxLevel);
        }
    }

    @Before
    public void setUp() throws Exception {
        sut = new AvlTree();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList(new Object[][]{
                {1000, 5},
                {100, 1000},
                {10000, 2000},
                {100000, 10000},
        });
    }

    @org.junit.Test
    public void shouldContainElements(){
        Set<Integer> inputSet = fillTreeWithRandomNumbers(sizeOfTree, maxNumberInTree);
        for(Integer elem : inputSet){
            assertTrue(sut.contains(new Payload(elem)));
        }
    }

    @org.junit.Test
    public void shouldKeepCorrectHeightAfterInsertion(){
        fillTreeWithRandomNumbers(sizeOfTree, maxNumberInTree);
        int treeHeight = inorderGetHeight(sut.root, 0);
        System.out.println("Min level possible: " + getMinPossibleHeight());
        System.out.println("Max level: " + treeHeight);
        System.out.println("Max level possible: " + getMaxPossibleHeight());
        assertTrue(getMinPossibleHeight() <= treeHeight);
        assertTrue(treeHeight <= getMaxPossibleHeight());
    }

    @org.junit.Test
    public void shouldDeleteElements(){
        final int approxPartOfTreeToDelete = 4;
        Set<Integer> inputSet = fillTreeWithRandomNumbers(sizeOfTree, maxNumberInTree);
        Set<Integer> deletedSet = randomDeleteFromTree(inputSet, approxPartOfTreeToDelete);

        for(Integer elem : inputSet){
            assertTrue(sut.contains(new Payload(elem)));
        }
        for(Integer elem : deletedSet){
            assertFalse(sut.contains(new Payload(elem)));
        }
    }

    @org.junit.Test
    public void shouldKeepCorrectHeightAfterDeletion(){
        final int approxPartOfTreeToDelete = 2;
        Set<Integer> inputSet = fillTreeWithRandomNumbers(sizeOfTree, maxNumberInTree);
        randomDeleteFromTree(inputSet, approxPartOfTreeToDelete);
        int treeHeight = inorderGetHeight(sut.root, 0);
        assertTrue(getMinPossibleHeight() <= treeHeight);
        assertTrue(treeHeight <= getMaxPossibleHeight());
    }
}