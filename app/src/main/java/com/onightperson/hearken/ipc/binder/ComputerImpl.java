package com.onightperson.hearken.ipc.binder;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by liubaozhu on 17/9/3.
 */

public class ComputerImpl extends Binder implements IComputer {

    public ComputerImpl() {
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
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                data.enforceInterface(DESCRIPTOR);
                return true;
            case TRANSACTION_add:
                int a = data.readInt();
                int b = data.readInt();
                int result = this.add(a, b);
                reply.writeNoException();
                reply.writeInt(result);
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }
}
