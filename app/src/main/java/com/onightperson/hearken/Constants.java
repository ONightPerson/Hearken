package com.onightperson.hearken;

/**
 * Created by liubaozhu on 17/1/5.
 */

public class Constants {
    //time
    public static final long MILLISECOND = 1000l;
    public static final long MINUTE_MS = MILLISECOND * 60;
    public static final long HOUR_MS = MINUTE_MS * 60;
    public static final long HALF_DAY_MS = HOUR_MS * 12;
    public static final long DAY_MS = HOUR_MS * 24;
    public static final long WEEK_MS = DAY_MS * 7;
    public static final long TWO_MINUTE_MS = MINUTE_MS * 2;
    public static final long FIVE_MINUTES_MS = MINUTE_MS * 5;
    public static final long TEN_MINUTES_MS = MINUTE_MS * 10;
    public static final long HALF_HOUR_MS = MINUTE_MS * 30;
    public static final long FOUR_HOUR_MS = MINUTE_MS * 60 * 4;

    public static final String ACTION_NOTIFY_TO_INTENT_SERVICE = "action.alarm.notification.for.test";

    //action
    public static final String ACTION_ALARM_NOTIFICATION_FOR_TEST = "action.alarm.notification.for.test";
    //notification id
    public static final int ID_ALARM_NOTIFICATION = 1;
    public static final int ID_CUSTOM_NOTIFICATION = 2;
    public static final int ID_NORMAL_NOTIFICATION = 3;
    public static final int ID_BIG_CONTENTVIEW_NOTIFICATION = 4;
    public static final int ID_MUTI_BTN_NOTIFICATION = 5;
    //key
    public static final String KEY_LAUNCH_FROM = "launch_from";
}
