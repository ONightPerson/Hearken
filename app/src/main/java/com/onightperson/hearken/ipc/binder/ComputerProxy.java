package com.onightperson.hearken.ipc.binder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by liubaozhu on 17/9/3.
 */

public class ComputerProxy implements IComputer {

    private IBinder mRemote;

    public ComputerProxy(IBinder obj) {
        mRemote = obj;
    }

    @Override
    public int add(int a, int b) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int result = Integer.MIN_VALUE;
        data.writeInt(a);
        data.writeInt(b);
        try {
            mRemote.transact(TRANSACTION_add, data, reply, 0);
            reply.readException();
            result = reply.readInt();
            return result;
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            data.recycle();
            reply.recycle();
        }
        return result;
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
