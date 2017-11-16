package com.onightperson.hearken.notify.notificationparse.parse;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by tf on 8/1/2017.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class ListenerService extends NotificationListenerService {

    static final int AVOID_ID = 9998;
    static BindListener sListener;

    private boolean mCallLoadAll;

    @Override
    public IBinder onBind(Intent intent) {
        IBinder iBinder = super.onBind(intent);
        if (null != sListener) {
            sListener.listener(true);
        }
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (null != sListener) {
            sListener.listener(false);
        }
        mCallLoadAll = false;
        return super.onUnbind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (!mCallLoadAll) {
            Log.d("yymm", "onNotificationPosted:getAll:");
            NotificationManager.getInstance().addNotification(getApplicationContext(), getActiveNotifications());
            mCallLoadAll = true;
        }
        NotificationManager.getInstance().addNotification(getApplicationContext(), sbn);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }

    interface BindListener {
        void listener(boolean bind);
    }
}
