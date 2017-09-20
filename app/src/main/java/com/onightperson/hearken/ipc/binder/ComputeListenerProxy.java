package com.onightperson.hearken.ipc.binder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by liubaozhu on 17/9/4.
 */

public class ComputeListenerProxy implements IComputeCallback {

    private IBinder mRemote;

    public ComputeListenerProxy(IBinder remote) {
        mRemote = remote;
    }


    @Override
    public IBinder asBinder() {
        return mRemote;
    }

    @Override
    public void onAddCalled() {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            mRemote.transact(TRANSACTION_onAddCalled, data, reply, 0);
            reply.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            data.recycle();
            reply.recycle();
        }

    }
}
