package com.onightperson.hearken.ipc.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.HashMap;

/**
 * Created by liubaozhu on 17/9/3.
 */

public abstract class ComputerStub extends Binder implements IComputer {
    private static final String TAG = "ComputerStub";

    public ComputerStub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IComputer asInterface(IBinder obj) {
        if (obj == null) {
            return null;
        }

        IInterface iInterface = obj.queryLocalInterface(DESCRIPTOR);
        if (iInterface != null && (iInterface instanceof IComputer)) {
            return (IComputer) iInterface;
        }
        return new ComputerProxy(obj);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_add:
                data.enforceInterface(DESCRIPTOR);
                int a = data.readInt();
                int b = data.readInt();
                int result = this.add(a, b);
                reply.writeNoException();
                reply.writeInt(result);
                return true;
            case TRANSACTION_addInternal:
                data.enforceInterface(DESCRIPTOR);
                HashMap<String, Integer> values = data.readHashMap(null);
                this.addInternal(values);
                reply.writeNoException();
                return true;
            case TRANSACTION_registerListener:
                data.enforceInterface(DESCRIPTOR);
                IComputeCallback listener = ComputeListener.asInterface(data.readStrongBinder());
                this.registerListener(listener);
                reply.writeNoException();
                return true;
            case TRANSACTION_unregisterListener:
                data.enforceInterface(DESCRIPTOR);
                IComputeCallback toUnlistener = ComputeListener.asInterface(data.readStrongBinder());
                this.unregisterListener(toUnlistener);
                reply.writeNoException();
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
