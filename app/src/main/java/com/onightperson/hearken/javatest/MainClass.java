package com.onightperson.hearken.javatest;

/**
 * Created by liubaozhu on 17/8/14.
 */

public class MainClass {

    public static void main(String[] args) {
        ClassLoader loader = Test.class.getClassLoader();
        while (loader != null) {
            System.out.println("curLoader: " + loader);
            loader = loader.getParent();
        }

    }
}
