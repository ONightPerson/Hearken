package com.onightperson.hearken.ipc.binder;

import android.os.IBinder;
import android.os.IInterface;

/**
 * Created by liubaozhu on 17/9/4.
 */

public abstract interface IComputeCallback extends IInterface {

    public static final String DESCRIPTOR = "com.onightperson.hearken.ipc.binder.IComputeCallback";

    public static final int TRANSACTION_onAddCalled = IBinder.FIRST_CALL_TRANSACTION + 0;

    void onAddCalled();
}
