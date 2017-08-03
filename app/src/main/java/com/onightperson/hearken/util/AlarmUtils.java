package com.onightperson.hearken.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.onightperson.hearken.Constants;
import com.onightperson.hearken.service.MyIntentService;

/**
 * Created by liubaozhu on 17/1/5.
 */

public class AlarmUtils {

    public static void doSchedule(Context context, long triggerTime) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, PendingIntent.getService(context,
                0, new Intent(context, MyIntentService.class)
                        .setAction(Constants.ACTION_ALARM_NOTIFICATION_FOR_TEST), PendingIntent.FLAG_CANCEL_CURRENT));
    }
}
