package com.onightperson.hearken.notify.notificationparse.parse;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.widget.Toast;

import com.onightperson.hearken.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by tf on 8/1/2017.
 */

class NotificationManager {
    private static final File FOLDER = new File(Environment.getExternalStorageDirectory(), "Noti_C");
    private static final File CACHE_PATH = new File(FOLDER, "x_notification_cache.txt");
    private static final File BROKEN_PATH = new File(FOLDER, "bad_notification_cache.txt");
    private static final File REPORT_PATH = new File(FOLDER, "report_notification_cache.txt");

    private static final String VALUE_ARRAY = "ARRAY:";
    private static final String VALUE_LIST = "LIST:";
    private static final String VALUE_SPARSE = "SPARSE:";
    private static final String VALUE_BUNDLE = "BUNDLE:";
    private static final String VALUE_INTENT = "INTENT:";

    private static final String OUTPUT_TAG = "yymm";
    private static final String OUTPUT_EX_TAG = "yyee";
    static final String DEAD_PARCEL_PROMPT = "BAD PARCEL CAUSE TO END!";
    private static Field sParcelledDataField;

    static {
        try {
            sParcelledDataField = Bundle.class.getDeclaredField("mParcelledData");
            sParcelledDataField.setAccessible(true);
        } catch (Throwable e) {
            try {
                Class<?> baseBundleClass = NotificationManager.class.getClassLoader()
                        .loadClass("android.os.BaseBundle");
                sParcelledDataField = baseBundleClass.getDeclaredField("mParcelledData");
                sParcelledDataField.setAccessible(true);
            } catch (Throwable e1) {
                e1.printStackTrace();
            }
        }
    }

    private List<NotificationBean> mBeans;
    private List<NotificationBean> mReportBeans;
    private HashSet<Integer> mId;
    private HashSet<Long> mTime;
    private CallBack mCallBack;

    private static NotificationManager sInstance;

    static NotificationManager getInstance() {
        if (null != sInstance) {
            return sInstance;
        }

        synchronized (NotificationManager.class) {
            if (null != sInstance) {
                return sInstance;
            }

            return (sInstance = new NotificationManager());
        }
    }

