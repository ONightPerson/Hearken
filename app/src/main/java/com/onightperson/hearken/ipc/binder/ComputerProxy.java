package com.onightperson.hearken.ipc.binder;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.util.Map;

/**
 * Created by liubaozhu on 17/9/3.
 */

public class ComputerProxy implements IComputer {
    private static final String TAG = "ComputerProxy";

    private IBinder mRemote;

    public ComputerProxy(IBinder obj) {
        mRemote = obj;
    }

    @Override
    public int add(int a, int b) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int result = Integer.MIN_VALUE;
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeInt(a);
            data.writeInt(b);
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
    public void addInternal(Map<String, Integer> values) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeMap(values);
            mRemote.transact(TRANSACTION_addInternal, data, reply, 0);
            reply.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public void registerListener(IComputeCallback listener) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeStrongInterface(listener);
            mRemote.transact(TRANSACTION_registerListener, data, reply, 0);
            reply.readException();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public void unregisterListener(IComputeCallback listener) {
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            data.writeStrongInterface(listener);
            mRemote.transact(TRANSACTION_unregisterListener, data, reply, 0);
            reply.readException();
        } catch (RemoteException e) {
            Log.e(TAG, "unregisterListener: ", e);
        } finally {
            data.recycle();
            reply.recycle();
        }
    }

    @Override
    public IBinder asBinder() {
        return mRemote;
    }
}
