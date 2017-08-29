package com.onightperson.hearken.javacode;

/**
 * Created by liubaozhu on 17/8/28.
 */

public class OperatorTest {

    public static void main(String[] args) {

        int a = 0x1000004f;
        int b = 0xffffffff;

        long key = (long) a << 32;

        System.out.println("key: " + Long.toHexString(key) + ", b: " + Long.toHexString(b));
    }
}
