package com.onightperson.hearken.ipc.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by liubaozhu on 17/9/3.
 */

public class MessengerService extends Service {
    private static final String TAG = "MessengerService";

    public static final int MSG_SAY_HELLO = 0;

    private Handler messengerHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Log.i(TAG, "handleMessage: Hello");
                    break;
            }
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(messengerHandler).getBinder();
    }
}
