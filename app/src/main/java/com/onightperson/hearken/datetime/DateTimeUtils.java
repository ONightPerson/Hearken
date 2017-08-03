package com.onightperson.hearken.datetime;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liubaozhu on 17/7/9.
 */

public class DateTimeUtils {
    private static final String TAG = "DateTimeUtils";

    public static void testWeek(int add) {
        testWeekOfMonth(add);
        testWeekOfYear(add);
    }

    public static void testWeekOfYear(int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, add);
        Log.i(TAG, "testWeekOfYear: ");
        printTime(calendar.getTimeInMillis());
    }

    public static void testWeekOfMonth(int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, add);
        Log.i(TAG, "testWeekOfMonth: ");
        printTime(calendar.getTimeInMillis());
    }

    public static void printTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);

        String dateTime = format.format(date);
        Log.i(TAG, "printTime: dateTime: " + dateTime);
    }
}
