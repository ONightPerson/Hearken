package com.onightperson.hearken.ipc.binder;

import android.os.IBinder;
import android.os.IInterface;

import java.util.Map;

/**
 * Created by liubaozhu on 17/9/3.
 */

public interface IComputer extends IInterface {

    public static final String DESCRIPTOR = "com.onightperson.hearken.ipc.binder.IComputer";

    public static final int TRANSACTION_add = IBinder.FIRST_CALL_TRANSACTION + 0;
    public static final int TRANSACTION_addInternal = IBinder.FIRST_CALL_TRANSACTION + 1;
    public static final int TRANSACTION_registerListener = IBinder.FIRST_CALL_TRANSACTION + 2;
    public static final int TRANSACTION_unregisterListener = IBinder.FIRST_CALL_TRANSACTION + 3;

    public int add(int a, int b);

    public void addInternal(Map<String, Integer> values);

    public void registerListener(IComputeCallback listener);

    public void unregisterListener(IComputeCallback listener);

}
