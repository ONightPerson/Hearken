package com.onightperson.hearken.notify.notificationparse.parse;

import android.text.TextUtils;
import android.util.Pair;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tf on 8/2/2017.
 */
class NotificationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int PARSE_LINE_TYPE_INVALID = -1;
    static final int PARSE_LINE_TYPE_ID = 1;
    static final int PARSE_LINE_TYPE_TIME = 2;
    static final int PARSE_LINE_TYPE_END = 3;

    private static final String TAG_ID = "id:";
    private static final String TAG_TIME = "time:";
    private static final String TAG_PACKAGE = "package:";
    private static final String TAG_APP_NAME = "app_name:";
    private static final String TAG_CONTENT = "<content>";
    private static final String TAG_CONTENT_INDENT = "->>   ";
    private static final String TAG_ERROR = "ERROR_SKIP:";
    private static final String TAG_SPLITTER = "-----------------------";

    int id;
    long time;
    String pkg;
    String appName;
    private StringBuilder content;
    boolean isBroken;

    NotificationBean() {
        content = new StringBuilder();
    }

    NotificationBean copy() {
        NotificationBean notificationBean = new NotificationBean();
        notificationBean.id = id;
        notificationBean.time = time;
        notificationBean.pkg = pkg;
        notificationBean.appName = appName;
        notificationBean.content = new StringBuilder(content);
        notificationBean.isBroken = isBroken;
        return notificationBean;
    }

    int parseLine(String line) {
        if (line.startsWith(TAG_ID)) {
            id = Integer.parseInt(line.replaceFirst(TAG_ID, ""));
            return PARSE_LINE_TYPE_ID;
        } else if (line.startsWith(TAG_TIME)) {
            time = Long.parseLong(line.replaceFirst(TAG_TIME, ""));
            return PARSE_LINE_TYPE_TIME;
        } else if (line.startsWith(TAG_PACKAGE)) {
            pkg = line.replaceFirst(TAG_PACKAGE, "");
        } else if (line.startsWith(TAG_APP_NAME)) {
            appName = line.replaceFirst(TAG_APP_NAME, "");
        } else {
            content.append(line).append("\n");
            if (line.startsWith(TAG_SPLITTER)) {
                return PARSE_LINE_TYPE_END;
            }
        }
        return PARSE_LINE_TYPE_INVALID;
    }

    void appendErrorContent(String msg) {
        content.append(TAG_ERROR).append(msg).append("\n").append(TAG_SPLITTER);
    }

    void appendInfoContent(String mark, Pair<String, String> pair) {
        content.append(TextUtils.isEmpty(mark) ? TAG_CONTENT_INDENT : mark).append(pair.first)
                .append(":").append(pair.second).append("\n");
        if (NotificationManager.DEAD_PARCEL_PROMPT.equals(pair.second)) {
            isBroken = true;
        }
    }

    void appendInfoContent(List<Pair<String, String>> arrayList) {
        content.append(TAG_CONTENT).append("\n");
        for (Pair<String, String> pair : arrayList) {
            content.append(TAG_CONTENT_INDENT).append(pair.first).append(":").append(pair.second).append("\n");

            if (NotificationManager.DEAD_PARCEL_PROMPT.equals(pair.second)) {
                isBroken = true;
            }
        }
        content.append(TAG_SPLITTER);
    }

    @Override
    public String toString() {
        return TAG_ID + id + "\n"
                + TAG_PACKAGE + pkg + "\n"
                + TAG_APP_NAME + appName + "\n"
                + TAG_TIME + time + "\n"
                + content.toString() + "\n";
    }
}
