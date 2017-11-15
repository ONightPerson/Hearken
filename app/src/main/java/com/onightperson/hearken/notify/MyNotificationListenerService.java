package com.onightperson.hearken.notify;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liubaozhu on 17/6/16.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyNotificationListenerService extends NotificationListenerService {
    private static final String TAG = "MyNotificationListenerS";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = null;
        if (intent != null) {
            action = intent.getAction();
        }
        Object bound = isBound();
        Log.i(TAG, "onStartCommand: bound: " + bound);
        Log.i(TAG, "onStartCommand: intent: " + intent + ", action: " + action);
        StatusBarNotification[] sbns = getActiveNotifications();
        Log.i(TAG, "onStartCommand: sbns: " + sbns);
        if (sbns == null) {
            return super.onStartCommand(intent, flags, startId);
        }
        for (StatusBarNotification sbn : sbns) {
            Notification noti = sbn.getNotification();
            Bundle bundle = noti.extras;
            CharSequence title = (CharSequence) bundle.get(Notification.EXTRA_TITLE);
            Log.i(TAG, "onStartCommand: title: " + title);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        Intent startIntent = new Intent(this, NotifyTestActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
        return super.onBind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        // 解析通知
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        parsePendingIntent(sbn);
    }

    private void parsePendingIntent(StatusBarNotification sbn) {
        if (sbn == null) {
            return;
        }

        PendingIntent pendingIntent = sbn.getNotification().contentIntent;
        String key;
        //获得通知标示，清除缓存列表项所用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            key = sbn.getKey();
            Log.i(TAG, "parsePendingIntent: key: " + key);

        }
        // 反射拿出域
        try {
            Object target = getValue(pendingIntent, "mTarget");
            Log.i(TAG, "parsePendingIntent: target: " + target);


            IInterface sendInterface = null;
            if (target instanceof IInterface) {
                sendInterface = (IInterface) target;
            }


            Log.i(TAG, "parsePendingIntent: sendInterface: " + sendInterface);

            IBinder iBinder = null;
            if (sendInterface != null) {
                iBinder = sendInterface.asBinder();
            }

            Log.i(TAG, "parsePendingIntent: iBinder: " + iBinder);

            Object sent = getValue(iBinder, "sent");
            Log.i(TAG, "parsePendingIntent: sent: " + sent);

            if (sent instanceof Boolean) {
                boolean isSent = (Boolean) sent;
                Log.i(TAG, "parsePendingIntent: isSent: " + isSent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getValue(Object object, String fieldName) throws Exception {
        if (object == null) {
            return null;
        }
        if (TextUtils.isEmpty(fieldName)) {
            return null;
        }
        Field field;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(object);
            } catch (Exception e) {
                // do nothing
            }
        }

        return null;
     }

    private Object isBound() {

        Method method;
        Class<?> clazz = this.getClass().getSuperclass();
        Log.i(TAG, "isBound: clazz: " + clazz);
        try {
            method = clazz.getDeclaredMethod("isBound");
            method.setAccessible(true);
            return method.invoke(this);
        } catch (Exception e) {
            Log.i(TAG, "isBound: exception");
            e.printStackTrace();
        }
        return false;
    }
    
}
