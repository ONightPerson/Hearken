package com.onightperson.hearken.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.onightperson.hearken.Constants;
import com.onightperson.hearken.notify.NotificationConstants;
import com.onightperson.hearken.notify.NotificationMgr;

/**
 * Created by liubaozhu on 17/1/5.
 */

public class MyIntentService extends IntentService {
    private static final String TAG = "AlarmIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super("AlarmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) return;
        String action = intent.getAction();
        Log.d(TAG, "onHandleIntent--action: " + action);
        if (Constants.ACTION_ALARM_NOTIFICATION_FOR_TEST.equals(action)) {
            NotificationMgr.getInstance(this).sendAlarmNotification();
        }
    }
}
