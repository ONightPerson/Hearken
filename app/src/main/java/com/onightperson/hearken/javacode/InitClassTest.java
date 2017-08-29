package com.onightperson.hearken.javacode;

/**
 * Created by liubaozhu on 17/8/20.
 */

public class InitClassTest {

    private static int id = 1;

    static {
        id = 0;
        System.out.println("id: " + id);
    }

    public InitClassTest() {
        id  = 1;

        System.out.println("current id: " + id);
    }

}
