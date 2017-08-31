package com.onightperson.hearken.ipc.binder;

import android.os.IBinder;
import android.os.IInterface;

/**
 * Created by liubaozhu on 17/9/3.
 */

public interface IComputer extends IInterface {

    public static final String DESCRIPTOR = "com.onightperson.hearken.ipc.binder.IComputer";

    public static final int TRANSACTION_add = IBinder.FIRST_CALL_TRANSACTION + 0;

    public int add(int a, int b);

}
