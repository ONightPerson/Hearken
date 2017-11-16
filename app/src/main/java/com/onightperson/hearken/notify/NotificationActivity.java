package com.onightperson.hearken.notify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.onightperson.hearken.R;
import com.onightperson.hearken.notify.notificationparse.parse.ParseNotificationActivity;

/**
 * Created by liubaozhu on 17/6/13.
 */

public class NotificationActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "NotificationActivity";

    private Button mSendNormalNotiBtn;
    private Button mSendCustomNotiBtn;
    private Button mSendNormalNoti2Btn;
    private Button mSendBigNotiBtn;
    private Button mSendMutiBtnBtn;
    private Button mSendNotifications;
    private Button mStartListenerServiceBtn;
    private Button mStartIntentServiceNotifyBtn;
    private Button mStartReadPermBtn;
    private Button mSendContinuousNotificationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_layout);

        initViews();
    }

    private void initViews() {
        mSendNormalNotiBtn = (Button) findViewById(R.id.send_normal_notification);
        mSendNormalNotiBtn.setOnClickListener(this);
        mSendCustomNotiBtn = (Button) findViewById(R.id.send_custom_notification);
        mSendCustomNotiBtn.setOnClickListener(this);
        mSendNormalNoti2Btn = (Button) findViewById(R.id.send_normal_notification_2);
        mSendNormalNoti2Btn.setOnClickListener(this);
        mSendBigNotiBtn = (Button) findViewById(R.id.send_big_notification);
        mSendBigNotiBtn.setOnClickListener(this);
        mSendMutiBtnBtn = (Button) findViewById(R.id.send_muti_btn_notification);
        mSendMutiBtnBtn.setOnClickListener(this);
        mSendNotifications = (Button) findViewById(R.id.send_notifications);
        mSendNotifications.setOnClickListener(this);
        mStartListenerServiceBtn = (Button) findViewById(R.id.start_listener_service);
        mStartListenerServiceBtn.setOnClickListener(this);
        mStartIntentServiceNotifyBtn = (Button) findViewById(R.id.start_intent_service_notification);
        mStartIntentServiceNotifyBtn.setOnClickListener(this);
        mStartReadPermBtn = (Button) findViewById(R.id.start_read_perm_activity);
        mStartReadPermBtn.setOnClickListener(this);
        mSendContinuousNotificationBtn = (Button) findViewById(R.id.post_continuous_notifications);
        mSendContinuousNotificationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSendNormalNotiBtn) {
            NotificationMgr.getInstance(this).sendAlarmNotification();
        } else if (v == mSendCustomNotiBtn) {
            NotificationMgr.getInstance(this).sendCustomNotification();
        } else if (v == mSendNormalNoti2Btn) {
            NotificationMgr.getInstance(this).sendNormalNotification();
        } else if (v == mSendBigNotiBtn) {
            NotificationMgr.getInstance(this).sendBigContentViewNotification();
        } else if (v == mSendMutiBtnBtn) {
            NotificationMgr.getInstance(this).sendMutiBtnNotification();
        } else if (v == mStartListenerServiceBtn) {
            Intent intent = new Intent(this, MyNotificationListenerService.class);
            intent.setAction("com.zhuzi.start.service.action");
            startService(intent);
        } else if (v == mStartIntentServiceNotifyBtn) {
            NotificationMgr.getInstance(this).sendStartServiceNotification();
        } else if (v == mStartReadPermBtn) {
            Intent intent = new Intent(this, ParseNotificationActivity.class);
            startActivity(intent);
        } else if (v == mSendNotifications) {
            NotificationMgr.getInstance(this).sendNotificationsOneByOne();
            Object test = null;
            Log.i(TAG, "onClick: test: " + test);
        } else if (v == mSendContinuousNotificationBtn) {
            Log.i(TAG, "onClick: mSendContinuousNotificationBtn");
            NotificationMgr.getInstance(this).sendContinuousNotifications();
        }
    }
}
