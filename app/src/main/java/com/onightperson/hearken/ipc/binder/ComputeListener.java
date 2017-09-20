package com.onightperson.hearken.ipc.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by liubaozhu on 17/9/4.
 */

public abstract class ComputeListener extends Binder implements IComputeCallback {


    public ComputeListener() {
        attachInterface(this, DESCRIPTOR);
    }


    @Override
    public IBinder asBinder() {
        return this;
    }

    public static IComputeCallback asInterface(IBinder binder) {
        if (binder == null) {
            return null;
        }
        IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
        if (iin != null && (iin instanceof IComputeCallback)) {
            return (IComputeCallback) iin;
        }
        return new ComputeListenerProxy(binder);
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_onAddCalled:
                data.enforceInterface(DESCRIPTOR);
                this.onAddCalled();
                reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