    private NotificationManager() {
        mReportBeans = new ArrayList<>();
        mBeans = new ArrayList<>();
        mId = new HashSet<>();
        mTime = new HashSet<>();
        if (CACHE_PATH.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(CACHE_PATH));
                String line;
                NotificationBean bean = null;
                while (null != (line = reader.readLine())) {
                    if (null == bean) {
                        bean = new NotificationBean();
                        mBeans.add(bean);
                    }

                    switch (bean.parseLine(line)) {
                        case NotificationBean.PARSE_LINE_TYPE_ID:
                            mId.add(bean.id);
                            break;
                        case NotificationBean.PARSE_LINE_TYPE_TIME:
                            mTime.add(bean.time);
                            break;
                        case NotificationBean.PARSE_LINE_TYPE_END:
                            bean = new NotificationBean();
                            mBeans.add(bean);
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != reader) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }

    synchronized String loadContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (NotificationBean bean : mBeans) {
            stringBuilder.append(bean.toString());
        }
        return stringBuilder.toString();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    synchronized void addNotification(Context cxt, StatusBarNotification... notifications) {
        if (null == notifications) {
            return;
        }
        List<NotificationBean> beanList = new ArrayList<>();
        for (StatusBarNotification notification : notifications) {
            Log.d("yymm", "notification:" + notification.getPackageName());
            int id = notification.getId();
            if (ListenerService.AVOID_ID == id) {
                continue;
            }
            long time = notification.getPostTime();
            if (mId.contains(id) && mTime.contains(time)) {
                continue;
            }
            mTime.add(time);
            mId.add(id);
            NotificationBean bean = new NotificationBean();
            beanList.add(bean);
            bean.id = id;
            bean.pkg = notification.getPackageName();
            String appName;
            PackageManager pm = cxt.getPackageManager();
            try {
                ApplicationInfo ai = pm.getApplicationInfo(notification.getPackageName(), 0);
                appName = (String) pm.getApplicationLabel(ai);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                appName = null;
            }
            if (TextUtils.isEmpty(appName)) {
                appName = "[NULL]";
            }
            bean.appName = appName;
            bean.time = time;
            Notification n = notification.getNotification();

            Log.d("yymm", "-------" + bean);
            if (null == n) {
                bean.appendErrorContent("No Notification OBJ!");
                continue;
            }
            PendingIntent pi = n.contentIntent;
            if (null == pi) {
                bean.appendErrorContent("No ContentIntent OBJ!");
                continue;
            }
            Intent it = fetchPendingIntentMethod(pi);
            if (null == it) {
                bean.appendErrorContent("No Intent OBJ!");
                continue;
            }
            Bundle bundle = it.getExtras();
            List<Pair<String, String>> result = loadIntentBundle(bundle);
            NotificationBean reportBean;
            try {
                reportBean = ReportCollectUtils.filterReportBean(bean, it);
                if (null != reportBean) {
                    mReportBeans.add(reportBean);
                }
            } catch (Exception e) {
                reportBean = null;
                Log.e(OUTPUT_EX_TAG, "EX:", e);
            }

            if (result.isEmpty()) {
                if (null != reportBean) {
                    reportBean.appendErrorContent("No Intent Extras Existed!");
                }
                bean.appendErrorContent("No Intent Extras Existed!");
                continue;
            }
            if (null != reportBean) {
                reportBean.appendInfoContent(result);
            }
            bean.appendInfoContent(result);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (NotificationBean bean : beanList) {
            Log.d(OUTPUT_TAG, bean.toString());
            stringBuilder.append(bean.toString());
        }
        if (null != mCallBack) {
            mCallBack.onNotificationReceived(stringBuilder.toString());
        }
        mBeans.addAll(beanList);
    }

    @SuppressWarnings("unchecked")
    private static <T> T fetchPendingIntentMethod(PendingIntent pendingIntent) {
        try {
            Method method = PendingIntent.class.getDeclaredMethod("getIntent");
            method.setAccessible(true);
            T intent = (T) method.invoke(pendingIntent);
            Log.i("yymm", "fetchPendingIntentMethod: intent: " + intent);
            return intent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String analyzeValue(Object value) {
        if (value instanceof Bundle) {
            List<Pair<String, String>> result =  loadIntentBundle((Bundle) value);
            StringBuilder builder = new StringBuilder(VALUE_BUNDLE + "\n");
            for (Pair<String, String> pair : result) {
                builder.append("INNER:").append(pair.first).append("=").append(pair.second).append("\n");
            }
            return builder.toString();
        } else if (value instanceof Intent) {
            return VALUE_INTENT + ((Intent) value).toUri(0);
        } else if (value instanceof float[]) {
            return VALUE_ARRAY + Arrays.toString((float[]) value);
        } else if (value instanceof double[]) {
            return VALUE_ARRAY + Arrays.toString((double[]) value);
        } else if (value instanceof byte[]) {
            return VALUE_ARRAY + Arrays.toString((byte[]) value);
        } else if (value instanceof char[]) {
            return VALUE_ARRAY + Arrays.toString((char[]) value);
        } else if (value instanceof int[]) {
            return VALUE_ARRAY + Arrays.toString((int[]) value);
        } else if (value instanceof long[]) {
            return VALUE_ARRAY + Arrays.toString((long[]) value);
        } else if (value instanceof String[]) {
            return VALUE_ARRAY + Arrays.toString((String[]) value);
        } else if (value instanceof CharSequence[]) {
            return VALUE_ARRAY + Arrays.toString((CharSequence[]) value);
        } else if (value instanceof Parcelable[]) {
            try {
                return VALUE_ARRAY + Arrays.toString((Parcelable[]) value);
            } catch (Exception e) {
                return VALUE_ARRAY + "Parse parcelable error!";
            }
        } else if (value instanceof ArrayList) {
            ArrayList<?> list = (ArrayList<?>) value;
            StringBuilder builder = new StringBuilder(VALUE_LIST);
            int i = 0;
            int size = list.size();
            for (Object item : list) {
                builder.append(null == item ? "NULL" : item.toString());
                if (i != size - 1) {
                    builder.append(", ");
                }
                i ++;
            }
            return builder.toString();
        } else if (value instanceof SparseArray) {
            StringBuilder builder = new StringBuilder(VALUE_SPARSE);
            SparseArray<?> sparse = (SparseArray<?>) value;
            for (int i = 0, size = sparse.size(); i < size; i++) {
                Object v = sparse.valueAt(i);
                builder.append(null == v ? "NULL" : v.toString());
                if (i != size - 1) {
                    builder.append(", ");
                }
            }
            return builder.toString();
        }
        return value.toString();
    }

    private static List<Pair<String, String>> loadIntentBundle(Bundle intentBundle) {
        if (null == intentBundle) {
            return new ArrayList<>();
        }
        try {
            List<Pair<String, String>> result1 = new ArrayList<>();
            for (String key : intentBundle.keySet()) {
                Object value = intentBundle.get(key);
                result1.add(new Pair<>(key, null == value ? "EMPTY" : analyzeValue(value)));
            }
            return result1;
        } catch (Exception innerEx) {
            innerEx.printStackTrace();
            List<Pair<String, String>> result2 = new ArrayList<>();
            if (null == sParcelledDataField) {
                return result2;
            }

            Parcel parcelledData = null;
            try {
                parcelledData = (Parcel) sParcelledDataField.get(intentBundle);
                parcelledData.setDataPosition(0);

                for (int i = 0, size = parcelledData.readInt(); i < size; i++) {
                    String rKey = parcelledData.readString();
                    try {
                        Object rValue = parcelledData.readValue(intentBundle.getClassLoader());
                        result2.add(new Pair<>(rKey, null == rValue ? "EMPTY" : analyzeValue(rValue)));
                    } catch (RuntimeException e) {
                        if (shouldBreakWhenExceptionHappened(e)) {
                            result2.add(new Pair<>(rKey, DEAD_PARCEL_PROMPT));
                            break;
                        } else {
                            result2.add(new Pair<>(rKey, "BAD OBJECT"));
                        }
                    }
                }
            } catch (Exception finalEx) {
                finalEx.printStackTrace();
            } finally {
                if (null != parcelledData) {
                    parcelledData.setDataPosition(0);
                }
            }
            return result2;
        }
    }

    private static boolean shouldBreakWhenExceptionHappened(RuntimeException e) {
        e.printStackTrace();
        if (e instanceof BadParcelableException) {
            return true;
        }
        Throwable t = e.getCause();
        if (t instanceof IOException) {
            String message = t.getMessage();
            if (null != message && message.contains("Parcelable encountered "
                    + "IOException reading a Serializable object")) {
                return false;
            }
        } else if (t instanceof ClassNotFoundException) {
            String message = t.getMessage();
            if (null != message && message.contains("Parcelable encountered "
                    + "ClassNotFoundException reading a Serializable object")) {
                return false;
            }
        }
        return true;
    }

    private void outputToFile(File file, Collection<? extends Serializable> beanList, boolean append) {
        BufferedWriter writer = null;
        try {
            if (file.exists()) {
                writer = new BufferedWriter(new FileWriter(file, append));
            } else {
                writer = new BufferedWriter(new FileWriter(file));
            }

            for (Serializable bean : beanList) {
                writer.write(bean.toString());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    synchronized void save(final Handler h, final Context c) {
        if (mBeans.isEmpty()) {
            return;
        }

        new Thread() {
            @Override
            public void run() {
                if (!FOLDER.isDirectory()) {
                    FOLDER.delete();
                    boolean result = FOLDER.mkdirs();
                    Log.d("yymm", "create folder + " + FOLDER + " = " + result);
                }

                Log.d("yymm", "write to " + CACHE_PATH);
                outputToFile(CACHE_PATH, mBeans, false);
                TreeSet<String> pkgNames = new TreeSet<>();
                for (NotificationBean bean : mBeans) {
                    if (bean.isBroken) {
                        pkgNames.add(bean.pkg + ":" + bean.appName + "\n");
                    }
                }
                if (!pkgNames.isEmpty()) {
                    Log.d("yymm", "write to " + BROKEN_PATH);
                    outputToFile(BROKEN_PATH, pkgNames, false);
                }

                Log.d("yymm", "OUTPUT: reportFILE, mReportBeans.isEmpty()=" + mReportBeans.isEmpty());
                if (!mReportBeans.isEmpty()) {
                    Log.d("yymm", "write to " + REPORT_PATH);
                    outputToFile(REPORT_PATH, mReportBeans, true);
                }

                h.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(c, c.getString(R.string.save_prompt, FOLDER), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }

    interface CallBack {
        void onNotificationReceived(String notification);
    }
}
