package com.onightperson.hearken.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.WeakHashMap;


/**
 * Created by liubaozhu on 17/1/28.
 */

public class HearkenActivity extends AppCompatActivity {
    private static final String TAG = "HearkenActivity";

    private static WeakHashMap<String, WeakReference<HearkenActivity>> mInstanceList
            = new WeakHashMap<>();
    private String mActivityName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前Activity的名字

        HearkenActivity instance = getInstance();
        if (instance != null) {
            this.finish();
        }

        Log.i(TAG, "onCreate --> current activity name: " + getClass().getSimpleName()
                + ", it's task id: " + getTaskId());

        //创建一个实例的弱引用
        synchronized (mInstanceList) {
            mInstanceList.put(mActivityName, new WeakReference<>(this));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HearkenActivity instance = getInstance();
        if (instance == this) {
            synchronized (mInstanceList) {
                mInstanceList.remove(mActivityName);
            }
        }
    }

    private HearkenActivity getInstance() {
        //get the name of current activity
        if (mActivityName == null) {
            mActivityName = this.getClass().getSimpleName();
        }

        WeakReference<HearkenActivity> weakInstance = mInstanceList.get(mActivityName);
        if (weakInstance != null) {
            return weakInstance.get();
        }

        return null;
    }

    public static void finishAll() {
        Collection<WeakReference<HearkenActivity>> weakInstanceList = mInstanceList.values();
        Log.d(TAG, "finishAll--mInstanceList's size: " + mInstanceList.size());
        for (WeakReference<HearkenActivity> weakInstance : weakInstanceList) {
            if (weakInstance != null) {
                HearkenActivity instance = weakInstance.get();
                instance.finish();
            }
        }
    }

}
