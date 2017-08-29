package com.onightperson.hearken.javacode;

/**
 * Created by liubaozhu on 17/8/15.
 */

public class Test {

    private Test instance;

    public void setTest(Object newInstance) {
        instance = (Test) newInstance;
    }

    public static void printInfo() {
        System.out.println("instance: ");
    }

}
