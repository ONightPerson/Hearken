package com.onightperson.hearken.singleton;

/**
 * Created by liubaozhu on 17/5/26.
 */

public class SingletonTest {

    public static void main(String[] args) {

        DelayedLoadingDCL sInstance = DelayedLoadingDCL.getInstance();
        ObjectOld old = new ObjectOld();
        old.mDcl = sInstance;

        ObjectNew newObject = new ObjectNew();
        newObject.mDcl = sInstance;

        sInstance = null;
        System.out.println("old mDcl: " + old.mDcl +
                ", new mDcl: " + newObject.mDcl);


    }
}
