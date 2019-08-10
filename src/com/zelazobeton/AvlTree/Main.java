package com.zelazobeton.AvlTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        final int FIRST_KEY = 10;
//        final int SECOND_KEY = 5;
//        final int THIRD_KEY = 15;
//        final int FOURTH_KEY = 2;
//        final int FIFTH_KEY = 7;
//        IPayload firstPayload = new Payload(FIRST_KEY);
//        IPayload secondPayload = new Payload(SECOND_KEY);
//        IPayload thirdPayload = new Payload(THIRD_KEY);
//        IPayload fourthPayload = new Payload(FOURTH_KEY);
//        IPayload fifthPayload = new Payload(FIFTH_KEY);
//        IPayload sixthPayload = new Payload(20);
//        IPayload seventhPayload = new Payload(8);
//        IPayload eighthPayload = new Payload(17);
//        IPayload ninthPayload = new Payload(1);
//        IPayload tenthPayload = new Payload(12);
//        IPayload eleventhPayload = new Payload(13);
//
        AvlTree sut = new AvlTree();
//
//        sut.put(firstPayload);
//        sut.put(secondPayload);
//        sut.put(thirdPayload);
//        sut.put(fourthPayload);
//        sut.put(fifthPayload);
//        sut.put(sixthPayload);
//        sut.put(seventhPayload);
//        sut.put(eighthPayload);

//        sut.delete(FOURTH_KEY);
        List<Integer> toInsert = Arrays.asList(9, 12, 11);
        for (Integer elem : toInsert){
            sut.put(new Payload(elem));
        }
        sut.inorder(sut.root, "K");
    }
}
