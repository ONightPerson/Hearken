package com.onightperson.hearken.ipc.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by liubaozhu on 17/9/3.
 */

public class ComputeServer extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ComputerImpl();
    }
}
