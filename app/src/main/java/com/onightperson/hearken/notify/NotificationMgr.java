package com.onightperson.hearken.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.onightperson.hearken.Constants;
import com.onightperson.hearken.MainActivity;
import com.onightperson.hearken.R;

/**
 * Created by liubaozhu on 17/1/5.
 */

public class NotificationMgr {

    private static volatile NotificationMgr sInstance;
    private Context mContext;
    private NotificationManager mNotifyManager = null;

    public static NotificationMgr getInstance(Context context) {
        if (sInstance == null) {
            synchronized (NotificationMgr.class) {
                if (sInstance == null) {
                    sInstance = new NotificationMgr(context.getApplicationContext());
                }
            }
        }

        return sInstance;
    }

    private NotificationMgr(Context context) {
        mContext = context;
        mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void sendAlarmNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(mContext.getString(R.string.notification_alarm_title));
        builder.setContentText(mContext.getString(R.string.notification_alarm_text));
        builder.setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(mContext,
                MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = builder.build();
        mNotifyManager.notify(Constants.ID_ALARM_NOTIFICATION, notification);

    }

    public void sendNormalNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(mContext.getString(R.string.notification_normal_title));
        builder.setContentText(mContext.getString(R.string.notification_normal_text));
        builder.setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(mContext,
                MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = builder.build();
        mNotifyManager.notify(Constants.ID_NORMAL_NOTIFICATION, notification);

    }

    public void sendCustomNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        RemoteViews contentViews = new RemoteViews(mContext.getPackageName(), R.layout.notification_item_layout);
        builder.setContent(contentViews);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(mContext,
                MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = builder.build();
        mNotifyManager.notify(Constants.ID_CUSTOM_NOTIFICATION, notification);
    }
}
