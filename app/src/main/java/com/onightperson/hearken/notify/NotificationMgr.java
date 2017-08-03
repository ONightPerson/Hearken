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
import com.onightperson.hearken.service.MyIntentService;

/**
 * Created by liubaozhu on 17/1/5.
 */

public class NotificationMgr {

    private static volatile NotificationMgr sInstance;
    private static int mNotificationId = 100;
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
                NotificationActivity.class), PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
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

    public void sendMutiBtnNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        RemoteViews contentViews = new RemoteViews(mContext.getPackageName(), R.layout.notification_muti_button_layout);
        builder.setContent(contentViews);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(mContext,
                MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = builder.build();
        mNotifyManager.notify(Constants.ID_MUTI_BTN_NOTIFICATION, notification);
    }

    public void sendBigContentViewNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        RemoteViews bigContentView = new RemoteViews(mContext.getPackageName(), R.layout.big_notification_item_layout);
        RemoteViews customContentView = new RemoteViews(mContext.getPackageName(), R.layout.notification_item_layout);
        builder.setSmallIcon(R.drawable.ic_launcher);
        RemoteViews contentViewBySetContent = new RemoteViews(mContext.getPackageName(), R.layout.notification_set_content_item_layout);
        //此处，setContent和setCustomContentView方法无论谁先被调用，最终显示的视图是由setCustomContentView设置的。源码里
        builder.setContent(contentViewBySetContent);
        builder.setCustomContentView(customContentView);
        builder.setCustomBigContentView(bigContentView);
        Notification notification = builder.build();
        mNotifyManager.notify(Constants.ID_BIG_CONTENTVIEW_NOTIFICATION, notification);

    }

    public void sendStartServiceNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(mContext.getString(R.string.notification_normal_title));
        builder.setContentText(mContext.getString(R.string.notification_normal_text));
        Intent intent = new Intent(mContext, MyIntentService.class);
        intent.setAction(Constants.ACTION_ALARM_NOTIFICATION_FOR_TEST);
        intent.putExtra(NotificationConstants.EXTRA_NOTIFY_TO_INTENT_SERVICE, 5);
        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        mNotifyManager.notify(Constants.ID_NORMAL_NOTIFICATION, notification);
    }

    public void sendNotificationsOneByOne() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(String.format(mContext.getString(R.string.notification_alarm_title), mNotificationId));
        builder.setContentText(mContext.getString(R.string.notification_alarm_text));
        builder.setContentIntent(PendingIntent.getActivity(mContext, 0, new Intent(mContext,
                NotificationActivity.class), PendingIntent.FLAG_CANCEL_CURRENT));
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        mNotifyManager.notify(mNotificationId++, notification);
    }


}
